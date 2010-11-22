import junit.framework.*;

class TestStockExchange extends TestCase {
  
  /* Testar att det skapas orderböcker vid rätt tillfälle */
  public void testOrderBookAndDoneDeals(){
    
    /* En aktiehandel där alla ordrar genomförs bör ha en tom orderbok och en korrekt doneDeals-lista */
    StockExchange se = new StockExchange();
    se.registerBuy("Test buyer", 10);
    se.registerSell("Test seller", 10);
    se.trade();
    se.endTrading();
    String doneDeals = "Test buyer köper från Test seller för 10 kr";
    assertEquals("", se.getOrderBook());
    assertEquals(doneDeals, se.getDoneDeals());
    
    /* En aktiehandel där ingen order utförs bör ha en orderbok och en tom doneDeals-lista */
    StockExchange se2 = new StockExchange();
    se2.registerBuy("Test buyer", 8);
    se2.registerSell("Test seller", 10);
    se2.trade();
    se2.endTrading();
    
    assertTrue(se2.getOrderBook().indexOf("Test seller 10") > 0);
    assertTrue(se2.getOrderBook().indexOf("Test buyer 8") > 0);
    assertEquals("", se2.getDoneDeals());
  }
  
  public void testUpdate() {    
    /* Vi börjar med en lista med ett bud som går inte igenom och förändrar den så att budet går igenom */
    BasicHarness h = new BasicHarness();
    h.se.updateSell("Test seller", 10, 1);
    h.se.trade();
    h.se.endTrading();
    String doneDeals = "Test buyer köper från Test seller för 1 kr";
    assertEquals(doneDeals, h.se.getDoneDeals());
    
    /* Testar updateBuy på samma sätt */
    BasicHarness h2 = new BasicHarness();
    h2.se.updateBuy("Test buyer", 1, 10);
    h2.se.trade();
    h2.se.endTrading();
    String doneDeals2 = "Test buyer köper från Test seller för 10 kr";
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
    test.testUpdate();
  }
}