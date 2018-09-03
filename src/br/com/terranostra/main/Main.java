package br.com.terranostra.main;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import br.com.terranostra.view.PanelAddress;
import br.com.terranostra.view.PanelDataSource;

public final class Main {
    
    public static void main(String[] args) {
        
        JTabbedPane jtp = new JTabbedPane();
        PanelAddress panelAdress = new PanelAddress();
        PanelDataSource panelData = new PanelDataSource();
        jtp =  panelAdress.createPanelAddress(jtp);
        jtp = panelData.createPanelDataSource(jtp);
        
        
        panelAdress.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panelAdress.setVisible(true);
         
    }
    
}
