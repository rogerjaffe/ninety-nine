package ninetynine;

/**
 * Player object. Students will extend this class
 * and override the getNextMove method
 * 
 * @author Roger Jaffe
 * @version 2018-11-29
 */
public class Player
{
  protected Deck deck;
  private String teamName;
  
  /**
   * Player constructor
   */
  public Player() {
    this.deck = new Deck();
  }
  
  /**
   * Player constructor with teamName added as instance variable
   * If teamName is not set, then the class name is used when printed
   * during game play
   * @param teamName Name of the player's team
   */
  public Player(String teamName) {
    this();
    this.teamName = teamName;
  }
  
  /**
   * Gets the player's deck
   * @return Deck of cards
   */
  public Deck getDeck() {
    return this.deck;
  }

  /**
   * Sets the deck with the parameter
   * @param deck Deck to set this player with
   */
  public void setDeck(Deck deck) {
    this.deck = deck;
  }
  
  /**
   * Add the provided card to this player's deck
   * @param card Card to be added to the player's deck
   */
  public void draw(Card card) {
    this.deck.add(card);
  }
  
  /**
   * Remove the cards in the provided deck from this player's deck
   * @param cards Cards to be removed from the player's deck
   */
  public void removeCards(Deck cards) {
    for (Card card : cards.getCards()) {
      this.deck.removeCard(card);
    }
  }
  
  /**
   * Get the number of cards in the player's deck
   * @return Number of cards in the deck
   */
  public int getCardCount() {
    return this.deck.getCardCount();
  }
  
  /**
   * Display the player's hand 
   */
  public void displayHand() {
    System.out.println("\n******* "+this.getName()+" *******");
    Card[] cards = this.deck.getCards();
    for (Card card : cards) {
      System.out.println(card.toString());
    }
  }
  
  /**
   * Gets the player name
   * @return Player name
   */
  public String getName() {
    return (this.teamName.equals("")) ? this.getClass().getSimpleName() : this.teamName;
  }
  
  /**
   * The player must override getNextMove
   * @param board Game board
   * @return A position coordinate pair of his/her next move. Returns null
   *          if no move is available
   */
  Deck getNextMove(int total) {
    return null;
  };
  
  /**
   * This method is called by the Game Controller after each player's
   * move.  The Player can use this information as necessary.
   * @param deck Deck of cards that were just played 
   */
  public void cardsPlayed(Deck deck) {
    
  }  

}
