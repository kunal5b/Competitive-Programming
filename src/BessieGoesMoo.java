import java.io.*;
import java.util.StringTokenizer;

public class BessieGoesMoo {
static String s = "bgm";
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(s+".in"));
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new File(s+".out"));
        int n = Integer.parseInt(br.readLine());
        long[][] arr = new long[250][7];
        for(int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            char c = st.nextToken().charAt(0);
            int v = Integer.parseInt(st.nextToken());
            arr[c][(v % 7 + 7) % 7]++;
        }
        long cnt = 0;
        for(int b = 0; b<7; b++){
            for(int e = 0; e<7; e++){
                for(int s = 0; s<7; s++){
                    for(int i = 0; i<7; i++){
                        for(int g = 0; g<7; g++){
                            for(int o = 0; o<7; o++){
                                for(int m = 0; m<7; m++) {
                                    if (((b + e + s + s + i + e) * (g + o + e + s) * (m + o + o)) % 7 == 0) {
                                        cnt += (arr['B'][b] * arr['E'][e] * arr['S'][s] * arr['I'][i] * arr['G'][g] * arr['O'][o] * arr['M'][m]);

                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        pw.println(cnt);
        pw.close();
    }
}