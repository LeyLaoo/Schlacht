package Pieces;

//König
public class King extends Piece{
    public King(int startX, int startY, boolean playerOne){
        super(startX, startY, playerOne);
    }

    /**
     * moves the king to a new position
     * @param newX  the new X position of the king
     * @param newY  the new Y position of the king
     * @param board the current state of the board
     * @throws IllegalMoveException throws this exception if the move isn't allowed
     * @author Uhlig Bastian
     */
    public void move(int newX, int newY, Piece[][] board) throws IllegalMoveException{
        int[] pos = getPos();
        if(pos[0] - newX <= 1 && pos[0] - newX >= -1 && pos[1] - newY <= 1 && pos[1] - newY >= -1){
            changePos(newX, newY);
        }else{
            throw new IllegalMoveException("Illegal move");
        }
    }

    @Override
    public String toString() {
        return "King " + (isPlayerOne() ? 1 : 2);
    }
}