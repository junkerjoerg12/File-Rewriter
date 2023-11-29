package test;

import main.Rewriter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Test {

  public static String contents = "";
  public static ArrayList<String> textsToExclude = new ArrayList<String>();

  public static void main(String[] args) {

    // Text that is allways written before and after the copied files
    String intro = "const char webpageCode[] = R\"=====(\n";
    String outro = ")=====\";";

    ArrayList<String> writeToFile = new ArrayList<String>();

    // Contains the filepaths of the Files to read
    ArrayList<String> filesToRead = new ArrayList<String>();

    ArrayList<String> breakAt = new ArrayList<String>();

    breakAt.add("</head>");

    filesToRead.add("C:\\Users\\andre\\Documents\\Schule\\Info\\Projekte\\FernbedienungWebsite\\index.html");
    filesToRead.add("C:\\Users\\andre\\Documents\\Schule\\Info\\Projekte\\FernbedienungWebsite\\style.css");
    filesToRead.add("C:\\Users\\andre\\Documents\\Schule\\Info\\Projekte\\FernbedienungWebsite\\index.js");

    textsToExclude.add(" <link rel=\"stylesheet\" href=\"style.css\" />");
    // textsToExclude.add("");

    writeToFile.add("C:\\Users\\andre\\Desktop\\outputTest.h");

    Rewriter rewriter = new Rewriter(filesToRead, writeToFile, textsToExclude, breakAt);
    rewriter.rewrite();

  }

  public static boolean write(String filepath) {
    // returns true on succes and false on failure
    try {
      // writes the string to the file
      BufferedWriter writer = new BufferedWriter(new FileWriter(filepath));
      writer.write(contents);
      contents = null;
      writer.close();
      return true;
    } catch (IOException e) {
      e.printStackTrace();
      return false;
    }
  }

  public static int read(String filepath, String stopBefore) {

    return 0;
  }

  public static void read(String filepath) {

    try {
      String line;
      BufferedReader reader = new BufferedReader(new FileReader(filepath));
      // reads each line of the file and stores it in the contents string
      while ((line = reader.readLine()) != null) {

        // checks the line for Text that is not supposed to be written to the file
        boolean valid = true;
        for (int i = 0; i < textsToExclude.size(); i++) {
          if (line.contains(textsToExclude.get(i))) {
            valid = false;
          }
        }
        // only adds the line to the Temp. storage if it is not included in the
        // "textsToExcluide" list
        if (valid) {
          contents += line;
          // adds a linebreak after each line
          contents += '\n';
        }
      }
      reader.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    // System.out.println(contents);
  }
}
