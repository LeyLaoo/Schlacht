package Pieces;

public abstract class Piece{
    private int xPos;
    private int yPos;
    private final boolean playerOne;

    Piece(int startX, int startY, boolean playerOne) throws IllegalArgumentException{
        if(startX < 8 && startX >= 0 && startY < 8 && startY >= 0){
            this.xPos = startX;
            this.yPos = startY;
        }else{
            throw new IllegalArgumentException(startX + " or " + startX + " are not on the chess table");
        }
        this.playerOne = playerOne;
    }

    //Changes the x and the y position of the Piece
    protected void changePos (int newX, int newY) throws IllegalArgumentException{
        if(newX < 8 && newX >= 0 && newY < 8 && newY >= 0){
            this.xPos = newX;
            this.yPos = newY;
        }else{
            throw new IllegalArgumentException(newX + " or " + newY + " are not on the chess table");
        }
    }

    //returns the position as an array with index 0 being the x coordinate and index 1 the y coordinate
    public int[] getPos(){
        return new int[] {xPos, yPos};
    }

    public boolean isPlayerOne(){
        return playerOne;
    }

    protected static void checkInLine(int posStatic, int posChanging, int newCoordinate, int moveDir, Piece[][] board){
        for ( int i = 1; i < Math.abs(posChanging - newCoordinate); i++){
            if( board[posStatic][posChanging + i*moveDir] != null){
                throw new IllegalStateException("Something is in the way at " + posStatic + "|" + (posChanging+i*moveDir));
            }
        }
    }

    @Override
    abstract public String toString();
    abstract public void move(int newX, int newY, Piece[][] board) throws IllegalArgumentException, IllegalStateException;
}
