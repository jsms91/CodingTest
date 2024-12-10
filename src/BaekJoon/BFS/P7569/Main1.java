package BaekJoon.BFS.P7569;

import java.io.*;
import java.util.*;
//Main 방식보다 약 100ms 처리시간이 빠름(크게 차이는 없음)
public class Main1 {
    // 상, 하, 좌, 우, 위, 아래
    private static final int[] DX = {-1, 1, 0, 0, 0, 0};
    private static final int[] DY = {0, 0, -1, 1, 0, 0};
    private static final int[] DZ = {0, 0, 0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());

        int[][][] box = new int[H][N][M];
        Queue<int[]> queue = new LinkedList<>();
        int unripeCount = 0;

        for (int h = 0; h < H; h++) {
            for (int n = 0; n < N; n++) {
                st = new StringTokenizer(br.readLine());
                for (int m = 0; m < M; m++) {
                    box[h][n][m] = Integer.parseInt(st.nextToken());
                    if (box[h][n][m] == 1) {
                        queue.offer(new int[]{h, n, m}); // 익은 토마토 위치
                    } else if (box[h][n][m] == 0) {
                        unripeCount++; // 익지 않은 토마토 개수
                    }
                }
            }
        }

        // BFS로 최소 일수 계산
        int days = 0;
        while (!queue.isEmpty() && unripeCount > 0) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] current = queue.poll();
                int z = current[0], x = current[1], y = current[2];

                for (int d = 0; d < 6; d++) {
                    int nz = z + DZ[d], nx = x + DX[d], ny = y + DY[d];

                    if (nz >= 0 && nz < H && nx >= 0 && nx < N && ny >= 0 && ny < M && box[nz][nx][ny] == 0) {
                        box[nz][nx][ny] = 1; // 익음으로 변경
                        queue.offer(new int[]{nz, nx, ny});
                        unripeCount--; // 익지 않은 토마토 개수 감소
                    }
                }
            }
            days++;
        }

        // 결과 출력
        System.out.println(unripeCount == 0 ? days : -1);
    }
}