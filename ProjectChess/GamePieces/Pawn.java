package GamePieces;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Pawn extends Piece{

    //Start position, equals the initialized position, it's used in "en pessant" analysis and in the double movement action
    private int[] startPosition;
    //True if is a "en pessant" target, false if it's not
    private boolean enPessantTarget;
    public Pawn(int[] position, boolean isWhite) {
        super(position,isWhite);
        startPosition=position.clone();
        enPessantTarget=false;
        this.typePiece="P";
    }

    public List<int[]> movement(List<int[]> occupiedSpaces){
        List<int[]> movements= new ArrayList<>();
        int[] newPosition;

        //Normal Movement
        if(isWhite){
            newPosition=position.clone();
            newPosition[0]=newPosition[0]-1;
            if(noOccupiedAnalysisSpace(newPosition,occupiedSpaces)){
                movements.add(newPosition);
            }

            //First pawn movement (double space  movement)
            if(Arrays.equals(startPosition,position)){
                newPosition=position.clone();
                newPosition[0]=newPosition[0]-2;
                if(noOccupiedAnalysisSpace(newPosition,occupiedSpaces)){
                    movements.add(newPosition);
                }
            }
        }
        else{
            newPosition=position.clone();
            newPosition[0]=newPosition[0]+1;
            if(noOccupiedAnalysisSpace(newPosition,occupiedSpaces)){
                movements.add(newPosition);
            }

            //First pawn movement (double space  movement)
            if(Arrays.equals(startPosition,position)){
                newPosition=position.clone();
                newPosition[0]=newPosition[0]+2;
                if(noOccupiedAnalysisSpace(newPosition,occupiedSpaces)){
                    movements.add(newPosition);
                }
            }
        }
        return movements;
    }


    public List<int[]> possibleCaptureSpaces(List<int[]> occupiedSpaces){
        List<int[]> possibleCaptureSpaces=new ArrayList<>();
        int[] possibleCapture;
        possibleCapture=position.clone();
        if(isWhite) {

            possibleCapture[0]=possibleCapture[0]-1;
            possibleCapture[1]=possibleCapture[1]-1;
            if (!noOccupiedAnalysisSpace(possibleCapture,occupiedSpaces)){
                possibleCaptureSpaces.add(possibleCapture);
            }

            possibleCapture=position.clone();
            possibleCapture[0]=possibleCapture[0]-1;
            possibleCapture[1]=possibleCapture[1]+1;
            if (!noOccupiedAnalysisSpace(possibleCapture,occupiedSpaces)){
                possibleCaptureSpaces.add(possibleCapture);
            }

            possibleCapture=position.clone();
            possibleCapture[0]=possibleCapture[0]-1;
            return possibleCaptureSpaces;
        }else {
            possibleCapture[0]=possibleCapture[0]+1;
            possibleCapture[1]=possibleCapture[1]+1;
            if (!noOccupiedAnalysisSpace(possibleCapture,occupiedSpaces)){
                possibleCaptureSpaces.add(possibleCapture);
            }
            possibleCapture=position.clone();
            possibleCapture[0]=possibleCapture[0]+1;
            possibleCapture[1]=possibleCapture[1]-1;
            if (!noOccupiedAnalysisSpace(possibleCapture,occupiedSpaces)){
                possibleCaptureSpaces.add(possibleCapture);
            }
            return possibleCaptureSpaces;
        }

    }

    public boolean isEnPessantTarget() {
        return enPessantTarget;
    }

    public void setEnPessantTarget(boolean enPessantTarget) {
        this.enPessantTarget = enPessantTarget;
    }


}
