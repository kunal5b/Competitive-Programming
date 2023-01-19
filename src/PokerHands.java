import java.io.*;
public class PokerHands {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("poker.in"));
        PrintWriter pw = new PrintWriter(new File("poker.out"));
        int n = Integer.parseInt(br.readLine()), cnt = 0, prev = 0;
        for(int i = 0; i<n; i++){
            int cur = Integer.parseInt(br.readLine());
            cnt += (prev<cur) ? cur-prev : 0;
        }
        pw.println(cnt);
        pw.close();
    }
}