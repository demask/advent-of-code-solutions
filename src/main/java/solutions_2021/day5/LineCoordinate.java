package solutions_2021.day5;

public class LineCoordinate {
    int x1;
    int y1;
    int x2;
    int y2;

    public LineCoordinate(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }
    
    public int getX1() {
        return this.x1;
    }

    public int getY1() {
        return this.y1;
    }

    public int getX2() {
        return this.x2;
    }

    public int getY2() {
        return this.y2;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "x1: " + this.x1 + ", y1: " + this.y1 + "\n" + "x2: " + this.x2 + ", y2: " + this.y2 + "\n ";
    }  
}
