package cn.iceroot.db_ladder.task;

import java.sql.Connection;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.iceroot.db_ladder.DataBaseReader;
import cn.iceroot.db_ladder.DbLadderCountUtil;
import cn.iceroot.db_ladder.DbLadderUtil;
import cn.iceroot.db_ladder.TimeContext;

public class FileWriteTask {

    public static void write() {
        DbLadderCountUtil.accumulation();
        int count = DbLadderCountUtil.getCount();
        int start = TimeContext.getStart();
        int end = TimeContext.getEnd();
        int mulriple = TimeContext.getMulriple();
        int currentMinute = DbLadderUtil.currentMinute();
        if (!inRange(currentMinute, start, end)) {
            if (count % mulriple == 0) {
                taskInner();
            }
        } else {
            taskInner();
        }
    }

    private static void taskInner() {
        // String time = DateUtil.now();
        String url = TimeContext.getUrl();
        String username = TimeContext.getUsername();
        String password = TimeContext.getPassword();
        String driver = TimeContext.getDriver();
        String basePath = TimeContext.getBasePath();
        Connection connection = DbLadderUtil.GetConnection(url, driver, username, password);
        String dbinfo = DataBaseReader.dbinfo(connection);
        String md5 = SecureUtil.md5(dbinfo);
        basePath = StrUtil.removeSuffix(basePath, "/");
        DataBaseReader.write(dbinfo, basePath);
        String lastMd5 = TimeContext.getLastMd5();
        if (lastMd5 != null) {
            if (!md5.equals(lastMd5)) {
                TimeContext.setLastMd5(md5);
                changeBehavior();
            }
        } else {
            TimeContext.setLastMd5(md5);
        }
    }

    private static void changeBehavior() {
        String logPath = TimeContext.getLogPath();
        logPath = StrUtil.removeSuffix(logPath, "/");
        String logFile = logPath + "/" + "log.txt";
        String now = DateUtil.now();
        String content = now + "发生了数据库变更" + "\r\n";
        FileUtil.appendUtf8String(content, logFile);
        // 弹出警告对话框 TODO
        System.out.println(content);
    }

    private static boolean inRange(int currentMinute, int start, int end) {
        if (currentMinute > start && currentMinute < end) {
            return true;
        }
        return false;
    }
}
