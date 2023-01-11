package com.github.ryuzu.TestWebServer.utilities;

import java.sql.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

public class DataBaseUtilities {
    private static void connect(Consumer<Connection> consumer) {
        String host = "mariadb";
        String db = "test";
        String user = "user";
        String pass = "pwd";

        String url = "jdbc:mariadb://" + host + "/" + db;
        try {
            Connection connect = DriverManager.getConnection(url, user, pass);
            consumer.accept(connect);
            connect.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void state(Consumer<Statement> consumer) {
        connect(connect -> {
            try {
                Statement state = connect.createStatement();
                consumer.accept(state);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public static void createTable(String tableName, LinkedHashMap<String, String> columns) {
        executeUpdate("CREATE TABLE " + tableName + " (" + toSQLColumns(columns) + ")");
    }

    public static void insert(String tableName, List<String> values) {
        executeUpdate("INSERT INTO " + tableName + " VALUES (" + String.join(", ", values) + ")");
    }

    public static void truncate(String tableName) {
        executeUpdate("TRUNCATE TABLE " + tableName);
    }

    public static void executeUpdate(String sql) {
        state(state -> {
            try {
                state.executeUpdate(sql);
            } catch (RuntimeException | SQLException error) {
                error.printStackTrace();
            }
        });
    }

    public static boolean existTable(String tableName) {
        AtomicReference<Boolean> exist = new AtomicReference<>(false);
        state(state -> {
            try {
                String sql = "SELECT * FROM " + tableName;
                state.executeQuery(sql);
                exist.set(true);
            } catch (RuntimeException | SQLException error) {
                exist.set(false);
            }
        });
        return exist.get();
    }

    public static List<HashMap<String, String>> executeQuery(List<String> keys, String sql) {
        List<HashMap<String, String>> table = new ArrayList<>();
        state(state -> {
            try {
                ResultSet result = state.executeQuery(sql);
                while (result.next()) {
                    HashMap<String, String> row = new HashMap<>();
                    for (String column : keys) {
                        row.put(column, result.getString(column));
                    }
                    table.add(row);
                }
            } catch (RuntimeException | SQLException error) {
                error.printStackTrace();
            }
        });
        return table;
    }

    public static List<HashMap<String, String>> table(String tableName, List<String> keys) {
        String sql = "SELECT " + String.join(",", keys) + " FROM " + tableName;
        return executeQuery(keys, sql);
    }

    public static List<HashMap<String, String>> table(String tableName, List<String> keys, String where) {
        String sql = "SELECT " + String.join(",", keys) + " FROM " + tableName + " WHERE " + where;
        return executeQuery(keys, sql);
    }

    public static HashMap<String, String> column(String tableName, List<String> keys, int index) {
        return table(tableName, keys).get(index);
    }

    private static String toSQLColumns(LinkedHashMap<String, String> map) {
        StringBuilder columns = new StringBuilder();
        for (String key : map.keySet()) {
            columns.append(key).append(" ").append(map.get(key)).append(",");
        }
        return columns.substring(0, columns.length() - 1);
    }

    public static String toSQLString(String string) {
        return "'" + string + "'";
    }

    public static String concatAndWhere(String... strings) {
        return String.join(" AND ", strings);
    }
}
