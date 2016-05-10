package br.com.elearning.praticas.util;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Felipe
 */
public class Conexao {

    private static Connection con;
    private static boolean closeConection;

    /**
     *
     * @return retorna a conexao com a database
     */
    public static Connection getConnection() {
        try {
            if (con == null || con.isClosed()) {
                con = DriverManager.getConnection(
                        PropertiesUtils.getConfigValue(PropertiesUtils.URL),
                        PropertiesUtils.getConfigValue(PropertiesUtils.USERNAME),
                        PropertiesUtils.getConfigValue(PropertiesUtils.PASSWORD));
                closeConection = Boolean.parseBoolean(PropertiesUtils.getConfigValue(PropertiesUtils.CLOSECONNECTION));
            }
            return con;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void closeConnection() {
        try {

            if (con != null && !con.isClosed()) {
                if (closeConection) {
                    con.close();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void forceCloseConnection() {
        try {

            if (con != null && !con.isClosed()) {
                con.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
