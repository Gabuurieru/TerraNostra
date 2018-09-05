package br.com.terranostra.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;

import br.com.terranostra.jdbc.ConnectionFactory;
import br.com.terranostra.jdbc.GeocodingDAO;
import br.com.terranostra.webservice.ConnectionGoogle;

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
        
        JButton btConnect = new JButton();
        btConnect.setPreferredSize(new Dimension(200, 22));
        btConnect.setText("Geocodificar");
        
        btConnect.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              String response = null;
              if(fildHost.getText() != null && fildPort.getText() != null && fildUserName.getText() != null && fildPassWord.getText() != null){
                  ConnectionFactory  con = new ConnectionFactory();
                  //Connection conect = con.getConnection(fildHost.getText(), fildPort.getText(), fildUserName.getText(), fildPassWord.getText());
                  Connection conect = con.getConnection();
                  
                  GeocodingDAO geoDao = new GeocodingDAO();
                  ResultSet rs = geoDao.getSelect(conect);
                  ConnectionGoogle cgogle = new ConnectionGoogle();
                  try {
                    //List<String> listLatLong = new ArrayList<>();  
                      
                    while(rs.next()) {
                      //response = cgogle.callApiGoogle((String) rs.getString("endereco"));
                      //listLatLong.add(cgogle.callApiGoogle((String) rs.getString("endereco")));
                      //System.out.println(rs.getString("endereco"));
                      geoDao.insert(conect, Long.parseLong(rs.getString("id")), cgogle.callApiGoogle((String) rs.getString("endereco")));
                    }
                    
                    
                  } catch (SQLException | XPathExpressionException | IOException | ParserConfigurationException | SAXException e1) {
                    e1.printStackTrace();
                  }
                  geoDao.close();
                  
               }
                 
                  
                  
//              }else {
//                  response = "Informe um endereço"; 
//              }    
//              JOptionPane.showMessageDialog(null, response);
          }
      });
      jp2.add(btConnect);  
        
        return  jtp;
    }
    
    
    public void setJLabel(JPanel jp2, String ... labels){
      
      for (String string : labels) {
          JLabel label = new JLabel(string);
          label.setPreferredSize(new Dimension(120, 22));
      }
      
    }
    
}
