package chess;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

/**
 * For a class that can manage a chess game, making moves on a board
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessGame {
    private ChessGame.TeamColor curTeamTurn = TeamColor.WHITE;
    private ChessBoard chessBoard = new ChessBoard();

    public ChessGame() {}

    /**
     * @return Which team's turn it is
     */
    public TeamColor getTeamTurn() {
        return curTeamTurn;
    }

    /**
     * Set's which teams turn it is
     *
     * @param team the team whose turn it is
     */
    public void setTeamTurn(TeamColor team) {
        curTeamTurn = team;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChessGame chessGame = (ChessGame) o;
        return curTeamTurn == chessGame.curTeamTurn && Objects.equals(chessBoard, chessGame.chessBoard);
    }

    @Override
    public int hashCode() {
        return Objects.hash(curTeamTurn, chessBoard);
    }

    /**
     * Enum identifying the 2 possible teams in a chess game
     */
    public enum TeamColor {
        WHITE,
        BLACK
    }

    /**
     * Gets a valid moves for a piece at the given location
     *
     * @param startPosition the piece to get valid moves for
     * @return Set of valid moves for requested piece, or null if no piece at
     * startPosition
     */
    public Collection<ChessMove> validMoves(ChessPosition startPosition) {
        if (chessBoard.getPiece(startPosition) == null){
            return null;
        }
        else {
        return null;
        }
    }

    /**
     * Makes a move in a chess game
     *
     * @param move chess move to preform
     * @throws InvalidMoveException if move is invalid
     */
    public void makeMove(ChessMove move) throws InvalidMoveException {

    }

    /**
     * Determines if the given team is in check
     *
     * @param teamColor which team to check for check
     * @return True if the specified team is in check
     */
    public boolean isInCheck(TeamColor teamColor) {
        ChessPosition kingPosition = null;
        //find the piece of the teamcolor
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                ChessPosition tempPos = new ChessPosition(i+1,j+1);
                if (chessBoard.getPiece(tempPos) != null && chessBoard.getPiece(tempPos).getPieceType() == ChessPiece.PieceType.KING && chessBoard.getPiece(tempPos).getTeamColor() == teamColor){
                    //king found
                    kingPosition = tempPos;
                    break;
                }
            }
            if (kingPosition != null){
                break;
            }
        }

        //check if the kingPosition is the endPosition of any of the pieceMoves of each piece
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                ChessPosition tempPos = new ChessPosition(i+1,j+1);
                if (chessBoard.getPiece(tempPos) != null && chessBoard.getPiece(tempPos).getTeamColor() != teamColor){
                    //cycle thru that pieces valid moves
                    var curPieceMoves = chessBoard.getPiece(tempPos).pieceMoves(getBoard(),tempPos);
                    for (var move : curPieceMoves){
                        if (move.getEndPosition().equals(kingPosition)){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * Determines if the given team is in checkmate
     *
     * @param teamColor which team to check for checkmate
     * @return True if the specified team is in checkmate
     */
    public boolean isInCheckmate(TeamColor teamColor) {
        throw new RuntimeException("Not implemented");
    }

    /**
     * Determines if the given team is in stalemate, which here is defined as having
     * no valid moves
     *
     * @param teamColor which team to check for stalemate
     * @return True if the specified team is in stalemate, otherwise false
     */
    public boolean isInStalemate(TeamColor teamColor) {
        throw new RuntimeException("Not implemented");
    }

    /**
     * Sets this game's chessboard with a given board
     *
     * @param board the new board to use
     */
    public void setBoard(ChessBoard board) {
        chessBoard = board;
    }

    /**
     * Gets the current chessboard
     *
     * @return the chessboard
     */
    public ChessBoard getBoard() {
        return chessBoard;
    }
}
