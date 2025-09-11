package divideConquer;

import java.util.Scanner;

public class Backjun1629 {
    static long A, B, C;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        A = sc.nextLong();
        B = sc.nextLong();
        C = sc.nextLong();
        System.out.println(pow(A, B));
    }

    // 분할정복 거듭제곱
    static long pow(long a, long b) {
        if (b == 0) return 1;       // A^0 = 1
        if (b == 1) return a % C;   // A^1 = A mod C

        long half = pow(a, b / 2);

        if (b % 2 == 0) {
            return (half * half) % C;
        } else {
            return ((half * half) % C * (a % C)) % C;
        }
    }
}
