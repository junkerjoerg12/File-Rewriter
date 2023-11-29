package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

public class Rewriter {

  private ArrayList<String> textsToExclude = new ArrayList<String>();
  private ArrayList<String> filesToRead = new ArrayList<String>();
  private ArrayList<String> filesToWrite = new ArrayList<String>();
  private ArrayList<String> breakAt = new ArrayList<String>();

  int breakpiontNo = 0;

  private BufferedWriter writer;
  private BufferedReader reader;

  private String text = "";

  public Rewriter(ArrayList<String> filesToRead, ArrayList<String> filesToWrite, ArrayList<String> textsToExclude,
      ArrayList<String> breakAt) {
    this.textsToExclude = textsToExclude;
    this.filesToRead = filesToRead;
    this.filesToWrite = filesToWrite;
    this.breakAt = breakAt;
    System.out.println(breakAt);

    try {
      reader = new BufferedReader(new FileReader(filesToRead.get(0)));
      writer = new BufferedWriter(new FileWriter(filesToWrite.get(0)));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public boolean rewrite() {
    System.out.println("ja");
    String line = "";

    try { // reading the file line by line and checking for texts to exclude
      while ((line = reader.readLine()) != null) {
        // System.out.println(line);

        if (checkForExlution(line)) {
          checkFileChange(line);
          text += line + "\n";
        }

      }
    } catch (IOException e) {
      e.printStackTrace();
      System.out.println("hier wolle ich noch nicht raus");
      return false;
    }
    try {
      writer.write(text);
      System.out.println(text);
      return true;
    } catch (IOException e) {
      return false;
    }
  }

  // returns false if line contains text contained in textsToExclude
  private boolean checkForExlution(String line) {
    boolean valid = true;
    for (int i = 0; i < textsToExclude.size(); i++) {

      // System.out.println("input" + textsToExclude.get(i));
      // System.out.println("scan " + line);
      if (line.contains(textsToExclude.get(i))) {
        // System.out.println("raus damit");
        valid = false;
      }
    }
    return valid;
  }

  private void checkFileChange(String line) {
    if (line.contains(breakAt.get(0))) {
      breakpiontNo++;
      try {
        reader = new BufferedReader(new FileReader(filesToRead.get(breakpiontNo)));
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      }
    } else {
    }
    System.out.println(breakpiontNo);

  }

}
