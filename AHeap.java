/** En uppdaterarbar heap. Intar som nyckel och strängar som värde, tillsvidare.
 *  @author Jesper Josefsson
 *  @author Linus Oleander
 */

import java.util.*;

public class AHeap {  
  private Comparator comparator;
  private ArrayList<Node> list;
  private int size;
  private HashMap<Node,Integer> positionMap;
  
  /**
   * @param comparator komparatorn som bestämmer vilket element är högst prioriterat
   */
  
  public AHeap(Comparator comparator){
    this.comparator = comparator;
    size = 0;
  }
  
  public void add(Node n){
    size++;
    list.set(size, n);
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
    if (index <= size && index > 0) {
      return list.get(index);
    } else {
      throw new IndexOutOfBoundsException("Error in: get");
    }
  }
  
  public void update(Node old, Node update) throws GeneralException {
    if (positionMap.get(old) == null) {
      throw new GeneralException("Error in update: Node not found!");
    } else {
    int index = positionMap.get(old);
    list.set(index, update);
    bubble(list.get(index));
    }
  }
  
  private void delete(int index){
    /* Kopierar den sista noden till det givna indexet */
    Node lastNode = list.get(size);
    list.set(index, lastNode);
    
    /* Sparar undan den flyttade nodens index i positionsmappen */
    positionMap.put(lastNode,index);
    size--;
    
    /* Ser till att den flyttade noden ligger på rätt ställe */
    bubbleDown(lastNode);
    bubbleUp(lastNode);
  }
  
  private void swap(Node a, Node b){
    /* Tar fram index för vardera nod */
    int indexA = positionMap.get(a);
    int indexB = positionMap.get(b);
    
    /* sparar ner noderna på sina nya positioner */
    list.set(indexB, a);
    list.set(indexA, b);
    
    /* uppdaterar positionsmappen */
    positionMap.put(a,indexB);
    positionMap.put(b,indexA);
  }
  
  private int getParentIndex(int childIndex){
    return childIndex/2;
  }
  
  private Node getParent(Node n) {
    int index = positionMap.get(n);
    return list.get(index/2);
  }
  
  private void bubbleIndex(int index){
    bubble(list.get(index));
  } 
  
  private void bubble(Node n) {
    bubbleUp(n);
    bubbleDown(n);
  }
  
  /* Flyttar en nod uppåt i heapen till rätt position */
  private void bubbleUp(Node n) {
    /* Om n är root avbryter vi */
    if (list.get(1).equals(n)) {
      return;
    }
    
    /* Annars tar vi fram n:s förälder och kollar heapvillkor
     * Om villkoret ej uppfylls swappar vi och kör bubbleUp en gång till */
    Node parent = this.getParent(n);
    if (compareNodes(parent,n) > 0) {
      swap(parent,n);
      bubbleUp(n);
    }
  }
  
  private void bubbleDown(Node n) {
    if (!hasChildren(n)) return;
    
    int thisKey = n.getKey();
    int leftChildKey = leftChild(n).getKey();
    
    /* Om noden inte har ett högerbarn kollar vi om vänsterbarnet uppfyller heapvillkoret */
    if (!hasRightChild(n)) {
      /* Om villkoret inte uppfylls swappar vi, annars vet vi att vi är klara */
      if (comparator.compare(leftChildKey, thisKey) < 0) {
        swap(n,leftChild(n));
        bubbleDown(n);
      } else {
        return;
      }
    /* Om noden har ett högerbarn jämför vi båda barn med n */
    } else {
          int rightChildKey = rightChild(n).getKey();
          /* Om något av barnen har lägre nyckel än n ska vi swappa */
          if ((comparator.compare(leftChildKey, thisKey) < 0) || (comparator.compare(rightChildKey, thisKey) < 0)){
            Node swapNode = comparator.compare(leftChildKey,rightChildKey) < 0 ? leftChild(n) : rightChild(n);
            swap(n,swapNode);
            bubbleDown(n);
          } else {
            return;
          }
    }
    
  }
  
  private boolean hasChildren(Node n) {
    return positionMap.get(n)*2 <= size;
  }
  
  private boolean hasRightChild(Node n) {
    return positionMap.get(n)*2 + 1 <= size;
  }
  
  private Node leftChild(Node n) {
    int index = positionMap.get(n);
    return list.get(index*2);
  }
  
  private Node rightChild(Node n) {
    int index = positionMap.get(n);
    return list.get(index*2 + 1);
  }
  
  private int compareNodes(Node a, Node b) {
    return this.comparator.compare(a.getKey(),b.getKey());
  }
}