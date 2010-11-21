import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;

class Aktiehandel {
  public static void main(String[] args) {
    ArrayList<String> input = new ArrayList<String>();
    Scanner s;
    try {
      /* Om vi inte har några args så försöker vi läsa från system.in */
      if (args.length == 0) {
        s = new Scanner(System.in);
      } else {
        s = new Scanner(new File (args[0]));
      }
    
      while (s.hasNextLine()) {
        input.add(s.nextLine());
      }
      System.out.println(input);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}