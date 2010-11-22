import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.io.File;

class Aktiehandel {

  public static void main(String[] args) {
    ArrayList<ArrayList<String>> input = new ArrayList<ArrayList<String>>();
    final int apa = 10;
    Pattern p = Pattern.compile("(.+|[\\s.]+)\\s+(KS|K|S|NS)\\s+(\\d+\\s+\\d+|\\d+)");
    StockExchange se;
    Matcher m;
    Scanner s;
    String buffer;
    String[] parsed;
    int rowNumber = 1;
    
    try {
      /* Om vi inte har några args så försöker vi läsa från system.in */
      if (args.length == 0) {
        s = new Scanner(System.in);
      } else {
        s = new Scanner(new File (args[0]));
      }
      
      while (s.hasNextLine()) {
        ArrayList<String> row = new ArrayList<String>();
        String a = s.nextLine();
        System.out.println(a);
        m = p.matcher(a);
        if (m.find()) {
          int groups = m.groupCount();
          if (groups == 3 || groups == 4)
            for (int i = 1; i <= groups; i++) {
              row.add(m.group(i));
          } else {
            throw new GeneralException("Your input file has an error on line: " + rowNumber);
          }
        }
        input.add(row);
        rowNumber++;
      }
      
      System.out.println(input);
      
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    
  }
}