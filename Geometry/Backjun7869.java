package Geometry;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Backjun7869 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        double x1 = Double.parseDouble(st.nextToken());
        double y1 = Double.parseDouble(st.nextToken());
        double r1 = Double.parseDouble(st.nextToken());
        double x2 = Double.parseDouble(st.nextToken());
        double y2 = Double.parseDouble(st.nextToken());
        double r2 = Double.parseDouble(st.nextToken());

        double d = Math.hypot(x1 - x2, y1 - y2); // 중심 거리
        double area;

        // 안 겹침
        if (d >= r1 + r2) {
            area = 0.0;
        }
        // 포함 관계
        else if (d <= Math.abs(r1 - r2)) {
            double r = Math.min(r1, r2);
            area = Math.PI * r * r;
        }
        // 부분 겹침
        else {
            double angle1 = 2 * Math.acos(
                    (d * d + r1 * r1 - r2 * r2) / (2 * d * r1)
            );
            double angle2 = 2 * Math.acos(
                    (d * d + r2 * r2 - r1 * r1) / (2 * d * r2)
            );

            area = (r1 * r1 * (angle1 - Math.sin(angle1)) / 2)
                 + (r2 * r2 * (angle2 - Math.sin(angle2)) / 2);
        }

        System.out.printf("%.3f\n", area);
    }
}
