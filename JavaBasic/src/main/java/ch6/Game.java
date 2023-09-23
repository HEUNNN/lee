package ch6;

public class Game {
    public static void main(String[] args) {
        int x = 8; // Initial value of x
        int y = 16; // Initial value of y

        log(x, y);
    }

    private static void log(int x, int y) {
        Result result = playGame(x, y);

        System.out.println("Total moves: " + result.moves);
        System.out.println("Winner: Player " + result.winner);
    }

    public static class Result {
        int moves;
        int winner;

        public Result(int moves, int winner) {
            this.moves = moves;
            this.winner = winner;
        }
    }

    public static Result playGame(int x, int y) {
        int[] dp = new int[Math.max(x, y) + 1];

        for (int i = 1; i <= Math.max(x, y); i++) {
            dp[i] = 3 - dp[i - 1]; // Alternate players

            if (i <= x && dp[i - 1] == 2) {
                dp[i] = 1; // If previous player was Player B and there's a valid move for Player A
            }

            if (i <= y && dp[i - 1] == 1) {
                dp[i] = 2; // If previous player was Player A and there's a valid move for Player B
            }
        }

        int winner = dp[Math.max(x, y)];
        int totalMoves = x + y; // Total moves will be equal to the sum of initial x and y

        return new Result(totalMoves, winner);
    }
}
