package programers.java.level1;

//PCCE 기출문제 9번 - 지폐 접기
public class p2 {
    public int solution(int[] wallet, int[] bill) {
        int answer = 0;

        int bWidth = bill[0];
        int bLength = bill[1];

        while(true) {
            if((wallet[0] >= bWidth && wallet[1] >= bLength) ||
                    (wallet[0] >= bLength && wallet[1] >= bWidth)) {
                break; // 지갑에 들어갈 사이즈가 될 경우 종료
            }
            answer++; //접는 횟수 1씩 증가
            if(bWidth > bLength) { //길이가 긴부분을 반으로 접는다.
                bWidth /= 2;
            } else {
                bLength /= 2;
            }
        }

        return answer;
    }

}
