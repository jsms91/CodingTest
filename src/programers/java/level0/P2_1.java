package programers.java.level0;
//정수를 나선형으로 배치하기(50분)
public class P2_1 {

    public static int[][] solution(int n) {

        int[][] answer = new int[n][n];
        int start = 1; //시계방향으로 1->2->3->4 ->1...

        int xStart = 1;// x index 값이 감소 할 수 있는 limit
        int yStart = 0;// y index 값이 감소 할 수 있는 limit
        int xEnd = n-1; // x index 값이 증가 할 수 있는 limit
        int yEnd = n-1; // y index 값이 증가 할 수 있는 limit
        int xNow = 0;//현재 X좌표 위치
        int yNow = 0;//현재 Y좌표 위치

        for(int i=1; i<=n*n; i++) {
            if(start==1) {
                answer[xNow][yNow] = i;
                if(yNow == yEnd) {
                    start = 2;
                    xNow++;
                    yEnd--;
                } else {
                    yNow++;
                }
            } else if(start==2) {
                answer[xNow][yNow] = i;
                if(xNow == xEnd) {
                    start = 3;
                    yNow--;
                    xEnd--;
                } else {
                    xNow++;
                }
            } else if(start==3) {
                answer[xNow][yNow] = i;
                if(yNow == yStart) {
                    start = 4;
                    xNow--;
                    yStart++;
                } else {
                    yNow--;
                }
            } else {
                answer[xNow][yNow] = i;
                if(xNow == xStart) {
                    start = 1;
                    yNow++;
                    xStart++;
                } else {
                    xNow--;
                }

            }
        }

        return answer;
    }

}