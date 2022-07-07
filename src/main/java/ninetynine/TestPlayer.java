package ninetynine;

import java.util.ArrayList;

/**
 * Player object. Students will extend this class
 * and override the getNextMove method
 * 
 * @author Roger Jaffe
 * @version 2018-11-29
 */
public class TestPlayer extends Player {
  
  /**
   * Player constructor
   */
  public TestPlayer() {
    super();
  }
  
  /**
   * Player constructor with teamName added as instance variable
   * If teamName is not set, then the class name is used when printed
   * during game play
   * @param teamName Name of the player's team
   */
  public TestPlayer(String teamName) {
    super(teamName);
  }

  /**
   * The next move algorithm for the TestPlayer class looks plays the following
   * in this order:
   * 1. If a pair is found
   * 2. The lowest non-pair card in the hand as determined by the 
   *    card ranking (Ace=1, Jack=11, Queen=12, King=13)
   * @param total The current game total
   * @return A position coordinate pair of his/her next move. Returns null
   *          if no move is available
   */
  @Override
  Deck getNextMove(int total) {
    ArrayList<Deck> pairs = this.deck.findPairs();
    if (pairs.size() > 0) 
      return pairs.get(0);

    int i = 0;
    while (i < this.deck.getCardCount()) {
      Deck deck = new Deck();
      deck.add(this.deck.getCard(i));
      if (Utilities.isLegalMove(deck, total)) {
        return deck;
      }
      i++;
    }
    return null;
  };

}
