package chess;

import javax.swing.text.Position;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

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
        HashSet<ChessMove> pieceMoves = new HashSet<>();

        //directions arrays:
        int[][] diagonal = {{1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
        int[][] perpendicular = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int[][] diagonalAndPerpendicular = {{1, 1}, {1, -1}, {-1, 1}, {-1, -1}, {1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int[][] knight = {{2, 1}, {2, -1}, {-2, 1}, {-2,-1}, {-1, 2}, {-1, -2}, {1, 2}, {1, -2}};

        if (currPiece == null) {
            return null;
        }
        else {
            //calculate the moves of the piece based on the rest of the board
            if (currPiece.getPieceType() == PieceType.BISHOP){
                pieceMoves.addAll(calculateRepeatedly(board, myPosition, diagonal));
            }
            else if (currPiece.getPieceType() == PieceType.QUEEN){
                pieceMoves.addAll(calculateRepeatedly(board, myPosition, diagonalAndPerpendicular));
//                pieceMoves.
            }else if (currPiece.getPieceType() == PieceType.KING){
                pieceMoves.addAll(calculateOne(board, myPosition,  diagonalAndPerpendicular));
            }else if (currPiece.getPieceType() == PieceType.KNIGHT){
                pieceMoves.addAll(calculateOne(board, myPosition,  knight));
            }else if (currPiece.getPieceType() == PieceType.ROOK){
                pieceMoves.addAll(calculateRepeatedly(board, myPosition,  perpendicular));
            }
            else if (currPiece.getPieceType() == PieceType.PAWN){
                pieceMoves.addAll(calculatePawn(board,myPosition));
            }
        }
        return pieceMoves;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChessPiece that = (ChessPiece) o;
        return pieceColor == that.pieceColor && pieceType == that.pieceType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieceColor, pieceType);
    }

    public Collection<ChessMove> calculateRepeatedly(ChessBoard board, ChessPosition myPosition, int[][] directions) {
        ChessPiece currPiece = board.getPiece(myPosition);
        //possible diagonal moves:
        var theseMoves = new HashSet<ChessMove>();
        //first is row, second col

        //calculate diagonal
            for (int i = 0; i < directions.length; i++){
                int currRowIteration = myPosition.getRow() + (directions[i][0]);
                int currColIteration = myPosition.getColumn() + (directions[i][1]);
                ChessPosition currPositionIteration = new ChessPosition(currRowIteration, currColIteration);
                //move in the direction WHILE you still can. once that direction is done, next iteration in for loop.
                while (currPositionIteration.isValid()) {
                    if (board.getPiece(currPositionIteration) == null) {
                        //piece can move here, continue moving forward
                        theseMoves.add(new ChessMove(myPosition,currPositionIteration,null));
                        currRowIteration += (directions[i][0]);
                        currColIteration += (directions[i][1]);
                        currPositionIteration = new ChessPosition(currRowIteration, currColIteration);
                    }
                    else if (board.getPiece(currPositionIteration).getTeamColor() != pieceColor){
                        theseMoves.add(new ChessMove(myPosition,currPositionIteration,null));
                        break;
                    }
                    else {
                        break;
                    }
                }
            }
        return theseMoves;
    }

    public Collection<ChessMove> calculateOne(ChessBoard board, ChessPosition myPosition, int[][] directions) {
        ChessPiece currPiece = board.getPiece(myPosition);
        //possible diagonal moves:
        var theseMoves = new HashSet<ChessMove>();
        for (int i = 0; i < directions.length; i++){
            int currRowIteration = myPosition.getRow() + (directions[i][0]);
            int currColIteration = myPosition.getColumn() + (directions[i][1]);
            ChessPosition currPositionIteration = new ChessPosition(currRowIteration, currColIteration);
            //move in the direction WHILE you still can. once that direction is done, next iteration in for loop.
            if (currPositionIteration.isValid()) {
                if (board.getPiece(currPositionIteration) == null) {
                    //piece can move here, continue moving forward
                    theseMoves.add(new ChessMove(myPosition,currPositionIteration,null));
                    currPositionIteration = new ChessPosition(currRowIteration, currColIteration);
                }
                else if (board.getPiece(currPositionIteration).getTeamColor() != pieceColor){
                    theseMoves.add(new ChessMove(myPosition,currPositionIteration,null));
                    continue;
                }
                else {
                    continue;
                }
            }
        }
        return theseMoves;
    }

    public Collection<ChessMove> calculatePawn(ChessBoard board, ChessPosition myPosition) {
        ChessPiece currPiece = board.getPiece(myPosition);
        //possible diagonal moves:
        var theseMoves = new HashSet<ChessMove>();

        if (currPiece.pieceColor == ChessGame.TeamColor.BLACK){
            //            straight
            ChessPosition tempPos = new ChessPosition(myPosition.getRow()-1, myPosition.getColumn());
            if (board.getPiece(tempPos) == null){
                if (myPosition.getRow()==2){
                    theseMoves.add(new ChessMove(myPosition,tempPos,PieceType.QUEEN));
                    theseMoves.add(new ChessMove(myPosition,tempPos,PieceType.ROOK));
                    theseMoves.add(new ChessMove(myPosition,tempPos,PieceType.KNIGHT));
                    theseMoves.add(new ChessMove(myPosition,tempPos,PieceType.BISHOP));
                }
                else {
                    theseMoves.add(new ChessMove(myPosition, tempPos, null));
                }
            }
            //stright right
            tempPos = new ChessPosition(myPosition.getRow()-1, myPosition.getColumn()+1);
            if (tempPos.isValid() && board.getPiece(tempPos) != null && board.getPiece(tempPos).getTeamColor() != currPiece.pieceColor){
                if (tempPos.getRow()==1){
                    theseMoves.add(new ChessMove(myPosition,tempPos,PieceType.QUEEN));
                    theseMoves.add(new ChessMove(myPosition,tempPos,PieceType.ROOK));
                    theseMoves.add(new ChessMove(myPosition,tempPos,PieceType.KNIGHT));
                    theseMoves.add(new ChessMove(myPosition,tempPos,PieceType.BISHOP));
                }
                else {theseMoves.add(new ChessMove(myPosition, tempPos, null));}
            }
            //stright left
            tempPos = new ChessPosition(myPosition.getRow()-1, myPosition.getColumn()-1);
            if (tempPos.isValid() && board.getPiece(tempPos) != null && board.getPiece(tempPos).getTeamColor() != currPiece.pieceColor){
                if (tempPos.getRow()==1){
                    theseMoves.add(new ChessMove(myPosition,tempPos,PieceType.QUEEN));
                    theseMoves.add(new ChessMove(myPosition,tempPos,PieceType.ROOK));
                    theseMoves.add(new ChessMove(myPosition,tempPos,PieceType.KNIGHT));
                    theseMoves.add(new ChessMove(myPosition,tempPos,PieceType.BISHOP));
                }
                else {theseMoves.add(new ChessMove(myPosition, tempPos, null));}
            }

          if (myPosition.getRow() == 7){
              ChessPosition tempTwoJumpPosition = new ChessPosition(5, myPosition.getColumn());
              if (board.getPiece(tempTwoJumpPosition) == null && board.getPiece(new ChessPosition(tempTwoJumpPosition.getRow()+1, tempTwoJumpPosition.getColumn())) == null){
                  theseMoves.add(new ChessMove(myPosition, tempTwoJumpPosition, null));
              }
          }
        }

        else if (currPiece.pieceColor == ChessGame.TeamColor.WHITE){

// straight forward
            ChessPosition tempPos = new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn());
            if (board.getPiece(tempPos) == null) {
                if (tempPos.getRow() == 8) {
                    addPromotionMoves(tempPos, theseMoves, myPosition);
                } else {
                    theseMoves.add(new ChessMove(myPosition, tempPos, null));
                }
            }

// straight right
            tempPos = new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn() + 1);
            if (tempPos.isValid() && board.getPiece(tempPos) != null && board.getPiece(tempPos).getTeamColor() != currPiece.pieceColor) {
                theseMoves.add(new ChessMove(myPosition, tempPos, null));
                addPromotionMoves(tempPos, theseMoves, myPosition);
            }

// straight left
            tempPos = new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn() - 1);
            if (tempPos.isValid() && board.getPiece(tempPos) != null && board.getPiece(tempPos).getTeamColor() != currPiece.pieceColor) {
                theseMoves.add(new ChessMove(myPosition, tempPos, null));
                addPromotionMoves(tempPos, theseMoves, myPosition);
            }

            if (myPosition.getRow() == 2) {
                ChessPosition tempTwoJumpPosition = new ChessPosition(4, myPosition.getColumn());
                if (board.getPiece(tempTwoJumpPosition) == null && board.getPiece(new ChessPosition(tempTwoJumpPosition.getRow()-1, tempTwoJumpPosition.getColumn())) == null) {
                    theseMoves.add(new ChessMove(myPosition, tempTwoJumpPosition, null));
                }
            }
        }
        return theseMoves;
    }

    private void addPromotionMoves(ChessPosition tempPos, HashSet<ChessMove> theseMoves, ChessPosition myPosition) {
        if (tempPos.getRow() == 8) {
            theseMoves.add(new ChessMove(myPosition, tempPos, PieceType.QUEEN));
            theseMoves.add(new ChessMove(myPosition, tempPos, PieceType.ROOK));
            theseMoves.add(new ChessMove(myPosition, tempPos, PieceType.KNIGHT));
            theseMoves.add(new ChessMove(myPosition, tempPos, PieceType.BISHOP));
        }
    }

    public Boolean isValid(ChessPosition myPosition) {
        //its invalid
        return myPosition.getColumn() <= 8 && myPosition.getColumn() >= 1 && myPosition.getRow() <= 8 && myPosition.getRow() >= 1;
    }


}
