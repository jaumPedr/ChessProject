A simple text-based chess game simulator using Java, the code reads from the user a chess notation and translates to an action on the board. 

algebraic Chess Notation:

Naming the board positions

 - Every square is divided in vertical columns (a-b) and horizontal rows (1-8); a square position is named by the union {letter, number}, example: d6 (column d, row 6).

Namming the pieces

 - Every piece, other than pawn, is related to a letter.

 - K- King
 - Q- Queen
 - B- Bishop
 - N- Knight
 - R- Rook

Namming movements

 - During a movement declaration, is firstly used the piece letter, then the destination square, example: Ra5 (Rook to a5).
 - In a pawn move case, is used only the destination square, example: e4 (Pawn to e4).

Captures

 - Durring a capture is declared a piece (or nothing if is a pawn), then is used the char 'x' and the captured position.
 Example: Nxe4 (Knigth captures e4).

 - During a pawn capture, before the 'x' is declared the used pawn column. That occurs to disambiguate a pawn capture.
 Example: exd5 (the pawn in the column 'e' captures the position d5).

 - En Pessant is a special chess move made against another pawn. It is similar to a pawn capture, but in the capture notation is used the position where the pawn will move.
 Example: exd6 (Knowing that's a "en pessant", the pawn in the column 'e' captures a last turn moved twice pawn next to him, and moves to d6).

Redudant movement

 - When two or more pieces are able to make a move that is redundant, to disassemble a redundant move is used a column and/or row specification of the used piece.
 Example: Two rooks can make the d4 capture, Rgxd4 (Rook in the 'g' column captures the d4 square); ; R5xd4 (Rook in the '5' row captures the d4 square); Rg5xd4 (Rook in the "g5" square 
 captures the d4 position).

 - Whe a action is redudant, the action is not made.

Pawn Promotion

 - When a pawn reaches the extreme opposite row on the board, it is promoted. At the end of the notation it is declared which piece the pawn will promote.
 Example: e8Q (Pawn move to e8 and promotes to a Queen).

 - When the promotion is not declared the action does not occurs.

Castling

 - Special movement in chess that the king moves 2 spaces in direction of a unmoved rook and the Rook move to the space that the king skiped.
 -Castling only occurs if the king and the rook have not been moved and if the positions between those two are unoccupied.

 0-0
 - Notation to a king side castling.

 0-0-0
 - Notation to a queen side castling.

Check
- The code will anounce if a check occors

End game
 - The game ends if a king is captured


Code:

  Pakage GamePieces:

  - Piece Class


  Every piece extends the Piece class.

        //Every piece have a type
        protected String typePiece;
        //Every piece or is a white piece (isWhite) or is a black piece (!isWhite)
        protected boolean isWhite;
        //The piece position
        protected int[] position;

  Every piece have a diferent movement and capture pattern Overriding movement and possibleCaptureSpaces methods, the capture method is a possibility because is stil needed confirmation if the piece occupying the target square is a opposite color piece, that analysis is made in the GameMaster Class.

    public abstract List<int[]> movement(List<int[]> occupiedSpaces);
    public abstract List<int[]> possibleCaptureSpaces(List<int[]> occupiedSpaces);

  Both movement and possibleCaptureSpaces uses a list of occupied Spaces, a movement is only valid if its destination is not a occupied square and a capture can be valid if the targeted space is occupied. The superClass Piece have the noOccupiedAnalysisSpace method, every subClass use noOccupiedAnalysisSpace in the moviment or capture method.

    //true if the space is not occupied and false if is
    public boolean noOccupiedAnalysisSpace(int[] analysisSpace, List<int[]> occupiedSpaces){
        for(int i=0;i<occupiedSpaces.size();i++){
            if(Arrays.equals(analysisSpace,occupiedSpaces.get(i))){
                return false;
            }
        }
        return true;
    }

- Pawn Class

Uniques variables:

    //Start position, equals the initialized position, it's used in "en pessant" analysis and in the double movement action
    private int[] startPosition;
    //True if is a en pessant target, false if it's not
    private boolean enPessantTarget;


movement method:

A pawn movement is a white movement or a black movement, and a pawn movement can be a double movement if the pawn is in their starting position, every pawn only moves forward to the advesary side, moving normally one square and can be moved two squares if it's the first move.

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

possibleCaptureSpaces method:


A pawn capture is a white capture or a black capture, every pawn captures in the front diagonal, the front being the opposite board side, en pessant being a no-common move, it's not analyzed in that method, a "en pessant" analysis occurs in the GameMaster Class.

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
   

