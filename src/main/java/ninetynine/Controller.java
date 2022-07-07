package ninetynine;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


/**
 * Game controller
 * 
 * @author Roger Jaffe
 * @version 2018-11-29
 */
public final class Controller
{
  
  ArrayList<Player> players;
  Deck deck;
  Deck discards;
  int whoseTurn;
  int total;
  int direction;    // 1 = increasing order; -1 = decreasing order
  ArrayList<Player> finishOrder = new ArrayList<>();
  
  /**
   * Constructor for objects of class Controller
   * @param players ArrayList of Player objects
   */
  public Controller(ArrayList players)
  {
    this.players = players;
    this.displayMatchup();
    this.init();
  }
  
  /**
   * Initialize game
   */
  private void init() {
    this.whoseTurn = 0;
    this.direction = 1;
    this.deck = new Deck();
    this.deck.newDeck();
    this.discards = new Deck();
    for (int c = 0; c < Constants.INITIAL_CARD_COUNT; c++) 
      for (int i = 0; i < players.size() ; i++) 
        this.players.get(i).draw(this.deck.deal());  
  }

  /**
   * Moves the turn pointer to the next player
   */
  private void nextTurn() {
    this.whoseTurn = (this.whoseTurn + (this.direction));
    if (this.whoseTurn >= 0) 
      this.whoseTurn = this.whoseTurn % this.getPlayersStillAlive();
    else 
      this.whoseTurn = this.getPlayersStillAlive() - 1;
  }
  
  /**
   * Switches the player order the other way
   */
  private void switchDirection() {
    this.direction = this.direction * -1;
  }
  
  /**
   * Displays the match up with the player names
   */
  public void displayMatchup() {
    System.out.println("\n******* MATCHUP *******");
    for (int i = 0; i < this.players.size(); i++) {
      System.out.println("  "+this.players.get(i).getName());
    }
  }
  
  /**
   * Gets the number of players in the game
   * @return Number of players
   */
  private int getPlayersStillAlive() {
    return this.players.size();
  }
  
  /**
   * Checks if the provided card deck of either 1 or 2 cards is a valid move
   * @param play Card deck representing the proposed play
   * @return True if the play is valid
   */
  private boolean isValidPlay(Deck play) {
    boolean retValue = false;
    if (play.findPairs().size() > 0) 
      return true;
    else 
      return play.getCardCount() == 1 && Utilities.isLegalMove(play, this.total);
  }
  
  /**
   * Run the game with the four players.  Player 1 always goes first
   * @return 0 for normal exit
   */
  public int run() {
    boolean gameOver = false;
    try {
      // Print heading
      System.out.println("\nSTART OF GAME");
            
      // Turn first card over
      Card card = this.deck.deal();
      String rank = card.getRank();
      if (rank.equals(Constants.JACK))
        this.switchDirection();
      else if (rank.equals(Constants.KING))
        this.total = card.getPointValue();
      else
        this.total += card.getPointValue();
      System.out.println("\nFirst card is the "+rank+" of "+card.getSuit());
      System.out.println("Game total is "+this.total);
      this.discards.add(card);

      // Delay so we can see the progression in the console
      TimeUnit.MILLISECONDS.sleep(Constants.DELAY_TO_NEXT_PLAYER);

      while (!gameOver) {
        // Get the player and display the current hand
        Player player = this.players.get(this.whoseTurn);
        player.displayHand();
        
        // Delay so we can see the progression in the console
        TimeUnit.MILLISECONDS.sleep(Constants.DELAY_TO_MOVE);

        // Get the player's next move
        Deck play = player.getNextMove(this.total);

        // Process the play
        // If the play is not valid
        if (play == null || !this.isValidPlay(play)) {
          // Remove the player from the active player list
          this.players.remove(player);
          // Fix the whose turn counter if the removed player was the 
          // last player in the list
          if (this.whoseTurn == this.getPlayersStillAlive()) 
            this.whoseTurn--;
          // Add the removed player to the finished player
          this.finishOrder.add(player);
          // Notify 
          System.out.println("\n"+player.getName()+" cannot make a play and is OUT");
    
        } else if (this.isValidPlay(play)) {
          // Play is valid
          this.total = Utilities.getNewTotal(play, this.total);
          // Remove the cards from the player's hand
          player.removeCards(play);
          if (player.getCardCount() == 0) {
            // Remove the player from the active player list
            this.players.remove(player);
            // Fix the whose turn counter if the removed player was the 
            // last player in the list
            if (this.whoseTurn == this.getPlayersStillAlive()) 
              this.whoseTurn--;
            // Add the removed player to the finished player
            this.finishOrder.add(player);
            // Notify 
            System.out.println("\n"+player.getName()+" has no cards left and is OUT");
          }
          // Add the cards to the discards pile
          this.discards.add(play);
          // Player draws a new card from the deck
          player.draw(this.deck.deal());

          // If the deck is empty move the discards pile to the
          // deck, shuffle and continue the game
          if (this.deck.getCardCount() == 0) {
            this.deck = this.discards;
            this.discards = new Deck();
            this.deck.shuffle();
//            System.out.println("Out of cards... Reshuffling");
          }

          // If a Jack was played switch the direction of play
          if (play.getCard(0).getRank().equals(Constants.JACK)) 
            this.switchDirection();
          
          // Print out play to console
          System.out.print("\n"+player.getName()+" played ");
          if (play.getCardCount() == 2) {
            System.out.println("a pair of "+play.getCard(0).getRank()+"s");
          } else {
            System.out.println("the "+play.getCard(0).toString());
          }
          System.out.println("Now the total is --"+this.total+"--");
//          System.out.println("\nThere are "+this.deck.getCardCount()+" cards left in the deck");
          this.nextTurn();
        }
        
        // Notify all players of the cards that were played
        this.players.forEach((p) -> {
          p.cardsPlayed(play);
        });
        
        // Check to see if there's more than 1 player left
        gameOver = this.getPlayersStillAlive() == 1;

        // Delay so we can see the progression in the console
        TimeUnit.MILLISECONDS.sleep(Constants.DELAY_TO_NEXT_PLAYER);
      }
      
      System.out.println("\n***** "+this.players.get(this.whoseTurn).getName()+" WINS! *****");
      
    } catch (InterruptedException e) {
      System.out.println("OH NO!!! There was a time exception!\n");
      System.out.println(e.getMessage());
      return 0;
    }
    return 0;
  }
    
}
