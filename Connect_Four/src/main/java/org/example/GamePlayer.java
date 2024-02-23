package org.example;

import java.util.Scanner;

public class GamePlayer implements Player {

    private Board board;
    private GameController gameController;
    private String playerNumber;
    private volatile boolean running = true;
    private int lastMove = 0;
    private Scanner scanner = new Scanner(System.in);

    public GamePlayer(Board board, GameController gameController, String playerNumber) {
        this.board = board;
        this.gameController = gameController;
        this.playerNumber = playerNumber;
    }

    @Override
    public int nextMove(int otherPlayerLastMove) {
        System.out.println("Other player last move is:" + otherPlayerLastMove);
        System.out.println("Hint: choose near column to put your token.");
        int move = scanner.nextInt();
        return move;
    }

    @Override
    public synchronized void gameOver() {
        running = false;
        notify();
    }

    @Override
    public void run() {
        while(running) {
            synchronized (this) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            if(!running) {
                break;
            }

            int move = nextMove(lastMove);
            if(board.makeMove(move,playerNumber)){
                lastMove = move;
                System.out.println("Player " + playerNumber + " made a move in column" + move);
                gameController.checkGameState();
            } else {
                System.out.println("Invalid move by " + playerNumber);
                continue;
            }
            gameController.switchTurn();
        }
    }
}
