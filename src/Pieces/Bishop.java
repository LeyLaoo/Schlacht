package Pieces;

//Läufer
public class Bishop extends Piece{
    public Bishop(int startX, int startY, boolean playerOne){
        super(startX, startY, playerOne);
    }

    public void move(int newX, int newY, Piece[][] board) throws IllegalArgumentException{
        int[] pos = getPos();
        if(Math.abs(pos[0] - newX) == Math.abs(pos[1]-newY)){
            int xMoveDir = (pos[0] - newX) < 0 ? 1 : -1;
            int yMoveDir = (pos[1] - newY) < 0 ? 1 : -1;
            for(int i = 1; i < Math.abs(pos[0] - newX); i++) {
                if(board[pos[0]+i * xMoveDir][pos[1]+i * yMoveDir] != null){
                    throw new IllegalStateException("Something is in the way at "+ (pos[0]+i * xMoveDir) + "|"+(pos[1]+i * yMoveDir));
                }
            }
        }else{
            throw new IllegalArgumentException("Illegal move-not diagonal");
        }
    }

    @Override
    public String toString() {
        return "Bishop" + (isPlayerOne() ? 1 : 2);
    }
}
