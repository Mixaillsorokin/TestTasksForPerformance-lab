package task3;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
public class Task3 {

    public static void main(String[] args) {
        try {
            JSONObject configJson = readJSONFile("config.json");
            String testsFileName = configJson.getString("testsFilePath");
            String valuesFileName = configJson.getString("valuesFilePath");

            JSONObject testsJson = readJSONFile(testsFileName);
            JSONObject valuesJson = readJSONFile(valuesFileName);

            fillValues(testsJson.getJSONArray("tests"), valuesJson.getJSONArray("values"));

            FileWriter fileWriter = new FileWriter("report.json");
            fileWriter.write(testsJson.toString());
            fileWriter.close();

            System.out.println("Файл report.json успешно создан.");
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    private static JSONObject readJSONFile(String fileName) throws IOException {
        FileReader fileReader = new FileReader(fileName);
        JSONTokener tok = new JSONTokener(fileReader);
        return new JSONObject(tok);
    }

    private static void fillValues(JSONArray tests, JSONArray values) {
        for (int i = 0; i < tests.length(); i++) {
            try {
                JSONObject test = tests.getJSONObject(i);
                int testId = test.getInt("id");
                String value = test.optString("value");
                if (value.isEmpty()) {
                    for (int j = 0; j < values.length(); j++) {
                        JSONObject valueObj = values.getJSONObject(j);
                        int valueId = valueObj.getInt("id");
                        if (testId == valueId) {
                            test.put("value", valueObj.getString("value"));
                            break;
                        }
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
