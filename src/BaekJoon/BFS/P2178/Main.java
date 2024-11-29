package BaekJoon.BFS.P2178;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    //상,하,좌,우
    private static final int[] X = {-1,1,0,0};
    private static final int[] Y = {0,0,-1,1};
    private static int N;
    private static int M;
    private static int[][] map;
    private static boolean[][] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visit = new boolean[N][M];

        for(int i=0; i<N; i++) {
            String[] number = br.readLine().split("");
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(number[j]);
            }
        }

        BFS(0,0);
        System.out.println(map[N-1][M-1]);

    }

    private static void BFS(int x, int y) {
        Queue<int[]> que = new LinkedList<>();
        que.offer(new int[] {x,y});

        while (!que.isEmpty()) {
            int[] now = que.poll(); //선입선출 빼기
            int x1 = now[0];
            int y1 = now[1];
            visit[x][y] = true; // 방문 체크
            for(int i=0; i<4; i++) { //상,하,좌,우 탐색
                int nextX = x1 + X[i];
                int nextY = y1 + Y[i];
                if(nextX >=0 && nextY >=0 && nextX<N && nextY<M) { //배열 범위안에서만 즉 유요한 좌표
                    if(!visit[nextX][nextY] && map[nextX][nextY] !=0) { //방문하지 않았고, 길이 존재(1)하는 경우만
                        visit[nextX][nextY] = true;
                        map[nextX][nextY] = map[x1][y1] + 1; //현재까지 지나온길 + 1 추가
                        que.add(new int[] {nextX,nextY});
                    }
                }
            }
        }
    }

}