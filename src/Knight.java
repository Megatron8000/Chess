class Knight extends Piece {

    public Knight(boolean white) {
        super(white);
    }

    @Override
    boolean canMove(Board board, Spot start, Spot end) {
        if (sameColor(start.getPiece(), end.getPiece())) return false;

        int dx = Math.abs(end.getX() - start.getX());
        int dy = Math.abs(end.getY() - start.getY());

        return (dx == 1 && dy == 2) || (dx == 2 && dy == 1);
    }
}