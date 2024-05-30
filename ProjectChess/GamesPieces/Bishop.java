package GamesPieces;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {
    public Bishop(int[] position, boolean isWhite) {
        super(position, isWhite);
        this.typePiece="B";
    }

    public List<int[]> movement(List<int[]> occupiedSpaces) {

        List<int[]> movementSpaces = new ArrayList<>();

        int[] moveDistance;

        //left up movement
        for(int i=1;i<8;i++){

            moveDistance= new int[]{position[0] - i, position[1] - i};
            if(noOccupiedAnalysisSpace(moveDistance,occupiedSpaces)){
                movementSpaces.add(moveDistance);
                continue;
            }

            break;
        }

        //left down movement
        for(int i=1;i<8;i++){

            moveDistance= new int[]{position[0] + i, position[1] - i};
            if(noOccupiedAnalysisSpace(moveDistance,occupiedSpaces)){
                movementSpaces.add(moveDistance);
                continue;
            }

            break;
        }

        //right up movement
        for(int i=1;i<8;i++){

            moveDistance= new int[]{position[0] - i, position[1] + i};
            if(noOccupiedAnalysisSpace(moveDistance,occupiedSpaces)){
                movementSpaces.add(moveDistance);
                continue;
            }

            break;
        }

        //right down movement
        for(int i=1;i<8;i++){

            moveDistance= new int[]{position[0] + i, position[1] + i};
            if(noOccupiedAnalysisSpace(moveDistance,occupiedSpaces)){
                movementSpaces.add(moveDistance);
                continue;
            }

            break;
        }

        return movementSpaces;
    }


    @Override
    public List<int[]> possibleCaptureSpaces(List<int[]> occupiedSpaces) {

        List<int[]> captureSpaces= new ArrayList<>();
        int[] captureMovement;


        //left up capture
        for(int i=1;i<8;i++){

            captureMovement= new int[]{position[0] - i, position[1] - i};
            if(!noOccupiedAnalysisSpace(captureMovement,occupiedSpaces)){
                captureSpaces.add(captureMovement);
                break;
            }
        }

        //left down capture
        for(int i=1;i<8;i++){

            captureMovement= new int[]{position[0] + i, position[1] - i};
            if(!noOccupiedAnalysisSpace(captureMovement,occupiedSpaces)){
                captureSpaces.add(captureMovement);
                break;
            }
        }

        //right up capture
        for(int i=1;i<8;i++){

            captureMovement= new int[]{position[0] - i, position[1] + i};
            if(!noOccupiedAnalysisSpace(captureMovement,occupiedSpaces)){
                captureSpaces.add(captureMovement);
                break;
            }
        }

        //right down capture
        for(int i=1;i<8;i++){

            captureMovement= new int[]{position[0] + i, position[1] + i};
            if(!noOccupiedAnalysisSpace(captureMovement,occupiedSpaces)){
                captureSpaces.add(captureMovement);
                break;
            }
        }

        return captureSpaces;
    }

}
