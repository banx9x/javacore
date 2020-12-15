public class Bubble {
    public static void main(String[] args) {
        int[] a = new int[] { 1, 4, 3, 2, 5, 6 };

        sort(a);

        for (int i : a) {
            System.out.println(i);
        }
    }

    public static void sort(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            int swap = 0;

            for (int j = 0; j < a.length - 1 - i; j++) {
                if (a[j] > a[j + 1]) {
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                    swap++;
                }
            }

            if (swap == 0) {
                break;
            }
        }
    }
}
