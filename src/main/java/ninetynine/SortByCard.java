package ninetynine;

import java.util.Comparator;

/**
 * Comparator that sorts by card rank only
 * @author Roger Jaffe
 * @version 2018-11-29
 */
public class SortByCard implements Comparator<Card> {
  
  @Override
  public int compare(Card a, Card b) {
    return a.getRankInt() - b.getRankInt();
  }

}
