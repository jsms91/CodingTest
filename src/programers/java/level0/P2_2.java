package programers.java.level0;
//나선형 다른 풀이방식
public class P2_2 {

    public static int[][] solution(int n) {

        int[][] answers = new int[n][n];

        // 방향을 나타내는 배열: 오른쪽, 아래쪽, 왼쪽, 위쪽 순서
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};

        int direction = 0;  // 현재 진행 방향 (0: 오른쪽, 1: 아래쪽, 2: 왼쪽, 3: 위쪽)
        int x = 0, y = 0;   // 현재 위치
        int value = 1;      // 배열에 채워질 값

        while (value <= n*n) {

            answers[x][y] = value++; // 현재 위치에 값을 넣고 증가


            // 다음 위치 계산
            int nextX = x + dx[direction];
            int nextY = y + dy[direction];

            // 범위를 벗어나거나 이미 채워진 경우 방향을 변경(이 부분이 중요)
            if (nextX < 0 || nextX > n-1 || nextY < 0 || nextY > n-1 || answers[nextX][nextY] != 0) {
                direction = (direction + 1) % 4;  // 시계 방향으로 변경
                nextX = x + dx[direction];
                nextY = y + dy[direction];
            }

            // 새로운 위치로 이동
            x = nextX;
            y = nextY;

        }

        return answers;

    }

}