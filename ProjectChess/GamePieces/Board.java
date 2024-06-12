package GamePieces;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {
    private List<Piece> pieces;
    private List<Piece> capturedPieces;






    public Board(){
        pieces= new ArrayList<>();
        capturedPieces= new ArrayList<>();

        Rook rook = new Rook(new int[]{0, 0},false);
        pieces.add(rook);
        rook=new Rook(new int[]{0,7},false);
        pieces.add(rook);
        rook=new Rook(new int[]{7,0},true);
        pieces.add(rook);
        rook=new Rook(new int[]{7,7},true);
        pieces.add(rook);

        Horse horse=new Horse(new int[]{0, 1},false);
        pieces.add(horse);
        horse=new Horse(new int[]{0, 6},false);
        pieces.add(horse);
        horse=new Horse(new int[]{7, 1},true);
        pieces.add(horse);
        horse=new Horse(new int[]{7, 6},true);
        pieces.add(horse);

        Bishop bishop=new Bishop(new int[]{0, 2},false);
        pieces.add(bishop);
        bishop=new Bishop(new int[]{0, 5},false);
        pieces.add(bishop);
        bishop=new Bishop(new int[]{7, 2},true);
        pieces.add(bishop);
        bishop=new Bishop(new int[]{7, 5},true);
        pieces.add(bishop);

        King king=new King(new int[]{0,4},false);
        pieces.add(king);
        king=new King(new int[]{7,4},true);
        pieces.add(king);

        Queen queen=new Queen(new int[] {0,3},false);
        pieces.add(queen);
        queen=new Queen(new int[] {7,3},true);
        pieces.add(queen);

        Pawn pawn;
        for(int i=0;i<8;i++){
            pawn=new Pawn(new int[]{1,i},false);
            pieces.add(pawn);
            pawn=new Pawn(new int[]{6,i},true);
            pieces.add(pawn);
        }
    }

    public  void  ShowBoard(){
        //ANSI color codes
        String RESET = "\u001B[0m";
        String WHITE_PIECE = "\033[0;97m";
        String BLACK_PIECE = "\u001B[30m";
        String YELLOW_BACKGROUND = "\033[43m";
        String CYAN_BACKGROUND = "\033[46m";

        String colorState;

        boolean boardColorState=true;

        boolean isEmpty=true;

        System.out.println("Captured Pieces:");
        for(int i=0;i<capturedPieces.size();i++){
            if(capturedPieces.get(i).isWhite()){
                System.out.print(WHITE_PIECE+capturedPieces.get(i).getTypePiece()+" "+RESET);
            }
        }

        System.out.println();

        for(int i=0;i<capturedPieces.size();i++){
            if(!capturedPieces.get(i).isWhite()){
                System.out.print(BLACK_PIECE+capturedPieces.get(i).getTypePiece()+" "+RESET);
            }
        }

        System.out.print("\n\n");

        System.out.println("  a  b  c  d  e  f  g  h");

        for(int i=0;i<8;i++){
            System.out.print(Math.abs(i-8));
            for(int in=0;in<8;in++){
                isEmpty=true;
                if(boardColorState){
                    colorState=YELLOW_BACKGROUND;
                }else{
                    colorState=CYAN_BACKGROUND;
                }
                for(int inn=0;inn< pieces.size();inn++){
                    if(Arrays.equals(pieces.get(inn).getPosition(), new int[]{i, in})){
                        if(pieces.get(inn).isWhite){
                            System.out.printf(WHITE_PIECE+colorState+" "+pieces.get(inn).typePiece+" "+RESET);
                        }else{
                            System.out.printf(BLACK_PIECE+colorState+" "+pieces.get(inn).typePiece+" "+RESET);
                        }
                        isEmpty=false;
                        boardColorState=!boardColorState;
                    }
                }
                if (isEmpty) {
                    System.out.print(colorState+"   "+RESET);
                    isEmpty=!isEmpty;
                    boardColorState=!boardColorState;
                }

            }
            boardColorState=!boardColorState;
            System.out.println();
        }
    }

    public void promotion(Piece piece, String pieceType, int[] newPosition){
        Piece pawnPromotion = null;
        for(int i=0;i<pieces.size();i++){
            if(pieces.get(i).equals(piece)){
                if(pieceType.equals("R")){
                    pawnPromotion= new Rook(newPosition,piece.isWhite());
                }
                if(pieceType.equals("N")){
                    pawnPromotion= new Horse(newPosition,piece.isWhite());
                }
                if(pieceType.equals("B")){
                    pawnPromotion= new Bishop(newPosition,piece.isWhite());
                }
                if(pieceType.equals("Q")){
                    pawnPromotion= new Queen(newPosition,piece.isWhite());
                }
                pieces.remove(i);
                pieces.add(pawnPromotion);
                return;
            }
        }
    }

    public List<int[]> getOccupiedSpaces(){

        List<int[]> occupiedSpaces=new ArrayList<>();
        //get every occupied space
        for(int i=0;i<pieces.size();i++){
            occupiedSpaces.add(pieces.get(i).getPosition());
        }
        return occupiedSpaces;
    }

    public boolean isInOccupiedSpaces(int[] position){
        for (int i=0;i<getOccupiedSpaces().size();i++){
            if(Arrays.equals(position,getOccupiedSpaces().get(i))){
                return true;
            }
        }
        return false;
    }


    public void setPieces(List<Piece> pieces) {
        this.pieces = pieces;
    }

    public List<Piece> getCapturedPieces() {
        return capturedPieces;
    }

    public void setCapturedPieces(List<Piece> capturedPieces) {
        this.capturedPieces = capturedPieces;
    }




    public List<Piece> getPieces() {
        return pieces;
    }

}
