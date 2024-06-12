package GamePieces;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

public class King extends Piece{

    private boolean moved;
    public King(int[] position, boolean isWhite) {
        super(position, isWhite);
        this.moved=false;
        this.typePiece="K";
    }


    public List<int[]> movement(List<int[]> occupiedSpaces) {

        List<int[]> movements = new ArrayList<>();
        int[] movementAnalysis;
        //getting the positions in the XAlim, YAlim and the diagonal(XAlim YAlim union) with 1 unit of distance and unoccupied spaces
        for(int i=0;i<8;i++){
            for (int in=0;in<8;in++){

                if(abs(i-position[0])==1&&in==position[1]){
                    movementAnalysis=new int[]{i, in};
                    if(noOccupiedAnalysisSpace(movementAnalysis,occupiedSpaces)){
                        movements.add(movementAnalysis);
                    }
                }

                if(abs(in-position[1])==1&&i==position[0]){
                    movementAnalysis=new int[]{i, in};
                    if(noOccupiedAnalysisSpace(movementAnalysis,occupiedSpaces)){
                        movements.add(movementAnalysis);
                    }
                }

                if(abs(i-position[0])==1&&abs(in-position[1])==1){
                    movementAnalysis=new int[]{i, in};
                    if(noOccupiedAnalysisSpace(movementAnalysis,occupiedSpaces)){
                        movements.add(movementAnalysis);
                    }
                }

            }
        }
        return movements;
    }


    @Override
    public List<int[]> possibleCaptureSpaces(List<int[]> occupiedSpaces) {

        List<int[]> captureSpaces = new ArrayList<>();
        int[] moveDistance= new int[2];
        int[] movementAnalysis;

        for(int i=0;i<8;i++){
            for (int in=0;in<8;in++){

                if(abs(i-position[0])==1&&in==position[1]){
                    movementAnalysis=new int[]{i, in};
                    if(!noOccupiedAnalysisSpace(movementAnalysis,occupiedSpaces)){
                        captureSpaces.add(movementAnalysis);
                    }
                }

                if(abs(in-position[1])==1&&i==position[0]){
                    movementAnalysis=new int[]{i, in};
                    if(!noOccupiedAnalysisSpace(movementAnalysis,occupiedSpaces)){
                        captureSpaces.add(movementAnalysis);
                    }
                }

                if(abs(i-position[0])==1&&abs(in-position[1])==1){
                    movementAnalysis=new int[]{i, in};
                    if(!noOccupiedAnalysisSpace(movementAnalysis,occupiedSpaces)){
                        captureSpaces.add(movementAnalysis);
                    }
                }

            }
        }
        return captureSpaces;
    }


    public boolean isMoved() {
        return moved;
    }

    public void setMoved(boolean moved) {
        this.moved = moved;
    }
}
