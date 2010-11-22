public class Node {
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
    return "value: " + this.value + ", key: " + Integer.toString(this.key);
  }
  
  public boolean equals(Object other) {
    if (other == null) {
      return false;
    } else if (this == other) {
      return true;
    } else if (other instanceof Node) {
      Node otherNode = (Node) other;
      return (this.getKey() == otherNode.getKey() && this.getValue().equals(otherNode.getValue()));
    }
    return false;
  }
  
  public int hashCode() {
    return key + value.hashCode();
  }
}