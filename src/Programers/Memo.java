package Programers;

import java.util.*;

public class Memo {
    public static void main(String[] args) {

        System.out.println("프로그래머스");

        List<String> list = new ArrayList<>();
        HashMap<String, List<String>> map = new HashMap<>();

        map.put("홍길동", new ArrayList<>());
        map.put("김철수", new ArrayList<>());

        map.get("홍길동").add("홍길동1");
        map.get("김철수").add("김철수1");

        System.out.println("list : " + map.get("김철수"));


    }
}
