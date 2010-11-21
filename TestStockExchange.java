import junit.framework.*;

class TestStockExchange extends TestCase {
  
  
  /* Testar att det skapas orderböcker vid rätt tillfälle */
  public void testOrderBook(){
    
    /* En aktiehandel där alla ordrar genomförs bör ha en tom orderbok */
    StockExchange se = new StockExchange();
    se.registerBuy("Test buyer", 10);
    se.registerSell("Test seller", 10);
    se.performExchanges();
    assertEquals("", se.getOrderBook());
    
    /* En aktiehandel där ingen order utförs bör ha en orderbok */
    StockExchange se2 = new StockExchange();
    se2.registerBuy("Test buyer", 8);
    se2.registerSell("Test seller", 10);
    String orderBook = "Orderbok:\nSäljare: Test buyer 8\nKöpare: Test seller 10";
    assertEquals(orderBook, se2.getOrderBook());
    
  }
}