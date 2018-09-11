package br.com.terranostra.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class GeocodingDAO {
  
    ResultSet rs = null;
    PreparedStatement stmt;
    Connection conect;
  
    public ResultSet getSelect(Connection conect) {
      //rs = null;
      try{  
        stmt = conect.prepareStatement("SELECT id, endereco FROM public.teste");
        stmt.execute();
        rs = stmt.executeQuery();
        //conect.close();
      } catch (SQLException e2) {
           e2.printStackTrace();
      }
      this.conect = conect;
      return rs;
    }
    
    
    public void insert(Connection conect, Long id, String latLong) {
      try{
        stmt = conect.prepareStatement("UPDATE public.teste set lat_long = ? where id = ?");
        stmt.setString(1, latLong);
        stmt.setLong(2, id);
        stmt.execute();
        this.conect = conect;
      } catch (SQLException e2) {
           e2.printStackTrace();
      }
    }
    
    public void addCollum(Connection conect, String table) {
      try{
//        stmt = conect.prepareStatement("ALTER TABLE "+table+" ADD COLUMN IF NOT EXISTS lat_long character varying(100);");
        stmt = conect.prepareStatement("DO $$ \n" + 
            "    BEGIN\n" + 
            "        BEGIN\n" + 
            "            ALTER TABLE "+table+" ADD COLUMN lat_long CHARACTER VARYING(100);\n" + 
            "        EXCEPTION\n" + 
            "            WHEN duplicate_column THEN RAISE NOTICE 'column <lat_long> already exists in <"+table+">.';\n" + 
            "        END;\n" + 
            "    END;\n" + 
            "$$");
        stmt.execute();
        this.conect = conect;
      } catch (SQLException e2) {
           e2.printStackTrace();
      }
    }
    
    public void close() {
      try {
        this.conect.close();
        stmt.close();
      } catch (SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
    
}
