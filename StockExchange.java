/* En virtuell aktiebörs. Tar emot sälj- och köpbud, utför handel och returnerar resultat */

class StockExchange {
  private PriorityQueue buyers;
  private PriorityQueue sellers;
  
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
    sellers.update(String name, int oldPrice, int newPrice);
  }
  
  /* Förändra en köporder */
  public void updateBuy(String name, int oldPrice, int newPrice) {
    buyers.update(String name, int oldPrice, int newPrice);
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
  
  
}