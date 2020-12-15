public class Merge {
    public static void main(String[] args) {
        int[] a = new int[] { 4, 2, 5, 3, 6, 2, 1, 7, 0, 10, 6 };

        sort(a, 0, a.length - 1);

        for (int i : a) {
            System.out.println(i);
        }
    }

    public static void merge(int[] a, int start, int mid, int end) {
        int[] temp = new int[end - start + 1];
        int i = start;
        int j = mid + 1;
        int m = 0;

        while (true) {
            if (i > mid && j > end) {
                break;
            } else if (i > mid && j <= end) {
                temp[m++] = a[j++];
            } else if (j > end && i <= mid) {
                temp[m++] = a[i++];
            } else {
                temp[m++] = a[i] < a[j] ? a[i++] : a[j++];
            }
        }

        m = 0;
        while (start <= end) {
            a[start++] = temp[m++];
        }
    }

    public static void sort(int[] a, int left, int right) {
        if (right > left) {
            int mid = Math.floorDiv(left + right, 2);
            sort(a, left, mid);
            sort(a, mid + 1, right);
            merge(a, left, mid, right);
        }
    }
}
