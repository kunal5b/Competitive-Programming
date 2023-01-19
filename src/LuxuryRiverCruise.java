import java.io.*;
import java.util.*;
public class LuxuryRiverCruise {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("cruise.in"));
        PrintWriter pw = new PrintWriter(new File("cruise.out"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken()), k = Integer.parseInt(st.nextToken());
        int[] left = new int[n];
        int[] right = new int[n];
        for(int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            left[i] = Integer.parseInt(st.nextToken())-1;
            right[i] = Integer.parseInt(st.nextToken())-1;
        }
        char[] path = new char[m];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<m; i++){
            path[i] = st.nextToken().charAt(0);
        }
        int[] adj = new int[n];
        for(int i = 0; i<n; i++){
            adj[i] = i;
            for(int j = 0; j<m; j++){
                if(path[j] == 'L'){
                    adj[i] = left[adj[i]];
                }
                else{
                    adj[i] = right[adj[i]];
                }
            }
        }
        ArrayList<Integer> cyc = new ArrayList<Integer>();
        boolean[] visited = new boolean[n];
        int cur = 0, cl = 0;
        while(true){
            if(visited[cur]){
                cl = cyc.size()-cyc.indexOf(cur);
                k-=n;
                k%=cl;
                k+=n;
                break;
            }
            visited[cur] = true;
            cyc.add(cur);
            cur = adj[cur];
        }
        int c = 0;
        for(int i = 0; i<k; i++){
            c = adj[c];
        }
        pw.println(c+1);
        pw.close();
    }
}