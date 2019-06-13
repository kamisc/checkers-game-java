package pl.sewerynkamil.board;

import pl.sewerynkamil.game.Computer;
import pl.sewerynkamil.game.EndGame;
import pl.sewerynkamil.game.SaveLoadGame;
import pl.sewerynkamil.menu.LoadGame;
import pl.sewerynkamil.menu.Ranking;
import pl.sewerynkamil.menu.SaveGame;
import pl.sewerynkamil.moves.*;
import pl.sewerynkamil.pieces.BlackPieces;
import pl.sewerynkamil.pieces.Piece;
import pl.sewerynkamil.pieces.PositionsPieces;
import pl.sewerynkamil.pieces.WhitePieces;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Author Kamil Seweryn
 */

public class Board {

    private Map<PositionsPieces, Piece> board = new HashMap<>();

    private Set<PositionsPieces> possiblePromote = new HashSet<>();

    private BlackPieces blackPieces = new BlackPieces();
    private WhitePieces whitePieces = new WhitePieces();

    private NormalMoves normalMoves = new NormalMoves(this);
    private QueenMoves queenMoves = new QueenMoves(this);
    private NormalKicks normalKicks = new NormalKicks(this);
    private QueenKicks queenKicks = new QueenKicks(this);

    private KickScanner kickScanner;
    private QueenKickScanner queenKickScanner;
    private Computer computer;

    private EndGame endGame = new EndGame(this);

    private SaveLoadGame saveLoadGame = new SaveLoadGame(this);

    private SaveGame saveGame = new SaveGame(this);
    private LoadGame loadGame = new LoadGame();
    private Ranking ranking = new Ranking();

    private boolean turn = true;
    private boolean isKick = false;

    private PositionsPieces pickedPosition;

    public Board() {
        if(saveLoadGame.isFileExist()) {
            saveLoadGame.loadGame();
            saveLoadGame.removeFile();
            loadGame.loadInfo();
        } else {
            putAllPieces();
        }

        this.kickScanner = new KickScanner(this);
        this.queenKickScanner = new QueenKickScanner(this);
        this.computer = new Computer(this);
    }

    private void putAllPieces() {
        board.putAll(whitePieces.setUpPieces());
        board.putAll(blackPieces.setUpPieces());
    }

    public void addPieceToBoard(PositionsPieces position, Piece piece) {
        board.put(position, piece);
    }

    public void removePieceFromBoard(PositionsPieces position) {
        board.remove(position);
    }

    public Piece getPiece(PositionsPieces position) {
        return board.get(position);
    }

    public boolean isFieldNull(PositionsPieces position) {
        return board.get(position) == null;
    }

    public void pickPiece(PositionsPieces position, PositionsPieces oldPosition, boolean light) {
        Piece pieceNew = getPiece(position);
        Piece pieceOld = getPiece(oldPosition);

        if(oldPosition != null) {
            Graphics.removePiece(oldPosition);
            Graphics.addPiece(oldPosition, pieceOld, !light);
        }

        Graphics.removePiece(position);
        Graphics.addPiece(position, pieceNew, light);
    }

    public void movePiece(PositionsPieces newPosition, PositionsPieces oldPosition) {
        Piece piece = getPiece(oldPosition);

        Graphics.addPiece(newPosition, piece, false);
        Graphics.removePiece(oldPosition);

        movePieceOnBoard(newPosition, oldPosition, piece);
    }

    public void movePieceOnBoard(PositionsPieces newPosition, PositionsPieces oldPosition, Piece piece) {
        removePieceFromBoard(oldPosition);
        addPieceToBoard(newPosition, piece);
    }

    public void kickPiece(PositionsPieces newPosition, PositionsPieces oldPosition) {
        Piece piece = getPiece(oldPosition);

        PositionsPieces kickPosition = findOppositePosition(newPosition, normalKicks.getPossibleKicks(), queenKicks.getPossibleKicks());

        Graphics.addPiece(newPosition, piece, false);
        Graphics.removePiece(oldPosition);
        Graphics.removePiece(kickPosition);

        kickPieceFromBoard(newPosition, oldPosition, kickPosition, piece);

        normalKicks.kickMovesCalculator(newPosition);
        queenKicks.calculateAllPossibleQueenKicks(newPosition);

        if(!normalKicks.getPossibleKickMoves().isEmpty() && piece.getPieceType().isNormal()) {
            Graphics.removePiece(oldPosition);
            Graphics.addPiece(newPosition, piece, true);
        }

        if(!queenKicks.getPossibleKickMoves().isEmpty() && piece.getPieceType().isQueen()) {
            Graphics.removePiece(oldPosition);
            Graphics.addPiece(newPosition, piece, true);
        }
    }

    public void kickPieceFromBoard(PositionsPieces newPosition, PositionsPieces oldPosition, PositionsPieces kickPositon, Piece piece) {
        addPieceToBoard(newPosition, piece);
        removePieceFromBoard(oldPosition);
        removePieceFromBoard(kickPositon);
    }

    public PositionsPieces findOppositePosition(PositionsPieces position, Set<PositionsPieces> normalPosition, Set<PositionsPieces> queenPosition) {
        PositionsPieces upLeft = new PositionsPieces(position.getCol() - 1, position.getRow() - 1);

        if(normalPosition.contains(upLeft) || queenPosition.contains(upLeft)) {
            return upLeft;
        }

        PositionsPieces downLeft = new PositionsPieces(position.getCol() - 1, position.getRow() + 1);

        if(normalPosition.contains(downLeft) || queenPosition.contains(downLeft)) {
            return downLeft;
        }

        PositionsPieces upRight = new PositionsPieces(position.getCol() + 1, position.getRow() - 1);

        if(normalPosition.contains(upRight) || queenPosition.contains(upRight)) {
            return upRight;
        }

        PositionsPieces downRight = new PositionsPieces(position.getCol() + 1, position.getRow() + 1);

        if(normalPosition.contains(downRight) || queenPosition.contains(downRight)) {
            return downRight;
        }

        return null;
    }

    public void promote() {
        possiblePromote.clear();
        calculatePromote(board.keySet());

        for(PositionsPieces position : possiblePromote) {
            Piece piece = getPiece(position);

            if(piece.getPieceColor().isWhite() && piece.getPieceType().isNormal()) {
                Graphics.removePiece(position);
                Graphics.addPiece(position, new Piece(piece.getPieceColor(), Piece.Type.QUEEN), false);

                promoteOnBoard(position, piece);
            }

            if(piece.getPieceColor().isBlack() && piece.getPieceType().isNormal()) {
                Graphics.removePiece(position);
                Graphics.addPiece(position, new Piece(piece.getPieceColor(), Piece.Type.QUEEN), false);

                promoteOnBoard(position, piece);
            }
        }
    }

    public void promoteOnBoard(PositionsPieces position, Piece piece) {
        removePieceFromBoard(position);
        addPieceToBoard(position, new Piece(piece.getPieceColor(), Piece.Type.QUEEN));
    }

    public void calculatePromote(Set<PositionsPieces> positions) {

        Set<PositionsPieces> whites = positions.stream()
                .filter(position -> position.getRow() == 0)
                .filter(position -> getPiece(position).getPieceColor() == Piece.Color.WHITE)
                .collect(Collectors.toSet());

        Set<PositionsPieces> blacks = positions.stream()
                .filter(position -> position.getRow() == 7)
                .filter(position -> getPiece(position).getPieceColor() == Piece.Color.BLACK)
                .collect(Collectors.toSet());

        possiblePromote.addAll(whites);
        possiblePromote.addAll(blacks);
    }

    public void setDifficultyLevel(int difficulty) {
        computer.setDifficultyLevel(difficulty);
    }

    public void handleMove(PositionsPieces position) {

        if(turn) {

            System.out.println(computer.getDifficultyLevel());

            kickScanner.calculateAllPossibleWhiteKicks();
            queenKickScanner.calculateAllPossibleWhiteQueenKicks();

            if(!kickScanner.getAllPossibleKicks().isEmpty() || !queenKickScanner.getAllPossibleQueenKicks().isEmpty()) {

                if((kickScanner.getAllPiecesWhichKick().contains(position)
                        || queenKickScanner.getAllQueenPiecesWhichKick().contains(position))
                        && getPiece(position).getPieceColor().isWhite()
                        && !isKick) {

                    pickPiece(position, pickedPosition, true);
                    pickedPosition = position;

                    if(getPiece(position).getPieceType().isNormal()) {

                        queenKicks.clear();
                        normalKicks.kickMovesCalculator(position);

                    } else {

                        normalKicks.clear();
                        queenKicks.calculateAllPossibleQueenKicks(position);
                    }

                } else {

                    if(normalKicks.getPossibleKickMoves().contains(position)
                            && getPiece(pickedPosition).getPieceType().isNormal()) {

                        kickPiece(position, pickedPosition);
                        pickedPosition = position;

                        if(normalKicks.getPossibleKickMoves().isEmpty()) {

                            turn = false;

                            isKick = false;

                            endKick();

                        } else {

                            isKick = true;

                        }

                    } else if(queenKicks.getPossibleKickMoves().contains(position)
                            && getPiece(pickedPosition).getPieceType().isQueen()) {

                        kickPiece(position, pickedPosition);
                        pickedPosition = position;

                        if(queenKicks.getPossibleKickMoves().isEmpty()) {

                            turn = false;

                            isKick = false;

                            endKick();

                        } else {

                            isKick = true;

                        }
                    }
                }

            } else {

                if(!isFieldNull(position)
                        && getPiece(position).getPieceColor() == Piece.Color.WHITE) {

                    pickPiece(position, pickedPosition,true);
                    pickedPosition = position;

                    if(getPiece(position).getPieceType().isNormal()) {

                        normalMoves.normalMoveCalculator(position, true);

                    } else {

                        queenMoves.normalQueenMoveCalculator(position);

                    }

                } else if(normalMoves.getPossibleMoves().contains(position)
                        && pickedPosition != null) {

                    movePiece(position, pickedPosition);

                    turn = false;

                    endTurn();

                } else if(queenMoves.getPossibleQueenMoves().contains(position)
                        && pickedPosition != null) {

                    movePiece(position, pickedPosition);

                    turn = false;

                    endTurn();
                }
            }
        }

        if(!turn) {

            computerMove();

        }
    }

    public void computerMove() {

        do {

            if(computer.checkBlacksEnd()) {
                break;
            }

            kickScanner.calculateAllPossibleBlackKicks();
            queenKickScanner.calculateAllPossibleBlackQueenKicks();

            if (!kickScanner.getAllPossibleKicks().isEmpty() || !queenKickScanner.getAllPossibleQueenKicks().isEmpty()) {

                Set<PositionsPieces> allBlacks = new HashSet<>();

                allBlacks.addAll(kickScanner.getAllPiecesWhichKick());
                allBlacks.addAll(queenKickScanner.getAllQueenPiecesWhichKick());

                PositionsPieces computerKick = computer.selectPosition(allBlacks);

                pickedPosition = computerKick;

                pickPiece(computerKick, pickedPosition, true);

                if (getPiece(pickedPosition).getPieceType().isNormal()) {

                    getQueenKicks().clear();

                    getNormalKicks().kickMovesCalculator(pickedPosition);

                    if (!getNormalKicks().getPossibleKickMoves().isEmpty()) {

                        computerKick = computer.selectPosition(getNormalKicks().getPossibleKickMoves());

                        kickPiece(computerKick, pickedPosition);

                        if (getNormalKicks().getPossibleKickMoves().isEmpty()) {

                            endKick();

                            setTurn(true);
                        }
                    }

                } else {

                    getNormalKicks().clear();

                    getQueenKicks().calculateAllPossibleQueenKicks(pickedPosition);

                    if (!getQueenKicks().getPossibleKickMoves().isEmpty()) {

                        computerKick = computer.selectPosition(getQueenKicks().getPossibleKickMoves());

                        kickPiece(computerKick, pickedPosition);

                        if (getQueenKicks().getPossibleKickMoves().isEmpty()) {

                            endKick();

                            setTurn(true);
                        }
                    }
                }

            } else {

                if(computer.getDifficultyLevel() == 1) {

                    normalMoves.difficultyNormal();

                    System.out.println(normalMoves.getAllPossibleBlack());

                    if(normalMoves.getAllPossibleBlack().isEmpty()) {

                        normalMoves.allPossibleBlackMoves();
                    }
                } else {

                    normalMoves.allPossibleBlackMoves();

                }

                PositionsPieces computerMove = computer.selectPosition(normalMoves.getAllPossibleBlack());

                pickedPosition = computerMove;

                if (getPiece(computerMove).getPieceType().isNormal()) {

                    getNormalMoves().clear();

                    getNormalMoves().normalMoveCalculator(computerMove, false);

                    computerMove = computer.selectPosition(getNormalMoves().getPossibleMoves());

                    movePiece(computerMove, pickedPosition);

                    setTurn(true);

                    endTurn();

                } else {

                    getQueenMoves().normalQueenMoveCalculator(computerMove);

                    computerMove = computer.selectPosition(getQueenMoves().getPossibleQueenMoves());

                    movePiece(computerMove, pickedPosition);

                    setTurn(true);

                    endTurn();
                }
            }

        } while(!turn);

    }

    private void endTurn() {
        pickedPosition = null;

        promote();
        endGame.checkEndGame(getBoard().keySet());

        normalMoves.clear();
        normalKicks.clear();
        queenKicks.clear();
        kickScanner.clear();
        queenKickScanner.clear();
    }

    private void endKick() {
        pickedPosition = null;

        promote();
        endGame.checkEndGame(getBoard().keySet());

        normalKicks.clear();
        queenKicks.clear();
        kickScanner.clear();
        queenKickScanner.clear();
    }

    public NormalKicks getNormalKicks() {
        return normalKicks;
    }

    public QueenKicks getQueenKicks() {
        return queenKicks;
    }

    public NormalMoves getNormalMoves() {
        return normalMoves;
    }

    public QueenMoves getQueenMoves() {
        return queenMoves;
    }

    public EndGame getEndGame() {
        return endGame;
    }

    public Ranking getRanking() {
        return ranking;
    }

    public Set<PositionsPieces> getPossiblePromote() {
        return possiblePromote;
    }

    public SaveLoadGame getSaveLoadGame() {
        return saveLoadGame;
    }

    public SaveGame getSaveGame() {
        return saveGame;
    }

    public Map<PositionsPieces, Piece> getBoard() {
        return board;
    }

    public void setBoard(Map<PositionsPieces, Piece> board) {
        this.board = board;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }
}