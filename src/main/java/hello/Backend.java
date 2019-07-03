package hello;

import java.io.*;
import java.util.*;
import org.apache.http.*;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import java.net.URISyntaxException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.SerializationFeature;
//import com.fasterxml.jackson.databind.node.ObjectNode;

public class Backend {
    private static String apiKey = "1c216659-d074-4c95-875c-e312a07ab25c";
    private static String uri = "";
    private static ArrayList<NameValuePair> parameters = new ArrayList<NameValuePair>();

    static ArrayList<Crypto> fetchData(ArrayList<String> str_coins) throws Exception {
        ArrayList<Crypto> DataObjects = new ArrayList<Crypto>();
        String stringofcoins = str_coins.get(0);
        // String of coins is the string of form BTC,ETH,XRP
        for (int i = 1; i < str_coins.size(); i++) {
            stringofcoins += "," + str_coins.get(i);
        }
        uri = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/quotes/latest";
        parameters.add(new BasicNameValuePair("symbol", stringofcoins));
        parameters.add(new BasicNameValuePair("convert", "INR"));
        String result = null;
        try (FileOutputStream fos = new FileOutputStream("data.json");) {
            result = makeAPICall();
            fos.write(result.getBytes());
        } catch (IOException e) {
            System.out.println("Error: cannont access content - " + e.toString());
        } catch (URISyntaxException e) {
            System.out.println("Error: Invalid URL " + e.toString());
        }
        byte[] jsonData = result.getBytes();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(jsonData);
        JsonNode data = root.path("data");
        for (int i = 0; i < str_coins.size(); i++) {
            JsonNode BTC = data.path(str_coins.get(i));
            Crypto btc = mapper.convertValue(BTC, Crypto.class);
            DataObjects.add(btc);
            System.out.println(
                    "Price of " + str_coins.get(i) + " is " + (((DataObjects.get(i)).getquote()).getINR()).getprice());
        }
        return DataObjects;
    }

    private static String makeAPICall() throws URISyntaxException, IOException {
        String response_content = "";

        URIBuilder query = new URIBuilder(uri);
        query.addParameters(parameters);

        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet request = new HttpGet(query.build());

        request.setHeader(HttpHeaders.ACCEPT, "application/json");
        request.addHeader("X-CMC_PRO_API_KEY", apiKey);

        CloseableHttpResponse response = client.execute(request);

        try {
            System.out.println(response.getStatusLine());
            HttpEntity entity = response.getEntity();
            response_content = EntityUtils.toString(entity);
            EntityUtils.consume(entity);
        } finally {
            response.close();
        }

        return response_content;
    }

}
