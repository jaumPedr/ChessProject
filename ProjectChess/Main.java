import ChessGameMaster.GameMaster;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        GameMaster game = new GameMaster();
        Scanner scanner = new Scanner(System.in);
        
        while (true){
            game.getBoard().ShowBoard();
            game.turnStart();
            game.chessMoveCadastration(scanner.nextLine());
            System.out.println("\n\nENTER to continue\nOr print 'EXIT' to close the chess game");
            if(scanner.nextLine().equals("EXIT")){
                break;
            }
        }

    }

}

//pawn capture column