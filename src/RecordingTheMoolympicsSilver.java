import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class RecordingTheMoolympicsSilver {
    static String s = "recording";
    static class event implements Comparable<event>{
        int a, b;
        public event(int a1, int b1){
            a = a1;
            b = b1;
        }
        public int compareTo(event e){
            return b-e.b;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(s+".in"));
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new File(s+".out"));
        int n = Integer.parseInt(br.readLine());
        event[] programs = new event[n];
        for(int i = 0; i<n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            programs[i] = new event(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(programs);
        int e1 = 0, e2 = 0, ans = 0;
        for(int i = 0; i<n; i++){
            if(programs[i].a<e1 && programs[i].a<e2) continue;
            if(programs[i].a<e1){
                e2 = programs[i].b;
                ans++;
            }
            else if(programs[i].a<e2){
                e1 = programs[i].b;
                ans++;
            }
            else{
                if(e1>e2){
                    e1 = programs[i].b;
                    ans++;
                }
                else{
                    e2 = programs[i].b;
                    ans++;
                }
            }
        }
        pw.println(ans);
        pw.close();
    }
}