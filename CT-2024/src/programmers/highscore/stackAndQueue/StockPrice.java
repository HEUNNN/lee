package programmers.highscore.stackAndQueue;

import java.util.Stack;

public class StockPrice {
    public static void main(String[] args) {
        // [1, 2, 3, 2, 3]
        // [4, 3, 1, 1, 0]

        int[] prices = {2, 2, 3, 1, 5};
//        int[] answer = getStockPriceWithArray(prices);
        int[] answer = getStockPriceWithStack(prices);

        for (int i : answer) {
            System.out.println(i);
        }
    }

    public static int[] getStockPriceWithArray(int[] prices) {
        int[] answer = new int[prices.length];

        for (int i = 0; i < prices.length - 1; i++) {
            int curr = prices[i];
            for (int j = i + 1; j < prices.length; j++) {
                answer[i] ++;
                if (curr > prices[j]) {
                    break;
                }
            }
        }

        return answer;
    }

    public static int[] getStockPriceWithStack(int[] prices) {
        int[] answer = new int[prices.length];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < prices.length; i++) {
            while (!stack.isEmpty() && prices[stack.peek()] > prices[i]) {
                answer[stack.peek()] = (i - stack.peek());
                stack.pop();
            }
            stack.push(i);
        }

        while (!stack.isEmpty()) {
            answer[stack.peek()] = (prices.length - 1) - stack.peek();
            stack.pop();
        }

        return answer;
    }
}
