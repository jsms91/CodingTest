package programers.java.level1;

//PCCE 기출문제 10번 - 공원
public class p3 {

    public int solution(int[] mats, String[][] park) {

        int maxSize = 0;

        for(int i=0; i<park.length; i++) {
            for(int j=0; j<park[i].length; j++) {
                int size = 0; //돗자리 펼칠 자리가 나타날 때 해당 위치 최대 크기
                int[] x = {i,j}; //오른으로 이동
                int[] y = {i,j}; //아래로 이동

                size = check(park, 0, x, y); //해당 위치에서 펼칠 수 있는 최대 크기
                maxSize = Math.max(maxSize,size); //현재까지 가장 큰 사이즈 구하기
            }
        }
        return answer(mats, maxSize); //정답
    }

    private int check(String[][] p, int size, int[] x, int[] y) {
        for(int i=0; i<=size; i++) {
            //배열의 크기를 벗어나서 이돟할 수 없다.
            if((x[1]+i) >= p[x[0]].length || (y[0]+i) >= p.length) {
                return size;
            }
            //오른쪽, 아래로 이동시 -1이 아니면 직전에 구한 최대 크기 반환
            if(!p[x[0]][x[1]+i].equals("-1") || !p[y[0]+i][y[1]].equals("-1") ) {
                return size;
            }
        }
        size++;
        x[0]++; //아래로 한칸 이동
        y[1]++; //오른쪽 한칸 이동

        // 이동 후 배열크기를 벗어나면 종료 아니면 다음으로 이동
        return x[0] >= p.length || y[1] >= p[y[0]].length ? size : check(p, size, x, y);
    }

    private int answer(int[] sizes, int maxSize) {
        int result = -1; //돗자리를 깔 수 없는 경우
        for(int size : sizes) {
            //보유한 돗자리 크기가 펼칠수 있는 크기와 같거나 작을 경우, 펼칠수 있는 돗자리 크기중 가장 큰 값
            result = size <= maxSize ? Math.max(size,result) : result;
        }
        return result;
    }

}