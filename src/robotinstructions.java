import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class robotinstructions {
    static class pair{
        int x, y;
        public pair(int x1, int y1){
            x = x1;
            y = y1;
        }
    }
    static class triple{
        int x, y, moves;
        public triple(int x1, int y1, int m1){
            x = x1;
            y = y1;
            moves = m1;
        }
    }
    static int n, xdest, ydest;
    static ArrayList<triple> dest = new ArrayList<>();
    static ArrayList<triple> start = new ArrayList<>();
    static void loc(int curx, int cury, int ind, ArrayList<pair> instructions, int nummoves, boolean flag){
        if(ind == instructions.size()) return;
            if (flag) {
                dest.add(new triple(curx, cury, nummoves));
            } else {
                    start.add(new triple(curx, cury, nummoves));
            }

        loc(curx+instructions.get(ind).x, cury+instructions.get(ind).y, ind+1, instructions, nummoves+1, flag);
        loc(curx, cury, ind+1, instructions, nummoves, flag);
    }
    static ArrayList<pair> instructions1 = new ArrayList<>();
    static ArrayList<pair> instructions2 = new ArrayList<>();
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        n = scan.nextInt();
        xdest = scan.nextInt();
        ydest = scan.nextInt();
        for(int i = 0; i<n; i++){
            if(i<(n/2)) {
                instructions1.add(new pair(scan.nextInt(), scan.nextInt()));
            }
            else {
                instructions2.add(new pair(scan.nextInt(), scan.nextInt()));
            }
        }
        loc(0, 0, 0, instructions1, 0, false);
        loc(xdest, ydest, 0, instructions2, 0, true);
        for(triple t: start){
            System.out.println(t.x + " " + t.y + " " + t.moves);
        }
        System.out.println();
        for(triple t: dest){
            System.out.println(t.x + " " + t.y + " " + t.moves);
        }
    }
}
