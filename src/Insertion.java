import java.util.*;
import java.io.*;
import java.nio.file.*;
import java.text.*;
import java.time.*;

public class Insertion {
  
  public static final int INFILE = 0;
  public static final int OUTFILE = 1;
  public static int lineCount = 0;
  public static String[] lines;
  
  public static void main(String[] args) {
    if (args.length != 2) {
        System.out.println("Usage: java -cp bin Insertion infile outfile");
        System.exit(1);
    }
    
    Scanner in = null;
    PrintWriter out = null;
    
    try {
        in = new Scanner(new FileInputStream(args[INFILE]));
        while(in.hasNextLine()) {
          in.nextLine();
          lineCount++;
        }
        in = new Scanner(new FileInputStream(args[INFILE]));
    } 
    catch (FileNotFoundException e) {
        System.out.println("Unable to access input file: " + args[INFILE]);
        System.exit(1);
    }

    Scanner scnr = new Scanner(System.in);
    Path path = Path.of(args[OUTFILE]);
    /*
    if (Files.exists(path)) {
        System.out.print(args[OUTFILE] + " exists - OK to overwrite (y,n)?: ");
        String answer = scnr.next();
        if (!answer.toLowerCase().startsWith("y")) {
            System.exit(1);
        }
    }
    */
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
      } catch (Exception pe) {
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
    for(int i = 0; i < lineCount; i++) {
      lines[i] = in.nextLine();
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
    //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
    for(int i = 1; i < lineCount; i++) {
      Scanner line =  new Scanner(lines[i]);
      String dateStr = line.next();
      LocalDateTime key = getDate(dateStr);
      line.close();

      int j = i - 1;
      
      Scanner jDateScanner = new Scanner(lines[j]);
      String jDateStr = jDateScanner.next();
      LocalDateTime jDate = getDate(jDateStr);
      jDateScanner.close();
      
      String keyHolder = lines[i];
      
      while(j >= 0 && jDate.compareTo(key) > 0) {
        lines[j + 1] = lines[j];
        j--;
        if(j > 0) {
          jDateScanner = new Scanner(lines[j]);
          jDateStr = jDateScanner.next();
          jDate = getDate(jDateStr);
          jDateScanner.close();
        }
      }
      
      lines[j + 1] = keyHolder;
    }
  }
  
  public static void writeOutput(PrintWriter out) {
    for(int i = 0; i < lineCount; i++) {
      out.println(lines[i]);
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
}