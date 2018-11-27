package Server;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class WriteToFile {

    File file;

    public WriteToFile() {
        file = new File("GameLog.txt");
    }

    public void writeLine(String line) {
        try {
            Writer out = new BufferedWriter(new FileWriter(file, true));
            out.append(line + "\n");
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> getAllLines() {
        List<String> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                list.add(line);
            }
        } catch(Exception e) {

        }

        return list;
    }
}
