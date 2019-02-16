package cn.iceroot.db_ladder;

import java.sql.Connection;
import java.sql.DriverManager;
//import java.util.Collection;
import java.sql.SQLException;
import java.util.Date;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;

public class DbLadderUtil {

    public static Connection GetConnection(String url, String driver, String username, String password) {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            String name = e.getClass().getName();
            String message = e.getMessage();
            if ("com.mysql.jdbc.exceptions.jdbc4.CommunicationsException".equals(name)) {
                message = StrUtil.trim(message);
                if (message != null && message.startsWith("Communications link failure") && message.endsWith(
                        "The last packet sent successfully to the server was 0 milliseconds ago. The driver has not received any packets from the server.")) {
                    System.out.println("数据库未启动");
                    // CronUtil.stop();
                    return null;
                }
            } else {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static boolean close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static int currentMinute() {
        String time = DateUtil.format(new Date(), "HH:mm");
        int minute = DateUtil.timeToSecond(time);
        return minute;
    }

    public static void initTimeContext(String rangeTime) {
        String startTime = StrUtil.subBefore(rangeTime, "-", false);
        String endTime = StrUtil.subAfter(rangeTime, "-", true);
        int start = DateUtil.timeToSecond(startTime);
        int end = DateUtil.timeToSecond(endTime);
        TimeContext.setStart(start);
        TimeContext.setEnd(end);
    }
}
