public interface Config {
    static String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";

    static String SCHEMA_NAME = "msg_java_training_schema";

    static String DB_URL = "jdbc:mysql://localhost:3306/" + SCHEMA_NAME;

    static String USER = "root";

    static String PASSWORD = "password";

}
