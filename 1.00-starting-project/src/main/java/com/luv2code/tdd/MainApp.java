package com.luv2code.tdd;

public class MainApp {
    public static void main(String[] args) {
        int counter = 0;
        while(counter <= 100){
            System.out.println(FizzBuzz.compute(counter));
            counter++;
        }
    }
}
