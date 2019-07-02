package hello;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Example {

    private static String apiKey = "1c216659-d074-4c95-875c-e312a07ab25c";
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        String uri = "";
        List<NameValuePair> parameters = new ArrayList<NameValuePair>();
        ArrayList<String> coins = new ArrayList<String>();

        System.out.println("Enter 0 for all and 1 for Custom");
        int choice = sc.nextInt();
        if (choice == 0) {
            uri = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/latest";
            parameters.add(new BasicNameValuePair("start", "1"));
            parameters.add(new BasicNameValuePair("limit", "5000"));
        } else if (choice == 1) {
            System.out.println("Enter coin symbol or -1 to Stop");
            String coin;
            while (!(coin = sc.next()).equals(new String("-1"))) {
                System.out.println("Enter coin symbol or -1 to Stop");
                coins.add(coin);
            }
            String sym = coins.get(0);
            for (int i = 1; i < coins.size(); i++) {
                sym += "," + coins.get(i);
            }
            uri = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/quotes/latest";
            // System.out.println("Enter which Cryptocurrency rate in form BTC,ETH");
            // String symbol = sc.next();
            parameters.add(new BasicNameValuePair("symbol", sym));
        }

        parameters.add(new BasicNameValuePair("convert", "INR"));
        String result;
        try (FileOutputStream fos = new FileOutputStream("data.json");) {
            result = makeAPICall(uri, parameters);
            // System.out.println(result);
            fos.write(result.getBytes());
        } catch (IOException e) {
            System.out.println("Error: cannont access content - " + e.toString());
        } catch (URISyntaxException e) {
            System.out.println("Error: Invalid URL " + e.toString());
        }
    }

    public static String makeAPICall(String uri, List<NameValuePair> parameters)
            throws URISyntaxException, IOException {
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
