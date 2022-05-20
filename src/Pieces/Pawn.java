package Pieces;

//Tower
public class Pawn extends Piece {
    public Pawn(int startX, int startY, boolean playerOne) {
        super(startX, startY, playerOne);
    }

    /**
     * tries to kill the piece on the new position
     * @param newX  the new X position of the pawn
     * @param newY  the new Y position of the pawn
     * @throws IllegalArgumentException throws this exception if the move isn't allowed
     * @author Uhlig Bastian
     */
    public void kill(int newX, int newY) throws IllegalArgumentException {
        int[] pos = getPos();
        if (isPlayerOne()) {
            if (pos[0] - 1 == newX && (pos[1] + 1 == newY || pos[1] - 1 == newY)) {
                changePos(newX, newY);
            } else {
                throw new IllegalArgumentException("Illegal move");
            }
        } else {
            if (pos[0] + 1 == newX && (pos[1] + 1 == newY || pos[1] - 1 == newY)) {
                changePos(newX, newY);
            } else {
                throw new IllegalArgumentException("Illegal move");
            }
        }
    }

    /**
     * moves the pawn
     * @param newX  the new X position of the pawn
     * @param newY  the new Y position of the pawn
     * @param board the current state of the board
     * @throws IllegalArgumentException throws this exception if the move isn't allowed
     * @throws IllegalStateException    throws this exception if something is in the way
     * @author Uhlig Bastian
     */
    public void move(int newX, int newY, Piece[][] board) throws IllegalArgumentException, IllegalStateException {
        int[] pos = getPos();
        if (isPlayerOne()) {
            if (pos[1] - 1 == newY && pos[0] == newX) {
                changePos(newX, newY);
            } else if (pos[1] == 6 && pos[1] - 2 == newY && pos[0] == newX) {
                if(board[newX][pos[1]-1] != null){
                    throw new IllegalStateException("Something is in the way at " + newX + "|" + (pos[1]-1));
                }
                changePos(newX, newY);
            } else {
                throw new IllegalArgumentException("Illegal move");
            }
        } else {
            if (pos[1] + 1 == newY && pos[0] == newX) {
                changePos(newX, newY);
            } else if (pos[1] == 1 && pos[1] + 2 == newY && pos[0] == newX) {
                if(board[newX][pos[1]+1] != null){
                    throw new IllegalStateException("Something is in the way at " + newX + "|" + (pos[1]+1));
                }
                changePos(newX, newY);
            } else {
                throw new IllegalArgumentException("Illegal move");
            }
        }
    }

    @Override
    public String toString() {
        return "Pawn " + (isPlayerOne() ? 1 : 2);
    }
}
