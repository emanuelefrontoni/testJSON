
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import javax.net.ssl.HttpsURLConnection;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


//PPPP
public class JsonParser {
   
    public static void main(String[] args) {
        JSONParser parser = new JSONParser();

        try {        
            URL oracle = new URL("https://api.domainsdb.info/v1/info/stat/?limit=50&aoikey=dadsdsasa"); // URL to Parse
            HttpsURLConnection yc = (HttpsURLConnection) oracle.openConnection();
    		yc.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
            
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
           
            String inputLine;
            while ((inputLine = in.readLine()) != null) { 
            	
            	JSONObject stats = (JSONObject) parser.parse(inputLine);
            	
                JSONArray a = (JSONArray) stats.get("statistics");
                
               
                // Loop through each item
                for (Object o : a) {
                    JSONObject domain = (JSONObject) o;

                    String z = (String) domain.get("zone");
                    System.out.println("Zone : " + z);

                    Long total = (Long) domain.get("total");
                    System.out.println("Total : " + total);
                    System.out.println("\n");
                }
            }
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }  
    }  
}