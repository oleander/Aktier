import junit.framework.*;
import java.util.*;

public class TestAHeap extends TestCase {
  private AHeap heap;
  private Node[] nodes = new Node[10];
  
  public TestAHeap(){
    /* Skapar en testheap som är global över hela applikationen */
    this.heap = new AHeap(new TestComp());
    
    /* Skapar 10 exempel-noder som vi kan testa mot */
    for(int n = 0; n < 10; n++){
      this.nodes[n] = new Node("My node nr " + Integer.toString(n), n);
    }
  }
  
  public void testAdd() {
      this.heap = new AHeap(new TestComp());
      
      /* Lägger till första noden i listan */
      this.heap.add(this.nodes[0]);
      
      /* Hämtar första noden, vilket då bör vara samma node som vi nyss slängde in */
      assertEquals(this.heap.pull(), this.nodes[0]);
  }
  
  public void testSize() {
      this.heap = new AHeap(new TestComp());
      
      /* Lägger in alla noder i heap:en */
      for(Node node : this.nodes){
        this.heap.add(node);
      }
      
      /* Vi bör nu se alla noder som vi nyss adderade */
      assertEquals(this.heap.getSize(), this.nodes.length);
  }
  
  public void testGet(){
    this.heap = new AHeap(new TestComp());

    /* Lägger in alla noder i heap:en */
    for(Node node : this.nodes){
      this.heap.add(node);
    }

    /* Vi bör nu se alla noder som vi nyss adderade */
    for(int n = 0, stop = this.nodes.length; n < stop; n++){
      assertEquals(this.nodes[n], this.heap.get(n + 1));
    }
    
  }
  
  public void testUpdate(){
    this.heap = new AHeap(new TestComp());

    /* Lägger in alla noder i heap:en */
    for(Node node : this.nodes){
      this.heap.add(node);
    }
    Node aNode = new Node("My new non existing heap node", 12300);
    
    /* Försöker uppdatera en node som inte finns */
    try{
      this.heap.update(aNode, -2000);
      this.fail("Should raise an GeneralException");
    } catch(GeneralException e){}
    
    this.heap.add(aNode);
        
    this.heap.update(aNode, -10);
    
    assertEquals(this.heap.pull().getKey(), -10);
  }
  
  public void testPull(){
    this.heap = new AHeap(new TestComp());
    
    /* Hämtar vi första noden i heap:en så bör den returnera ett tomt värde */
    assertEquals(this.heap.pull(), null);
    
    this.heap.add(this.nodes[0]);
    
    /* Noden vi la till på raden över bör nu finnas i heapen */
    assertEquals(this.heap.pull(), this.nodes[0]);
    
    /* Nu bör den dock vara tom */
    assertEquals(this.heap.pull(), null);
    
    /* Lägger in alla noder i heap:en */
    for(Node node : this.nodes){
      this.heap.add(node);
    }
    
    /* Pull bör returnera den första noden */
    assertEquals(this.heap.pull(), this.nodes[0]);
    
    /* Noden som ligger överst bör inte längre vara första noden */
    assertNotSame(this.heap.pull(), this.nodes[0]);
    
  }
  
  public static void main(String[] args){
    TestAHeap testAHeap = new TestAHeap();
    testAHeap.testAdd();
    testAHeap.testSize();
    testAHeap.testGet();
    testAHeap.testUpdate();
    testAHeap.testPull();
  }
}