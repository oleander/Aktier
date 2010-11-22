import java.util.*;

class PriorityQueue {
  public static final boolean DESC = true;
  public static final boolean ASC = false;
  
  private AHeap heap;
  
  public PriorityQueue(boolean desc){
    this.heap = new AHeap(new HeapComparator(desc));
  }
  
  public Node pull(){
    return this.heap.pull();
  }
  
  public void add(String name, int key){
    this.heap.add(new Node(name,key));
  }
  
  public void update(String name, int key){
    
  }
  
  public int getSize(){
    return this.heap.getSize();
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
      System.out.println(a);
      System.out.println(b);
      System.out.println(this.desc);
      int value = ((Comparable<Integer>) a).compareTo(b);
      System.out.println((this.desc ? value : value*-1));
      System.out.println(!this.desc ? value : value*-1);
      System.out.println("-----------------------");
      return this.desc ? value : value*-1;
    }
  }
  
}