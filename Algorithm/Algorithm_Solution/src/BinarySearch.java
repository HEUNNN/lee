public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = new int[]{3, 6, 7, 8, 10, 14, 27, 58};
        int target = 6;
        int result1 = new BinarySearch().binarySearchV1(target, arr);
        System.out.println(result1);
        int result2 = new BinarySearch().binarySearchV2(target, 0, arr.length - 1, arr);
        System.out.println(result2);
    }

    private int binarySearchV1(int target, int[] arr) {
        int low = 0;
        int high = arr.length - 1;
        int answer = -1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (target > arr[mid]) {
                low = mid + 1;
            } else if (target < arr[mid]) {
                high = mid - 1;
            } else { // target == mid
                return mid;
            }
        }
        return answer;
    }

    private int binarySearchV2(int target, int low, int high, int[] arr) {
        if (low > high) return -1;
        int mid = (low + high) / 2;
        if (target > arr[mid]) {
            return binarySearchV2(target, mid + 1, high, arr);
        } else if (target < arr[mid]) {
            return binarySearchV2(target, low, mid - 1, arr);
        } else {
            return mid;
        }
    }
}
