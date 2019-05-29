package pl.sewerynkamil.game;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import pl.sewerynkamil.board.Board;
import pl.sewerynkamil.moves.NormalKick;
import pl.sewerynkamil.moves.KickScanner;
import pl.sewerynkamil.moves.NormalMoves;
import pl.sewerynkamil.moves.Promote;
import pl.sewerynkamil.pieces.Piece;
import pl.sewerynkamil.pieces.PositionsPieces;

public class MouseControl {

    private Board board;
    private NormalMoves normalMoves;
    private NormalKick normalKick;
    private KickScanner kickScanner;
    private Promote promote;

    private PositionsPieces pickedPosition;

    private boolean playerTurn = true;
    private boolean computerTurn = false;
    private boolean isPicked = false;
    private boolean isKick = false;

    public MouseControl(Board board, NormalMoves normalMoves /*KickScanner kickScanner*/, NormalKick normalKick, Promote promote) {
        this.board = board;
        this.normalMoves = normalMoves;
        this.normalKick = normalKick;
        this.promote = promote;

        this.kickScanner = new KickScanner(board);
    }

    private EventHandler<MouseEvent> mouseClick = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            PositionsPieces clickPosition = new PositionsPieces((int) ((event.getX() - 59) / 62), (int) ((event.getY() - 59) / 62));

            if(!clickPosition.isValidPosition()) {
                System.out.println("Wrong place!");
            }

            if(playerTurn){

                kickScanner.calculateAllPossibleWhiteKicks();

                if(!kickScanner.getAllPossibleKicks().isEmpty()){

                    isKick = true;

                    if(kickScanner.getAllPiecesWhichKick().contains(clickPosition)
                            && board.getPiece(clickPosition).getPieceColor() == Piece.Color.WHITE){

                        if(pickedPosition != null){
                            board.pickPiece(pickedPosition, false);
                        }

                        board.pickPiece(clickPosition, true);
                        pickedPosition = clickPosition;

                        normalKick.kickMovesCalculator(pickedPosition);

                    } else if(isKick) {

                        if(normalKick.getPossibleKickMoves().contains(clickPosition)){
                            board.kickPiece(clickPosition, pickedPosition);
                            pickedPosition = clickPosition;

                            if(normalKick.getPossibleKickMoves().isEmpty()){
                                isKick = false;
                                pickedPosition = null;
                                playerTurn = false;
                                computerTurn = true;

                                promote.promote();

                                normalKick.getPossibleKickMoves().clear();
                                kickScanner.clear();
                            }
                        }

                    } else {
                        promote.promote();

                        pickedPosition = null;
                        playerTurn = false;
                        computerTurn = true;
                        isKick = false;

                        normalKick.getPossibleKickMoves().clear();
                        kickScanner.clear();
                    }
                } else {

                    if(!board.isFieldNull(clickPosition)
                            && board.getPiece(clickPosition).getPieceColor() == Piece.Color.WHITE){
                        if(pickedPosition != null){
                            board.pickPiece(pickedPosition, false);
                        }
                        board.pickPiece(clickPosition, true);
                        pickedPosition = clickPosition;

                        normalMoves.normalMoveCalculator(clickPosition, true);

                    } else if(normalMoves.getPossibleMoves().contains(clickPosition)) {
                        board.movePiece(clickPosition, pickedPosition);

                        promote.promote();

                        pickedPosition = null;
                        playerTurn = false;
                        computerTurn = true;

                        promote.promote();

                        normalMoves.getPossibleMoves().clear();
                    }
                }
            }

            if(computerTurn){

                kickScanner.calculateAllPossibleBlackKicks();

                if(!kickScanner.getAllPossibleKicks().isEmpty()){

                    isKick = true;

                    if(kickScanner.getAllPiecesWhichKick().contains(clickPosition)
                            && board.getPiece(clickPosition).getPieceColor() == Piece.Color.BLACK){

                        if(pickedPosition != null){
                            board.pickPiece(pickedPosition, false);
                        }

                        board.pickPiece(clickPosition, true);
                        pickedPosition = clickPosition;

                        normalKick.kickMovesCalculator(pickedPosition);

                    } else if(isKick) {

                        if(normalKick.getPossibleKickMoves().contains(clickPosition)){
                            board.kickPiece(clickPosition, pickedPosition);
                            pickedPosition = clickPosition;

                            if(normalKick.getPossibleKickMoves().isEmpty()){
                                isKick = false;
                                pickedPosition = null;
                                playerTurn = true;
                                computerTurn = false;

                                promote.promote();

                                normalKick.getPossibleKickMoves().clear();
                                kickScanner.clear();
                            }
                        }

                    } else {
                        promote.promote();

                        pickedPosition = null;
                        playerTurn = true;
                        computerTurn = false;
                        isKick = false;

                        normalKick.getPossibleKickMoves().clear();
                        kickScanner.clear();
                    }
                } else {

                    if(!board.isFieldNull(clickPosition)
                            && board.getPiece(clickPosition).getPieceColor() == Piece.Color.BLACK){
                        if(pickedPosition != null){
                            board.pickPiece(pickedPosition, false);
                        }
                        board.pickPiece(clickPosition, true);
                        pickedPosition = clickPosition;

                        normalMoves.normalMoveCalculator(clickPosition, false);

                    } else if(normalMoves.getPossibleMoves().contains(clickPosition)) {
                        board.movePiece(clickPosition, pickedPosition);

                        promote.promote();

                        pickedPosition = null;
                        playerTurn = true;
                        computerTurn = false;

                        promote.promote();

                        normalMoves.getPossibleMoves().clear();
                    }
                }
            }

            System.out.println(board.getPiece(clickPosition));

            /*if(computerTurn){
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
            }*/
        }
    };

    public EventHandler<MouseEvent> getMouseClick() {
        return mouseClick;
    }
}