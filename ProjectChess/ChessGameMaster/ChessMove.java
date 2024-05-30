package ChessGameMaster;

public class ChessMove {
    //translate a notation to an action

    private String piece;
    private String lineSpecification="";
    private String columnSpecification ="";
    private boolean capture;
    private String newPosition;
    private String pawnPromotion="";
    private boolean kingsideCastling=false;
    private boolean queensideCastling=false;
    private boolean surrender=false;

    public int[] notationToInt(String notation){
        int yAlim=columSpecificationToInt(notation.charAt(0));
        int xAlim= lineSpecificationToInt(notation.charAt(1));
        return new int[]{xAlim,yAlim};
    }
    public int lineSpecificationToInt(char line){
        return Math.abs(Integer.parseInt(""+line)-8);
    }

    public int columSpecificationToInt(char colum){
        return colum - 'a';
    }

    public String getLineSpecification() {
        return lineSpecification;
    }

    public void setLineSpecification(String lineSpecification) {
        this.lineSpecification = lineSpecification;
    }

    public String getColumnSpecification() {
        return columnSpecification;
    }

    public void setColumnSpecification(String columnSpecification) {
        this.columnSpecification = columnSpecification;
    }

    public boolean isCapture() {
        return capture;
    }

    public void setCapture(boolean capture) {
        this.capture = capture;
    }

    public String getNewPosition() {
        return newPosition;
    }

    public void setNewPosition(String newPosition) {
        this.newPosition = newPosition;
    }



    public String getPiece() {
        return piece;
    }

    public void setPiece(String piece) {
        this.piece = piece;
    }

    public String getPawnPromotion() {
        return pawnPromotion;
    }

    public void setPawnPromotion(String pawnPromotion) {
        this.pawnPromotion = pawnPromotion;
    }

    public boolean isKingsideCastling() {
        return kingsideCastling;
    }

    public void setKingsideCastling(boolean kingsideCastling) {
        this.kingsideCastling = kingsideCastling;
    }

    public boolean isQueensideCastling() {
        return queensideCastling;
    }

    public void setQueensideCastling(boolean queensideCastling) {
        this.queensideCastling = queensideCastling;
    }

    public boolean isSurrender() {
        return surrender;
    }

    public void setSurrender(boolean surrender) {
        this.surrender = surrender;
    }
}
