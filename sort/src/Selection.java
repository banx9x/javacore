public class Selection {
    public static void main(String[] args) {
        int[] a = new int[] { 1, 3, 2, 6, 3, 2, 5 };

        sort(a);

        for (int i : a) {
            System.out.println(i);
        }
    }
    
    public static void sort(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            int min = i;

            for (int j = i + 1; j < a.length; j++) {
                if (a[j] < a[min]) {
                    min = j;
                }
            }

            if (min > i) {
                int temp = a[min];
                a[min] = a[i];
                a[i] = temp;
            }
        }
    }
}
