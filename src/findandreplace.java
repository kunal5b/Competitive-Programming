import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class findandreplace {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        while(t-->0){
            String s1 = scan.next(), s2 = scan.next();
            int[] to = new int[52];
            Arrays.fill(to, -1);
            Set<Character> set = new HashSet<>();
            boolean impossible = false;
            for(int i = 0; i<s1.length(); i++){
                int a = 0, b = 0;
                if('a'<=s1.charAt(i) && s1.charAt(i)<='z'){
                    a = s1.charAt(i)-'a';
                }
                else{
                    a = s1.charAt(i)-'A'+26;
                }
                if('a'<=s2.charAt(i) && s2.charAt(i)<='z'){
                    b = s2.charAt(i)-'a';
                }
                else{
                    b = s2.charAt(i)-'A'+26;
                }
                set.add(s2.charAt(i));
                if(to[a]!=b && to[a]!=-1) impossible = true;
                to[a] = b;
            }
            if(set.size() == 52){
                impossible = true;
            }
            if(s1.equals(s2)) impossible = false;
            int ans = 0;
            if(!impossible){
                int[] inDegree = new int[52];
                for (int a = 0; a < 52; a++) {
                    if (to[a] != -1 && to[a] != a) {
                        inDegree[to[a]]++;
                    }
                }

                for (int a = 0; a < 52; a++) {
                    if (to[a] != -1 && to[a] != a) {
                        ans++;
                    }
                }

                int[] seen = new int[52];
                for (int r = 0; r < 52; r++) {
                    if (seen[r] == 0) {
                        int a = r;
                        while (a != -1 && seen[a] == 0) {
                            seen[a] = r + 1;
                            a = to[a];
                        }
                        if (a != -1 && a != to[a] && seen[a] == r + 1) {
                            int s = a;
                            boolean freePass = false;
                            do {
                                seen[a] = 2;
                                if (inDegree[a] > 1) {
                                    freePass = true;
                                }
                                a = to[a];
                            } while (a != s);
                            if (!freePass) {
                                ans++;
                            }
                        }
                    }
                }
            } else {
                ans = -1;
            }
            System.out.println(ans);
        }
    }
}
