package ultimatetictactoe.field;

import java.util.ArrayList;
import java.util.List;
import ultimatetictactoe.move.IMove;
import ultimatetictactoe.move.Move;

public class Field implements IField{

    volatile String[][] board = new String[9][9];
    volatile String[][] macroBoard = new String[3][3];

    public Field() {
        clearBoard();
    }

    @Override
    public void clearBoard() {
        board = new String[9][9];
        for (int i = 0; i < board.length; i++)
            for (int k = 0; k < board[i].length; k++) {
                board[i][k] = EMPTY_FIELD;
            }
        for (int i = 0; i < macroBoard.length; i++)
            for (int k = 0; k < macroBoard[i].length; k++) {
                macroBoard[i][k] = AVAILABLE_FIELD;
            }
    }

    @Override
    public List<IMove> getAvailableMoves() {
        List<IMove> availMoves = new ArrayList<>();

        for (int i = 0; i < board.length; i++)
            for (int k = 0; k < board[i].length; k++) {
                if(isInActiveMicroboard(i,k) && board[i][k].equals(EMPTY_FIELD)) {
                    availMoves.add(new Move(i,k));
                }
        }

        return availMoves;
    }

    @Override
    public String getPlayerId(int column, int row) {
        return board[column][row];
    }

    @Override
    public boolean isEmpty() {
        for (int i = 0; i < board.length; i++)
            for (int k = 0; k < board[i].length; k++) {
                if(board[i][k]!=EMPTY_FIELD && board[i][k]!=AVAILABLE_FIELD)
                    return false;
            }
        return true;
    }

    @Override
    public boolean isFull() {
        for (int i = 0; i < board.length; i++)
            for (int k = 0; k < board[i].length; k++) {
                if(board[i][k]==EMPTY_FIELD || board[i][k]==AVAILABLE_FIELD)
                    return false;
            }
        return true;
    }

    @Override
    public Boolean isInActiveMicroboard(int x, int y) {

        String[][] macroBoard = getMacroboard();
        boolean isInActiveMacroboardsMicroBoard = macroBoard[x][y].equals(IField.AVAILABLE_FIELD);
        return isInActiveMacroboardsMicroBoard;
    }

    @Override
    public String[][] getBoard() {
        return board;
    }

    @Override
    public String[][] getMacroboard() {
        return macroBoard;
    }

    @Override
    public void setBoard(String[][] board)
    {
        //NOTE: Cloning here, for simulation purposes
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                this.board[i][j] = board[i][j];
            }
        }
    }

    @Override
    public void setMacroboard(String[][] macroboard)
    {
        //NOTE: Cloning here, for simulation purposes
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.macroBoard[i][j] = macroboard[i][j];
            }
        }
    }
}