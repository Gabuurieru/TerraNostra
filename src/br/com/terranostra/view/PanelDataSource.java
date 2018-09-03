package br.com.terranostra.view;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class PanelDataSource {
    // -- dados do banco
    //host
    //port
    //username
    //password
    //schema
    //table
    //collumn
  
  
    public JTabbedPane createPanelDataSource(JTabbedPane jtp) {
        JPanel jp2 = new JPanel();
        JLabel label1 = new JLabel();
        label1.setText("Dados para conexão:");
        
        JLabel host = new JLabel("Host: ");
        JLabel port = new JLabel("Port: ");
        JLabel username = new JLabel("Username: ");
        JLabel password = new JLabel("Password: ");
        
        jp2.add(label1);
        
        jp2.add(host);
        JTextField fildHost = new JTextField();
        fildHost.setPreferredSize(new Dimension(120, 22));
        jp2.add(fildHost);
        jp2.add(port);
        JTextField fildPort = new JTextField();
        fildPort.setPreferredSize(new Dimension(120, 22));
        jp2.add(fildPort);
        jp2.add(username);
        JTextField fildUserName = new JTextField();
        fildUserName.setPreferredSize(new Dimension(120, 22));
        jp2.add(fildUserName);
        jp2.add(password);
        JTextField fildPassWord = new JTextField();
        fildPassWord.setPreferredSize(new Dimension(120, 22));
        jp2.add(fildPassWord);
        
        jtp.addTab("Bando de Dados PostgreSQL", jp2);
        
        return  jtp;
    }
    
    
    
//  public static void main(String[] args) {
//  Connection con = new ConnectionFactory().getConnection();
//  try {
//    
//    DatabaseMetaData meta = con.getMetaData();
//    ResultSet schemas = meta.getSchemas();
//    while (schemas.next()) {
//      String tableSchema = schemas.getString(1);    // "TABLE_SCHEM"
////      String tableCatalog = schemas.getString(2); //"TABLE_CATALOG"
//      System.out.println("tableSchema: "+tableSchema);
//    }
//    
//  } catch (SQLException e) {
//    // TODO Auto-generated catch block
//    e.printStackTrace();
//  }
//  
//}
}
