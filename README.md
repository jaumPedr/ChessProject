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

 -During a movement declaration, is firstly used the piece letter, then the destination square, example: Ra5 (Rook to a5).
 -In a pawn move case, is used only the destination square, example: e4 (Pawn to e4).

Captures

 -Durring a capture is declared a piece (or nothing if is a pawn), then is used the char 'x' and the captured position.
 Example: Nxe4 (Knigth captures e4).

 -During a pawn capture, before the 'x' is declared the used pawn column. That occurs to disambiguate a pawn capture.
 Example: exd5 (the pawn in the column 'e' captures the position d5).

 - En Pessant is a special chess move made against another pawn. It is similar to a pawn capture, but in the capture notation is used the position where the pawn will move.
 Example: exd6 (Knowing that's a "en pessant", the pawn in the column 'e' captures a last turn moved twice pawn next to him, and moves to d6).

Redudant movement

 -When two or more pieces are able to make a move that is redundant, to disassemble a redundant move is used a column and/or row specification of the used piece.
 Example: Two rooks can make the d4 capture, Rgxd4 (Rook in the 'g' column captures the d4 square); ; R5xd4 (Rook in the '5' row captures the d4 square); Rg5xd4 (Rook in the "g5" square 
 captures the d4 position).

 -Whe a action is redudant, the action is not made.

Pawn Promotion

 -When a pawn reaches the extreme opposite row on the board, it is promoted. At the end of the notation it is declared which piece the pawn will promote.
 Example: e8Q (Pawn move to e8 and promotes to a Queen).

 -When the promotion is not declared the action does not occurs.







////////

- (Pt-Br)


Um simples simulador de uma partida de xadrez baseado em texto, feito usando linguagem java, o programa recebe notações de movimentos de xadrez e converte em ações no tabuleiro.

Notação Algébrica do xadrez:

Nomeando os espaços no tabuleiro

 - Os espaços do xadrez são divididos em linhas enumeradas de 1-8 e colunas nomeadas de a-h, cada posição do tabuleiro é declarado por uma união {Letra,Numero}, exemplo: d6.

Nomeando as peças

  - As peças em jogo são relacionadas a uma letra, com exerção do peão;

   - K- Rei (King)
   
   - Q- Rainha (Queen)
   
   - B- Bispo (Bishop)
   
   - N- Cavalo (Knight)
   
   - R- Torre (Rook)

Nomeando Movimentos

  - Durante a declaração de um movimento, é colocado a letra da peça na frente, exemplo: Ra5 (Torre move para a5).
  - Caso seja movido um peão, não é colocado nada antes da declaração do movimento, exemplo: a4 (Peão se move para a4)

Capturas

 - Durante uma captura é declarado a peça (ou nada no caso do peão), é posto a letra 'x' e a posição na qual a peça fará a captura
   Exemplo: Nxe4 (Cavalo captura perça em e4)

  - Durante a captura com peões, deve-se antes do 'x' declarar qual coluna do peão usado para a captura, diferente do movimento de peões, na captura pode ocorrer redundância, por isso
  é declarado a especificação da coluna na qual o peão está.
  Exemplo: exd5 (peão na coluna 'e' captura peça na posição d5)

  - En Pessant é uma captura especial que o peão pode realizar contra outro peão, na declaração do en pessant devese por a posição na qual o peão ira se mover e não da peça capturada
  Exemplo: exd6 (Como sabemos que isso se trata de um en pessant, peão na coluna e, captura peão adversário e se move para d6)

Redundância de Movimentos

  - Quando há dois ou mais possíveis peças para executar um movimento ou captura é posto logo após a letra da peça uma especificação de qual coluna ou linha (podendo usar ambas se necessárrio)
Exemplo: Caso 2 ou mais torres possam realizar a captura em d4, Rgxd4 (Torre na coluna g realizando a captura); R5xd4 (Torre na linha 5 realizando a captura); Rg5xd4 (Torre em g5 fazendo a captura)
  
  - A ação da peça não ocorre caso haja redundância na notação.

Promoção de  peão 

  - Ao chegar no extremo oposto do tabuleiro, o peão é promovido a outra peça, após a ação que levou à promoção do peão, é posto a letra de qual peça ele ira se tornar
  Exemplo: e8Q (Peão se move para e8 e vira uma Rainha); dxe8Q (Peão na coluna d, captura e8 e se torna uma rainha)

  - Caso não seja posto a peça que o peão é promovido, a ação não ocorre.

"Roque"

  - Movimento especial no xadrez no qual o rei anda duas casas em direção a uma das torres e a torre equivalente se move até a posição que o rei pulou.
  - Esse movimento só ocorre se o rei n tiver se movido, nem a torre escolhida para o movimento, o rei não esteja em check, e não haja peças entre o rei e a torre.

  0-0
  
  -Notação para o roque no lado do rei

  0-0-0 
  
  - Notação para o roque no lado da rainha

Check

  - O programa avisa quando é feito check contra o advesário

Fim de Jogo

  - O programa encerra o jogo quando um dos reis é capturado


