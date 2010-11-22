import junit.framework.*;
import java.util.*;

public class TestPriorityQueue extends TestCase {
  PriorityQueue pq;
  
  public TestPriorityQueue(){
    this.pq = new PriorityQueue(PriorityQueue.DESC);
  }
  
  public void testAddAndPull(){
    this.pq = new PriorityQueue(PriorityQueue.ASC);
    
    /* Skapar 10 test-noder */
    for(int n = 0; n < 10; n++){
      this.pq.add("My super value: " + n, n);
    }
    
    /* Första värdet i PQ-listan borde ha en nyckel av värdet noll */
    assertEquals(0, this.pq.pull().getKey());
    
    /* Första värdet i listan bör nu inte längre vara noll */
    assertNotSame(0, this.pq.pull().getKey());
    
    
    this.pq = new PriorityQueue(PriorityQueue.DESC);
    
    for(int n = 0; n <= 10; n++){
      this.pq.add("My super value: " + n, n);
    }

    /* Första värdet i PQ-listan borde ha en nyckel av värdet tio */
    assertEquals(10, this.pq.pull().getKey());

    /* Första värdet i listan bör nu inte längre vara tio */
    assertNotSame(10, this.pq.pull().getKey());
    
    this.pq = new PriorityQueue(PriorityQueue.DESC);
    
    /* Om inga värden läggs till så borde kön vara tom */
    assertNull(this.pq.pull());    
  }
  
  public void testSize(){
    Random random = new Random();
    this.pq = new PriorityQueue(PriorityQueue.DESC);
    int stop = random.nextInt(30);
    for(int n = 0; n < stop; n++){
      this.pq.add("My super value: " + n, n);
    }
    
    /* Kontrollerar att längden på listan är samma som antalet element som vi nyss slängde in */
    assertEquals(this.pq.getSize(), stop);
  }
  
  public void testUpdate(){
    this.pq = new PriorityQueue(PriorityQueue.ASC);
    for(int n = 0; n < 10; n++){
      this.pq.add("My super value: " + n, n);
    }
    
    this.pq.update("My super value: 3", 3, -10);
    
    /* Kontrollerar att vårt nya värde finns i listan */
    assertEquals(this.pq.pull().getKey(), -10);
    
    /* Nu borde inte värdet finnas kvar längre, eftersom vi plockade bort värdet från kön på raden över */
    assertNotSame(this.pq.pull().getKey(), -10);
    
    this.pq = new PriorityQueue(PriorityQueue.DESC);
    try{
      this.pq.update("My super value: 3", 3,-10);
      fail("Should throw GeneralException");
    } catch(GeneralException e){}
  }
  
  public static void main(String[] args){
    TestPriorityQueue pq = new TestPriorityQueue();
    pq.testAddAndPull();
    pq.testSize();
    pq.testUpdate();
  }
  
}