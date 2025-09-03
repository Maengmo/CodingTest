package stackQue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Backjun2346 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        String[] input = br.readLine().trim().split(" ");
        List<int[]> balloons = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            balloons.add(new int[]{i + 1, Integer.parseInt(input[i])});
        }

        int idx = 0;
        StringBuilder result = new StringBuilder();

        while(!balloons.isEmpty()) {
            int[] now = balloons.remove(idx);
            result.append(now[0]).append(" ");

            if(balloons.isEmpty()) break;

            int move = now[1];
            if (move > 0) {
                idx = (idx + (move - 1)) % balloons.size();
            } else {
                idx = (idx + move) % balloons.size();
                if (idx < 0) idx += balloons.size();
            }
        }
        
        System.out.print(result);

    }
    
}
