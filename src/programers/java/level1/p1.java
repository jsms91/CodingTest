package programers.java.level1;

import java.util.*;

//2024 KAKAO WINTER INTERNSHIP > 가장 많이 받은 선물
public class p1 {

    public int solution(String[] friends, String[] gifts) {

        HashMap<String, Integer> person = new HashMap<>(); //사람,인덱스번호
        HashMap<String, Integer> giftIndex = new HashMap<>(); //사람,선물지수

        for(int i=0; i<friends.length; i++) {
            person.put(friends[i],i); //사람, 해당사람 인덱스번호
            giftIndex.put(friends[i],0); //사람, 선물지수 초기값 0
        }

        int[][] sell = new int[friends.length][friends.length]; //2차원배열로 주고받은 선물 표시


        for(String gift : gifts) {
            String[] giftArray = gift.split(" ");
            int x = person.get(giftArray[0]); //선물준 사람의 인덱스 번호
            int y = person.get(giftArray[1]); //선물받은 살마의 인덱스 번호
            sell[x][y]++;

            //선물지수 구하기
            giftIndex.put(giftArray[0],giftIndex.get(giftArray[0])+1); //선물 준 사람은 + 1
            giftIndex.put(giftArray[1],giftIndex.get(giftArray[1])-1); //선물 받은 사람은 -1
        }

        int answer = 0;
        //선물 개수 구하기
        for(int i=0; i<friends.length; i++) {
            int cnt = 0;
            for(int j=0; j<friends.length; j++) {
                if(j != i) { //나 자신이 아닐때
                    if(sell[i][j] == sell[j][i] || (sell[i][j]==0 && sell[j][i]==0)) { //주고 받은 선물의 개수가 동일 or 없다.
                        cnt += giftIndex.get(friends[i]) > giftIndex.get(friends[j]) ? 1 : 0; //선물지수가 클 경우에만 +1
                    } else {
                        cnt += sell[i][j] > sell[j][i] ? 1 : 0; //선물을 주고 받았을 경우 준 개수가 많을 때만 +1
                    }
                }
            }
            answer = Math.max(answer,cnt);
        }

        return answer;
    }

}