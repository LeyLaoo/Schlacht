package Pieces;

//Königin
public class Queen extends Piece{
    public Queen(int startX, int startY, boolean playerOne){
        super(startX, startY, playerOne);
    }

    public void move(int newX, int newY) throws IllegalArgumentException{
        int[] pos = getPos();
        //Checks the move in a row
        if(pos[0] == newX || pos[1] == newY){
            changePos(newX, newY);
        }
        //Check the diagonal move
        for(int i = -8; i < 8; i++){
            if(pos[0] +i == newX && pos[1] +i == newY){
                move(newX, newY);
            }
        }
    }

    @Override
    public String toString() {
        return "Queen" + (isPlayerOne() ? 1 : 2);
    }
}
