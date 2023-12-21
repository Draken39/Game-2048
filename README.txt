=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=
CIS 1200 Game Project README
PennKey: wnath3
=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=

===================
=: Core Concepts :=
===================

- List the four core concepts, the features they implement, and why each feature
  is an appropriate use of the concept. Incorporate the feedback you got after
  submitting your proposal.

  1. 2D Arrays were used to model the state of the GameBoard, in this case
  only a 4 by 4 2d array of integers was used, with each entry representing a value
  on the game board. The arrays were updated based on each Keyboard move, for instance,
  the array value changed from 2 to 4 to 8, etc.

  2. Collections was used to implement the undo button. In this case, I used one linked list
  to keep track of the Board  after every move and when the board was created, I
  added a copy of the board to the BoardMoves Linked List. Therefore, when undo is hit,
  the current board can just be kicked off the end of the list, and the new last value on the linked list
  can be drawn out as the new board.

  3. File I/O was used to store the gameBoard whenever save was called. This meant that the game
  could be written into a file, with the score, and each integer value in the gameBoard, from (0,0) to (3,3).
  For Load, I/O was used to load in the most recent saved state. Thus if a user leaves the game, and then
  launches it again, everything is saved.

  4. JUNIT testing was used to test if the methods that controlled the board actually worked.
  For instance, I tested that the board could move left, right, up and down, independent of the GUI, and
  tested that the board would not take anymore inputs if the game was lost or if the board had a 2048 block.
  Furthermore, I tested reset and undo independent of the GUI using just the methods defined in the board class.

===============================
=: File Structure Screenshot :=
===============================
- Include a screenshot of your project's file structure. This should include
  all of the files in your project, and the folders they are in. You can
  upload this screenshot in your homework submission to gradescope, named 
  "file_structure.png".

=========================
=: Your Implementation :=
=========================

- Provide an overview of each of the classes in your code, and what their
  function is in the overall game.

  The Board Elements class stores the actual board as a 2-d array and contains methods
  to update the 2d array which correspond to movement of the arrow keys. It also contains functionality
  for undo, and resetting the game, both of which change the 2-d array. The BoardLayout class lays out the
  actual board using a JFrame and puts everything where it should go so it's easy for the viewer to
  see everything clearly. RunGame2048 contains the wiring behind the game, such as how the game should
  handle input and what the board drawn should look like. Lastly, SaveAndLoad separates the IO component
  of the board from the rest and if a new instance of it is declared, will allow the board variable that is part
  of it to be saved and allow for boards to be loaded into the display.


- Were there any significant stumbling blocks while you were implementing your
  game (related to your design, or otherwise)?

  There was a bug with my undo button for a long time and I wasn't quite sure why, but then I realized
  it likely had to do with the fact that I was not cloning the board and passing it straight into the
  LinkedLists which stored past states of the board.

- Evaluate your design. Is there a good separation of functionality? How well is
  private state encapsulated? What would you refactor, if given the chance?

  I think the functionality is well separated into different classes, and each board state
  is a copy of another, so this means that there won't be an issue where a value on the heap
  points to the same Board as an old Board which should not be modified/passed into the Game.
  I think I also did a good job encapsulating the private state of the board and past board states, since
  those cannot be modified directly outside the class. If I were to redo this project, I would add the
  functionality where the user/player can see the blocks moving, rather than the instant moves
  that it shows now.

========================
=: External Resources :=
========================

- Cite any external resources (images, tutorials, etc.) that you may have used 
  while implementing your game.
  I used JavaDocs to research methods for different classes/libraries and also imported
  Java.awt to change the color of my display and font size.
