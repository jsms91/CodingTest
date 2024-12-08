package BaekJoon.BFS.P14502;

import java.io.*;
import java.util.*;

public class Main {
    //상,하,좌,우
    private static final int[] X = {-1,1,0,0};
    private static final int[] Y = {0,0,-1,1};
    private static int N; //세로
    private static int M; //가로크기
    private static int[][] map; //지역
    private static boolean[][] checkArr; //방문 체크
    private static int MaxArea = Integer.MIN_VALUE; //최대 안전지역 수
    private static final Queue<Cooldinate> VIRUS = new ArrayDeque<>(); //초기 바이러스 정보
    public static class Cooldinate {
        private int x,y;
        public Cooldinate(int x, int y) {
            this.x = x;
            this.y = y;
        }
        public int[] data() {
            return new int[]{x,y};
        }
    }
    public static void main(String[] args) throws IOException {
        //입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 2) {
                    VIRUS.offer(new Cooldinate(i, j)); //바이러스 좌표 입력
                }
            }
        }

        //문제풀이 시작(3개의 벽을 세우는 경우의 수 -> 각 경우의 수마다 안전지역 구하기 -> 경우의 수 별 안전지역 개수 비교 후 최대 안전지역 개수 구하기)
        placeWall(0,0,0);
        //정답
        System.out.println(MaxArea);
    }
    // 벽을 세우는 함수 (백트래킹 방식)
    private static void placeWall(int count, int startX, int startY) {
        if (count == 3) { // 벽을 3개 세웠으면 안전 지역 계산
            int result = SafeArea();
            MaxArea = Math.max(MaxArea, result); // 최대 안전지역 갱신
            return; //종료
        }
        // 벽을 세울 수 있는 위치 탐색
        for (int i = startX; i < N; i++) {
            //(i == startX) ? startY : 0 ->> 여기를 잘 이해해야 됨(현재 좌표 다음 좌표부터 탐색하여 앞에 탐색했던 좌표 중복 탐색 방지)
            for (int j = (i == startX) ? startY : 0; j < M; j++) {
                if (map[i][j] == 0) { // 벽을 세울 수 있는 곳
                    map[i][j] = 1; // 벽 세우기
                    placeWall(count + 1, i, j + 1); // 재귀적으로 벽을 세우기
                    map[i][j] = 0; // 벽 제거
                }
            }
        }
    }
    //안전지역 계산
    private static int SafeArea() {

        checkArr = new boolean[N][M];
        Queue<Cooldinate> queue = new ArrayDeque<>(VIRUS);

        Iterator<Cooldinate> iterator = queue.iterator();
        while (iterator.hasNext()) {
            Cooldinate co = iterator.next();
            checkArr[co.data()[0]][co.data()[1]] = true; //바이러스 위치 표시
        }

        while (!queue.isEmpty()) {
            Cooldinate cooldinate = queue.poll(); //순서대로 바이러스 좌표 출력
            int currentX = cooldinate.data()[0];
            int currentY = cooldinate.data()[1];

            for(int i=0; i<4; i++) { //바이러스 좌료로부터 상하좌우 탐색 시작
                int nextX = currentX + X[i];
                int nextY = currentY + Y[i];
                //상하좌우 이동 후 방문하지 않았고 배열범위인 곳만
                if(nextX>=0 && nextY>=0 && nextX<N && nextY<M && !checkArr[nextX][nextY]) {
                    if(map[nextX][nextY] == 0) { //안전지역은 바이러스 지역으로 오염
                        checkArr[nextX][nextY] = true;
                        queue.offer(new Cooldinate(nextX,nextY));
                    }
                }
            }
        }
        // 안전 지역 계산
        int safeArea = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0 && !checkArr[i][j]) {
                    safeArea++;
                }
            }
        }
        return safeArea;
    }

}