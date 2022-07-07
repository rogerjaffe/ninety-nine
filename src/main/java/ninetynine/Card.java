package ninetynine;

import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Playing card abstraction
 * 
 * @author Roger Jaffe
 * @version 2018-11-29
 */
public class Card
{
  // instance variables - replace the example below with your own
  private final String rank;
  private final String suit;

  /**
   * Constructor for objects of class Card
   * @param rank Card rank using Constants static constants
   * @param suit Card suit using Constants static constants
   */
  public Card(String rank, String suit)
  {
    this.rank = rank;
    this.suit = suit;
  }
  
  /**
   * Get the card rank
   * @return Card rank
   */
  public String getRank() {
    return this.rank;
  }
  
  /**
   * Get the card rank as an integer; J=11, Q=12, K=13 
   * to allow for sorting the hand by card
   * @return Card rank
   */
  public int getRankInt() {
    switch (this.rank) {
      case Constants.ACE:
        return 1;
      case Constants.TWO:
      case Constants.THREE:
      case Constants.FOUR:
      case Constants.FIVE:
      case Constants.SIX:
      case Constants.SEVEN:
      case Constants.EIGHT:
      case Constants.NINE:
      case Constants.TEN:
        return new Integer(this.rank);
      case Constants.JACK:
        return 11;
      case Constants.QUEEN:
        return 12;
      case Constants.KING:
        return 13;
      default:
        {
          try {
            throw new Exception("Invalid card rank");
          } catch (Exception ex) {
            Logger.getLogger(Card.class.getName()).log(Level.SEVERE, null, ex);
          }
        }
    }
    return 0;
  }
  
  /**
   * Get the point value for each card in the game
   * Note: J = no change; Q = minus 10; K = 99
   * @return Point value
   */
  public int getPointValue() {
    switch (this.rank) {
      case Constants.ACE:
        return 1;
      case Constants.TWO:
      case Constants.THREE:
      case Constants.FOUR:
      case Constants.FIVE:
      case Constants.SIX:
      case Constants.SEVEN:
      case Constants.EIGHT:
      case Constants.NINE:
      case Constants.TEN:
        return new Integer(this.rank);
      case Constants.JACK:
        return 0;
      case Constants.QUEEN:
        return -10;
      case Constants.KING:
        return Constants.NINETY_NINE;
      default:
        {
          try {
            throw new Exception("Invalid card rank");
          } catch (Exception ex) {
            Logger.getLogger(Card.class.getName()).log(Level.SEVERE, null, ex);
          }
        }
    }
    return 0;
  }
  
  /**
   * Get the card suit
   * @return Card suit
   */
  public String getSuit() {
    return this.suit.toUpperCase();
  }
  
  /**
   * Formats the card value, i.e. "3 of HEARTS"
   * @return Card string
   */
  public String toString() {
    return this.getRank()+" of "+this.getSuit();
  }
  
}
