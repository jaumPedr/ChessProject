package GamePieces;

import java.util.Arrays;
import java.util.List;

public abstract class Piece {



    //general pieces
    protected String typePiece;
    protected boolean isWhite;
    protected int[] position = new int[2];

    //Every piece have a color (black or white) and a white
    public Piece(int[] position, boolean isWhite){
        this.position=position;
        this.isWhite=isWhite;
    }

    public abstract List<int[]> movement(List<int[]> occupiedSpaces);

    public abstract List<int[]> possibleCaptureSpaces(List<int[]> occupiedSpaces);

    //true if the space is not occupied and false if is
    public boolean noOccupiedAnalysisSpace(int[] analysisSpace, List<int[]> occupiedSpaces){
        for(int i=0;i<occupiedSpaces.size();i++){
            if(Arrays.equals(analysisSpace,occupiedSpaces.get(i))){
                return false;
            }
        }
        return true;
    }





    public int[] getPosition() {
        return position;
    }

    public void setPosition(int[] position) {
        this.position = position;
    }


    public String getTypePiece() {
        return typePiece;
    }


    public boolean isWhite() {
        return isWhite;
    }

}