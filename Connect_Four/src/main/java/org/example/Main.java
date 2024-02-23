package org.example;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        GameController gameController = new GameController();
        Thread newThread = new Thread(gameController);
        newThread.start();
        newThread.join();
    }
}