package chess;

/**
 * A chessboard that can hold and rearrange chess pieces.
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessBoard {
    private ChessPiece[][] chessBoard = new ChessPiece[8][8];

    public ChessBoard() {
        System.out.println(toString());
    }

    /**
     * Adds a chess piece to the chessboard
     *
     * @param position where to add the piece to
     * @param piece    the piece to add
     */
    public void addPiece(ChessPosition position, ChessPiece piece) {
        chessBoard[position.getRow()-1][position.getColumn()-1] = piece;
    }

    /**
     * Gets a chess piece on the chessboard
     *
     * @param position The position to get the piece from
     * @return Either the piece at the position, or null if no piece is at that
     * position
     */
    public ChessPiece getPiece(ChessPosition position) {
        return chessBoard[position.getRow()-1][position.getColumn()-1];
    }

    /**
     * Sets the board to the default starting board
     * (How the game of chess normally starts)
     */
    public void resetBoard() {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (int rows = 0; rows < 8; rows++) {
            output.append("|");
            for (int cols = 0; cols < 8; cols++) {
                ChessPosition tempPos = new ChessPosition(8-rows,cols+1);
                char pieceChar = ' ';
                if (getPiece(tempPos) == null) {
                    //do nothing
                }
                else if (getPiece(tempPos).getPieceType() == ChessPiece.PieceType.BISHOP){
                    pieceChar = 'B';
                }else if (getPiece(tempPos).getPieceType() == ChessPiece.PieceType.KING){
                    pieceChar = 'K';
                }else if (getPiece(tempPos).getPieceType() == ChessPiece.PieceType.QUEEN){
                    pieceChar = 'Q';
                }else if (getPiece(tempPos).getPieceType() == ChessPiece.PieceType.KNIGHT){
                    pieceChar = 'N';
                }else if (getPiece(tempPos).getPieceType() == ChessPiece.PieceType.ROOK){
                    pieceChar = 'R';
                }else if (getPiece(tempPos).getPieceType() == ChessPiece.PieceType.PAWN){
                    pieceChar = 'B';
                }
                if (getPiece(tempPos) != null && getPiece(tempPos).getTeamColor() == ChessGame.TeamColor.BLACK){
                    pieceChar = String.valueOf(pieceChar).toLowerCase().charAt(0);
                }
                output.append(pieceChar);
                output.append("|");
            }
            output.append("\n");
        }
        return output.toString();
    }
}
