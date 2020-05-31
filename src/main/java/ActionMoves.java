import java.io.*;

public class ActionMoves {
    private static ActionMoves instance;
    private String actionPath;

    private ActionMoves(String actionPath) throws IOException {
        this.actionPath = actionPath;

        // Check if file is empty

        BufferedReader br = new BufferedReader(new FileReader(actionPath));
        if (br.readLine() == null) {
            FileWriter csvWriter = new FileWriter(this.actionPath, true);
            csvWriter.append("action,timestamp,thread_name\n");
            csvWriter.flush();
            csvWriter.close();
        }

    }

    public static ActionMoves getInstance(String actionPath) throws IOException {
        if (instance == null) {
            instance = new ActionMoves(actionPath);
        }
        return instance;
    }

    public void write(String action, String timeStamp, String threadName) throws IOException {
        FileWriter csvWrite = new FileWriter(this.actionPath, true);
        csvWrite.append(action);
        csvWrite.append(',');
        csvWrite.append(timeStamp);
        csvWrite.append(',');
        csvWrite.append(threadName);
        csvWrite.append('\n');
        csvWrite.flush();
        csvWrite.close();
    }
}
