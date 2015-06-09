package ClassesforBackEnd;

import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author srhodes47
 */
public class JsonManager {

    public JSONObject htmlToJavaEmployee(String table) {
        String justHtml = table.substring(table.indexOf("<table>"), table.indexOf("</table>"));
        String headers = justHtml.substring(justHtml.indexOf("<tr>") + 4, justHtml.indexOf("</tr>"));
        headers = headers.replace("</th>", "");
        String[] columns = headers.split("<th>");
        String information = justHtml.substring(justHtml.lastIndexOf("<tr>"), justHtml.lastIndexOf("</tr>"));
        information = information.replace("</td>", "");
        String[] rows = information.split("<td>");

        JSONObject employee = new JSONObject();
        employee.put(columns[1], rows[1]);
        employee.put(columns[2], rows[2]);
        employee.put(columns[3], rows[3]);
        employee.put(columns[4], rows[4]);
        employee.put(columns[5], rows[5]);

        return employee;
    }

    //not used
    public String htmlToJavaCurrentLog(String table) {
        String justHtml = table.substring(table.indexOf("Current"), table.indexOf("History"));
        String headers = justHtml.substring(justHtml.indexOf("<tr>") + 4, justHtml.indexOf("</tr>"));
        headers = headers.replace("</th>", "");
        String[] columns = headers.split("<th>");
        String information = justHtml.substring(justHtml.lastIndexOf("<tr>"), justHtml.lastIndexOf("</tr>"));
        information = information.replace("</td>", "");
        String[] rows = information.split("<td>");
        information = information.replace("</td", "");
        JSONObject currentLog = new JSONObject();
        currentLog.put(columns[1], rows[1]);
        currentLog.put(columns[2], rows[2]);
        currentLog.put(columns[3], rows[3]);
        currentLog.put(columns[4], rows[4]);
        currentLog.put(columns[5], rows[5]);
        return currentLog.toJSONString();
    }

    public JSONArray htmlToJavaLogHistory(String table) {
        String justHtml = table.substring(table.indexOf("History"), table.lastIndexOf("History"));
        String headers = justHtml.substring(justHtml.indexOf("<tr>"), justHtml.indexOf("</tr>"));
        headers = headers.replace("</th>", "");
        String[] columns = headers.split("<th>");

        JsonManager manageJson = new JsonManager();
        JSONObject workingObject = new JSONObject();
        JSONArray logHistory = new JSONArray();

        ProjectManager myManager = new ProjectManager();
        ArrayList myArray = new ArrayList();
        myArray = myManager.getEmployeeLogHistory("smukhia"); //need to pass Parameter

        for (int i = 2; i <= myArray.size() + 1; i++) {
            int startIndex = manageJson.findIndex(justHtml, "<tr>", i);
            int endIndex = manageJson.findIndex(justHtml, "</tr>", i);
            String information = justHtml.substring(startIndex, endIndex);
            information = information.replace("</td>", "");
            information = information.replace("</td", "");
            String[] rows = information.split("<td>");
            workingObject.put(columns[1], rows[1]);
            workingObject.put(columns[2], rows[2]);
            workingObject.put(columns[3], rows[3]);
            workingObject.put(columns[4], rows[4]);
            workingObject.put(columns[5], rows[5]);
            logHistory.add(workingObject);
            workingObject = new JSONObject();
        }
        return logHistory;
    }

    public JSONArray htmlToJsonProjectHistory(String table) {
        String justHtml = table.substring(table.lastIndexOf("History"), table.length());
        String headers = justHtml.substring(justHtml.indexOf("<tr>"), justHtml.indexOf("</tr>"));
        headers = headers.replace("</th>", "");
        String[] columns = headers.split("<th>");

        JsonManager manageJson = new JsonManager();
        JSONObject workingObject = new JSONObject();
        JSONArray logHistory = new JSONArray();
        ProjectManager myManager = new ProjectManager();
        ArrayList myArray = new ArrayList();
        myArray = myManager.getEmployeeProject("smukhia");

        for (int i = 2; i <= myArray.size() + 1; i++) {
            int startIndex = manageJson.findIndex(justHtml, "<tr>", i);
            int endIndex = manageJson.findIndex(justHtml, "</tr>", i);

            String information = justHtml.substring(startIndex, endIndex);
            information = information.replace("</td>", "");
            information = information.replace("</td", "");
            String[] rows = information.split("<td>");
            workingObject.put(columns[1], rows[1]);
            workingObject.put(columns[2], rows[2]);
            workingObject.put(columns[3], rows[3]);
            logHistory.add(workingObject);
            workingObject = new JSONObject();
        }
        return logHistory;
    }

    public JSONArray htmlToJsonEmployeeLog(String table) {
        String justHtml = table.substring(table.indexOf("Employee"), table.lastIndexOf("Log for Project"));
        String headers = justHtml.substring(justHtml.indexOf("<tr>"), justHtml.indexOf("</tr>"));
        headers = headers.replace("</th>", "");
        String[] columns = headers.split("<th>");

        JsonManager manageJson = new JsonManager();
        JSONObject workingObject = new JSONObject();
        JSONArray logHistory = new JSONArray();
        ProjectManager myManager = new ProjectManager();
        ArrayList myArray = new ArrayList();
        myArray = myManager.getEmployeeLogHistory("smukhia"); //need to pass Parameter        

        for (int i = 2; i <= myArray.size() + 1; i++) {
            int startIndex = manageJson.findIndex(justHtml, "<tr>", i);
            int endIndex = manageJson.findIndex(justHtml, "</tr>", i);
            String information = justHtml.substring(startIndex, endIndex);
            information = information.replace("</td>", "");
            information = information.replace("</td", "");
            String[] rows = information.split("<td>");
            workingObject.put(columns[1], rows[1]);
            workingObject.put(columns[2], rows[2]);
            workingObject.put(columns[3], rows[3]);
            workingObject.put(columns[4], rows[4]);
            workingObject.put(columns[5], rows[5]);
            logHistory.add(workingObject);
            workingObject = new JSONObject();
        }

        return logHistory;
    }

    public JSONArray htmlToJsonProjectId(String table) {
        String justHtml = table.substring(table.indexOf("Project Log"), table.indexOf("Management"));
        String headers = justHtml.substring(justHtml.indexOf("<tr>"), justHtml.indexOf("</tr>"));
        headers = headers.replace("</th>", "");
        String[] columns = headers.split("<th>");
        JsonManager manageJson = new JsonManager();
        JSONObject workingObject = new JSONObject();
        JSONArray logHistory = new JSONArray();
        ProjectManager myManager = new ProjectManager();
        ArrayList myArray = new ArrayList();
        myArray = myManager.getProjectLog("2"); //need to pass Parameter    

        for (int i = 2; i <= myArray.size() + 1; i++) {
            int startIndex = manageJson.findIndex(justHtml, "<tr>", i);
            int endIndex = manageJson.findIndex(justHtml, "</tr>", i);
            String information = justHtml.substring(startIndex, endIndex);
            information = information.replace("</td>", "");
            information = information.replace("</td", "");
            String[] rows = information.split("<td>");
            workingObject.put(columns[1], rows[1]);
            workingObject.put(columns[2], rows[2]);
            workingObject.put(columns[3], rows[3]);
            workingObject.put(columns[4], rows[4]);
            workingObject.put(columns[5], rows[5]);
            logHistory.add(workingObject);
            workingObject = new JSONObject();
        }
        return logHistory;
    }
    
    public JSONArray htmlToJsonManagementHistory (String table) {
        String justHtml = table.substring(table.indexOf("Management"),table.indexOf("To see"));
        String headers = justHtml.substring(justHtml.indexOf("<tr>"), justHtml.indexOf("</tr>"));
        headers = headers.replace("</th>", "");
        String[] columns = headers.split("<th>");
        JsonManager manageJson = new JsonManager();
        JSONObject workingObject = new JSONObject();
        JSONArray logHistory = new JSONArray();
        ProjectManager myManager = new ProjectManager();
        ArrayList myArray = new ArrayList();
        myArray = myManager.getManagerHistory("agarg76"); //need to pass Parameter  
        
        for (int i = 2; i <= myArray.size() + 1; i++) {
            int startIndex = manageJson.findIndex(justHtml, "<tr>", i);
            int endIndex = manageJson.findIndex(justHtml, "</tr>", i);
            String information = justHtml.substring(startIndex, endIndex);
            information = information.replace("</td>", "");
            information = information.replace("</td", "");
           
            String[] rows = information.split("<td>");
            workingObject.put(columns[1], rows[1]);
            workingObject.put(columns[2], rows[2]);
            logHistory.add(workingObject);
            workingObject = new JSONObject();
        }
        return logHistory;
    }

    public int findIndex(String toSearch, String toCount, int totalIndex) {
        int j = 0;
        for (int i = 0; i < totalIndex; i++) {
            j = toSearch.indexOf(toCount, j + 1);
            if (j == -1) {
                break;
            }
        }
        return j;
    }
}
