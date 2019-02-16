package cn.iceroot.db_ladder;

import cn.hutool.setting.Setting;

public class SettingUtil {
    public static Setting setting;

    public static Setting getSetting() {
        return setting;
    }

    public static void setSetting(Setting setting) {
        SettingUtil.setting = setting;
    }

    public static void init(String confFile) {
        SettingUtil.setting = new Setting(confFile);
    }

    public static String getStr(String key) {
        String str = SettingUtil.setting.getStr(key);
        return str;
    }

    public static String getStr(String key, String defaultValue) {
        String str = SettingUtil.setting.getStr(key, defaultValue);
        return str;
    }
}
