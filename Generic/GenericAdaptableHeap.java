/** En uppdaterarbar heap. Intar som nyckel och strängar som värde, tillsvidare.
 *  @author Jesper Josefsson
 *  @author Linus Oleander
 */

import java.util.*;

public class GenericAdaptableHeap<T>{  
  private Comparator comparator;
  private ArrayList<GenericNode<T>> list;
  private int size;
  private HashMap<GenericNode<T>,Integer> positionMap;
  
  /**
   * @param comparator komparatorn som bestämmer vilket element är högst prioriterat
   */
  
  public GenericAdaptableHeap(Comparator comparator){
    this.comparator = comparator;
    this.size = 0;
    this.list = new ArrayList<GenericNode<T>>();
    this.list.add(new GenericNode<T>());
    this.positionMap = new HashMap<GenericNode<T>,Integer>();
  }
  
  public void add(GenericNode n){
    size++;
    if(this.list.size() <= size){
     this.list.add(n);
    } else {
      list.set(size, n);
    }
    positionMap.put(n,size);
    bubbleUp(n);
  }
  
  public int getSize(){
    return size;
  }
  
  public boolean isEmpty() {
    return size == 0;
  }
  
  public GenericNode pull(){
    if(this.size == 0) return null;
    
    GenericNode output = get(1);
    delete(1);
    return output;
  }
  
  public GenericNode get(int index){
    if (index <= size && index > 0) {
      return list.get(index);
    } else {
      throw new IndexOutOfBoundsException("Error in: get");
    }
  }
  
  public GenericNode peek() {
    return get(1);
  }
  
  public void update(GenericNode old, int key) throws GeneralException {
    if (positionMap.get(old) == null) {
      throw new GeneralException("Error in update: GenericNode not found!");
    } else {
    int index = positionMap.get(old);
    this.positionMap.remove(old);
    this.list.get(index).setKey(key);
    this.positionMap.put(this.list.get(index),index);
    this.bubble(list.get(index));
    }
  }
  
  private void delete(int index){
    /* Kopierar den sista noden till det givna indexet */
    GenericNode lastNode = list.get(size);
    list.set(index, lastNode);
    
    /* Sparar undan den flyttade nodens index i positionsmappen */
    positionMap.put(lastNode,index);
    size--;
    
    /* Ser till att den flyttade noden ligger på rätt ställe */
    bubbleDown(lastNode);
    bubbleUp(lastNode);
  }
  
  public void removeMin(){
    this.delete(1);
  }
  
  private void swap(GenericNode a, GenericNode b){
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
  
  private GenericNode getParent(GenericNode n) {
    int index = positionMap.get(n);
    return list.get(index/2);
  }
  
  private void bubbleIndex(int index){
    bubble(list.get(index));
  } 
  
  private void bubble(GenericNode n) {
    bubbleUp(n);
    bubbleDown(n);
  }
  
  /* Flyttar en nod uppåt i heapen till rätt position */
  private void bubbleUp(GenericNode n) {
    /* Om n är root avbryter vi */
    if (list.get(1).equals(n)) {
      return;
    }
    
    /* Annars tar vi fram n:s förälder och kollar heapvillkor
     * Om villkoret ej uppfylls swappar vi och kör bubbleUp en gång till */
    GenericNode parent = this.getParent(n);
    if (compareNodes(parent,n) > 0) {
      swap(parent,n);
      bubbleUp(n);
    }
  }
  
  private void bubbleDown(GenericNode n) {
    if (!hasChildren(n)) return;
    
    int thisKey = n.getKey();
    int leftChildKey = leftChild(n).getKey();
    
    /* Om noden inte har ett högerbarn kollar vi om vänsterbarnet uppfyller heapvillkoret */
    if (!hasRightChild(n)) {
      /* Om villkoret inte uppfylls swappar vi och bubblar en gång till, annars vet vi att vi är klara */
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
            GenericNode swapNode = comparator.compare(leftChildKey,rightChildKey) < 0 ? leftChild(n) : rightChild(n);
            swap(n,swapNode);
            bubbleDown(n);
          } else {
            return;
          }
    }
    
  }
  
  private boolean hasChildren(GenericNode n) {
    return positionMap.get(n)*2 <= size;
  }
  
  private boolean hasRightChild(GenericNode n) {
    return positionMap.get(n)*2 + 1 <= size;
  }
  
  private GenericNode leftChild(GenericNode n) {
    int index = positionMap.get(n);
    return list.get(index*2);
  }
  
  private GenericNode rightChild(GenericNode n) {
    int index = positionMap.get(n);
    return list.get(index*2 + 1);
  }
  
  private int compareNodes(GenericNode a, GenericNode b) {
    return this.comparator.compare(a.getKey(),b.getKey());
  }
  
  public String toString() {
    return this.list.toString();
  }
}