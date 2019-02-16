package cn.iceroot.db_ladder;

public class DbLadderCountUtil {
    public static int count = 0;

    public static int getCount() {
        return count;
    }

    public static int init() {
        count = 0;
        return count;
    }

    public static int accumulation() {
        count++;
        return count;
    }
}
