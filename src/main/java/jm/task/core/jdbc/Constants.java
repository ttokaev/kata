package jm.task.core.jdbc;

public class Constants {

    public static final String CREATE_TABLE_USER = "CREATE TABLE IF NOT EXISTS USER (id BIGINT PRIMARY KEY AUTO_INCREMENT, name TEXT, lastname TEXT, age TINYINT)";
    public static final String INSERT_INTO_USER = "INSERT INTO USER (name, lastname, age) VALUES('%s', '%s', %d)";
    public static final String DELETE_FROM_USER = "DELETE FROM USER WHERE ID = %d";
    public static final String GET_ALL_USERS = "SELECT * FROM USER";
    public static String DROP_TABLE_USER = "DROP TABLE IF EXISTS USER";
    public static String DELETE_TABLE_USER = "DELETE FROM USER";
}
