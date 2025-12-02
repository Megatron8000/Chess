class Board {
    Spot[][] boxes = new Spot[8][8];

    public Board() {
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                boxes[x][y] = new Spot(x, y, null);
            }
        }

        // Белые пешки (ряд 6)
        for (int x = 0; x < 8; x++) boxes[x][6].setPiece(new Pawn(true));
        // Чёрные пешки (ряд 1)
        for (int x = 0; x < 8; x++) boxes[x][1].setPiece(new Pawn(false));

        // Белые фигуры (ряд 7)
        boxes[0][7].setPiece(new Rook(true)); boxes[7][7].setPiece(new Rook(true));
        boxes[1][7].setPiece(new Knight(true)); boxes[6][7].setPiece(new Knight(true));
        boxes[2][7].setPiece(new Bishop(true)); boxes[5][7].setPiece(new Bishop(true));
        boxes[3][7].setPiece(new Queen(true)); boxes[4][7].setPiece(new King(true));

        // Чёрные фигуры (ряд 0)
        boxes[0][0].setPiece(new Rook(false)); boxes[7][0].setPiece(new Rook(false));
        boxes[1][0].setPiece(new Knight(false)); boxes[6][0].setPiece(new Knight(false));
        boxes[2][0].setPiece(new Bishop(false)); boxes[5][0].setPiece(new Bishop(false));
        boxes[3][0].setPiece(new Queen(false)); boxes[4][0].setPiece(new King(false));
    }

    public Spot getBox(int x, int y) { return boxes[x][y]; }
    public Spot findKing(boolean whiteKing) {
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                Piece p = boxes[x][y].getPiece();
                if (p instanceof King && p.isWhite() == whiteKing) return boxes[x][y];
            }
        }
        return null;
    }
}