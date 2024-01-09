package chess;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Represents a single chess piece
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessPiece {
    private ChessGame.TeamColor pieceColor;
    private ChessPiece.PieceType pieceType;

    public ChessPiece(ChessGame.TeamColor color, ChessPiece.PieceType type) {
        pieceColor = color;
        pieceType = type;
    }

    /**
     * The various different chess piece options
     */
    public enum PieceType {
        KING,
        QUEEN,
        BISHOP,
        KNIGHT,
        ROOK,
        PAWN
    }

    /**
     * @return Which team this chess piece belongs to
     */
    public ChessGame.TeamColor getTeamColor() {
        return pieceColor;
    }

    /**
     * @return which type of chess piece this piece is
     */
    public PieceType getPieceType() {
        return pieceType;
    }

    /**
     * Calculates all the positions a chess piece can move to
     * Does not take into account moves that are illegal due to leaving the king in
     * danger
     *
     * @return Collection of valid moves
     */
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        ChessPiece currPiece = board.getPiece(myPosition);
        if (currPiece == null) {
            return null;
        }
        ArrayList pieceMoves = new ArrayList();
//        else {
//            //calculate the moves of the piece based on the rest of the board
//            if (currPiece.getPieceType() == PieceType.BISHOP){
//                pieceMoves.add(calculateDiagonal(board, myPosition));
//            }
//        }
        return null;
    }

    public Collection<ChessMove> calculateDiagonal(ChessBoard board, ChessPosition myPosition) {
        ChessPiece currPiece = board.getPiece(myPosition);
        //calculate diagonal
        int currRow = myPosition.getRow();
        int currCol = myPosition.getColumn();
//        while (currCol)
        return null;
    }

    public Boolean isValid(ChessPosition myPosition) {
//        if (myPosition.getColumn())
//    }
        return false;
    }
}
