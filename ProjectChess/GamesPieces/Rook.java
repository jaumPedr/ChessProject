package GamesPieces;

import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece{

    private boolean moved;
    public Rook(int[] position, boolean isWhite) {
        super(position, isWhite);
        this.moved=false;
        this.typePiece="R";
    }

    @Override
    public List<int[]> movement(List<int[]> occupiedSpaces) {
        List<int[]> movements = new ArrayList<>();

        //up movement
        for(int xAlim=position[0]-1;xAlim>=0;xAlim--){
            if(noOccupiedAnalysisSpace(new int[] {xAlim,position[1]},occupiedSpaces)){
                movements.add(new int[] {xAlim,position[1]});
                continue;
            }
            break;
        }

        //down movement
        for(int xAlim=position[0]+1;xAlim<8;xAlim++){
            if(noOccupiedAnalysisSpace(new int[] {xAlim,position[1]},occupiedSpaces)){
                movements.add(new int[] {xAlim,position[1]});
                continue;
            }
            break;
        }

        //left movement
        for(int yAlim=position[1]-1;yAlim>=0;yAlim--){
            if(noOccupiedAnalysisSpace(new int[] {position[0],yAlim},occupiedSpaces)){
                movements.add(new int[] {position[0],yAlim});
                continue;
            }
            break;
        }

        //right movement
        for(int yAlim=position[1]+1;yAlim<8;yAlim++){
            if(noOccupiedAnalysisSpace(new int[] {position[0],yAlim},occupiedSpaces)){
                movements.add(new int[] {position[0],yAlim});
                continue;
            }
            break;
        }

        return movements;
    }

    @Override
    public List<int[]> possibleCaptureSpaces(List<int[]> occupiedSpaces) {

        List<int[]> captureSpaces= new ArrayList<>();


        //up capture
        for(int xAlim=position[0]-1;xAlim>=0;xAlim--){
            if(!noOccupiedAnalysisSpace(new int[] {xAlim,position[1]},occupiedSpaces)){
                captureSpaces.add(new int[] {xAlim,position[1]});
                break;
            }
        }

        //down capture
        for(int xAlim=position[0]+1;xAlim<8;xAlim++){
            if(!noOccupiedAnalysisSpace(new int[] {xAlim,position[1]},occupiedSpaces)){
                captureSpaces.add(new int[] {xAlim,position[1]});
                break;
            }
        }

        //left capture
        for(int yAlim=position[1]-1;yAlim>=0;yAlim--){
            if(!noOccupiedAnalysisSpace(new int[] {position[0],yAlim},occupiedSpaces)){
                captureSpaces.add(new int[] {position[0],yAlim});
                break;
            }
        }

        //right capture
        for(int yAlim=position[1]+1;yAlim<8;yAlim++){
            if(!noOccupiedAnalysisSpace(new int[] {position[0],yAlim},occupiedSpaces)){
                captureSpaces.add(new int[] {position[0],yAlim});
                break;
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
