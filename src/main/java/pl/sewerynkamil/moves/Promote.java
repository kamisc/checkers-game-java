package pl.sewerynkamil.moves;

import pl.sewerynkamil.board.Board;
import pl.sewerynkamil.game.Controller;
import pl.sewerynkamil.pieces.Piece;
import pl.sewerynkamil.pieces.PositionsPieces;

import java.util.HashSet;
import java.util.Set;

public class Promote {

    Board board;
    Controller controller;

    private Set<PositionsPieces> possibleWhitePromote = new HashSet<>();
    private Set<PositionsPieces> possibleBlackPromote = new HashSet<>();

    public Promote(Board board, Controller controller) {
        this.board = board;
        this.controller = controller;
    }

    public void promoteWhite(){
        calculateWhitePromote();
        for(PositionsPieces position : possibleWhitePromote){
            /*board.removePieceFromBoard(position);
            board.addPieceOnBoard(position, board.getWhitePieces().getWhiteCrownImage());
            board.getWhitePieces().removePieceFromMap(position);
            board.getWhitePieces().addPieceToMap(position, new Piece(Piece.Color.WHITE, Piece.Type.QUEEN));*/
        }
        possibleWhitePromote.clear();
    }

    private void calculateWhitePromote(){
       /* for(PositionsPieces position : board.getWhitePieces().getWhitePiecesMap().keySet()){
            if(position.getRow() == 0 && board.getWhitePieces().getWhitePiecesMap().get(position).getPieceColor() == Piece.Color.WHITE){
                possibleWhitePromote.add(position);
            }
        }*/
    }

    public void promoteBlack(){
        calculateBlackPromote();
        for(PositionsPieces position : possibleBlackPromote){
        /*    board.removePieceFromBoard(position);
            board.addPieceOnBoard(position, board.getBlackPieces().getBlackCrownImage());
            board.getBlackPieces().removePieceFromMap(position);
            board.getBlackPieces().addPieceToMap(position, new Piece(Piece.Color.BLACK, Piece.Type.QUEEN));*/
        }
        possibleWhitePromote.clear();
    }

    private void calculateBlackPromote(){
        /*for(PositionsPieces position : board.getBlackPieces().getBlackPiecesMap().keySet()){
            if(position.getRow() == 7 && board.getBlackPieces().getBlackPiecesMap().get(position).getPieceColor() == Piece.Color.BLACK){
                possibleBlackPromote.add(position);
            }
        }*/
    }

}
