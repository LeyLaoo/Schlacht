package Pieces;

//Läufer
public class Bishop extends Piece{
    public Bishop(int startX, int startY, boolean playerOne){
        super(startX, startY, playerOne);
    }

    /**
     * moves the bishop ot the new position
     * @param newX  the new X position of the bishop
     * @param newY  the new Y position of the bishop
     * @param board the current state of the board
     * @throws IllegalMoveException throws this exception if the move isn't allowed
     * @throws IllegalStateException    throws this exception if something is in the way
     * @author Uhlig Bastian
     */
    public void move(int newX, int newY, Piece[][] board) throws IllegalMoveException, IllegalStateException{
        int[] pos = getPos();
        if(Math.abs(pos[0] - newX) == Math.abs(pos[1]-newY)){
            int xMoveDir = (pos[0] - newX) < 0 ? 1 : -1;
            int yMoveDir = (pos[1] - newY) < 0 ? 1 : -1;
            Piece.checkInDiagonal(pos[0], pos[1], newX, xMoveDir, yMoveDir, board);
            changePos(newX, newY);
        }else{
            throw new IllegalMoveException("Illegal move - not diagonal");
        }
    }

    @Override
    public String toString() {
        return "Bishop " + (isPlayerOne() ? 1 : 2);
    }
}
