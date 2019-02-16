package cn.iceroot.db_ladder.main;

import cn.hutool.core.convert.Convert;
import cn.hutool.cron.CronUtil;
import cn.hutool.setting.Setting;
import cn.iceroot.db_ladder.DbLadderUtil;
import cn.iceroot.db_ladder.SettingUtil;
import cn.iceroot.db_ladder.TimeContext;

public class Main {

    public static void main(String[] args) {
        SettingUtil.init("conf.setting");
        String rangeTime = SettingUtil.getStr("rangeTime");
        String mulriple = SettingUtil.getStr("mulriple");
        DbLadderUtil.initTimeContext(rangeTime);
        int mulripleNum = Convert.toInt(mulriple, 12);
        TimeContext.setMulriple(mulripleNum);
        Setting settingDatabase = new Setting("db.setting");
        String url = settingDatabase.getStr("url");
        String username = settingDatabase.getStr("username");
        String password = settingDatabase.getStr("password");
        String driver = settingDatabase.getStr("driver");
        Setting setting = new Setting("conf.setting");
        String basePath = setting.getStr("basePath");
        String logPath = setting.getStr("logPath");
        TimeContext.setUrl(url);
        TimeContext.setDriver(driver);
        TimeContext.setUsername(username);
        TimeContext.setPassword(password);
        TimeContext.setBasePath(basePath);
        TimeContext.setLogPath(logPath);
        CronUtil.start();
    }

}
