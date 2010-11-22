import java.util.ArrayList;

/**
 * En virtuell aktiebörs. Tar emot sälj- och köpbud, utför handel och returnerar resultat.
 * Först registras köp- och säljordrar, samt förändrade ordrar.
 * Sedan utförs handel med hjälp av metoden "trading".
 * När trading anropas utförs alla köp som är möjliga och införs i listan över utförda köp.
 * Efter trading är resten av ordrarna kvar i kön och man kan således registrera nya ordrar och utföra mera trading.
 * När handeln är avslutat anropas endTrading.
 * Då överförs alla kvarvarande ordrar till orderboken.
 */
 
public class StockExchange {
  private PriorityQueue buyers;
  private PriorityQueue sellers;
  private ArrayList<Deal> deals;
  private String orderBook;
  
  public StockExchange() {
    buyers = new PriorityQueue(PriorityQueue.DESC);
    sellers = new PriorityQueue(PriorityQueue.ASC);
    deals = new ArrayList<Deal>();
    orderBook = "";
  }
  
  /** 
   * Registrera en säljorder 
   * @param name Namnet på säljaren
   * @param price Priset
   */
  public void registerSell(String name, int price) {
    sellers.add(name,price);
  }
  
  /**
   * Registrera en köporder
   * @param name Namnet på säljaren
   * @param price Priset
   */
  public void registerBuy(String name, int price) {
    buyers.add(name,price);
  }
  
  /** 
   * Förändra en säljorder
   * @param name Namnet på säljaren
   * @param oldPrice Det gamla priset
   * @param newPrice Det nya priset
   */
  public void updateSell(String name, int oldPrice, int newPrice) {
    sellers.update(name, oldPrice, newPrice);
  }
  
  /** 
   * Förändra en köporder
   * @param name Namnet på köparen
   * @param oldPrice Det gamla priset
   * @param newPrice Det nya priset
   */
  public void updateBuy(String name, int oldPrice, int newPrice) {
    buyers.update(name, oldPrice, newPrice);
  }
  
  /**
   * Utför alla köp som kan utföras och sparar dem. 
   * Kön innehåller fortfarande alla ordrar som inte uppfyllts.
   * Listan med uförda ordrar kommer man åt med getDoneDeals()
   */
  public void trade(){
    Node seller;
    Node buyer;
    
    /* Så länge köpbudet är högre eller lika med säljbudet och köp- eller säljkön inte är tom, 
     * så fortsätter vi att para ihop deals */
    do {
      seller = sellers.peek();
      buyer = buyers.peek();
      if (buyer.getKey() >= seller.getKey()) {
        deals.add(new Deal(buyer, seller));
        buyers.pull();
        sellers.pull();
      }
    } while (buyer.getKey() >= seller.getKey() && !(buyers.isEmpty() || sellers.isEmpty()));
  }
  
  /** 
   * När vi avslutar handlandet töms köerna och vi konstruerar orderboken. 
   * Orderbooken kommer man åt med getOrderBook()
   */
  public void endTrading() {
    ArrayList<Node> buyersLeft = new ArrayList<Node>();
    ArrayList<Node> sellersLeft = new ArrayList<Node>();
    Node n;
    Node lastNode;
    
    /* Om båda köerna är tomma behöver vi inte konstruera en orderbok */
    if (buyers.isEmpty() && sellers.isEmpty()) {
      return;
    }
  
    /* Skapar listor av alla kvarvarande ordrar */
    while (!sellers.isEmpty()) {
      sellersLeft.add(sellers.pull());
    }
    while (!buyers.isEmpty()) {
      buyersLeft.add(buyers.pull());
    }
    
    /* Sparar undan storleken på listorna */
    int buyersSize = buyersLeft.size();
    int sellersSize = sellersLeft.size();
    
    orderBook = "Orderbok:\nSäljare: ";
    
    /* Om det finns oavslutade köpbud lägger vi till dem till orderboken */
    if (sellersSize > 0) {
      /* Lägger in alla säljbud utom det sista i orderboken */
      for (int i = 0; i < sellersSize - 1; i++) {
        n = sellersLeft.get(i);
        orderBook += n.getValue() + " " + n.getKey() + ", ";
      }
      /* Lägger till det sista budet i orderboken */
      lastNode = sellersLeft.get(sellersSize - 1);
      orderBook += lastNode.getValue() + " " + lastNode.getKey();
    }
    
    orderBook += "\nKöpare: ";
    
    /* Om det finns oavslutade köpbud lägger vi till dem i orderboken */
    if (buyersSize > 0) {
      /* Lägger in alla köpbud, utom det sista */
      for (int i = 0; i < buyersSize - 1; i++) {
        n = buyersLeft.get(i);
        orderBook += n.getValue() + " " + n.getKey() + ", ";
      }
      /* Lägger till det sista budet i orderboken */
      lastNode = buyersLeft.get(buyersSize - 1);
      orderBook += lastNode.getValue() + " " + lastNode.getKey();
    }
  }
  
  /**
   * Returnerar sträng med alla köp som utfördes.
   * @return Alla utförda köp
   */
  public String getDoneDeals() {
    String output = "";
    for (Deal d : deals) {
      output += d + "\n";
    }
    return output.trim();
  }
  
  /**
   * Returnerar sträng med alla registrerade ordrar som inte utfördes
   * @return Orderboken 
   */
  public String getOrderBook() {
    return orderBook;
  }
  
  /* Inkapslar en genomförd affär */
  private class Deal{
    private Node buyer;
    private Node seller;
    
    public Deal(Node buyer, Node seller) {
      this.buyer = buyer;
      this.seller = seller;
    }
    
    public String toString() {
      return buyer.getValue() + " köper från " + seller.getValue() + " för " + buyer.getKey() + " kr";
    }
  }
  
  
}