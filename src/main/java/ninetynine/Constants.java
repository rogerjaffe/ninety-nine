package ninetynine;


/**
 * Ninety-nine game constants
 * @author Roger Jaffe
 * @version 2018-11-29
 */
public class Constants  
{
  public static final int INITIAL_CARD_COUNT = 4;       // Initial number of cards in hand
  public static final int DELAY_TO_MOVE = 3500;         // Delay between moves (ms)
  public static final int DELAY_TO_NEXT_PLAYER = 3500;  // Delay between moves (ms)
  public static final int NINETY_NINE = 99;

  // Card suits
  public static final String SPADES = "SPADES";
  public static final String DIAMONDS = "DIAMONDS";
  public static final String CLUBS = "CLUBS";
  public static final String HEARTS = "HEARTS";
  public static final String[] suits = {SPADES, DIAMONDS, CLUBS, HEARTS};
  
  // Card ranks
  public static final String ACE = "Ace";
  public static final String TWO = "2";
  public static final String THREE = "3";
  public static final String FOUR = "4";
  public static final String FIVE = "5";
  public static final String SIX = "6";
  public static final String SEVEN = "7";
  public static final String EIGHT = "8";
  public static final String NINE = "9";
  public static final String TEN = "10";
  public static final String JACK = "Jack";
  public static final String QUEEN = "Queen";
  public static final String KING = "King";
  public static final String[] ranks = {ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING};  
}
