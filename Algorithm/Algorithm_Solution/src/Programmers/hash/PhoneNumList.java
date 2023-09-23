package Programmers.hash;

import java.util.HashMap;

public class PhoneNumList {
    public boolean isPrefix1(String[] phone_book) {
        HashMap<String, Integer> phoneNumMap = new HashMap<>();
        for(int i = 0; i < phone_book.length; i++) {
            phoneNumMap.put(phone_book[i], i);
        }

        for (int i = 0; i < phone_book.length; i++) {
            String target = phone_book[i];
            for (int j = 0; j < target.length(); j++) {
                String check = phone_book[i].substring(0, j);
                if (phoneNumMap.containsKey(check)) {
                    return false;
                }
            }
        }

        return true;
    }
}
