package ChessGameMaster;

import GamesPieces.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class GameMaster {


    private Board board= new Board();
    private boolean turnWhite=true;

    public void chessMoveCadastration(String notation){

        //chess notation pattern;
        //(START)* [KQRBN]?- piece type, if none, the piece is a pawn
        //([a-h]?)([1-8]?)- line and colum specification, useful for disambiguating moves
        //(x?) - is capture or not
        //([a-h][1-8]) move destiny ou capture target
        //([KQRBN]?) *(END) -  pawn promotion
        Pattern patternNotation=Pattern.compile("([KQRBN]?)([a-h]?)([1-8]?)(x?)([a-h][1-8])([KQRBN]?)");
        Matcher matcher=patternNotation.matcher(notation);
        ChessMove chessMove= new ChessMove();

        if(matcher.matches()) {
            matcher.reset();

            while (matcher.find()) {

                //identify piece type
                if (matcher.group(1).isEmpty()) {
                    chessMove.setPiece("P");
                } else {
                    chessMove.setPiece(matcher.group(1));
                }

                //identify colum specification
                chessMove.setColumnSpecification(matcher.group(2));

                //identify line specification
                chessMove.setLineSpecification(matcher.group(3));

                //identify if a capture
                chessMove.setCapture(matcher.group(4).equals("x"));

                //identify new position
                chessMove.setNewPosition(matcher.group(5));

                if (!matcher.group(6).isEmpty()) {
                    if (chessMove.getPiece().equals("P")) {
                        chessMove.setPawnPromotion(matcher.group(6));
                    } else {
                        return;
                    }
                }
            }
            chessMoveValidation(chessMove);
            return;
        }

        //if not a regular chess notation, the notation can be a castling move
        //0-0 -Kingside castling
        //0-0-0 -Queenside castling
        patternNotation=Pattern.compile("(0-0|0-0-0)");
        matcher=patternNotation.matcher(notation);


            if(matcher.matches()){

                if(matcher.group(1).equals("0-0")){
                    chessMove.setKingsideCastling(true);
                }else {
                    chessMove.setQueensideCastling(true);
                }
                chessMoveValidation(chessMove);
                return;
            }


    }

    public void chessMoveValidation(ChessMove chessMove){

        //castling kingside
        if(chessMove.isKingsideCastling()){
            //white pieces castling
            if(turnWhite){
                //check if the spaces between the king start position and the rook in kingside start position
                if(!board.isInOccupiedSpaces(new int[]{7,5})&&!board.isInOccupiedSpaces(new int[]{7,6})){
                    //getting the white king
                    for(int i=0;i<board.getPieces().size();i++){
                        if(board.getPieces().get(i).getTypePiece().equals("K")&&board.getPieces().get(i).isWhite()&&!((King) board.getPieces().get(i)).isMoved()&&!kingInCheck(board.getPieces().get(i))){
                            //getting the white rook
                            for(int ii=0;ii<board.getPieces().size();ii++){
                                if(Arrays.equals(board.getPieces().get(ii).getPosition(), new int[]{7, 7})&&board.getPieces().get(ii).getTypePiece().equals("R")&&!((Rook)board.getPieces().get(ii)).isMoved()){
                                    //castling
                                    board.getPieces().get(i).setPosition(new int[]{7,6});
                                    board.getPieces().get(ii).setPosition(new int[]{7,5});
                                    passTurn();
                                    return;
                                }
                            }
                        }
                    }
                }
            //black pieces
            }else{
                //check if the spaces between the king start position and the rook in kingside start position
                if(!board.isInOccupiedSpaces(new int[]{0,5})&&!board.isInOccupiedSpaces(new int[]{0,6})){
                    //getting the black king
                    for(int i=0;i<board.getPieces().size();i++){
                        if(board.getPieces().get(i).getTypePiece().equals("K")&&!board.getPieces().get(i).isWhite()&&!((King) board.getPieces().get(i)).isMoved()&&!kingInCheck(board.getPieces().get(i))){
                            //getting the black rook
                            for(int ii=0;ii<board.getPieces().size();ii++){
                                if(Arrays.equals(board.getPieces().get(ii).getPosition(), new int[]{0, 7})&&board.getPieces().get(ii).getTypePiece().equals("R")&&!((Rook)board.getPieces().get(ii)).isMoved()){
                                    //castling
                                    board.getPieces().get(i).setPosition(new int[]{0,6});
                                    board.getPieces().get(ii).setPosition(new int[]{0,5});
                                    passTurn();
                                    return;
                                }
                            }
                        }
                    }
                }
            }
            return;
        }

        //castling queenside
        if(chessMove.isQueensideCastling()){
            //white pieces castling
            if(turnWhite){
                //check if the spaces between the king start position and the rook in queenside start position
                if(!board.isInOccupiedSpaces(new int[]{7,1})&&!board.isInOccupiedSpaces(new int[]{7,2})&&!board.isInOccupiedSpaces(new int[]{7,3})){
                    //getting the white king
                    for(int i=0;i<board.getPieces().size();i++){
                        if(board.getPieces().get(i).getTypePiece().equals("K")&&board.getPieces().get(i).isWhite()&&!((King) board.getPieces().get(i)).isMoved()&&!kingInCheck(board.getPieces().get(i))){
                            //getting the white rook
                            for(int ii=0;ii<board.getPieces().size();ii++){
                                if(Arrays.equals(board.getPieces().get(ii).getPosition(), new int[]{7, 0})&&board.getPieces().get(ii).getTypePiece().equals("R")&&!((Rook)board.getPieces().get(ii)).isMoved()){
                                    //castling
                                    board.getPieces().get(i).setPosition(new int[]{7,2});
                                    board.getPieces().get(ii).setPosition(new int[]{7,3});
                                    passTurn();
                                    return;
                                }
                            }
                        }
                    }
                }
            }

        }
        //black pieces
        else {
            //check if the spaces between the king start position and the rook in queenside start position
            if(!board.isInOccupiedSpaces(new int[]{0,1})&&!board.isInOccupiedSpaces(new int[]{0,2})&&!board.isInOccupiedSpaces(new int[]{0,3})){
                //getting the black king
                for(int i=0;i<board.getPieces().size();i++){
                    if(board.getPieces().get(i).getTypePiece().equals("K")&&!board.getPieces().get(i).isWhite()&&!((King) board.getPieces().get(i)).isMoved()&&!kingInCheck(board.getPieces().get(i))){
                        //getting the black rook
                        for(int ii=0;ii<board.getPieces().size();ii++){
                            if(Arrays.equals(board.getPieces().get(ii).getPosition(), new int[]{0, 0})&&board.getPieces().get(ii).getTypePiece().equals("R")&&!((Rook)board.getPieces().get(ii)).isMoved()){
                                //castling
                                board.getPieces().get(i).setPosition(new int[]{0,2});
                                board.getPieces().get(ii).setPosition(new int[]{0,3});
                                passTurn();
                                return;
                            }
                        }
                    }
                }
            }
        }

        List<Piece> possiblePiecesToAct = new ArrayList<>();
        //validation of a piece movement
        if(!chessMove.isCapture()){
            //get each piece in game
            for(int i=0;i<board.getPieces().size();i++){
                //get only the pieces with the command notation used type
                if(board.getPieces().get(i).getTypePiece().equals(chessMove.getPiece())){
                    //get every movement possibilities of each piece
                    for (int ii=0;ii<board.getPieces().get(i).movement(board.getOccupiedSpaces()).size();ii++){
                        //compare the movement possibility with the notation given one
                        if(Arrays.equals(board.getPieces().get(i).movement(board.getOccupiedSpaces()).get(ii),chessMove.notationToInt(chessMove.getNewPosition()))){
                            //Compare the movement possibility with the notation specification (if the specification exists)
                            //line specification
                            if((chessMove.getLineSpecification().isEmpty()||(board.getPieces().get(i).getPosition()[0] == chessMove.lineSpecificationToInt(chessMove.getLineSpecification().charAt(0))))){
                                //colum specification
                                if (chessMove.getColumnSpecification().isEmpty()||(board.getPieces().get(i).getPosition()[1] == chessMove.columSpecificationToInt(chessMove.getColumnSpecification().charAt(0)))){
                                    //add possible moves into a list, only if the piece color matches the turn player color
                                    if(board.getPieces().get(i).isWhite()==turnWhite){
                                        possiblePiecesToAct.add(board.getPieces().get(i));
                                    }
                                }
                            }

                        }

                    }
                }
            }
            //move if is the correct given move, and if the notation isn't redundant
            if(possiblePiecesToAct.size()==1){

                //verify en pessant and pawn promotion
                if(possiblePiecesToAct.get(0).getTypePiece().equals("P")){
                    //En pessant Target declaration,  this piece is now a target for an en pessant
                    if(enPessantConfirmation(possiblePiecesToAct.get(0),chessMove.notationToInt(chessMove.getNewPosition()))){
                        ((Pawn) possiblePiecesToAct.get(0)).setEnPessantTarget(true);
                    }else if(((Pawn) possiblePiecesToAct.get(0)).isEnPessantTarget()){
                        ((Pawn) possiblePiecesToAct.get(0)).setEnPessantTarget(false);
                    }
                    //Promotion
                    if(pawnPromotionConfirmation(possiblePiecesToAct.get(0),chessMove.notationToInt(chessMove.getNewPosition()))){
                        if(!chessMove.getPawnPromotion().isEmpty()){

                            board.promotion(possiblePiecesToAct.get(0),chessMove.getPawnPromotion(),chessMove.notationToInt(chessMove.getNewPosition()));
                            passTurn();
                            return;
                        }
                    }
                }


                possiblePiecesToAct.get(0).setPosition(chessMove.notationToInt(chessMove.getNewPosition()));
                //pass the turn
                passTurn();


                return;
            }
        }

        //capture validation
        if(chessMove.isCapture()){
            //get each piece in game
            for(int i=0;i<board.getPieces().size();i++){
                //get only the pieces with the command notation used type
                if(board.getPieces().get(i).getTypePiece().equals(chessMove.getPiece())&&((board.getPieces().get(i).isWhite())==turnWhite)){
                    //get every capture possibilities of each piece
                    for (int ii=0;ii<board.getPieces().get(i).possibleCaptureSpaces(board.getOccupiedSpaces()).size();ii++){
                        //compare the movement possibility with the notation given one

                        if(Arrays.equals(board.getPieces().get(i).possibleCaptureSpaces(board.getOccupiedSpaces()).get(ii),chessMove.notationToInt(chessMove.getNewPosition()))){
                            //Compare the movement possibility with the notation specification (if the specification exists)
                            //line specification
                            if((chessMove.getLineSpecification().isEmpty()||(board.getPieces().get(i).getPosition()[0] == chessMove.lineSpecificationToInt(chessMove.getLineSpecification().charAt(0))))){
                                //colum specification
                                if (chessMove.getColumnSpecification().isEmpty()||(board.getPieces().get(i).getPosition()[1] == chessMove.columSpecificationToInt(chessMove.getColumnSpecification().charAt(0)))){
                                    //a pawn capture must have a column specification
                                    if(chessMove.getColumnSpecification().isEmpty()&&board.getPieces().get(i).getTypePiece().equals("P")){
                                        return;
                                    }
                                    //add possible moves into a list, only if the piece color matches the turn player color and if the captured piece color is different from the moved piece color
                                    if(board.getPieces().get(i).isWhite()==turnWhite){

                                        for(int iii=0;iii<board.getPieces().size();iii++){
                                            if(Arrays.equals(chessMove.notationToInt(chessMove.getNewPosition()),board.getPieces().get(iii).getPosition())){
                                                if(board.getPieces().get(i).isWhite()!=board.getPieces().get(iii).isWhite()){
                                                    possiblePiecesToAct.add(board.getPieces().get(i));
                                                    break;
                                                }
                                            }
                                        }
                                    }

                                }

                            }

                        }

                    }
                    //en pessant, only if is a pawn, no other movements possible, and colum specification is correct
                    if(board.getPieces().get(i).getTypePiece().equals("P")&&possiblePiecesToAct.isEmpty()&&!chessMove.getColumnSpecification().isEmpty()&&board.getPieces().get(i).getPosition()[1]==chessMove.columSpecificationToInt(chessMove.getColumnSpecification().charAt(0))){
                        int[] enPessantAnalysis;

                        for (int ii = 0; ii < board.getPieces().size(); ii++) {
                            //find a en pessant target
                            if(board.getPieces().get(ii).getTypePiece().equals("P")&&((Pawn) board.getPieces().get(ii)).isEnPessantTarget()){
                                //find if the used piece is next to the en pessant target
                                enPessantAnalysis = board.getPieces().get(i).getPosition().clone();

                                enPessantAnalysis[1] = enPessantAnalysis[1] - 1;
                                if (Arrays.equals(board.getPieces().get(ii).getPosition(),enPessantAnalysis )){
                                    possiblePiecesToAct.add(board.getPieces().get(ii));
                                    break;
                                }

                                enPessantAnalysis = board.getPieces().get(i).getPosition().clone();
                                enPessantAnalysis[1] = enPessantAnalysis[1] + 1;

                                if (Arrays.equals(board.getPieces().get(ii).getPosition(),enPessantAnalysis )){
                                    possiblePiecesToAct.add(board.getPieces().get(ii));
                                    break;
                                }

                            }
                        }
                        //do the en pessant if exist a valid piece to act
                        if(possiblePiecesToAct.size()==1){
                            //get the used piece new position (pos-en pessant capture)
                            int[] enPessantCapture=possiblePiecesToAct.get(0).getPosition().clone();
                            if(turnWhite){
                                enPessantCapture[0]=enPessantCapture[0]-1;
                            }else {
                                enPessantCapture[0]=enPessantCapture[0]+1;
                            }
                            //compare if the possible piece to act is equals the used piece in notation
                            if(Arrays.equals(chessMove.notationToInt(chessMove.getNewPosition()),enPessantCapture)){
                                board.getPieces().get(i).setPosition(chessMove.notationToInt(chessMove.getNewPosition()));
                                board.getCapturedPieces().add(possiblePiecesToAct.get(0));
                                board.getPieces().remove(possiblePiecesToAct.get(0));
                                passTurn();
                                return;
                            }
                        }
                    }
                }
            }
            //move if is the correct given capture command, and if the notation isn't redundant
            if(possiblePiecesToAct.size()==1){


                for(int i=0;i<board.getPieces().size();i++){



                    //remove from play the captured piece
                    if(Arrays.equals(chessMove.notationToInt(chessMove.getNewPosition()),board.getPieces().get(i).getPosition())){

                        // if pawn promotion
                        if(possiblePiecesToAct.get(0).getTypePiece().equals("P")&&pawnPromotionConfirmation(possiblePiecesToAct.get(0),chessMove.notationToInt(chessMove.getNewPosition()))){
                            if(!chessMove.getPawnPromotion().isEmpty()){
                                //do the promotion
                                board.promotion(possiblePiecesToAct.get(0),chessMove.getPawnPromotion(),chessMove.notationToInt(chessMove.getNewPosition()));

                            }else {
                                return;
                            }
                        }
                        //capture piece
                        board.getCapturedPieces().add(board.getPieces().get(i));
                        board.getPieces().remove(i);

                        possiblePiecesToAct.get(0).setPosition(chessMove.notationToInt(chessMove.getNewPosition()));
                        passTurn();
                        return;
                    }
                }
            }
        }

    }

    public void passTurn(){

        //analysis of en pessant castling and check/mate situations
        for(int i=0;i<board.getPieces().size();i++){

            //if an adversary pawn is an en pessant target this turn, remove the en pessant target (an en pessant only occur the turn immediately after the two spaces advance
            if(board.getPieces().get(i).getTypePiece().equals("P")&&board.getPieces().get(i).isWhite()!=turnWhite&& ((Pawn) board.getPieces().get(i)).isEnPessantTarget()){
                ((Pawn) board.getPieces().get(i)).setEnPessantTarget(false);
            }

            //if a no moved rook, compare their position if the rook initial position (that occur for castling analysis)
            //white rooks
            if (board.getPieces().get(i).getTypePiece().equals("R")&&!((Rook)board.getPieces().get(i)).isMoved()){
                if(board.getPieces().get(i).isWhite()){
                    if(!Arrays.equals(board.getPieces().get(i).getPosition(),new int[]{7,0})){
                        if(!Arrays.equals(board.getPieces().get(i).getPosition(),new int[]{7,7})){
                            ((Rook) board.getPieces().get(i)).setMoved(true);
                        }

                    }
                //black rooks
                }else{
                    if(!Arrays.equals(board.getPieces().get(i).getPosition(),new int[]{0,0})){
                        if(!Arrays.equals(board.getPieces().get(i).getPosition(),new int[]{0,7})){
                            ((Rook) board.getPieces().get(i)).setMoved(true);
                        }

                    }
                }
            }

            //king in check analysis
            if (board.getPieces().get(i).getTypePiece().equals("K")){
                if(board.getPieces().get(i).isWhite()!=turnWhite){
                    if(kingInCheck(board.getPieces().get(i))){
                        printKingCheck();
                    }
                }

                //king had moved analysis (for castling purpose)
                if(!((King)board.getPieces().get(i)).isMoved()){
                    //white king
                    if(board.getPieces().get(i).isWhite()){
                        if(!Arrays.equals(board.getPieces().get(i).getPosition(),new int[]{7,4})){
                            ((King) board.getPieces().get(i)).setMoved(true);
                        }
                    //black king
                    }else{
                        if(!Arrays.equals(board.getPieces().get(i).getPosition(),new int[]{0,4})){
                            ((King) board.getPieces().get(i)).setMoved(true);
                        }
                    }
                }
            }
        }
        //end game analysis, if a king piece is captured the game ends
        for(int i=0;i<board.getCapturedPieces().size();i++){
            if(board.getCapturedPieces().get(i).getTypePiece().equals("K")){
                if(board.getCapturedPieces().get(i).isWhite()){
                    System.out.println("Blacks Wins");
                    System.exit(0);
                }else {
                    System.out.println("Whites Wins");
                    System.exit(0);
                }
            }
        }


        turnWhite=!turnWhite;
    }

    public boolean kingInCheck(Piece king){
        for(int i=0;i<board.getPieces().size();i++){
            for (int ii=0;ii<board.getPieces().get(i).possibleCaptureSpaces(board.getOccupiedSpaces()).size();ii++){
                if(board.getPieces().get(i).isWhite()!=king.isWhite()&&Arrays.equals(board.getPieces().get(i).possibleCaptureSpaces(board.getOccupiedSpaces()).get(ii),king.getPosition())){
                    return true;
                }
            }
        }
        return false;
    }

    public void printKingCheck(){
        System.out.println("CHECK");
    }

    public boolean enPessantConfirmation(Piece pawn, int[] newPosition){
        if(pawn.isWhite()){
            return pawn.getPosition()[0]==6 && newPosition[0]==4;
        }else{
            return pawn.getPosition()[0]==1 && newPosition[0]==3;
        }
    }

    public boolean pawnPromotionConfirmation(Piece pawn, int[] newPosition){
        if(pawn.isWhite()){
            return newPosition[0]==0;
        }else{
            return newPosition[0]==7;
        }

    }

    public void turnStart(){
        if(turnWhite){
            System.out.println("\n\nWhite to move");
        }else {
            System.out.println("\n\nBlack to move");
        }
    }

    public Board getBoard() {
        return board;
    }

}
