package ninetynine;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Deck of cards abstraction
 * 
 * @author Roger Jaffe
 * @version 2018-11-29
 */
public class Deck
{
  private ArrayList<Card> cards;

  /**
   * Constructor for objects of class Deck
   */
  public Deck()
  {
    this.cards = new ArrayList<>();
  }
  
  /**
   * Add a card to this deck
   * @param card Card to add
   */
  public void add(Card card) {
    this.cards.add(card);
    this.sort();
  }
  
  public void add(Deck deck) {
    for (Card card : deck.getCards()) {
      this.add(card);
    }
  }
  
  /**
   * Remove the provided card from the deck if it exists
   * @param card Card to remove
   */
  public void removeCard(Card card) {
    this.cards.remove(card);
  }
  
  /**
   * Removes the cards in the provided deck from this deck if they exist
   * @param deck Deck whose cards should be removed
   */
  public void removeCard(Deck deck) {
    for (Card card : deck.getCards()) {
      this.removeCard(card);
    }
  }
  
  /**
   * Returns the top card on the deck and removes it from the deck
   * @return The top card on the deck
   */
  public Card deal() {
    Card card = this.getCard();
    this.removeCard(card);
    return card;
  }
  
  /**
   * Get a card from the top of the deck but DO NOT remove it!
   * @return Top card of the deck
   */
  public Card getCard() {
    if (this.getCardCount() > 0)
      return this.getCard(0);
    else 
      return null;
  }
  
  /**
   * Get an individual card from the deck but DO NOT remove it!
   * @param idx Index of card to get
   * @return Retrieved card
   */
  public Card getCard(int idx) {
    if (0 <= idx && idx < this.cards.size()) 
      return this.cards.get(idx);
    else
      return null;
  }
  
  /**
   * Gets the number of cards in this deck
   * @return Card count
   */
  public int getCardCount() {
    return this.cards.size();
  }
  
  /**
   * Gets the cards in the deck
   * @return Array of Card objects
   */
  public Card[] getCards() {
    if (this.getCardCount() == 0) return null;    
    Card[] cards = new Card[this.getCardCount()];
    for (int i=0; i<this.getCardCount(); i++) {
      cards[i] = this.getCard(i);
    }
    return cards;
  }

  /** 
   * Creates a new deck of cards in order
   */
  public void newDeck() {
    for (String suit : Constants.suits) {
      for (String rank : Constants.ranks) {
        Card card = new Card(rank, suit);
        cards.add(card);
      }
    }
    this.shuffle();
  }
  
  /**
   * Shuffles the cards in the deck
   */
  public void shuffle() {
    ArrayList<Card> newCards = new ArrayList<>();
    while (this.cards.size() > 0) {
      int pos = (int)(Math.random() * this.cards.size());
      newCards.add(this.cards.get(pos));
      this.cards.remove(pos);
    }
    this.cards = newCards;
  }
  
  /**
   * Sorts the cards in the deck
   */
  public void sort() {
    Collections.sort(cards, new SortByCard());
  }
  
  /**
   * Gets all the card pairs by rank only
   * @return ArrayList of Decks with the cards that form each pair
   */
  public ArrayList<Deck> findPairs() {
    ArrayList<Deck> pairs = new ArrayList<>();
    for (int i = 0; i < cards.size()-1; i++) {
      for (int j = i+1; j < cards.size(); j++) {
        if (cards.get(i).getRank().equals(cards.get(j).getRank())) {
          Deck d = new Deck();
          d.add(this.getCard(i));
          d.add(this.getCard(j));
          pairs.add(d);
        }
      }
    }
    return pairs;
  }
  
  /**
   * Check if deck is empty
   * @return True if the deck is empty
   */
  public boolean isEmptyDeck() {
    return this.cards.isEmpty();
  }
  
}
