package BaekJoon.BFS.P7576;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    //상,하,좌,우
    private static final int[] X = {-1,1,0,0};
    private static final int[] Y = {0,0,-1,1};
    private static int M;
    private static int N;
    private static int[][] box;
    private static Queue<Point> que = new ArrayDeque<>();

    private static class Point {
        private int x,y,day;
        public Point(int x, int y, int day) {
            this.x = x;
            this.y = y;
            this.day = day;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); //세로(x)
        M = Integer.parseInt(st.nextToken()); //가로(y)
        box = new int[M][N];

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                box[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        //초기 Queue에 익은 토마토 좌표를 offer
        for(int i=0; i<M; i++) {
            for(int j=0; j<N; j++) {
                if(box[i][j] == 1) {
                    que.offer(new Point(i,j,0));
                }
            }
        }
        //BFS로 현재 익은 토마토 상,하,좌,우 익었다고 표시하기
        int answer = BFS();
        
        //익은 토마토 유무
        System.out.println(search() ? answer : -1);

    }

    private static boolean search() {
        for(int i=0; i<M; i++) {
            for(int j=0; j<N; j++) {
                if(box[i][j] == 0) {
                    return false; //하나라도 덜 익은 토마토가 존재하면 false 반환
                }
            }
        }
        return true; //true: 전부 익은 토마토
    }
    private static int BFS() {
        int MaxDay = 0;
        while (!que.isEmpty()) {
            Point current = que.poll();
            int x = current.x;
            int y = current.y;
            MaxDay = Math.max(MaxDay,current.day);

            for(int i=0; i<4; i++) {
                int nextX = x + X[i];
                int nextY = y + Y[i];
                if(nextX>=0 && nextY >=0 && nextX<M && nextY<N) {
                    if (box[nextX][nextY] == 0) { // 익지 않은 토마토만 처리
                        box[nextX][nextY] = 1; // 익은 상태로 변경
                        que.offer(new Point(nextX, nextY, current.day + 1)); // 큐에 추가
                    }
                }
            }
        }
        return MaxDay;
    }

}