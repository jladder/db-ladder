package cn.iceroot.db_ladder;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;

/**
 * Hello world!
 *
 */
public class DataBaseReader {

    public static void write(String content, String folder) {
        folder = folder.replace("\\", "/");
        folder = StrUtil.addSuffixIfNot(folder, "/");
        String date = DateUtil.format(new Date(), "yyyyMMdd");
        folder += date;
        String time = DateUtil.format(new Date(), "yyyyMMdd_HHmmss");
        String fileName = folder + "/" + time + ".txt";
        writeFile(content, fileName);
    }

    private static void writeFile(String content, String fileName) {
        FileUtil.writeUtf8String(content, fileName);
    }

    public static String dbinfo(Connection connection) {
        StringBuilder sb = new StringBuilder();
        String nl = "\r\n";
        DatabaseMetaData dbmd = null;
        try {
            dbmd = connection.getMetaData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Statement createStatement = null;
        try {
            createStatement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // metaData = connection.getMetaData();
        String[] types = { "TABLE" };
        ResultSet rs = null;
        try {
            rs = dbmd.getTables(null, null, "%", types);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            while (rs.next()) {
                String tableName = rs.getString("TABLE_NAME"); // 表名
                // String tableType = rs.getString("TABLE_TYPE"); // 表类型
                // String remarks = rs.getString("REMARKS"); // 表备注
                sb.append("==============================");
                sb.append(nl);
                sb.append(tableName);
                sb.append(nl);
                sb.append("==============================");
                sb.append(nl);
                String sql = "select * from " + tableName + " limit 1";
                ResultSet executeQuery = createStatement.executeQuery(sql);
                ResultSetMetaData metaDataColumn = executeQuery.getMetaData();
                int columnCount = metaDataColumn.getColumnCount();
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaDataColumn.getColumnName(i);
                    // String columnLabel = metaDataColumn.getColumnLabel(i);
                    String columnTypeName = metaDataColumn.getColumnTypeName(i);
                    int columnDisplaySize = metaDataColumn.getColumnDisplaySize(i);
                    int nullable = metaDataColumn.isNullable(i);
                    sb.append(StrUtil.fillAfter(columnName, ' ', 64));
                    sb.append(StrUtil.fillAfter(columnTypeName, ' ', 20));
                    sb.append(StrUtil.fillAfter(columnDisplaySize + "", ' ', 10));
                    sb.append(StrUtil.fillAfter(castBoolean(nullable), ' ', 10));
                    sb.append(nl);
                }
            }
            return sb.toString();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String castBoolean(int num) {
        if (num == 0) {
            return "false";
        } else {
            return "true";
        }
    }
}
