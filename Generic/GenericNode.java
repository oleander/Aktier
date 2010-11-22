class GenericNode<T> {
  private int key;
  private T value;
  
  public GenericNode(T value, int key){
    this.value = value;
    this.key = key;
  }
  
  public GenericNode() {
    value = null;
    key = -100000;
  }
  
  public int getKey(){
    return this.key;
  }
  
  public T getValue(){
    return this.value;
  }
  
  public void setValue(T value){
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