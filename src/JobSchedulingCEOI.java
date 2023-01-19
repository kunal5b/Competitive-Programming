import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class JobSchedulingCEOI {
    static class task implements Comparable<task>{
        int day, ind;
        public task(int d, int i){
            day = d;
            ind = i;
        }
        public int compareTo(task t){
            return day-t.day;
        }
    }
    static int n, m, d;
    static task[] list;
    static boolean works(int cnt){
        int ind = 0;
        Queue<task> q = new LinkedList<>();
        for(int i = 0; i<m; i++) q.add(list[i]);
        while(!q.isEmpty()){
            task t = q.poll();

        }
        return true;
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        n = scan.nextInt();
        d = scan.nextInt();
        m = scan.nextInt();
        list = new task[m];
        for(int i = 0; i<m; i++){
            list[i] = new task(scan.nextInt(), i+1);
        }
        Arrays.sort(list);
        int lo = 0, hi = m;
        while(lo<hi){
            int mid = (lo+hi)/2;
            if(works(mid)) hi = mid;
            else lo = mid+1;
        }
    }
}