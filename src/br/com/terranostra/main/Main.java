package br.com.terranostra.main;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public final class Main {
  
    private static final String GEOCODER_REQUEST_PREFIX_FOR_XML = "http://maps.google.com/maps/api/geocode/xml";
  
//    public static void main(String[] args) throws IOException, XPathExpressionException, ParserConfigurationException, SAXException{
    public static String geocodificacaoApiGoogle(String address) throws IOException, XPathExpressionException, ParserConfigurationException, SAXException{
        
       // String address = "qn 8b conjunto 3, Riacho Fundo 2, DF";
        
        
        address = java.net.URLEncoder.encode(address, "UTF-8");
        
        URL url = new URL(GEOCODER_REQUEST_PREFIX_FOR_XML + "?address=" + address +"&sensor=false");
        
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        Document geocoderResultDocument = null;
        try {
          // open the connection and get results as InputSource.
          conn.connect();
          InputSource geocoderResultInputSource = new InputSource(conn.getInputStream());

          // read result and parse into XML Document
          geocoderResultDocument = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(geocoderResultInputSource);
        } finally {
          conn.disconnect();
        }

        // prepare XPath
        XPath xpath = XPathFactory.newInstance().newXPath();

        // extract the result
        NodeList resultNodeList = null;

        // a) obtain the formatted_address field for every result
        resultNodeList = (NodeList) xpath.evaluate("/GeocodeResponse/result/formatted_address", geocoderResultDocument, XPathConstants.NODESET);
        for(int i=0; i<resultNodeList.getLength(); ++i) {
          System.out.println(resultNodeList.item(i).getTextContent());
        }

        // b) extract the locality for the first result
        resultNodeList = (NodeList) xpath.evaluate("/GeocodeResponse/result[1]/address_component[type/text()='locality']/long_name", geocoderResultDocument, XPathConstants.NODESET);
        for(int i=0; i<resultNodeList.getLength(); ++i) {
          System.out.println(resultNodeList.item(i).getTextContent());
        }

        // c) extract the coordinates of the first result
        resultNodeList = (NodeList) xpath.evaluate("/GeocodeResponse/result[1]/geometry/location/*", geocoderResultDocument, XPathConstants.NODESET);
        float lat = 0;
        float lng = Float.NaN;
        for(int i=0; i<resultNodeList.getLength(); ++i) {
            Node node = resultNodeList.item(i);
            if("lat".equals(node.getNodeName())) {
                lat = Float.parseFloat(node.getTextContent());
            } 
            if("lng".equals(node.getNodeName())) {
                lng = Float.parseFloat(node.getTextContent());
            } 
            System.out.println(lat);
            System.out.println(lng);
        }
        return lat +" / "+ lng;
        
    }
    public static void main(String[] args) throws XPathExpressionException, IOException, ParserConfigurationException, SAXException {
      String endereco = JOptionPane.showInputDialog("Informe endereço a ser geocodificado");
      
      String latlong = geocodificacaoApiGoogle(endereco);
      
      JOptionPane.showMessageDialog(null, "lat e long: " + latlong);
    }
}
