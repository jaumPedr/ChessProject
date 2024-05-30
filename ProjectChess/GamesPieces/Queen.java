package GamesPieces;

import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece {
    public Queen(int[] position, boolean isWhite) {
        super(position, isWhite);
        this.typePiece="Q";
    }


    public List<int[]> movement(List<int[]> occupiedSpaces) {
        List<int[]> movements = new ArrayList<>();


        //Let's consider Queen movements equals the union of rook movements and bishop movements
        Rook rockTypeMove= new Rook(this.position,this.isWhite);
        movements.addAll(rockTypeMove.movement(occupiedSpaces));

        Bishop bishopTypeMove= new Bishop(this.position,this.isWhite);
        movements.addAll(bishopTypeMove.movement(occupiedSpaces));


        return movements;
    }


    @Override
    public List<int[]> possibleCaptureSpaces(List<int[]> occupiedSpaces) {


        List<int[]> captureSpaces= new ArrayList<>();

        //Let's consider Queen captures equals the union of rook captures and bishop captures
        Rook rockTypeMove= new Rook(this.position,this.isWhite);
        captureSpaces.addAll(rockTypeMove.possibleCaptureSpaces(occupiedSpaces));

        Bishop bishopTypeMove= new Bishop(this.position,this.isWhite);
        captureSpaces.addAll(bishopTypeMove.possibleCaptureSpaces(occupiedSpaces));

        return captureSpaces;
    }

}
