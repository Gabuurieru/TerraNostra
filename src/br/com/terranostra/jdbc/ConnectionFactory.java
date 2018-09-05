package br.com.terranostra.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
//  public Connection getConnection(String host, String port, String username, String password) {
    public Connection getConnection() {
      try {
          return DriverManager.getConnection("jdbc:postgresql://localhost:5432/terra_nostra", "geoidentificacao", "geoidentificacao");
                  //"jdbc:postgresql://"+host+":"+port+"/mpog", username, password);
      } catch (SQLException e) {
          throw new RuntimeException(e);
      }
  }
}
//                  "jdbc:postgresql://localhost:5432/mpog", "geoidentificacao", "geoidentificacao");
