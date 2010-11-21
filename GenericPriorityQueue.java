import java.util.*;

class GenericPriorityQueue<T> {
  public static final boolean DESC = true;
  public static final boolean ASC = false;
  
  private GenericAdaptableHeap<T> heap;
  
  public GenericPriorityQueue(boolean desc){
    this.heap = new GenericAdaptableHeap<T>(new HeapComparator(desc));
  }
  
  public GenericNode pull(){
    return heap.pull();
  }
  
  public void removeMin() {
    heap.removeMin();
  }
  
  public void add(T value, int key){
    this.heap.add(new GenericNode<T>(value,key));
  }
  
  public void update(T value, int oldKey, int newKey){
    this.heap.update(new GenericNode<T>(value,oldKey), newKey);
  }
  
  public int getSize(){
    return this.heap.getSize();
  }
  
  public String toString() {
    return this.heap.toString();
  }
  
  public GenericNode peek() {
    return heap.peek();
  }
  
  public boolean isEmpty() {
    return heap.isEmpty();
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
      int value = a.compareTo(b);
      return this.desc ? value : value*(-1);
    }
  }
  
}