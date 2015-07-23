package currency_client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author srhodes47
 */
public class Currency_client {

    
    public static void main(String[] args) throws IOException {
       //POST request, uses string or file no limit on the parameters
        String urlparameters = "currency_id=7589&currency_name=dollar";
            
            URL url = new URL("http://localhost:8084/Currency/Currency");
            HttpURLConnection web = (HttpURLConnection) url.openConnection();
            web.setRequestMethod("POST");
            web.setDoOutput(true);
            
            DataOutputStream wr = new DataOutputStream(web.getOutputStream());
            wr.writeBytes(urlparameters);
            wr.flush();
            wr.close();

            System.out.println("\nSending 'POST' request to URL : " + url);
            System.out.println("\nPost parameters: " + urlparameters);
            
            BufferedReader in = new BufferedReader(new InputStreamReader(web.getInputStream()));
            
            String inputline;
            
            StringBuffer myresponse;
            myresponse = new StringBuffer();
            
            while(in.ready())
            {
                inputline = in.readLine();
                myresponse.append(inputline);
            }
            in.close();
            
            System.out.println(myresponse.toString()); 
    }
    
}
