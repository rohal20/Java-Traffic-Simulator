package Controller;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

/**
 * This Class is used to read and Parse the configuration settings from the xml file
 * and then provide MapConfig with this data.
 */
public class MapLoader {
    public static MapConfig loadConfig(String filePath) {
        MapConfig config = new MapConfig();
        try {
            File xmlFile = new File(filePath);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder(); // Create a DocumentBuilder
            Document doc = db.parse(xmlFile); //Parse
            doc.getDocumentElement().normalize();

            Element mapDesignElement = (Element) doc.getElementsByTagName("MapDesign").item(0);

            // Read and parse
            int mapLanes = Integer.parseInt(mapDesignElement.getElementsByTagName("MapLanes").item(0).getTextContent());
            int rounds = Integer.parseInt(mapDesignElement.getElementsByTagName("Rounds").item(0).getTextContent());
            int botVehicleLane = Integer.parseInt(mapDesignElement.getElementsByTagName("BotVehicleLane").item(0).getTextContent());

            // Set the parsed values
            config.setMapLanes(mapLanes);
            config.setRounds(rounds);
            config.setBotVehicleLane(botVehicleLane);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return config;
    }
}

