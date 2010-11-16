import java.util.*;

class AHeap {  
  private Comparator comparator;
  private ArrayList<Node> list;
  private int size;
  private HashMap<String,Integer> positionMap;
  
  public AHeap(Comparator comparator){
    this.comparator = comparator;
  }
  
  public void add(Node n){
    
  }
  
  public Node pull(){
    return new Node("Testing", 3);
  }
  
  private Node get(int index){
    return new Node("Testing", 3);
  }
  
  public void update(Node old, Node update) throws GeneralException {
    
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