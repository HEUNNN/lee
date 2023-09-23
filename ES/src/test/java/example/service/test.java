package example.service;

import java.util.ArrayList;
import java.util.List;

public class test {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("apple");
        list.add("pizza");
//        list.add("");

        if (list.contains("")) {
            System.out.println("i am here");
        } else {
            System.out.println("i am not here");
        }
    }
}
