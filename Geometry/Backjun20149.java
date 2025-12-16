package Geometry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Backjun20149 {
     static class Point {
        long x, y;
        
        Point(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }
    
    // CCW 계산
    static int ccw(Point p1, Point p2, Point p3) {
        long result = (p2.x - p1.x) * (p3.y - p1.y) - (p2.y - p1.y) * (p3.x - p1.x);
        if (result > 0) return 1;
        if (result < 0) return -1;
        return 0;
    }
    
    // 선분 교차 판정
    static boolean isIntersect(Point p1, Point p2, Point p3, Point p4) {
        int ccw1 = ccw(p1, p2, p3);
        int ccw2 = ccw(p1, p2, p4);
        int ccw3 = ccw(p3, p4, p1);
        int ccw4 = ccw(p3, p4, p2);
        
        int ab = ccw1 * ccw2;
        int cd = ccw3 * ccw4;
        
        // 일반적인 교차
        if (ab < 0 && cd < 0) return true;
        
        // 일직선상에 있는 경우
        if (ab == 0 && cd == 0) {
            // 선분이 겹치는지 확인
            if (Math.min(p1.x, p2.x) <= Math.max(p3.x, p4.x) &&
                Math.min(p3.x, p4.x) <= Math.max(p1.x, p2.x) &&
                Math.min(p1.y, p2.y) <= Math.max(p3.y, p4.y) &&
                Math.min(p3.y, p4.y) <= Math.max(p1.y, p2.y)) {
                return true;
            }
            return false;
        }
        
        // 한 선분의 끝점이 다른 선분 위에 있는 경우
        if (ab <= 0 && cd <= 0) return true;
        
        return false;
    }
    
    // 교점 계산
    static double[] getIntersection(Point p1, Point p2, Point p3, Point p4) {
        int ccw1 = ccw(p1, p2, p3);
        int ccw2 = ccw(p1, p2, p4);
        int ccw3 = ccw(p3, p4, p1);
        int ccw4 = ccw(p3, p4, p2);
        
        // 일직선상에 있는 경우
        if (ccw1 == 0 && ccw2 == 0 && ccw3 == 0 && ccw4 == 0) {
            // 한 점에서만 만나는지 확인
            Point minL1 = p1.x < p2.x || (p1.x == p2.x && p1.y < p2.y) ? p1 : p2;
            Point maxL1 = p1.x > p2.x || (p1.x == p2.x && p1.y > p2.y) ? p1 : p2;
            Point minL2 = p3.x < p4.x || (p3.x == p4.x && p3.y < p4.y) ? p3 : p4;
            Point maxL2 = p3.x > p4.x || (p3.x == p4.x && p3.y > p4.y) ? p3 : p4;
            
            // 한 점에서만 만나는 경우
            if (maxL1.x == minL2.x && maxL1.y == minL2.y) {
                return new double[]{maxL1.x, maxL1.y};
            }
            if (maxL2.x == minL1.x && maxL2.y == minL1.y) {
                return new double[]{maxL2.x, maxL2.y};
            }
            
            return null; // 겹치거나 떨어져 있음
        }
        
        // 일반적인 교점 계산
        double px = (double) p1.x;
        double py = (double) p1.y;
        double qx = (double) p2.x;
        double qy = (double) p2.y;
        double rx = (double) p3.x;
        double ry = (double) p3.y;
        double sx = (double) p4.x;
        double sy = (double) p4.y;
        
        double det = (px - qx) * (ry - sy) - (py - qy) * (rx - sx);
        
        if (Math.abs(det) < 1e-10) return null; // 평행
        
        double t = ((px - rx) * (ry - sy) - (py - ry) * (rx - sx)) / det;
        
        double ix = px + t * (qx - px);
        double iy = py + t * (qy - py);
        
        return new double[]{ix, iy};
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        Point p1 = new Point(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()));
        Point p2 = new Point(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()));
        
        st = new StringTokenizer(br.readLine());
        Point p3 = new Point(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()));
        Point p4 = new Point(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()));
        
        if (isIntersect(p1, p2, p3, p4)) {
            System.out.println(1);
            double[] intersection = getIntersection(p1, p2, p3, p4);
            if (intersection != null) {
                System.out.println(intersection[0] + " " + intersection[1]);
            }
        } else {
            System.out.println(0);
        }
    }
}
