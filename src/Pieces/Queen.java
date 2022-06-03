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
        int xMoveDir = (pos[0] - newX) < 0 ? -1 : 1;
        int yMoveDir = (pos[1] - newY) < 0 ? -1 : 1;
        if (pos[0] == newX) {
            Piece.checkInLine(pos[0], pos[1], newY, xMoveDir, board);
            changePos(newX, newY);
        } else if( pos[1] == newY){
            Piece.checkInLine(pos[0], pos[1], newX, yMoveDir, board);
            changePos(newX, newY);
        }else {
            //Check the diagonal move
            if(Math.abs(pos[0] - newX) == Math.abs(pos[1]-newY)){
                for(int i = 1; i < Math.abs(pos[0] - newX); i++){
                    if(board[pos[0]+i * xMoveDir][pos[1]+i * yMoveDir] != null){
                        throw new IllegalStateException("Something is in the way at "+ (pos[0]+i * xMoveDir) + "|"+(pos[1]+i * yMoveDir));
                    }
                }
            }else{
                throw new IllegalMoveException();
            }
        }
    }

    @Override
    public String toString() {
        return "Queen " + (isPlayerOne() ? 1 : 2);
    }
}