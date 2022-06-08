package Pieces;

//Königin
public class Queen extends Piece {
    public Queen(int startX, int startY, boolean playerOne) {
        super(startX, startY, playerOne);
    }

    /**
     * moves the queen to the new position
     * @param newX  the new X position of the queen
     * @param newY  the new Y position of the queen
     * @param board the current state of the board
     * @throws IllegalMoveException throws this exception if the move isn't allowed
     * @throws IllegalStateException    throws this exception if something is in the way
     * @author Uhlig Bastian
     */
    public void move(int newX, int newY, Piece[][] board) throws IllegalMoveException, IllegalStateException {
        int[] pos = getPos();
        //Checks the move in a row
        int xMoveDir = (pos[0] - newX) > 0 ? -1 : 1;
        int yMoveDir = (pos[1] - newY) > 0 ? -1 : 1;
        if (pos[0] == newX) {
            Piece.checkInXLine(pos[0], pos[1], newY, yMoveDir, board);
            changePos(newX, newY);
        } else if( pos[1] == newY){
            Piece.checkInYLine(pos[1], pos[0], newX, xMoveDir, board);
            changePos(newX, newY);
        }else {
            //Check the diagonal move
            if(Math.abs(pos[0] - newX) == Math.abs(pos[1]-newY)){
                Piece.checkInDiagonal(pos[0], pos[1], newX, xMoveDir, yMoveDir, board);
                changePos(newX, newY);
            }else{
                throw new IllegalMoveException("Illegal diagonal move at queen");
            }
        }
    }

    @Override
    public String toString() {
        return "Queen " + (isPlayerOne() ? 1 : 2);
    }
}