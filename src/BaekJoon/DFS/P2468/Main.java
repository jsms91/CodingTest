package BaekJoon.DFS.P2468;

import java.io.*;
import java.util.*;

//안전영역(실버1)
public class Main {
    //상,하,좌,우
    private static final int[] X = {-1,1,0,0};
    private static final int[] Y = {0,0,-1,1};
    private static int N; //NxN 지역 크기
    private static int[][] Map; //해당 지역 높이가 저장된 지도
    private static boolean[][] Check; //탐색 여부 체크
    private static int ans = 1; //어떠한 지역도 물에 잠기지 않을 경우를 초기 값으로 설정
    private static final HashSet<Integer> H = new HashSet<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        Map = new int[N][N];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                Map[i][j] = Integer.parseInt(st.nextToken());
                H.add(Map[i][j]);
            }
        }

        for(int height : H) {
            Check = new boolean[N][N]; //탐색 여부 초기화
            ans = Math.max(ans,safeHeight(height));
        }

        System.out.println(ans); //정답
    }
    
    //해당 높이 안전지대 탐색
    private static int safeHeight(int h) {
        int count = 0;
        for(int i=0; i<Map.length; i++) {
            for(int j=0; j<Map[i].length; j++) {
                if(!Check[i][j] && Map[i][j] > h) { //탐색하지 않았고 안전 지대 경우
                    DFS(i,j,h);
                    count++;
                }
            }
        }
        return count;
    }
    //안전지대 탐색
    private static void DFS(int x, int y, int h) {
        if(Check[x][y] || Map[x][y] <= h) { return; } //탐색한 경우 또는 물에 잠기는 지역인 경우 종료
        Check[x][y] = true; //탐색했다고 체크
        for(int i=0; i<4; i++) {
            int nextX = x + X[i];
            int nextY = y + Y[i];
            if(nextX>=0 && nextY>=0 && nextX<N && nextY<N) { //배열 범위인 경우만 탐색
                DFS(nextX,nextY,h);
            }
        }
    }
    
}