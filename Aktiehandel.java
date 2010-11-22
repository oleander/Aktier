import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.io.File;
import java.util.regex.*;

class Aktiehandel {

  public static void main(String[] args) {
    ArrayList<String> input = new ArrayList<String>();
    StockExchange se = new StockExchange();
    Scanner s;
    Matcher m;
    String buffer;
    
    try {
      /* Om vi inte har några args så försöker vi läsa från system.in */
      if (args.length == 0) {
        s = new Scanner(System.in);
      } else {
        s = new Scanner(new File (args[0]));
      }
      
      /* Kontrollerar varje rad i vårt ingående dokument */
      while (s.hasNextLine()) {
        buffer = s.nextLine();
        
        /* Matchar ut ingående strängar m.h.a regexp */
        m = Pattern.compile("\\s*(.+|[\\s.]+)\\s+(NK|K|S|NS)\\s+((\\d+)\\s+(\\d+)|\\d+)\\s*").matcher(buffer);
        
        /* Kastar ett fel om inget hittas */
        if (!m.find()) {
          throw new GeneralException("Error in input: " + buffer);
        }
        
        /* Kontrollerar vilken sort order som ska utföras
           Eftersom switch-casen inte klarar av strängar så konverterar vi först NS, K o.s.v till en int m.hj.a {hashCode()} 
           2501 => NS 
           2493 => KS
           75   => K 
           83   => S 
        */
        switch (m.group(2).hashCode()) {
          case 2501 : se.updateSell(m.group(1), Integer.valueOf(m.group(4)), Integer.valueOf(m.group(5))); break;
          case 2493 : se.updateBuy(m.group(1), Integer.valueOf(m.group(4)), Integer.valueOf(m.group(5))); break;
          case 75   : se.registerBuy(m.group(1), Integer.valueOf(m.group(3))); break;
          case 83   : se.registerSell(m.group(1), Integer.valueOf(m.group(3))); break;
          default   : System.out.println("Error in input: " + buffer);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println(e.getMessage());
    }
    
    /* Utför våra deal */
    se.trade();

    /* Tömmer alla köper och konstruerar vår loggbok */
    se.endTrading();
    
    /* Printar ut bearbetad data */
    System.out.println(se.getDoneDeals());
    System.out.println("");
    System.out.println(se.getOrderBook());
  }
}