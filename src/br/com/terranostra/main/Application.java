package br.com.terranostra.main;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import br.com.terranostra.view.PanelAddress;
import br.com.terranostra.view.PanelDataSource;

public final class Application {
    
    public static void main(String[] args) {
        
        JTabbedPane jtp = new JTabbedPane();
        PanelAddress panelAdress = new PanelAddress();
        PanelDataSource panelData = new PanelDataSource();
        jtp =  panelAdress.createPanelAddress(jtp);
        jtp = panelData.createPanelDataSource(jtp);
        panelAdress.setMinimumSize(new Dimension(500, 500));
        panelAdress.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panelAdress.setVisible(true);
        ImageIcon img = new ImageIcon("/home/gabriel/Imagens/2018-08-10.png"); 
        panelAdress.setIconImage(img.getImage());
    }
}