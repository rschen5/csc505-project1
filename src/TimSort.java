import java.util.*;
import java.io.*;
import java.nio.file.*;
import java.text.*;
import java.time.*;

public class TimSort {

  /**
   * Timsort implementation and helper methods adapted from: https://www.geeksforgeeks.org/timsort/
   */
  
  public static final int INFILE = 0;
  public static final int OUTFILE = 1;
  public static int lineCount = 0;
  public static String[] lines;
  public static int MIN_MERGE = 32;
  public static String timeRegex = "[0-9][0-9][0-9][0-9]-[0-9][0-9]-[0-9][0-9]T[0-9][0-9]:" +
  "[0-9][0-9]:[0-9][0-9]-[0-9][0-9]:[0-9][0-9](.*)";
  
  public static void main(String[] args) {
    if (args.length != 2) {
        System.out.println("Usage: java -cp bin Insertion infile outfile");
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
      timsort(lines, lineCount);
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
    } catch (Exception pe) {
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
  
  // public static void sort(String[] dataStrings) throws ParseException {
  //   //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
  //   for(int i = 1; i < lineCount; i++) {
  //     Scanner line =  new Scanner(lines[i]);
  //     String dateStr = line.next();
  //     LocalDateTime key = getDate(dateStr);
  //     line.close();

  //     int j = i - 1;
      
  //     Scanner jDateScanner = new Scanner(lines[j]);
  //     String jDateStr = jDateScanner.next();
  //     LocalDateTime jDate = getDate(jDateStr);
  //     jDateScanner.close();
      
  //     String keyHolder = lines[i];
  //   }
  // }

  public static void writeOutput(PrintWriter out) {
    for(int i = 0; i < lineCount; i++) {
      out.println(lines[i]);
    }
  }

  public static int minRunLength(int n) {
    assert n >= 0;

    // Becomes 1 if any 1 bits are shifted off
    int r = 0;
    while (n >= MIN_MERGE) {
        r |= (n & 1);
        n >>= 1;
    }
    return n + r;
  }

  // This function sorts array from left index to
  // to right index which is of size atmost RUN
  public static void insertionSort(String[] arr, int left, int right) {
    for (int i = left + 1; i <= right; i++) {
        String temp = arr[i];
        int j = i - 1;
        while (j >= left && compare(arr[j], temp) > 0) { /**arr[j] > temp*/  // CHANGE
            arr[j + 1] = arr[j];
            j--;
        }
        arr[j + 1] = temp;
    }
  }

  // Merge function merges the sorted runs
  public static void merge(String[] arr, int l, int m, int r) {
    // Original array is broken in two parts
    // left and right array
    int len1 = m - l + 1, len2 = r - m;
    String[] left = new String[len1];
    String[] right = new String[len2];
    for (int x = 0; x < len1; x++) {
        left[x] = arr[l + x];
    }
    for (int x = 0; x < len2; x++) {
        right[x] = arr[m + 1 + x];
    }

    int i = 0;
    int j = 0;
    int k = l;

    // After comparing, we merge those two array
    // in larger sub array
    while (i < len1 && j < len2) {
        if (compare(left[i], right[j]) <= 0)/**left[i] <= right[j]**/ {
            arr[k] = left[i];
            i++;
        }
        else {
            arr[k] = right[j];
            j++;
        }
        k++;
    }

    // Copy remaining elements
    // of left, if any
    while (i < len1) {
        arr[k] = left[i];
        k++;
        i++;
    }

    // Copy remaining element
    // of right, if any
    while (j < len2) {
        arr[k] = right[j];
        k++;
        j++;
    }
  }

  // Iterative Timsort function to sort the
  // array[0...n-1] (similar to merge sort)
  public static void timsort(String[] arr, int n) {
    int minRun = minRunLength(MIN_MERGE);
    
    // Sort individual subarrays of size RUN
    for (int i = 0; i < n; i += minRun) {
        insertionSort(arr, i, Math.min((i + MIN_MERGE - 1), (n - 1)));
    }

    // Start merging from size
    // RUN (or 32). It will
    // merge to form size 64,
    // then 128, 256 and so on
    // ....
    for (int size = minRun; size < n; size = 2 * size)
    {

        // Pick starting point
        // of left sub array. We
        // are going to merge
        // arr[left..left+size-1]
        // and arr[left+size, left+2*size-1]
        // After every merge, we
        // increase left by 2*size
        for (int left = 0; left < n;
                              left += 2 * size)
        {

            // Find ending point of left sub array
            // mid+1 is starting point of right sub
            // array
            int mid = left + size - 1;
            int right = Math.min((left + 2 * size - 1),
                                  (n - 1));

            // Merge sub array arr[left.....mid] &
            // arr[mid+1....right]
              if(mid < right)
                merge(arr, left, mid, right);
        }
    }
  }

  private static int compare(String first, String second) {
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