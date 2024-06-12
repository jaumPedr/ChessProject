package GamePieces;

import java.util.ArrayList;
import java.util.List;

public class Horse extends Piece{


    public Horse(int[] position, boolean isWhite) {
        super(position, isWhite);
    this.typePiece="N";
    }

    public List<int[]> movement(List<int[]> occupiedSpaces) {

        List<int[]> movementSpaces = new ArrayList<>();
        int[] moveDistance;

        for(int i= -2;i<=2;i=i+4){

            for(int in=-1;in<=1;in=in+2){
                moveDistance = new int[] {i, in};

                moveDistance[0]=moveDistance[0]+position[0]; moveDistance[1]=moveDistance[1]+position[1];

                if(noOccupiedAnalysisSpace(moveDistance,occupiedSpaces)){
                    movementSpaces.add(moveDistance);
                }

                moveDistance = new int[] {in,i};

                moveDistance[0]=moveDistance[0]+position[0]; moveDistance[1]=moveDistance[1]+position[1];

                if(noOccupiedAnalysisSpace(moveDistance,occupiedSpaces)){
                    movementSpaces.add(moveDistance);
                }
            }
        }

        return movementSpaces;
    }


    @Override
    public List<int[]> possibleCaptureSpaces(List<int[]> occupiedSpaces) {

        List<int[]> captureSpaces = new ArrayList<>();
        int[] moveDistance;

        for(int i= -2;i<=2;i=i+4){

            for(int in=-1;in<=1;in=in+2){
                moveDistance = new int[] {i, in};
                moveDistance[0]=moveDistance[0]+position[0]; moveDistance[1]=moveDistance[1]+position[1];
                if(!noOccupiedAnalysisSpace(moveDistance,occupiedSpaces)){
                    captureSpaces.add(moveDistance);
                }

                moveDistance = new int[] {in,i};
                moveDistance[0]=moveDistance[0]+position[0]; moveDistance[1]=moveDistance[1]+position[1];
                if(!noOccupiedAnalysisSpace(moveDistance,occupiedSpaces)){
                    captureSpaces.add(moveDistance);
                }
            }
        }
        return captureSpaces;
    }

}
