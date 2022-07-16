package org.academiadecodigo.org;

import java.io.*;

public class FileManager {

    private static final String FILEPATH = "resources/map.txt";

    public static String readFile(){

        String result = "";
        BufferedReader reader = null;

        try {

            String line;
            reader = new BufferedReader(new FileReader(FILEPATH));
            while ((line=reader.readLine())!=null) {
                result += line + "\n";

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {

            try {
                reader.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return result;
    }

    public static void writeToFile(String string){

        BufferedWriter writer;

        try {
            writer = new BufferedWriter(new FileWriter(FILEPATH));
            writer.write(string);
            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
