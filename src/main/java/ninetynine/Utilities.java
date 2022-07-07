package ninetynine;

/**
 * Utility functions
 * 
 * @author Roger Jaffe
 * @version 2018-11-29
 */
public class Utilities {

  /**
   * Gets the new score when playing the provided deck and the old score
   * @param deck Deck to check
   * @param total Current total
   * @return The new score
   */
  public static int getNewTotal(Deck deck, int total) {
    if (deck.getCardCount() == 1) {
      Card card = deck.getCard(0);
      switch(card.getRank()) {
        case Constants.ACE: return total + 1;
        case Constants.TWO:
        case Constants.THREE: 
        case Constants.FOUR: 
        case Constants.FIVE: 
        case Constants.SIX: 
        case Constants.SEVEN:
        case Constants.EIGHT: 
        case Constants.NINE: 
        case Constants.TEN: 
          return total + (new Integer(card.getRank()));
        case Constants.QUEEN: return total - 10;
        case Constants.KING: return Constants.NINETY_NINE;
      }      
    }
    return total;
  }
  
  /**
   * Checks if the provided deck and game total represents a legal move
   * @param deck Deck whose cards should be checked for a legal move
   * @param total Current game total
   * @return True if the move is legal
   */
  public static boolean isLegalMove(Deck deck, int total) {
    boolean retValue = false;
    if (deck.getCardCount() == 2) {
      return (deck.getCard(0).getRank().equals(deck.getCard(1).getRank()));
    } else if (deck.getCardCount() == 1) {
      Card card = deck.getCard(0);
      switch(card.getRank()) {
        case Constants.JACK:
        case Constants.QUEEN:
        case Constants.KING:
          return true;
        case Constants.ACE:
          return total + 1 <= Constants.NINETY_NINE;
        case Constants.TWO:
        case Constants.THREE: 
        case Constants.FOUR: 
        case Constants.FIVE: 
        case Constants.SIX: 
        case Constants.SEVEN:
        case Constants.EIGHT: 
        case Constants.NINE: 
        case Constants.TEN: 
          return total + (new Integer(card.getRank())) <= Constants.NINETY_NINE;
      }
    }
    return false;
  }
}
