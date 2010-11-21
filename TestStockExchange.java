import junit.framework.*;

class TestStockExchange extends TestCase {
  
  /* Testar att det skapas orderböcker vid rätt tillfälle */
  public void testOrderBookAndDoneDeals(){
    
    /* En aktiehandel där alla ordrar genomförs bör ha en tom orderbok och en korrekt doneDeals-lista */
    StockExchange se = new StockExchange();
    se.registerBuy("Test buyer", 10);
    se.registerSell("Test seller", 10);
    se.performExchanges();
    String doneDeals = "Test buyer köper från Test seller för 10";
    assertEquals("", se.getOrderBook());
    assertEquals(doneDeals, se.getDoneDeals());
    
    /* En aktiehandel där ingen order utförs bör ha en orderbok och en tom doneDeals-lista */
    StockExchange se2 = new StockExchange();
    se2.registerBuy("Test buyer", 8);
    se2.registerSell("Test seller", 10);
    String orderBook = "Orderbok:\nSäljare: Test buyer 8\nKöpare: Test seller 10";
    assertEquals(orderBook, se2.getOrderBook()); 
    assertEquals("", se2.getDoneDeals());
  }
  
  public void testUpdate() {    
    /* Vi börjar med en lista med ett bud som går inte igenom och förändrar den så att budet går igenom */
    BasicHarness h = new BasicHarness();
    h.se.updateSell("Test seller", 10, 1);
    h.se.performExchanges();
    String doneDeals = "Test buyer köper från Test seller för 1";
    assertEquals(doneDeals, h.se.getDoneDeals());
    
    /* Testar updateBuy på samma sätt */
    BasicHarness h2 = new BasicHarness();
    h2.se.updateBuy("Test buyer", 1, 10);
    h2.se.performExchanges();
    String doneDeals2 = "Test buyer köper från Test seller för 10";
    assertEquals(doneDeals2, h2.se.getDoneDeals());
    
    
  }
  
  /* En grundläggande fulharness */
  private class BasicHarness {
    public StockExchange se;
    public BasicHarness() {
      se = new StockExchange();
      se.registerBuy("Test buyer", 1);
      se.registerSell("Test seller", 10);
    }
  }
  
  public static void main(String[] args) {
    TestStockExchange test = new TestStockExchange();
    test.testOrderBookAndDoneDeals();
  }
}