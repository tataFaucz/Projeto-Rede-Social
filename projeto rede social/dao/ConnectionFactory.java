package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private static final String HOST = configuracao("DB_HOST", "::1");
    private static final String PORT = configuracao("DB_PORT", "5433");
    private static final String DATABASE = configuracao("DB_NAME", "omellety");
    private static final String USER = configuracao("DB_USER", "postgres");
    private static final String PASSWORD = configuracao("DB_PASSWORD", "postgres");
    private static final String JDBC_HOST = HOST.contains(":") && !HOST.startsWith("[")
        ? "[" + HOST + "]"
        : HOST;
    private static final String URL = "jdbc:postgresql://" + JDBC_HOST + ":" + PORT + "/" +
        DATABASE.replace(" ", "%20");

    private static String configuracao(String nome, String padrao) {
        String valor = System.getProperty(nome);
        if (valor == null || valor.isBlank()) {
            valor = System.getenv(nome);
        }
        return valor == null || valor.isBlank() ? padrao : valor;
    }

    public static Connection getConnection() throws SQLException {
        System.out.println("[DB] Conectando em: " + URL + " | usuario=" + USER);
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
