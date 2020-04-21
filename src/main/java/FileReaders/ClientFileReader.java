package FileReaders;

import UsersTypes.Client;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ClientFileReader {

    private String clientPath;
    private static ClientFileReader instance;

    private ClientFileReader(String clientPath) {
        this.clientPath = clientPath;
    }

    public static ClientFileReader getInstance(String clientPath) {
        if (instance == null) {
            instance = new ClientFileReader(clientPath);
        }
        return instance;
    }

    public List<Client> read() throws IOException {
        BufferedReader csvReader = new BufferedReader(new FileReader(clientPath));
        List<String> fields = Arrays.asList(csvReader.readLine().split(","));
        String row;
        List<Client> clients = new ArrayList<Client>();
        while ((row = csvReader.readLine()) != null) {
            List<String> listClient = Arrays.asList(row.split(","));
            HashMap<String, String> mapClient = new HashMap<String, String>();
            for (int i = 0; i < listClient.size(); ++i) {
                mapClient.put(fields.get(i), listClient.get(i));
            }
            clients.add(new Client(mapClient));
        }

        return clients;
    }

}
