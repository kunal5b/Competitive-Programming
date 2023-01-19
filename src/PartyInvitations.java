import java.io.*;
import java.util.*;

public class PartyInvitations {
    static String s = "invite";
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(new File(s+".in"));
        PrintWriter pw = new PrintWriter(new File(s+".out"));
        int n = scan.nextInt(), g = scan.nextInt();
        Queue<Integer> q = new LinkedList<Integer>();
        q.add(1);
        TreeSet<Integer>[] groups = new TreeSet[g];
        for(int i = 0; i<g; i++){
            int sz = scan.nextInt();
            groups[i] = new TreeSet<Integer>();
            for(int j = 0; j<sz; j++){
                groups[i].add(scan.nextInt());
            }
        }
        int cnt = 1;
        HashSet<Integer> set = new HashSet<Integer>();
        while(!q.isEmpty()){
            int cur = q.poll();
            for(int i = 0; i<g; i++){
                if(groups[i].contains(cur)){
                    groups[i].remove(cur);
                    if(groups[i].size() == 1){
                        q.add(groups[i].first());
                        if(!set.contains(groups[i].first())){
                            cnt++;
                            set.add(groups[i].first());
                        }
                    }
                }
            }
        }
        pw.print(cnt);
        pw.close();
    }
}