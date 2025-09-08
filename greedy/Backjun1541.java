package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Backjun1541 {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String expr = br.readLine();

        // '-' 기준으로 쪼개기
        String[] minusParts = expr.split("-");

        int result = 0;

        // 첫 번째 덩어리 (그냥 더하기)
        result += sumOfPlus(minusParts[0]);

        // 나머지 덩어리들은 모두 합쳐서 빼기
        for (int i = 1; i < minusParts.length; i++) {
            result -= sumOfPlus(minusParts[i]);
        }

        System.out.println(result);
    }

    // '+' 기준으로 쪼개서 합 구하기
    private static int sumOfPlus(String s) {
        String[] parts = s.split("\\+");
        int sum = 0;
        for (String part : parts) {
            sum += Integer.parseInt(part);
        }
        return sum;
    }

}
