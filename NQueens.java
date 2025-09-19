import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NQueens {

    // Function to check if it's safe to place a queen at board[row][col]
    public static boolean isSafe(char[][] board, int row, int col, int n) {
        // Check the column on the left side for threats
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 'Q') {
                return false;
            }
        }

        // Check the upper left diagonal for threats
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }

        // Check the upper right diagonal for threats
        for (int i = row, j = col; i >= 0 && j < n; i--, j++) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }

        return true;
    }

    // Recursive utility function to solve N-Queens
    public static void solveNQueensUtil(char[][] board, int row, int n, List<List<String>> solutions) {
        if (row == n) {
            // Convert the board to a solution format and add to solutions list
            List<String> solution = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                solution.add(new String(board[i]));
            }
            solutions.add(solution);
            return;
        }

        for (int col = 0; col < n; col++) {
            if (isSafe(board, row, col, n)) {
                board[row][col] = 'Q'; // Place the queen
                solveNQueensUtil(board, row + 1, n, solutions); // Recur to place queens in the next row
                board[row][col] = '.'; // Backtrack by removing the queen
            }
        }
    }

    // Main function to solve N-Queens problem
    public static List<List<String>> solveNQueens(int n) {
        char[][] board = new char[n][n];

        // Initialize the board with '.' (empty squares)
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }

        List<List<String>> solutions = new ArrayList<>();
        solveNQueensUtil(board, 0, n, solutions);
        return solutions;
    }

    // Function to print the solution in a boxed format
    public static void printSolution(List<String> solution) {
        int n = solution.size();
        // Print the top border
        for (int j = 0; j < n; j++) {
            System.out.print("+---");
        }
        System.out.println("+");

        // Print each row
        for (String row : solution) {
            System.out.print("| "); // Left border
            for (char cell : row.toCharArray()) {
                System.out.print(cell == 'Q' ? "Q" : " "); // Print 'Q' or space
                System.out.print(" | "); // Right border
            }
            System.out.println();

            // Print the separator border after each row
            for (int j = 0; j < n; j++) {
                System.out.print("+---");
            }
            System.out.println("+");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Enter the size of the board (N): ");
            int n = scanner.nextInt();

            if (n < 1) {
                System.out.println("The size of the board must be a positive integer.");
            } else {
                List<List<String>> solutions = solveNQueens(n);
                System.out.printf("\nTotal solutions for a %dx%d board: %d\n", n, n, solutions.size());

                for (int i = 0; i < solutions.size(); i++) {
                    System.out.printf("Solution %d:\n", i + 1);
                    printSolution(solutions.get(i));
                    System.out.println(); // Print a blank line between solutions
                }
            }

        } catch (Exception e) {
            System.out.println("Please enter a valid integer.");
        } finally {
            scanner.close();
        }
    }
}






