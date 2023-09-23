package com.example;

import java.util.Scanner;

public class Consol2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        while (true) {
            System.out.println("waiting");
            String str = sc.nextLine(); // String ????
            System.out.println(str);
            if (str.equalsIgnoreCase("close")) {
                break;
            }
        }
    }
}
