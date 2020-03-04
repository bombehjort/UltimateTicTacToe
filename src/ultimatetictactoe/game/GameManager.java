package ultimatetictactoe.game;

import ultimatetictactoe.bot.IBot;
import ultimatetictactoe.field.IField;
import static ultimatetictactoe.field.IField.EMPTY_FIELD;
import ultimatetictactoe.move.IMove;
import ultimatetictactoe.move.Move;

/**
 * This is a proposed GameManager for Ultimate Tic-Tac-Toe, the implementation
 * of which is up to whoever uses this interface. Note that initializing a game
 * through the constructors means that you have to create a new instance of the
 * game manager for every new game of a different type (e.g. Human vs Human,
 * Human vs Bot or Bot vs Bot), which may not be ideal for your solution, so you
 * could consider refactoring that into an (re-)initialize method instead.
 *
 * @author mjl
 */
public class GameManager {
    
    public boolean checkForWinInMicroBoard() {
        return false;
    }
    
    public int checkForWinInMacroBoard() {
        return -1;
    }
    
    private void checkIfAlreadyWon(int macroX, int macroY, String[][] macroBoard) {
    }
    
    private void resetAllToAvailable(IMove mov , int macroX, int macroY, String[][] macroBoard) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.println(i +"" + j + "" +macroBoard[i][j].equals(IField.AVAILABLE_FIELD));
                if (macroBoard[i][j].equals(IField.AVAILABLE_FIELD) ) {
                    macroBoard[i][j] = "0";
                }
                
            }
        }
        macroBoard[macroX][macroY]=IField.AVAILABLE_FIELD;
    }

    /**
     * Three different game modes.
     */
    public enum GameMode {
        HumanVsHuman,
        HumanVsBot,
        BotVsBot
    }
    private final String NON_AVAILABLE_MACRO_CELL = "-";
    private final IGameState currentState;
    private int currentPlayer = 0; //player0 == 0 && player1 == 1
    private GameMode mode = GameMode.HumanVsHuman;
    private IBot bot = null;
    private IBot bot2 = null;

    /**
     * Set's the currentState so the game can begin. Game expected to be played
     * Human vs Human
     *
     * @param currentState Current game state, usually an empty board, but could
     * load a saved game.
     */
    public GameManager(IGameState currentState) {
        this.currentState = currentState;
        
        currentState.getField().clearBoard();
        mode = GameMode.HumanVsHuman;
    }

    /**
     * Set's the currentState so the game can begin. Game expected to be played
     * Human vs Bot
     *
     * @param currentState Current game state, usually an empty board, but could
     * load a saved game.
     * @param bot The bot to play against in vsBot mode.
     */
    public GameManager(IGameState currentState, IBot bot) {
        this.currentState = currentState;
        currentState.getField().clearBoard();
        mode = GameMode.HumanVsBot;
        this.bot = bot;
    }

    /**
     * Set's the currentState so the game can begin. Game expected to be played
     * Bot vs Bot
     *
     * @param currentState Current game state, usually an empty board, but could
     * load a saved game.
     * @param bot The first bot to play.
     * @param bot2 The second bot to play.
     */
    public GameManager(IGameState currentState, IBot bot, IBot bot2) {
        this.currentState = currentState;
        mode = GameMode.BotVsBot;
        this.bot = bot;
        this.bot2 = bot2;
    }

    /**
     * User input driven Update
     *
     * @param move The next user move
     * @return Returns true if the update was successful, false otherwise.
     */
    public Boolean updateGame(IMove move, Integer moveX, Integer moveY) {
        //Verify the new move
        if (!verifyMoveLegality(move, moveX, moveY)) {
            System.out.println("Move is not move");
            return false;
        }
        //Update the currentState
        updateBoard(move);
        updateMacroboard(move, moveX, moveY);

        //Update currentPlayer
        currentPlayer = (currentPlayer + 1) % 2;
        currentState.setMoveNumber(currentState.getMoveNumber() + 1);
        
        return true;
    }

    /**
     * Non-User driven input, e.g. an update for playing a bot move.
     *
     * @return Returns true if the update was successful, false otherwise.
     */
    public Boolean updateGame() {
        //Check game mode is set to one of the bot modes.
        assert (mode != GameMode.HumanVsHuman);

        //Check if player is bot, if so, get bot input and update the state based on that.
        if (mode == GameMode.HumanVsBot && currentPlayer == 1) {
            //Check bot is not equal to null, and throw an exception if it is.
            assert (bot != null);
            
            IMove botMove = bot.doMove(currentState);

            //Be aware that your bots might perform illegal moves.
            return updateGame(botMove, botMove.getX(), botMove.getY());
        }

        //Check bot is not equal to null, and throw an exception if it is.
        assert (bot != null);
        assert (bot2 != null);

        //TODO: Implement a bot vs bot Update.
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    private Boolean verifyMoveLegality(IMove move, int macroX, int macroY) {
        //Test if the move is legal   
        //NOTE: should also check whether the move is placed on an occupied spot

        boolean isInActiveMacroboardsMicroBoard = currentState.getField().isInActiveMicroboard(macroX, macroY);
        //boolean hasAvailableMove = currentState.getField().getBoard()[move.getX()][move.getY()].equals(IField.EMPTY_FIELD);
       
        return isInActiveMacroboardsMicroBoard;
    }
    
    private void updateBoard(IMove move) {
        currentState.getField().getBoard()[move.getX()][move.getY()] = "" + currentPlayer;
    }
    
    private void updateMacroboard(IMove move, int macroX, int macroY) {
        
        String[][] macroBoard = currentState.getField().getMacroboard();
        
        int microX = move.getX() % 3;
        int microY = move.getY() % 3;
        
        resetAllToAvailable(move,macroX, macroY, macroBoard);

        //Checks if already won, if already won all macrocells available for play   
        checkIfAlreadyWon(macroX, macroY, macroBoard);
        
    }

    /*    private boolean isTie(String[][] board, IMove move){
        int localX = move.getX() % 3;
        int localY = move.getY() % 3;
        int startX = move.getX() - (localX);
        int startY = move.getY() - (localY);

        for (int i = startX; i < startX+3; i++) {
            for (int k = startY; k < startY+3; k++) {
                if(board[i][k].equals(IField.AVAILABLE_FIELD) ||
                        board[i][k].equals(IField.EMPTY_FIELD) )
                    return false;
            }
        }
        return true;
    }


    public static boolean isWin(String[][] board, IMove move, String currentPlayer){
        int localX = move.getX() % 3;
        int localY = move.getY() % 3;
        int startX = move.getX() - (localX);
        int startY = move.getY() - (localY);

        //check col
        for (int i = startY; i < startY + 3; i++) {
            if (!board[move.getX()][i].equals(currentPlayer))
                break;
            if (i == startY + 3 - 1) return true;
        }

        //check row
        for (int i = startX; i < startX + 3; i++) {
            if (!board[i][move.getY()].equals(currentPlayer))
                break;
            if (i == startX + 3 - 1) return true;
        }

        //check diagonal
        if (localX == localY) {
            //we're on a diagonal
            int y = startY;
            for (int i = startX; i < startX + 3; i++) {
                if (!board[i][y++].equals(currentPlayer))
                    break;
                if (i == startX + 3 - 1) return true;
            }
        }

        //check anti diagonal
        if (localX + localY == 3 - 1) {
            int less = 0;
            for (int i = startX; i < startX + 3; i++) {
                if (!board[i][(startY + 2)-less++].equals(currentPlayer))
                    break;
                if (i == startX + 3 - 1) return true;
            }
        }
        return false;
    }*/
}
