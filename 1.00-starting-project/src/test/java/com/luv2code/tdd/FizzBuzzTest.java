package com.luv2code.tdd;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class FizzBuzzTest {

    // If number is divisible by 3, print Fizz
    // If number is divisible by 5, print Buzz
    // If number divisible by 3 nad 5, print FizzBuzz
    // If number is NOT divisible by 3 or 5, then print the number

    @DisplayName("Divisible by Three")
    @Test
    @Order(1)
    void testForDivisibleByThree(){
        String expected = "Fizz";


        assertEquals(expected, FizzBuzz.compute(3), "Should return 'Fizz'");
    }

    @DisplayName("Divisible by Five")
    @Test
    @Order(2)
    void testForDivisibleByFive(){
        String expected = "Buzz";


        assertEquals(expected, FizzBuzz.compute(5), "Should return 'Buzz'");
    }

    @DisplayName("Divisible by Three And Five")
    @Test
    @Order(3)
    void testForDivisibleByThreeAndFive(){
        String expected = "FizzBuzz";


        assertEquals(expected, FizzBuzz.compute(30), "Should return 'FizzBuzz'");
    }

    @DisplayName("Not Divisible by Three And Five")
    @Test
    @Order(4)
    void testForNotDivisibleByThreeAndFive(){
        String expected = Integer.toString(7);


        assertEquals(expected, FizzBuzz.compute(7), "Should return number");
    }

    @DisplayName("Testing with small data file")
    @ParameterizedTest(name="value={0}, expected={1}")
    @CsvFileSource(resources="/small-test-data.csv")
    @Order(5)
    void testSmallDataFile(int value, String expected){

        assertEquals(expected, FizzBuzz.compute(value));

    }

    @DisplayName("Testing with medium data file")
    @ParameterizedTest(name="value={0}, expected={1}")
    @CsvFileSource(resources="/medium-test-data.csv")
    @Order(6)
    void testMediumDataFile(int value, String expected){

        assertEquals(expected, FizzBuzz.compute(value));

    }

    @DisplayName("Testing with large data file")
    @ParameterizedTest(name="value={0}, expected={1}")
    @CsvFileSource(resources="/large-test-data.csv")
    @Order(7)
    void testLargeDataFile(int value, String expected){

        assertEquals(expected, FizzBuzz.compute(value));

    }
}
