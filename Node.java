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
  
  public boolean equals(Object other) {
    if (other == null) {
      return false;
    } else if (this == other) {
      return true;
    } else if (other instanceof Node) {
      return (this.getKey() == other.getKey() && this.getValue() == other.getValue());
    }
  }
  
  public int hashCode() {
    return key + value.hashCode();
  }
}