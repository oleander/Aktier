/** En uppdaterarbar heap. Intar som nyckel och strängar som värde, tillsvidare.
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
   * @param comparator komparatorn som bestämmer vilket element är högst prioriterat
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
    delete(1);
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
    if (positionMap.get(old) == null) {
      throw new GeneralException("Error in update: Node not found!")
    } else {
    int index = positionMap.get(old);
    list[index] = update;
    bubble(list[index]);
    }
  }
  
  private void delete(int index){
    
  }
  
  private void swap(Node a, Node b){
    /* Tar fram index för vardera nod */
    int indexA = positionMap.get(a);
    int indexB = positionMap.get(b);
    
    /* sparar ner noderna på sina nya positioner */
    list[indexB] = a;
    list[indexA] = b;
    
    /* uppdaterar positionsmappen */
    positionMap.put(a,indexB);
    positionMap.put(b,indexA);
  }
  
  private int getParentIndex(int a){
    return a/2;
  }
  
  private Node getParent(Node n) {
    int index = positionMap.get(n);
    return list[index/2];
  }
  
  private void bubbleIndex(int index){
    bubble(positionMap.get(index));
  }  
}