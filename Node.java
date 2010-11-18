class Node {
  private int key;
  private String value;
  
  public Node(String value, int key){
    this.value = value;
    this.key = key;
  }
  
  public int getKey(){
    return this.key;
  }
  
  public String getValue(){
    return this.value;
  }
  
  public void setValue(String value){
    this.value = value;
  }
  
  public void setKey(int key){
    this.key = key;
  }
  
  public String toString(){
    return "Tjohoo";
  }
}