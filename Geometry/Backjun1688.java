package Geometry;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Backjun1688 {
     static class Point {
        long x, y;
        Point(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }

    static int N;
    static Point[] polygon;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        polygon = new Point[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            long x = Long.parseLong(st.nextToken());
            long y = Long.parseLong(st.nextToken());
            polygon[i] = new Point(x, y);
        }

        // 대연, 영훈, 범진 좌표 3개
        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            long x = Long.parseLong(st.nextToken());
            long y = Long.parseLong(st.nextToken());
            Point p = new Point(x, y);

            System.out.println(isInsideOrOnBoundary(p) ? 1 : 0);
        }
    }

    // 점이 다각형 내부 또는 경계에 있는지
    static boolean isInsideOrOnBoundary(Point p) {
        int count = 0;

        for (int i = 0; i < N; i++) {
            Point a = polygon[i];
            Point b = polygon[(i + 1) % N];

            // 1️⃣ 경계 위 체크
            if (onSegment(a, b, p)) {
                return true;
            }

            // 2️⃣ Ray Casting
            if ((a.y > p.y) != (b.y > p.y)) {
                double intersectX =
                        (double)(b.x - a.x) * (p.y - a.y) / (double)(b.y - a.y) + a.x;

                if (intersectX > p.x) {
                    count++;
                }
            }
        }

        return count % 2 == 1;
    }

    // 점 p가 선분 ab 위에 있는지
    static boolean onSegment(Point a, Point b, Point p) {
        if (ccw(a, b, p) != 0) return false;

        return Math.min(a.x, b.x) <= p.x && p.x <= Math.max(a.x, b.x) &&
               Math.min(a.y, b.y) <= p.y && p.y <= Math.max(a.y, b.y);
    }

    // CCW 계산
    static long ccw(Point a, Point b, Point c) {
        long value = (b.x - a.x) * (c.y - a.y)
                   - (b.y - a.y) * (c.x - a.x);

        if (value > 0) return 1;
        if (value < 0) return -1;
        return 0;
    }
}
