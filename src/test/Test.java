package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Test {
    public static void main(String[] args) {

        String writeToFile = "C:\\Users\\andre\\Desktop\\outputTest.txt";
        String readFromFile = "C:\\Users\\andre\\Desktop\\inputTest.txt";

        write(read(readFromFile), writeToFile);

    }

    public static boolean write(String contents, String filepath) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filepath));
            writer.write(contents);
            writer.close();
            System.out.println("succes");
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

            while ((line = reader.readLine()) != null) {
                contents += line;
                contents += '\n';
            }

            reader.close();

        } catch (IOException e) {
            // TODO: handle exception
        }

        return contents;
    }
}
