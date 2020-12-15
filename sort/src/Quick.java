public class Quick {
    public static void main(String[] args) {
        int[] a = new int[] { 1, 5, 0, 3, 7, 3, 2, 4, 7, 2, 4, 6 };

        sort(a, 0, a.length - 1);

        for (int i : a) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static void sort(int a[], int low, int high) {
        if (low < high) {
            int p = partition(a, low, high);

            sort(a, low, p - 1);
            sort(a, p + 1, high);
        }
    }

    public static int partition(int[] a, int low, int high) {
        int p = a[high];
        int i = low;

        for (int j = low; j < high; j++) {
            if (a[j] < p) {
                int temp = a[i];
                a[i] = a[j];
                a[j] = temp;
                i++;
            }
        }

        int temp = a[i];
        a[i] = a[high];
        a[high] = temp;

        return i;
    }
}
