package org.example;

public interface Player extends Runnable{

    public int nextMove(int otherPlayerLastMove);
    public void gameOver();
}
