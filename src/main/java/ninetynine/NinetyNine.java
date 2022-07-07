/*
 * Copyright 2018 Roger Jaffe
 * All rights reserved
 */
package ninetynine;

import java.util.ArrayList;

/**
 * Ninety-nine card game!!
 * @author Roger Jaffe
 */
public class NinetyNine {

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    ArrayList<Player> players = new ArrayList<>();
    players.add(new TestPlayer("Adam"));
    players.add(new TestPlayer("Ben"));
    players.add(new TestPlayer("Cathy")); 
    players.add(new TestPlayer("Emily")); 
    Controller c = new Controller(players);
    c.run();
  }

}
