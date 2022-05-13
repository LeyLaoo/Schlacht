package Pieces;

//Läufer
public class Bishop extends Piece{
    public Bishop(int startX, int startY, boolean playerOne){
        super(startX, startY, playerOne);
    }

    public void move(int newX, int newY) throws IllegalArgumentException{
        int[] pos = getPos();
        for(int i = -8; i < 8; i++){
            if(pos[0] +i == newX && pos[1] +i == newY){
                move(newX, newY);
            }
        }
    }

    @Override
    public String toString() {
        return "Bishop" + (isPlayerOne() ? 1 : 2);
    }
}
