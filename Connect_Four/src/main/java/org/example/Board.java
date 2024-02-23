package org.example;

public class Board {

    private String[][] board;
    private int rows = 6;
    private int cols = 7;

    public Board() {
        board = new String[cols][rows];
        fillBoardWithDots();
    }

    public String[][] getBoard() {
        return board;
    }

    public synchronized boolean makeMove(int column, String player) {
        if (column < 1 || column > cols) {
            System.out.println("You must choose column from 1 to seven");
            return false;
        }

        for (int row = rows - 1; row >= 0; row--) {
            if (board[column - 1][row].equals(".")) {
                board[column - 1][row] = player;
                return true;
            }
        }
        System.out.println("Column " + column + "is full. Choose another column");
        return false;
    }

    public synchronized boolean isFull() {
        for (int col = 0; col < cols; col++) {
            if (board[col][0].equals(".")) {
                return false;
            }
        }
        return true;
    }

    public synchronized boolean checkWin() {
        for (int col = 0; col < cols; col++) {
            for (int row = 0; row < rows; row++) {
                String currentCell = board[col][row];
                if (currentCell.equals(".")) {
                    continue;
                }

                //Check for winner horizontal
                if (col + 3 < cols &&
                        currentCell.equals(board[col + 1][row]) &&
                        currentCell.equals(board[col + 2][row]) &&
                        currentCell.equals(board[col + 3][row])) {
                    return true;
                }

                //Check for winner vertical
                if (row + 3 < rows &&
                        currentCell.equals(board[col][row + 1]) &&
                        currentCell.equals(board[col][row + 2]) &&
                        currentCell.equals(board[col][row + 3])) {
                    return true;
                }

                //Check for winner diagonal- down-right
                if(row + 3 < rows && col + 3 < cols &&
                        currentCell.equals(board[col + 1][row + 1]) &&
                        currentCell.equals(board[col + 2][row + 2]) &&
                        currentCell.equals(board[col + 3][row + 3])) {
                    return true;
                }

                //Check for winner diagonal- down-left
                if(row + 3 <rows && col -3 >=0  &&
                        currentCell.equals(board[col - 1][row + 1]) &&
                        currentCell.equals(board[col - 2][row + 2]) &&
                        currentCell.equals(board[col - 3][row + 3])) {
                    return true;
                }
            }
        }
        return false;
    }

    public synchronized void printBoard() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                System.out.print(board[col][row] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private void fillBoardWithDots() {
        for (int col = 0; col< cols;col++) {
            for (int row = 0; row<rows;row++) {
                board[col][row] = ".";
            }
        }
    }
}
