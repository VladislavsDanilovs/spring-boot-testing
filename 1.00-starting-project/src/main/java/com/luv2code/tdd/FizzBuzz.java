package com.luv2code.tdd;

public class FizzBuzz {

    // If number is divisible by 3, print Fizz
    // If number is divisible by 5, print Buzz
    // If number divisible by 3 nad 5, print FizzBuzz
    // If number is NOT divisible by 3 or 5, then print the number

//    public static String compute(int i) {
//        String result = "";
//        result = (i % 3 == 0 && i % 5 == 0) ? "FizzBuzz" : (i % 3 != 0 && i % 5 != 0) ? Integer.toString(i)
//                : (i % 3 == 0) ? "Fizz" : (i % 5 == 0) ? "Buzz" : result;
//
//        return result;
//    }

    /* Testing another approach */
    public static String compute(int i) {

        StringBuilder result = new StringBuilder();
        if (i == 0){
            return result.toString();
        }
        if (i % 3 == 0) {
            result.append("Fizz");
        }

        if (i % 5 == 0) {
            result.append("Buzz");
        }

        if (result.isEmpty()){
            result.append(i);
        }

        return result.toString();
    }
}
