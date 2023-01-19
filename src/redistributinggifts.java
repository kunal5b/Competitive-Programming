import java.util.ArrayList;
import java.util.Scanner;

public class redistributinggifts {
    static int MAX_N = 500;
    static boolean[][] possible = new boolean[MAX_N][MAX_N];
    static ArrayList<Integer>[] adj = new ArrayList[MAX_N];
    public static void reachable(int currentStart, int currentNode){
        if(possible[currentStart][currentNode]) return;
        possible[currentStart][currentNode] = true;
        for(int nextNode: adj[currentNode]){
            reachable(currentStart, nextNode);
        }
    }
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        for(int i = 0; i<n; i++){
            adj[i] = new ArrayList<>();
        }
        for(int i = 0; i<n; i++){
            boolean foundI = false;
            for(int j = 0; j<n; j++){
                int l = scan.nextInt()-1;
                if(foundI) continue;
                adj[i].add(l);
                if(l == i) foundI = true;
            }
        }
        scan.close();
        for(int i = 0; i<n; i++){
            reachable(i, i);
        }
        for(int i = 0; i<n; i++){
            for(int j: adj[i]){
                if(possible[j][i]){
                    System.out.println(j+1);
                    break;
                }
            }
        }
    }
}
