import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class FollowingDirections {
    static boolean[][] vis;
    static long dfs(int i, int j, long curcnt, int[][] mat, int n){
        if(i == n || j == n){
            return curcnt*mat[i][j];
        }
        vis[i][j] = true;
        if(mat[i][j] == 0){
            return dfs(i+1, j, curcnt+1, mat, n);
        }
        else {
            return dfs(i, j+1, curcnt+1, mat, n);
        }
    }
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int[][] mat = new int[n+1][n+1];
        for(int i = 0; i<n; i++){
            String s = scan.next();
            for(int j = 0; j<s.length(); j++){
                mat[i][j] = s.charAt(j) == 'R' ? 1 : 0;
            }
            mat[i][n] = scan.nextInt();
        }
        for(int i = 0; i<n; i++){
            mat[n][i] = scan.nextInt();
        }
        int q = scan.nextInt();
        long cnt = 0;
        vis = new boolean[n][n];
        for(int i = 0; i<n; i++){
            for(int j = 0; j<n; j++){
                if(!vis[i][j]){
                    cnt+=dfs(i, j, 0, mat, n);
                }
            }
        }
        System.out.println(cnt);
        while(q-->0){
            int x = scan.nextInt()-1, y = scan.nextInt()-1;
            if(mat[x][y] == 0){
                mat[x][y] = 1;
            }
            else{
                mat[x][y] = 0;
            }
            cnt = 0;
            vis = new boolean[n][n];
            for(int i = 0; i<n; i++){
                for(int j = 0; j<n; j++){
                    if(!vis[i][j]){
                        cnt+=dfs(i, j, 0, mat, n);
                    }
                }
            }
            System.out.println(cnt);
        }
    }
}
