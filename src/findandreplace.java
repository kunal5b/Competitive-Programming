import java.util.HashSet;
import java.util.Scanner;

public class findandreplace {
    static boolean[] vis = new boolean[100];
    static void dfs(int i){
        if(vis[i])
        vis[i] = true;
    }
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        while(t-->0){
            String s = scan.next(), s2 = scan.next();
            HashSet<Integer>[] numdiff = new HashSet[100];
            for(int i = 0; i< numdiff.length; i++) numdiff[i] = new HashSet<>();
            for(int i = 0; i<s.length(); i++){
                numdiff[s.charAt(i)-'0'].add(s2.charAt(i)-'0');
            }
            boolean impossible = false;
            for(HashSet<Integer> i: numdiff){
                if(i.size()>=2) impossible = true;
            }
            if(impossible){
                System.out.println(-1);
                continue;
            }

        }
    }
}
