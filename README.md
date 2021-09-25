# Mankala-game-board

  This project is a kind of gaming boards based on command line.

  Features :
  ----------------------
   - Game is ready just for (Real to Bot) player strategy right now
   - Strategy design pattern has implemented for different types of players
   - Rules handler is an interface can extend easily and implement on different playing strategy
   - Command line handler manage the command process out of the game logics 
   - Providing Unit test for testing (Real to Bot) player rules


  How to Use My Project:
  -------------------------
    - Just run (MancalaApplication) that is including main method
    - Real player should enter her/his name
    - Then After showing the game regulation application ask real player
        to enter the hole number (between 1 and 6) which wants to play by that
    - There are 6 holes for each player which fill out by 6 beans (values)
    - After each move from real player ,bot player automatically choose a number (between 1 and 6)
