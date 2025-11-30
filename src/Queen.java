// комбинация ходов Ладьи и Коня

class Queen extends Piece {

    public Queen(boolean white) {
        super(white);
    }

    @Override
    boolean canMove(Board board, Spot start, Spot end) {
        if (sameColor(start.getPiece(), end.getPiece())) return false;

        int dx = end.getX() - start.getX();
        int dy = end.getY() - start.getY();

        if (dx == 0 || dy == 0) {
            // как ладья
            int stepX = Integer.signum(dx);
            int stepY = Integer.signum(dy);
            int x = start.getX() + stepX;
            int y = start.getY() + stepY;
            while (x != end.getX() || y != end.getY()) {
                if (board.getBox(x, y).getPiece() != null) return false;
                x += stepX;
                y += stepY;
            }
            return true;
        } else if (Math.abs(dx) == Math.abs(dy)) {
            // как слон
            int stepX = Integer.signum(dx);
            int stepY = Integer.signum(dy);
            int x = start.getX() + stepX;
            int y = start.getY() + stepY;
            while (x != end.getX() || y != end.getY()) {
                if (board.getBox(x, y).getPiece() != null) return false;
                x += stepX;
                y += stepY;
            }
            return true;
        }

        return false;
    }
}
