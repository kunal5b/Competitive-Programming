import java.util.Scanner;

public class FieldDay {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int c = scan.nextInt(), n = scan.nextInt();
        int[] teams = new int[n];
        int[] maxs = new int[n];
        for (int i = 0; i < n; i++) {
            String str = scan.next();
            int bits = 0;
            for (int j = 0; j < c; j++) {
                bits |= (str.charAt(j) == 'H') ? (1 << j) : 0;
            }
            teams[i] = bits;
        }
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int diff = Integer.bitCount(teams[i] ^ teams[j]);
                maxs[i] = Math.max(maxs[i], diff);
                maxs[j] = Math.max(maxs[j], diff);
            }
        }
        for (int i = 0; i < n; i++) {
            System.out.println(maxs[i]);
        }
    }
}