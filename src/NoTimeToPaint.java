import java.util.Arrays;
import java.util.Scanner;

public class NoTimeToPaint {
    /*
    Algorithm:
    Find out the number of strokes for all 2n possible substrings(that include prefix/suffix) to
    determine in O(1) for each query later on.
    Cases:
    1. current char greater than prev, requires extra stroke
    2. current char == prev, we can simply add it on to the previous stroke that included prev
    3. current char is less than prev, slightly more complicated. We have to
       see if it will require an extra stroke by determining if it could have been
       included with the last stroke of the same color(all colors between them must be
       lighter. As we progress through the string we can make an array that stores the
       last time we encountered each given index.
    Time complexity to find prefix/suffix DP is O(N) and queries are O(1) each, so total time
    complexity is O(N+Q)
     */

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt(), q = scan.nextInt();
        StringBuilder s = new StringBuilder(scan.next());
        int[] prefixDP = new int[n], suffixDP = new int[n], lastEncounter = new int[35];
        Arrays.fill(lastEncounter, -1);
        prefixDP[0] = 1;
        lastEncounter[s.charAt(0)-'A'] = 0;
        for(int i = 1; i<n; i++){
            prefixDP[i] = prefixDP[i-1];
            if(s.charAt(i)>s.charAt(i-1)){
                // Case 1
                prefixDP[i]++;
            }
            // case 2 accounted for with prefixDP[i] = prefixDP[i-1]
            if(s.charAt(i)<s.charAt(i-1)){
                for(int j = 0; j<s.charAt(i)-'A'; j++){
                    if(lastEncounter[j]>lastEncounter[s.charAt(i)-'A'] || lastEncounter[s.charAt(i)-'A'] == -1){
                        prefixDP[i]++;
                        break;
                    }
                }
                // Case 3
            }
            lastEncounter[s.charAt(i)-'A'] = i;
        }
        Arrays.fill(lastEncounter, -1);
        suffixDP[0] = 1;
        lastEncounter[s.charAt(0)-'A'] = 0;
        s.reverse();
        int cnt = 0;
        for(int i = 1; i<n; i++){
            suffixDP[i] = suffixDP[i-1];
            if(s.charAt(i)>s.charAt(i-1)){
                suffixDP[i]++;
            }
            if(s.charAt(i)<s.charAt(i-1)){
                for(int j = 0; j<s.charAt(i)-'A'; j++){
                    if(lastEncounter[j]>lastEncounter[s.charAt(i)-'A'] || lastEncounter[s.charAt(i)-'A'] == -1){
                        suffixDP[i]++;
                        break;
                    }
                }
            }
            lastEncounter[s.charAt(i)-'A'] = i;
        }
        for(int i = 0; i<q; i++){
            int a = scan.nextInt(), b = scan.nextInt();
            if(a == 1 && b == n){
                System.out.println(0);
            }
            else if(a == 1){
                System.out.println(suffixDP[n-b-1]);
            }
            else if(b == n){
                System.out.println(prefixDP[a-2]);
            }
            else System.out.println(prefixDP[a-2]+suffixDP[n-b-1]);
        }
    }
}