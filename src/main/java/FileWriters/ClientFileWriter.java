package FileWriters;

import UsersTypes.Client;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ClientFileWriter {

    private String clientPath;
    private static ClientFileWriter instance;

    private ClientFileWriter(String clientPath) {
        this.clientPath = clientPath;
    }

    public static ClientFileWriter getInstance(String clientPath) {
        if (instance == null) {
            instance = new ClientFileWriter(clientPath);
        }
        return instance;
    }

    public void write(List<Client> clientList) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        JSONArray clientFields = (JSONArray) parser.parse(new FileReader("src/main/config/client_fields.json"));
        FileWriter csvWriter;

        BufferedReader br = new BufferedReader(new FileReader(clientPath));
        if (br.readLine() == null) {
            // File is empty, so we fill the first line with data names
            csvWriter = new FileWriter(clientPath);
            for (Object objectClientFields: clientFields) {
                JSONObject fields =(JSONObject) objectClientFields;
                for (int i = 0; i < fields.keySet().toArray().length; ++i) {
                    String key = (String) fields.keySet().toArray()[i];
                    csvWriter.append(key);
                    if (i != fields.keySet().toArray().length - 1) {
                        csvWriter.append(",");
                    } else {
                        csvWriter.append("\n");
                    }
                }
            }

        } else {
            // File already has data inside, so we just append data here
            csvWriter = new FileWriter(clientPath, true);
        }


        List<String> keyList = new ArrayList<String>();

        for (Object objectClientFields: clientFields) {
            JSONObject fields =(JSONObject) objectClientFields;
            for (int i = 0; i < fields.keySet().toArray().length; ++i) {
                String key = (String) fields.keySet().toArray()[i];
                keyList.add(key);
            }
        }

        for (Client client: clientList) {
            for (int i = 0; i < keyList.size(); ++i) {
                String key = keyList.get(i);
                String item = client.getByField(key);
                csvWriter.append(item);
                if (i != keyList.size() - 1) {
                    csvWriter.append(",");
                } else {
                    csvWriter.append("\n");
                }
            }
        }

        csvWriter.flush();
        csvWriter.close();
    }
}
