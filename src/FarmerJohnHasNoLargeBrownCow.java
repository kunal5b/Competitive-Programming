import java.io.*;
import java.util.*;

public class FarmerJohnHasNoLargeBrownCow {
    static final String filename = "nocow";

    public static void main(String[] args) throws IOException {
        // Scanner scan = new Scanner(new File(filename+".in"));
        Scanner scan = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(new File(filename + ".out"));
        int n = scan.nextInt(), k = scan.nextInt();
        ArrayList<String>[] set = new ArrayList[35];
        for(int i = 0; i<n; i++){
            StringTokenizer st = new StringTokenizer(scan.next());
            String farmer = st.nextToken(), john = st.nextToken(), has = st.nextToken(), no = st.nextToken(), s = st.nextToken();
            int c = 0;
            while(!s.equals("cow.")){
                set[c].add(s);
                c++;
                s = st.nextToken();
            }
        }
        for(int i = 0; i<set.length; i++){
            Collections.sort(set[i]);
        }

        pw.close();
    }
}