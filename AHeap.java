/** An adaptable heap. Uses ints as keys and strings as values.
 *  @author Jesper Josefsson
 *  @author Linus Oleander
 */



import java.util.*;

public class AHeap {  
  private Comparator comparator;
  private ArrayList<Node> list;
  private int size;
  private HashMap<String,Integer> positionMap;
  
  /**
   * @param comparator the comparator with which the elements are to be compared
   */
  
  public AHeap(Comparator comparator){
    this.comparator = comparator;
  }
  
  public void add(Node n){
    size++;
    list[size] = n;
    positionMap.put(n,size);
  }
  
  public int getSize(){
    return size;
  }
  
  public Node pull(){
    Node output = get(1);
    remove(1);
    return output;
  }
  
  public Node get(int index){
    if (index <= size && index > 0)
      return list[index];
    } else {
      throw new IndexOutOfBoundsException("Error in: get")
    }
  }
  
  public void update(Node old, Node update) throws GeneralException {
    int index = positionMap.get(old);
    list[index] = update;
    bubble(list[index]);
  }
  
  private void delete(int index){
    
  }
  
  private void swap(int a, int b){
    
  }
  
  private int getParentIndex(int a){
    return 3;
  }
  
  private void bubble(int index){
    
  }  
}