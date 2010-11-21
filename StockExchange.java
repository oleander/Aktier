/* En virtuell aktiebörs. Tar emot sälj- och köpbud, utför handel och returnerar resultat */
import java.util.ArrayList;

class StockExchange {
  private PriorityQueue buyers;
  private PriorityQueue sellers;
  private ArrayList<Deal> deals;
  
  public StockExchange() {
    buyers = new PriorityQueue(PriorityQueue.DESC);
    sellers = new PriorityQueue(PriorityQueue.ASC);
  }
  
  /* Registrera en säljorder */
  public void registerSell(String name, int price) {
    sellers.add(name,price);
  }
  
  /* Registrera en köporder */
  public void registerBuy(String name, int price) {
    buyers.add(name,price);
  }
  
  /* Förändra en säljorder */
  public void updateSell(String name, int oldPrice, int newPrice) {
    sellers.update(name, oldPrice, newPrice);
  }
  
  /* Förändra en köporder */
  public void updateBuy(String name, int oldPrice, int newPrice) {
    buyers.update(name, oldPrice, newPrice);
  }
  
  /* Utför alla köp som kan utföras. */
  public void performExchanges(){
  
  }
  
  /* Returnerar sträng med alla köp som utfördes */
  public String getDoneDeals() {
    return "done deals";
  }
  
  /* Returnerar sträng med alla registrerade ordrar som inte utfördes */
  public String getOrderBook() {
    return "order book";
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
      return buyer.getValue() + " köper från " + seller.getValue() + " för " + seller.getKey() + " kr";
    }
  }
  
  
}