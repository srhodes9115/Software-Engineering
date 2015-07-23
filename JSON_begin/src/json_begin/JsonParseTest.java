package json_begin;

/**
 *
 * @author srhodes47
 */
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonParseTest {

    public static void main(String[] args) {
        try {
            URL aol = new URL("http://csoweb-m01.office.aol.com:8080/EnterpriseApprovalWS/hub/system/getDetails?requestID=M-2054&user=art-test&pw=art-test-pw");
            //url has changed so this project will not work
            BufferedReader readin = new BufferedReader(new InputStreamReader(aol.openStream()));
            String linebyline;
            linebyline = readin.readLine();

            JSONParser myjsonParser = new JSONParser();
            JSONObject myjsonObject = (JSONObject) myjsonParser.parse(linebyline);

            JSONObject response = (JSONObject) myjsonObject.get("response");
            System.out.println("Response: ");

            String message = (String) response.get("message");
            System.out.println("\tMessage: " + message);

            long status = (long) response.get("status");
            System.out.println(" \tStatus: " + status);

            JSONArray array = (JSONArray) myjsonObject.get("ApprovalList");
            System.out.println("ApprovalList: ");
            JSONObject object = (JSONObject) array.get(0);

            String requestorCDID = (String) object.get("requestorCDID");
            System.out.println("\trequestorCDID: " + requestorCDID);

            String actionedByName = (String) object.get("actionedByName");
            System.out.println("\tactionedByName: " + actionedByName);

            String reason = (String) object.get("reason");
            System.out.println("\treason: " + reason);

            String state = (String) object.get("state");
            System.out.println("\tstate: " + state);

            String url = (String) object.get("url");
            System.out.println("\turl: " + url);

            String actionedByWhen = (String) object.get("actionedByWhen");
            System.out.println("\tactionedByWhen: " + actionedByWhen);

            String requestID = (String) object.get("requestID");
            System.out.println("\trequestID: " + requestID);

            String source = (String) object.get("source");
            System.out.println("\tsource: " + source);

            String shortDescription = (String) object.get("shortDescription");
            System.out.println("\tshortDescription " + shortDescription);

            String requestorName = (String) object.get("requestorName");
            System.out.println("\trequestorName: " + requestorName);

            String longDescription = (String) object.get("longDescription");
            System.out.println("\tlongDescription: " + longDescription);

            String actionedByCDID = (String) object.get("actionedByCDID");
            System.out.println("\tactionedByCDID: " + actionedByCDID);

            String actionedBySource = (String) object.get("actionedBySource");
            System.out.println("\tactionedBySource: " + actionedBySource);

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ParseException ex) {
            ex.printStackTrace();
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }
    }
}
