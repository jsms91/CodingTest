package BaekJoon.BFS.P14502;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main1 {
    // 상, 하, 좌, 우
    private static final int[] X = {-1, 1, 0, 0};
    private static final int[] Y = {0, 0, -1, 1};
    private static int N; // 세로
    private static int M; // 가로
    private static int[][] map; // 지역
    private static int MaxArea = Integer.MIN_VALUE; // 최대 안전지역 수
    private static List<Cooldinate> VIRUS = new ArrayList<>();
    private static int wallCount = 0; // 벽의 개수

    public static class Cooldinate {
        private int x;
        private int y;

        public Cooldinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int[] data() {
            return new int[]{x, y};
        }
    }

    public static void main(String[] args) throws IOException {
        // 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        // 지도와 바이러스 위치 저장
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    VIRUS.add(new Cooldinate(i, j));
                }
                if (map[i][j] == 1) {
                    wallCount++;
                }
            }
        }

        // 벽을 세우는 모든 경우의 수 탐색
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) {
                    map[i][j] = 1; // 첫 번째 벽 세우기
                    for (int k = i; k < N; k++) {
                        for (int l = (k == i) ? j + 1 : 0; l < M; l++) {
                            if (map[k][l] == 0) {
                                map[k][l] = 1; // 두 번째 벽 세우기
                                for (int m = k; m < N; m++) {
                                    for (int n = (m == k) ? l + 1 : 0; n < M; n++) {
                                        if (map[m][n] == 0) {
                                            map[m][n] = 1; // 세 번째 벽 세우기
                                            int result = SafeArea(); // 안전지역 계산
                                            MaxArea = Math.max(MaxArea, result); // 최대 안전지역 갱신
                                            map[m][n] = 0; // 세 번째 벽 제거
                                        }
                                    }
                                }
                                map[k][l] = 0; // 두 번째 벽 제거
                            }
                        }
                    }
                    map[i][j] = 0; // 첫 번째 벽 제거
                }
            }
        }

        // 결과 출력
        System.out.println(MaxArea);
    }

    // 바이러스 확산 후 안전지역 계산
    private static int SafeArea() {
        boolean[][] checkArr = new boolean[N][M]; //바이러스지역 탐색을 위한 초기화
        Queue<Cooldinate> queue = new LinkedList<>();

        // 바이러스 초기화
        for (Cooldinate c : VIRUS) {
            queue.add(c);
            checkArr[c.data()[0]][c.data()[1]] = true; //바이러스 지역만 true
        }

        // BFS로 바이러스 확산
        while (!queue.isEmpty()) {
            Cooldinate current = queue.poll();
            int x = current.data()[0];
            int y = current.data()[1];

            for (int i = 0; i < 4; i++) {
                int nextX = x + X[i];
                int nextY = y + Y[i];

                if (nextX >= 0 && nextY >= 0 && nextX < N && nextY < M && !checkArr[nextX][nextY]) {
                    if (map[nextX][nextY] == 0) {
                        checkArr[nextX][nextY] = true; //바이러스 지역만 true
                        queue.add(new Cooldinate(nextX, nextY)); //다음 인접 지역 바이러스  전파
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