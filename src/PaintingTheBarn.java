import java.io.*;
import java.util.*;
public class PaintingTheBarn {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("paintbarn.in"));
        PrintWriter pw = new PrintWriter(new File("paintbarn.out"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()), k = Integer.parseInt(st.nextToken()), cnt = 0;
        int[][] pre = new int[1005][1005];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken()), y1 = Integer.parseInt(st.nextToken()), x2 = Integer.parseInt(st.nextToken()), y2 = Integer.parseInt(st.nextToken());
            pre[x1][y1]++;
            pre[x2][y2]++;
            pre[x1][y2]--;
            pre[x2][y1]--;
        }
        for (int i = 0; i < 1005; i++) {
            for (int j = 0; j < 1005; j++) {
                if (i > 0) pre[i][j] += pre[i - 1][j];
                if (j > 0) pre[i][j] += pre[i][j - 1];
                if (i > 0 && j > 0) pre[i][j] -= pre[i - 1][j - 1];
                if (pre[i][j] == k) cnt++;
            }
        }
        pw.println(cnt);
        pw.close();
    }
}