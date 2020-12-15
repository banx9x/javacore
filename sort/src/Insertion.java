public class Insertion {
    public static void main(String[] args) {
        int[] a = new int[] { 4, 3, 5, 2, 4, 3, 6 };

        sort(a);

        for (int i : a) {
            System.out.println(i);
        }
    }

    public static void sort(int[] a) {
        for (int i = 1; i < a.length - 1; i++) {
            int temp = a[i];
            int j = i;
            
            while (j > 0 && a[j - 1] > temp) {
                a[j] = a[j - 1];
                j--;
            }

            a[j] = temp;
        }
    }
}
