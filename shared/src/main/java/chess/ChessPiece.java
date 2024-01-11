package chess;

import java.sql.Array;
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
    private PieceType pieceType;

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
        ArrayList<ChessMove> pieceMoves = new ArrayList<>();
        if (currPiece == null) {
            return null;
        }
        else {
            //calculate the moves of the piece based on the rest of the board
            if (currPiece.getPieceType() == PieceType.BISHOP){
                pieceMoves.addAll(calculateDiagonal(board, myPosition));
            }
            else if (currPiece.getPieceType() == PieceType.QUEEN){
                pieceMoves.addAll(calculateDiagonal(board, myPosition));
            }
        }
        return pieceMoves;
    }

    public Collection<ChessMove> calculateDiagonal(ChessBoard board, ChessPosition myPosition) {
        ChessPiece currPiece = board.getPiece(myPosition);
        //first is row, second col
        int[][] directions = {{1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
        //calculate diagonal

        for (int i = 0; i < 4; i++) {
            int currRowIteration = myPosition.getRow() + (directions[i][0] * i);
            int currColIteration = myPosition.getColumn() + (directions[i][1] * i);
            ChessPosition currPositionIteration = new ChessPosition(currRowIteration, currColIteration);
            while (currPositionIteration.isValid()) {
                if (board.getPiece(currPositionIteration) != null) {
                    //piece can move here, continue moving forward

                }
            }
        }
        return null;
    }

    public Boolean isValid(ChessPosition myPosition) {
        //its invalid
        return myPosition.getColumn() <= 8 && myPosition.getColumn() >= 1 && myPosition.getRow() <= 8 && myPosition.getRow() >= 1;
    }
}
