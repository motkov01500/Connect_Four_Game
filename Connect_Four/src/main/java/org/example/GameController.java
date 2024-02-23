package org.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class GameController implements Runnable{

    private Board board = new Board();
    private Player playerOne;
    private Player playerTwo;
    private Player currentPlayer;
    private ExecutorService executor = Executors.newFixedThreadPool(2);

    public GameController() {
        playerOne = new GamePlayer(board,this,"1");
        playerTwo = new GamePlayer(board, this, "2");
        currentPlayer = playerOne;
    }

    @Override
    public void run() {
        executor.execute(playerOne);
        executor.execute(playerTwo);

        while (!board.checkWin() && !Thread.currentThread().isInterrupted()) {
            synchronized (currentPlayer) {
                currentPlayer.notify();
            }

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            checkGameState();
            switchTurn();
        }
        executor.shutdownNow();

        System.out.println("Game Over.");
        playerOne.gameOver();
        playerTwo.gameOver();
    }

    public void switchTurn() {
        currentPlayer = (currentPlayer == playerOne) ? playerTwo: playerOne;
    }

    public synchronized void checkGameState() {
        if(board.checkWin()) {
            System.out.println("Player " + (currentPlayer == playerOne ? "1": "2") + " wins the game!");
            stopGame();
        } else if(board.isFull()) {
            System.out.println("The board is full of tokens, it's a draw!");
            stopGame();
        }
        board.printBoard();
    }

    private void stopGame() {
        Thread.currentThread().interrupt();
        playerOne.gameOver();
        playerTwo.gameOver();
    }
}
