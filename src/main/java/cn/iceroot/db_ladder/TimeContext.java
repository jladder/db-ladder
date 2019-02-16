package cn.iceroot.db_ladder;

public class TimeContext {
    private static int start;
    private static int end;
    private static int mulriple;
    private static String lastMd5;
    private static String basePath;
    private static String logPath;
    private static String username;
    private static String password;
    private static String url;
    private static String driver;

    public static int getStart() {
        return start;
    }

    public static void setStart(int start) {
        TimeContext.start = start;
    }

    public static int getEnd() {
        return end;
    }

    public static void setEnd(int end) {
        TimeContext.end = end;
    }

    public static int getMulriple() {
        return mulriple;
    }

    public static void setMulriple(int mulriple) {
        TimeContext.mulriple = mulriple;
    }

    public static String getLastMd5() {
        return lastMd5;
    }

    public static void setLastMd5(String lastMd5) {
        TimeContext.lastMd5 = lastMd5;
    }

    public static String getBasePath() {
        return basePath;
    }

    public static void setBasePath(String basePath) {
        TimeContext.basePath = basePath;
    }

    public static String getLogPath() {
        return logPath;
    }

    public static void setLogPath(String logPath) {
        TimeContext.logPath = logPath;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        TimeContext.username = username;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        TimeContext.password = password;
    }

    public static String getUrl() {
        return url;
    }

    public static void setUrl(String url) {
        TimeContext.url = url;
    }

    public static String getDriver() {
        return driver;
    }

    public static void setDriver(String driver) {
        TimeContext.driver = driver;
    }
}
