package Pieces;

//Turm
public class Rook extends Piece{
    public Rook(int startX, int startY, boolean playerOne){
        super(startX, startY, playerOne);
    }

    /**
     * moves the rook to a new position
     * @param newX  the new X position of the rook
     * @param newY  the new Y position of the rook
     * @param board the current state of the board
     * @throws IllegalMoveException throws this exception if the move isn't allowed
     * @throws IllegalStateException    throws this exception if something is in the way
     * @author Uhlig Bastian
     */
    public void move(int newX, int newY, Piece[][] board) throws IllegalMoveException, IllegalStateException {
        int[] pos = getPos();
        int xMoveDir = (pos[0] - newX) > 0 ? -1 : 1;
        int yMoveDir = (pos[1] - newY) > 0 ? -1 : 1;
        if (pos[0] == newX) {
            Piece.checkInXLine(pos[0], pos[1], newY, yMoveDir, board);
            changePos(newX, newY);
        } else if( pos[1] == newY){
            Piece.checkInYLine(pos[1], pos[0], newX, xMoveDir, board);
            changePos(newX, newY);
        } else{
            throw new IllegalMoveException();
        }
    }

    @Override
    public String toString() {
        return "Rook " + (isPlayerOne() ? 1 : 2);
    }
}