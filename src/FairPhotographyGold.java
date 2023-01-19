import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class FairPhotographyGold {
    static String filename = "fairphoto";
    static class cow implements Comparable<cow>{
        int x, b;
        public cow(int x1, int b1){
            x = x1;
            b = b1;
        }
        public int compareTo(cow c){
            return x-c.x;
        }
    }
    public static void main(String[] args) throws IOException {
        // Scanner scan = new Scanner(new File(filename+".in"));
        Scanner scan = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(new File(filename + ".out"));
        int n = scan.nextInt(), k = scan.nextInt();
        cow[] arr = new cow[n];
        for(int i = 0; i<n; i++){
            arr[i] = new cow(scan.nextInt(), scan.nextInt());
        }
        Arrays.sort(arr);
        for(int i = 0; i<n; i++){
            System.out.print(arr[i].x + (i<n-1 ? " " : "\n"));
        }
        for(int i = 0; i<n; i++){
            System.out.print(arr[i].b + (i<n-1 ? " " : "\n"));
        }
        pw.close();
    }
}