package Geometry;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Backjun1069 {
    
   public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()), 
            m = Integer.parseInt(st.nextToken()), 
            d = Integer.parseInt(st.nextToken()), 
            t = Integer.parseInt(st.nextToken());
        br.close();

        double len = Math.sqrt(n * n + m * m);
        double walk, jump_and_walk, jump_one_more;

        walk = len;
        if (len >= d) {
            int jump = (int)(len / d);
            jump_and_walk = (t * jump) + (len - (jump * d));
            jump_one_more = t * (jump + 1);
        } else {
            jump_and_walk = t + (d - len);
            jump_one_more = t * 2;
        }
        System.out.println(Math.min(walk, Math.min(jump_and_walk, jump_one_more)));

   }

}