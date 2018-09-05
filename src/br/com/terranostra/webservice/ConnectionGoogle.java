package br.com.terranostra.webservice;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

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

public class ConnectionGoogle {

    private static final String GEOCODER_REQUEST_PREFIX_FOR_XML = "http://maps.google.com/maps/api/geocode/xml";  
    
    public static String callApiGoogle(String address) throws IOException, XPathExpressionException, ParserConfigurationException, SAXException{
            
            address = address.replace( " ", "%20" );
            System.out.println(address);
            URL url = new URL(GEOCODER_REQUEST_PREFIX_FOR_XML + "?address=" + address +"&sensor=false");
            System.out.println(url);
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
            double lat = 0;
            double lng = 0;
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
}
