public class Board {
    Spot[][] boxes = new Spot[8][8];
    public Board() { /* инициализация доски и фигур */ }
    public Spot getBox(int x, int y) { return boxes[x][y]; }
}
