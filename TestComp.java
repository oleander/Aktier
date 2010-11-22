import java.util.*;
public public class TestComp implements Comparator<Integer> {
  public int compare(Integer a, Integer b) throws ClassCastException {
    return ((Comparable<Integer>) a).compareTo(b);
  }
}