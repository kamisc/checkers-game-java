package pl.sewerynkamil.pieces;

import java.io.Serializable;

public class Piece implements Serializable {

    private Color pieceColor;
    private Type pieceType;

    public Piece(Color pieceColor, Type pieceType) {
        this.pieceColor = pieceColor;
        this.pieceType = pieceType;
    }

    public Color getPieceColor() {
        return pieceColor;
    }

    public Type getPieceType() {
        return pieceType;
    }

    public enum Color {
        BLACK, WHITE;

        public boolean isWhite() {
            return this == WHITE;
        }

        public boolean isBlack() {
            return this == BLACK;
        }
    }

    public enum Type {
        NORMAL, QUEEN;

        public boolean isNormal() {
            return this == NORMAL;
        }

        public boolean isQueen() {
            return this == QUEEN;
        }
    }
}
