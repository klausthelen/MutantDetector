package Business.Models;

public class NitrogenousBaseSpacialLocation {
    private int row;
    private int column;

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public NitrogenousBaseSpacialLocation(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public NitrogenousBaseSpacialLocation MoveHorizontal(){
        return new NitrogenousBaseSpacialLocation(this.row, this.column+1);
    }

    public NitrogenousBaseSpacialLocation MoveVertical(){
        return new NitrogenousBaseSpacialLocation(this.row+1, this.column);
    }

    public NitrogenousBaseSpacialLocation MoveOblique(boolean flag){
        return new NitrogenousBaseSpacialLocation(this.row+1, this.column+1);
    }
}
