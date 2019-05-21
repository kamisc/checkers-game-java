package pl.sewerynkamil.pieces;

public class PositionsPieces {

    private int col;
    private int row;

    public PositionsPieces(int col, int row) {
        this.col = col;
        this.row = row;
    }

    public boolean isValidPosition(){
        return col >= 0 && col <= 7 && row >= 0 && row <= 7;
    }

    public int nextCol(){
       return col + 1;
    }

    public int prevCol(){
        return col - 1;
    }

    public int nextRow(){
        return row + 1;
    }

    public int prevRow(){
        return row - 1;
    }

    public int secondNextCol(){
        return col + 2;
    }

    public int secondPrevCol(){
        return col - 2;
    }

    public int secondNextRow(){
        return row + 2;
    }

    public int secondPrevRow(){
        return row - 2;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PositionsPieces that = (PositionsPieces) o;

        if (col != that.col) return false;
        return row == that.row;

    }

    @Override
    public int hashCode() {
        int result = col;
        result = 31 * result + row;
        return result;
    }

    @Override
    public String toString() {
        return "PositionsPieces{" +
                "col=" + col +
                ", row=" + row +
                '}';
    }
}

