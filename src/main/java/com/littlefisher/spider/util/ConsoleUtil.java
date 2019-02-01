package com.littlefisher.spider.util;

import java.io.BufferedOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class ConsoleUtil {
    private static PrintWriter out=new PrintWriter(new BufferedOutputStream(System.out),true);
    private static Scanner s=new Scanner(System.in);
    public static void anykeytocontinue(){
        out.println("[!]Press enter to continue, if doesn't work, one more please.");
        s.nextLine();
        s.nextLine();
    }
    public static String getNextLineNotEmpty(){
        while (true) {
            String str = s.nextLine().trim();
            if (!str.isEmpty()) return str;
        }

    }
    public static int yesOrNo(String question,boolean allowInvalidInput){
        //yes 1 no 0 error -1
        out.println(question + "(y/n)");
        while(true) {
            String str = getNextLineNotEmpty().toLowerCase();
            if (str.equals("y") || str.equals("yes")) {
                return 1;
            } else if (str.equals("n") || str.equals("no")) {
                return 0;
            } else if (!allowInvalidInput) {
                out.println("Invalid Input! Please enter 'y' or 'n'.");
            } else return -1;
        }
    }
}
