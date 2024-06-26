import java.util.*;
import java.io.*;
import java.nio.file.*;
import java.text.*;
import java.time.*;

public class Merge {
  
  public static final int INFILE = 0;
  public static final int OUTFILE = 1;
  public static int lineCount = 0;
  public static String[] lines;
  public static String timeRegex = "[0-9][0-9][0-9][0-9]-[0-9][0-9]-[0-9][0-9]T[0-9][0-9]:" +
                                   "[0-9][0-9]:[0-9][0-9]-[0-9][0-9]:[0-9][0-9](.*)";
  
  public static void main(String[] args) {
    if (args.length != 2) {
      System.out.println("Usage: java -cp bin Merge infile outfile");
      System.exit(1);
    }
    
    Scanner in = null;
    PrintWriter out = null;
    
    try {
      in = new Scanner(new FileInputStream(args[INFILE]));
      String inLine;
      while(in.hasNextLine()) {
        inLine = in.nextLine();
        if (inLine.matches(timeRegex)) {
          lineCount++;
        }
      }
      System.out.println("Lines: " + lineCount);
      in = new Scanner(new FileInputStream(args[INFILE]));
    } 
    catch (FileNotFoundException e) {
      System.out.println("Unable to access input file: " + args[INFILE]);
      System.exit(1);
    }

    Scanner scnr = new Scanner(System.in);
    Path path = Path.of(args[OUTFILE]);
    try {
      out = new PrintWriter(new FileOutputStream(args[OUTFILE]));
    } 
    catch (FileNotFoundException e) {
      System.out.println("Cannot create output file");
      System.exit(1);
    }
    
    try {
      long startTime = System.nanoTime();
      readInput(in);
      long endTime = System.nanoTime();
      long duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
      long durationms = duration;
      System.out.println("Time to read input: " + durationms + " nanoseconds.");
      in.close();
      
      startTime = System.nanoTime();
      sort(lines);
      endTime = System.nanoTime();
      duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
      durationms = duration;
      System.out.println("Time to sort input: " + durationms + " nanoseconds.");
      
      try {
        for (int i = 0; i < lines.length - 1; i++) {
          if (compare(lines[i], lines[i + 1]) > 0) {
            System.out.println("Line " + i + " is not sorted correctly");
          }
        }
      } catch (ParseException pe) {
        System.out.println("Caught ParseException during sorted output");
        System.exit(1);
      }
      
      writeOutput(out);
      out.close();
    } catch (ParseException pe) {
      System.out.println("Caught ParseException during sorting");
      System.exit(1);
    }
  }
  
  public static void readInput(Scanner in) {
    lines = new String[lineCount];
    String inLine;
    for(int i = 0; i < lineCount; i++) {
      inLine = in.nextLine();
      if (inLine.matches(timeRegex)) {
        lines[i] = inLine;
      } else {
        i--;
      }
    }
  }
  
  public static LocalDateTime getDate(String dateString) {
    int year = Integer.parseInt(dateString.substring(0, 4));
    int month = Integer.parseInt(dateString.substring(5, 7));
    int date = Integer.parseInt(dateString.substring(8, 10));
    int hour = Integer.parseInt(dateString.substring(11, 13));
    int minute = Integer.parseInt(dateString.substring(14, 16));
    int second = Integer.parseInt(dateString.substring(17, 19));
    LocalDateTime timestamp = LocalDateTime.of(year, month, date, hour, minute, second, 0);
    return timestamp;
  }
  
  public static void sort(String[] dataStrings) throws ParseException {
    if (dataStrings.length < 2) {
      return;
    }

    int mid = dataStrings.length / 2;
    String[] left = Arrays.copyOfRange(dataStrings, 0, mid);
    String[] right = Arrays.copyOfRange(dataStrings, mid, dataStrings.length);

    sort(left);
    sort(right);

    merge(left, right, dataStrings);
  }

  private static void merge(String[] left, String[] right, String[] dataStrings) {
    int leftIndex = 0;
    int rightIndex = 0;

    try {
      while (leftIndex + rightIndex < dataStrings.length) {
        if (rightIndex == right.length
            || (leftIndex < left.length && compare(left[leftIndex], right[rightIndex]) < 0)) {
          dataStrings[leftIndex + rightIndex] = left[leftIndex++];
        } else {
          dataStrings[leftIndex + rightIndex] = right[rightIndex++];
        }
      }
    } catch (ParseException pe) {
      System.out.println("Caught ParseException during sorting");
      System.exit(1);
    }
  }

  private static int compare(String first, String second) throws ParseException {
    Scanner firstLine = new Scanner(first);
    String firstDateStr = firstLine.next();
    LocalDateTime firstKey = getDate(firstDateStr);
    firstLine.close();

    Scanner secondLine = new Scanner(second);
    String secondDateStr = secondLine.next();
    LocalDateTime secondKey = getDate(secondDateStr);
    secondLine.close();

    return(firstKey.compareTo(secondKey));
  }
  
  public static void writeOutput(PrintWriter out) {
    for(int i = 0; i < lineCount; i++) {
      out.println(lines[i]);
    }
  }
}
