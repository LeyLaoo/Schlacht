package Pieces;

//Pferd
public class Knight extends Piece {
    public Knight(int startX, int startY, boolean playerOne) {
        super(startX, startY, playerOne);
    }

    /**
     * moves the knight
     *
     * @param newX  the new X position of the knight
     * @param newY  the new Y position of the knight
     * @param board the current state of the board
     * @throws IllegalMoveException throws this exception when the move isn't allowed
     * @author Uhlig Bastian
     */
    public void move(int newX, int newY, Piece[][] board) throws IllegalMoveException {
        int[] pos = getPos();
        if (((pos[1] + 2 == newY || pos[1] - 2 == newY) && (pos[0] + 1 == newX || pos[0] - 1 == newX)) || ((pos[0] + 2 == newX || pos[0] - 2 == newX) && (pos[1] + 1 == newY || pos[1] - 1 == newY))) {  //TODO other direction
            changePos(newX, newY);
        } else throw new IllegalMoveException();
    }

    @Override
    public String toString() {
        return "Knight " + (isPlayerOne() ? 1 : 2);
    }
}
