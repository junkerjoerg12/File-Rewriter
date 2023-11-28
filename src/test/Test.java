package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {

        // Text that is allways written before and after the copied files
        String intro = "const char webpageCode[] = R\"=====(\n";
        String outro = ")=====\";";

        String writeToFile = "C:\\Users\\andre\\Desktop\\outputTest.h";

        // Contains the filepaths of the Files to read
        ArrayList<String> filesToRead = new ArrayList<String>();

        filesToRead.add("C:\\Users\\andre\\Documents\\Schule\\Info\\Projekte\\FernbedienungWebsite\\index.html");
        filesToRead.add("C:\\Users\\andre\\Documents\\Schule\\Info\\Projekte\\FernbedienungWebsite\\index.js");
        filesToRead.add("C:\\Users\\andre\\Documents\\Schule\\Info\\Projekte\\FernbedienungWebsite\\style.css");

        String contents = intro;

        for (int i = 0; i < filesToRead.size(); i++) {
            contents += read(filesToRead.get(i));
        }
        contents += outro;
        write(contents, writeToFile);

    }

    public static boolean write(String contents, String filepath) {
        // returns true on succes and false on failure
        try {
            // writes the string to the file
            BufferedWriter writer = new BufferedWriter(new FileWriter(filepath));
            writer.write(contents);
            writer.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String read(String filepath) {
        String contents = "";
        try {
            String line;
            BufferedReader reader = new BufferedReader(new FileReader(filepath));
            // reads each line of the file and stores it in the contents string
            while ((line = reader.readLine()) != null) {
                contents += line;
                // adds a linebreak after each line
                contents += '\n';
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(contents);
        return contents;
    }
}
