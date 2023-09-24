package Programmers.lv1;

public class ParkWalking {

    // https://school.programmers.co.kr/learn/courses/30/lessons/172928

    public static void main(String[] args) {
        String[] par1k = {"SOO", "OOO", "OOO"};
        String[] routes1 = {"E 2", "S 2", "W 1"};

        String[] park2 = {"SOO", "OXX", "OOO"};
        String[] routes2 = {"E 2", "S 2", "W 1"};

        String[] park3 = {"OSO", "OOO", "OXO", "OOO"};
        String[] routes3 = {"E 2", "S 3", "W 1"};

        int[] solution = new ParkWalking().solution(park3, routes3);
        for (int i : solution) {
            System.out.println(i);
        }
    }

    public int[] solution(String[] park, String[] routes) {
        int h = park.length;
        int w = park[0].length();

        int[][] hToW = {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};

        int[] currPosition = new int[2];

        getStartPosition(currPosition, park);

        for (int i = 0; i < routes.length; i++) {
            String[] route = routes[i].split(" ");
            String op = route[0];
            int step = Integer.parseInt(route[1]);

            if (op.equals("E") && (currPosition[1] + step) < w && isX(park, hToW[1], currPosition, step)) { // w++
                currPosition[1] += step;
            } else if (op.equals("W") && (currPosition[1] - step) >= 0 && isX(park, hToW[0], currPosition, step)) { // w--
                currPosition[1] -= step;
            } else if (op.equals("S") && (currPosition[0] + step) < h && isX(park, hToW[2], currPosition, step)) { // h++
                currPosition[0] += step;
            } else if (op.equals("N") && (currPosition[0] - step) >= 0 && isX(park, hToW[3], currPosition, step)) { // h--
                currPosition[0] -= step;
            }

        }

        return currPosition;
    }

    private boolean isX(String[] park, int[] directionArray, int[] currPosition, int step) {
        boolean isX = true;

        int h = currPosition[0] + directionArray[0];
        int w = currPosition[1] + directionArray[1];

        for (int i = 0; i < step; i++) {
            if (park[h].charAt(w) == 'X') {
                return false;
            }
            h += directionArray[0];
            w += directionArray[1];
        }

        return isX;
    }

    private void getStartPosition(int[] currPosition, String[] park) {
        int h = park.length;
        int w = park[0].length();
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (park[i].charAt(j) == 'S') {
                    currPosition[0] = i;
                    currPosition[1] = j;
                }
            }
        }
    }
}
