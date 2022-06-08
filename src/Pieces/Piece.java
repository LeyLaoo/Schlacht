package Pieces;

public abstract class Piece{
    private int xPos;
    private int yPos;
    private final boolean playerOne;

    /**
     * Creates a new piece
     * @param startX    X position of where the piece starts
     * @param startY    Y position of where the piece starts
     * @param playerOne is true if the piece is from player one / false if it's from player two
     * @throws IllegalArgumentException throws this exception if the position isn't on the board
     * @author Uhlig Bastian
     */
    Piece(int startX, int startY, boolean playerOne) throws IllegalArgumentException{
        if(startX < 8 && startX >= 0 && startY < 8 && startY >= 0){
            this.xPos = startX;
            this.yPos = startY;
        }else{
            throw new IllegalArgumentException(startX + " or " + startX + " are not on the chess table");
        }
        this.playerOne = playerOne;
    }

    /**
     * Changes the position of the piece
     * @param newX  X position of where the piece is moved
     * @param newY  Y position of where the piece is moved
     * @throws IllegalStateException throws this exception when the new position isn't on the board
     * @author Uhlig Bastian
     */
    protected void changePos (int newX, int newY) throws IllegalStateException{
        if(newX < 8 && newX >= 0 && newY < 8 && newY >= 0){
            this.xPos = newX;
            this.yPos = newY;
        }else{
            throw new IllegalStateException(newX + " or " + newY + " are not on the chess table");
        }
    }

    /**
     * @return  returns the x and y coordinates with index 0 being the x, and index 1 being the y coordinate
     * @author Uhlig Bastian
     */
    public int[] getPos(){
        return new int[] {xPos, yPos};
    }

    /**
     * @return returns true if the piece is from player one, false if it is from player two
     * @author Uhlig Bastian
     */
    public boolean isPlayerOne(){
        return playerOne;
    }

    /**
     * Checks if anything is in the way of the moved pawn
     * @param x the position that isn't changing when moved in a row
     * @param y   the start position that is changing when moved in a row
     * @param newY the new position of the changing coordinate
     * @param moveDir   is either +1 or -1, depending on the direction of the moved piece
     * @param board  the current state of the chessboard
     * @throws IllegalStateException    throws this exception if anything is in the way of the piece
     * @author Uhlig Bastian
     */
    protected static void checkInXLine(int x, int y, int newY, int moveDir, Piece[][] board) throws IllegalStateException{
        for ( int i = 1; i < Math.abs(y - newY); i++){
            if( board[x][y + i*moveDir] != null){
                throw new IllegalStateException("Something is in the way at " + x + "|" + (y+i*moveDir));
            }
        }
    }
    protected static void checkInYLine(int y, int x, int newX, int moveDir, Piece[][] board) throws IllegalStateException{
        for ( int i = 1; i < Math.abs(x - newX); i++){
            if( board[x + i*moveDir][y] != null){
                throw new IllegalStateException("Something is in the way at " + (x+i*moveDir) + "|" + (y));
            }
        }
    }
    protected static void checkInDiagonal(int currentX, int currentY, int newX, int xDir, int yDir, Piece[][] board) throws IllegalStateException{
        for(int i = 1; i < Math.abs(currentX - newX); i++){
            if(board[currentX+i * xDir][currentY+i * yDir] != null){
                throw new IllegalStateException("Something is in the way at "+ (currentX+i * xDir) + "|"+(currentY+i * yDir));
            }
        }
    }

    @Override
    abstract public String toString();
    abstract public void move(int newX, int newY, Piece[][] board) throws IllegalMoveException, IllegalArgumentException, IllegalStateException;
}
