package br.com.terranostra.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;

import webservice.ConnectionGoogle;

public class PanelAddress extends JFrame {
    
    public JTabbedPane createPanelAddress(JTabbedPane jtp) {
        setTitle(".::Terra Nostra::.");
        getContentPane().add(jtp);
        JPanel jp1 = new JPanel();
        JLabel label1 = new JLabel("Endereço a ser Geocodificado: ");
        jp1.add(label1);
        jtp.addTab("Por endereço", jp1);
        
        JTextField fildAddress = new JTextField();
        fildAddress.setPreferredSize(new Dimension(120, 22));
        JButton btGeocodificar = new JButton();
        btGeocodificar.setPreferredSize(new Dimension(200, 22));
        btGeocodificar.setText("Geocodificar");
        
        ConnectionGoogle cgogle = new ConnectionGoogle();
        
        btGeocodificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String myString = null;
                try {
                    myString = cgogle.callApiGoogle((String) fildAddress.getText());
                } catch (XPathExpressionException | IOException | ParserConfigurationException | SAXException e1) {
                    e1.printStackTrace();
                }
                JOptionPane.showMessageDialog(null, myString);
            }
        });
        
        
        
        jp1.add(fildAddress);
        jp1.add(btGeocodificar);
        
        
        

        
        
        return  jtp;
    }
    
}