package BaekJoon.DFS.P1987;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//비트마스크는 각 알파벳을 비트로 표현하여 메모리 사용을 최소화하고, 방문 여부를 빠르게 확인할 수 있다.
//속도가 빠르다.
//일단 비트마스크가 이런거다라고 확인 후 추후 개념정리하기
public class P1987_3 {
    private static final int[] X = {-1, 1, 0, 0};
    private static final int[] Y = {0, 0, -1, 1};

    private static int R; // 세로 크기
    private static int C; // 가로 크기
    private static char[][] board; // 알파벳 보드
    private static int max = 0; // 최장 경로 길이

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        board = new char[R][C];
        for (int i = 0; i < R; i++) {
            board[i] = br.readLine().toCharArray();
        }

        // DFS 시작: 좌측 상단에서 시작, 방문한 알파벳을 비트마스크로 저장
        //비트마스크를 초기화
        //board[0][0]의 알파벳 - 'A'를 하면 board[0][0]의 인덱스를 구할 수 있다.
        //ex) C - A = 67-65 = 2
        //1 << 숫자는 1을 왼쪽으로 숫자만큼 이동
        //ex) 1 << 3은 001을 왼쪽으로 3만큼 이동하면 1000이다(10진수로 8)

        DFS(0, 0, 1, 1 << (board[0][0] - 'A'));

        System.out.println(max);
    }

    private static void DFS(int x, int y, int count, int visited) {
        // 최댓값 갱신
        max = Math.max(max, count);

        for (int i = 0; i < 4; i++) {
            int nextX = x + X[i];
            int nextY = y + Y[i];

            // 보드 범위를 벗어나지 않고, 새로운 알파벳이라면
            if (nextX >= 0 && nextY >= 0 && nextX < R && nextY < C) {
                int nextAlpha = board[nextX][nextY] - 'A'; //다음 알바벳 인덱스번호
                // 방문하지 않은 알파벳
                //ex) current=A -> 1 next=B -> 10
                //A(1)&B(10) = 00 = 0(중복 x)
                //ex) current=110(A-B) next=B ->10
                //110&10 = 010 = 2 중복 O
                if ((visited & (1 << nextAlpha)) == 0) {
                    // 다음 칸으로 이동
                    //visited | (1 << nextAlpha)
                    //ex) visited(현재 A라서 001일경우) nextAlpha(B경우 1<<1 = 010)
                    //001 | 010 = 011
                    DFS(nextX, nextY, count + 1, visited | (1 << nextAlpha));
                }
            }
        }
    }
}