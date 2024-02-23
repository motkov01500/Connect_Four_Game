package org.example;

import org.junit.Assert;
import org.junit.Test;

public class BoardTest {

    @Test
    public void testMakeValidMove() {
        //given
        Board board = new Board();

        //when
        board.makeMove(1,"1");

        //then
        Assert.assertEquals(board.getBoard()[0][5], "1");
    }

    @Test
    public void testMakeMoveWithInvalidColumnInput() {
        //given
        Board board = new Board();

        //when
        boolean smallerCols = board.makeMove(0,"1");
        boolean higherCols = board.makeMove(8,"1");

        //then
        Assert.assertFalse(smallerCols);
        Assert.assertFalse(higherCols);
    }

    @Test
    public void testMakeMoveWhenColumnsIsFull() {
        //given
        Board board = new Board();

        //when
        board.makeMove(1,"1");
        board.makeMove(1,"1");
        board.makeMove(1,"1");
        board.makeMove(1,"1");
        board.makeMove(1,"1");
        board.makeMove(1,"1");
        boolean move = board.makeMove(1,"1");


        //then
        Assert.assertFalse(move);
    }

    @Test
    public void testIsBoardFullOfTokens() {
        //given
        Board board = new Board();
        for (int col = 0; col<7; col++){
            for (int row = 0; row< 6;row ++){
                board.getBoard()[col][row] = "1";
            }
        }

        //when
        boolean isFullBoard = board.isFull();

        //then
        Assert.assertTrue(isFullBoard);
    }

    @Test
    public void testForAvailablePlaceInBoard() {
        //given
        Board board = new Board();
        for (int col = 0; col<6 ; col++){
            for (int row = 5; row > 3;row --){
                board.getBoard()[col][row] = "1";
            }
        }

        //when
        boolean isFullBoard = board.isFull();
        boolean move = board.makeMove(1,"1");

        //then
        Assert.assertFalse(isFullBoard);
        Assert.assertTrue(move);
    }

    @Test
    public void testForNoWinner() {
        //given
        Board board = new Board();

        //when
        board.makeMove(1,"1");
        board.makeMove(1,"1");
        board.makeMove(1,"1");
        board.makeMove(1,"2");
        boolean isAvailableWinner = board.checkWin();

        //then
        Assert.assertFalse(isAvailableWinner);
    }

    @Test
    public void testForWinnerHorizontal() {
        //given
        Board board = new Board();

        //when
        board.makeMove(1,"1");
        board.makeMove(2,"1");
        board.makeMove(3,"1");
        board.makeMove(4,"1");
        boolean isAvailableWinner = board.checkWin();

        //then
        Assert.assertTrue(isAvailableWinner);
    }

    @Test
    public void testForWinnerVertical() {
        //given
        Board board = new Board();

        //when
        board.makeMove(1,"1");
        board.makeMove(1,"1");
        board.makeMove(1,"1");
        board.makeMove(1,"1");
        boolean isAvailableWinner = board.checkWin();

        //then
        Assert.assertTrue(isAvailableWinner);
    }

    @Test
    public void testForWinnerDiagonalDownToRight() {
        //given
        Board board = new Board();

        //when
        board.makeMove(1,"2");
        board.makeMove(1,"2");
        board.makeMove(1,"2");
        board.makeMove(1,"1");

        board.makeMove(2,"2");
        board.makeMove(2,"2");
        board.makeMove(2,"1");

        board.makeMove(3,"2");
        board.makeMove(3,"1");

        board.makeMove(4,"1");
        boolean isAvailableWinner = board.checkWin();
        board.printBoard();
        //then
        Assert.assertTrue(isAvailableWinner);
    }

    @Test
    public void testForWinnerDiagonalDownToLeft() {
        //given
        Board board = new Board();

        //when
        board.makeMove(1,"1");

        board.makeMove(2,"2");
        board.makeMove(2,"1");

        board.makeMove(3,"2");
        board.makeMove(3,"2");
        board.makeMove(3,"1");

        board.makeMove(4,"2");
        board.makeMove(4,"2");
        board.makeMove(4,"2");
        board.makeMove(4,"1");
        boolean isAvailableWinner = board.checkWin();
        board.printBoard();
        //then
        Assert.assertTrue(isAvailableWinner);
    }
}