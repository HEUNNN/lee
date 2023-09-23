package Programmers.sort;

public class KthNumber {
    public static void main(String[] args) {
        int[] array = {1, 5, 2, 6, 3, 7, 4};
        int[][] commands = {{2, 5, 3}, {4, 4, 1}, {1, 7, 3}};
        int[] kNum = new KthNumber().getKNums(array, commands);
        for (int i : kNum) {
            System.out.println(i);
        }
    }

    public int[] getKNums(int[] array, int[][] commands) {
        int[] result = new int[commands.length];
        for (int i = 0; i < commands.length; i++) {
            int knum = getKNum(array, commands[i][0], commands[i][1], commands[i][2]);
            result[i] = knum;
        }
        return result;
    }

    public int getKNum(int[] array, int start, int end, int target) {
        int[] cutArr = new int[end - start + 1];
        int idx = 0;
        for (int i = start - 1; i < end; i++) {
            cutArr[idx++] = array[i];
        }
        sort(cutArr);
        return cutArr[target - 1];
    }

    public void sort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] > array[j]) {
                    swap(array, i, j);
                }
            }
        }
    }

    public void swap(int[] array, int target1, int target2) {
        int tmp = array[target1];
        array[target1] = array[target2];
        array[target2] = tmp;
    }
}
