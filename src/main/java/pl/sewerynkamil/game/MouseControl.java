package pl.sewerynkamil.game;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import pl.sewerynkamil.board.Board;
import pl.sewerynkamil.moves.KickMoves;
import pl.sewerynkamil.moves.KickScanner;
import pl.sewerynkamil.moves.NormalMoves;
import pl.sewerynkamil.pieces.Piece;
import pl.sewerynkamil.pieces.PositionsPieces;

public class MouseControl {

    private Board board;
    private PositionsPieces pickedPosition;

    private NormalMoves normalMoves;
    private KickMoves kickMoves;
    private KickScanner kickScanner;

    private boolean playerTurn = true;
    private boolean computerTurn = false;
    private boolean isPicked = false;

    public MouseControl(Board board, NormalMoves normalMoves /*KickScanner kickScanner*/, KickMoves kickMoves) {
        this.board = board;
        this.normalMoves = normalMoves;
        this.kickScanner = new KickScanner(board);
        this.kickMoves = kickMoves;
    }

    private EventHandler<MouseEvent> mouseClick = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            PositionsPieces clickPosition = new PositionsPieces((int) ((event.getX() - 59) / 62), (int) ((event.getY() - 59) / 62));

            if(!clickPosition.isValidPosition()) {
                System.out.println("Wrong place!");
            }

            if(playerTurn){

                kickScanner.clear();

                kickScanner.calculateAllPossibleWhiteKicks();

                if(isPicked){
                    // Change pick piece
                    if(!pickedPosition.equals(clickPosition)
                            && !board.isFieldNull(clickPosition)
                            && board.getPiece(clickPosition).getPieceColor() == Piece.Color.WHITE){

                        board.pickPiece(pickedPosition, false);
                        pickedPosition = clickPosition;
                        board.pickPiece(clickPosition, true);
                        normalMoves.normalMoveCalculator(clickPosition, true);

                        // Move piece
                    } else if(normalMoves.getPossibleMoves().contains(clickPosition)) {

                        board.movePiece(clickPosition, pickedPosition);

                        pickedPosition = null;
                        isPicked = false;
                        playerTurn = false;
                        computerTurn = true;

                    }
                } else {
                    // Pick first piece
                    if(!board.isFieldNull(clickPosition)
                            && board.getPiece(clickPosition).getPieceColor() == Piece.Color.WHITE){

                        isPicked = true;

                        pickedPosition = clickPosition;
                        board.pickPiece(clickPosition, true);
                        normalMoves.normalMoveCalculator(clickPosition, true);
                    }
                }
            }

            if(computerTurn){
                kickScanner.clear();

                kickScanner.calculateAllPossibleBlackKicks();

                if(isPicked){
                    // Change pick piece
                    if(!pickedPosition.equals(clickPosition)
                            && !board.isFieldNull(clickPosition)
                            && board.getPiece(clickPosition).getPieceColor() == Piece.Color.BLACK){

                        board.pickPiece(pickedPosition, false);
                        pickedPosition = clickPosition;
                        board.pickPiece(clickPosition, true);
                        normalMoves.normalMoveCalculator(clickPosition, false);

                    // Move piece
                    } else if(normalMoves.getPossibleMoves().contains(clickPosition)) {

                        board.movePiece(clickPosition, pickedPosition);

                        pickedPosition = null;
                        isPicked = false;
                        playerTurn = true;
                        computerTurn = false;
                        kickScanner.clear();
                    }
                } else {
                    // Pick first piece
                    if(!board.isFieldNull(clickPosition)
                            && board.getPiece(clickPosition).getPieceColor() == Piece.Color.BLACK){

                        isPicked = true;

                        pickedPosition = clickPosition;
                        board.pickPiece(clickPosition, true);
                        normalMoves.normalMoveCalculator(clickPosition, false);
                    }
                }
            }

            System.out.println(kickScanner.getAllPiecesWhichKick());

        }
    };

    public EventHandler<MouseEvent> getMouseClick() {
        return mouseClick;
    }
}