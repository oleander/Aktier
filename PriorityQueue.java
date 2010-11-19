import java.util.*;

class PriorityQueue {
  public static final boolean DESC = true;
  public static final boolean ASC = false;
  
  private AHeap heap;
  
  public PriorityQueue(boolean desc){
    this.heap = new AHeap(new HeapComparator(desc));
  }
  
  public Node pull(){
    return new Node("Hejsan", 3);
  }
  
  public void add(String name, int key){
    
  }
  
  public void update(String name, int key){
    
  }
  
  public int getSize(){
    return 19;
    //this.heap.getSize();
  }
  
  /*
   * HeapComparator accepterar ett argument som bestämmer huruvida elementen ordnas i stigande eller i fallande ordning.
   */
  private class HeapComparator implements Comparator<Integer> {
    private boolean desc;
    public HeapComparator(boolean desc) {
      this.desc = desc;
    }
    public int compare(Integer a, Integer b) throws ClassCastException {
      int value = ((Comparable<Integer>) a).compareTo(b);
      return this.desc ? value : value*-1;
    }
  }
  
}