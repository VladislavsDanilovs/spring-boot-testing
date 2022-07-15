package com.luv2code.junitdemo;

import org.junit.jupiter.api.*;

import java.sql.SQLOutput;

import static org.junit.jupiter.api.Assertions.*;

class DemoUtilsTest {

    @BeforeAll
    static void setupBeforeEachClass(){
        System.out.println("@BeforeAll executes only once before all test methods");
    }

    @AfterAll
    static void setupAfterEachClass(){
        System.out.println("@AfterAll executes only once after all test methods");
    }
    DemoUtils demoUtils;


    @BeforeEach
    void setupBeforeEach() {
        demoUtils = new DemoUtils();
        System.out.println("@BeforeEach execute before the execution of each test method");
    }

    // Normaly after each use to clean up
    @AfterEach
    void tearDownAfterEach() {
        System.out.println("Running @After Each");
        System.out.println();
    }

    @Test
    void testEqualsAndNotEquals() {

        System.out.println("Running test: testEqualsAndNotEquals");

        assertEquals(6, demoUtils.add(2,4), "2+4 must be 6");
        assertNotEquals(6, demoUtils.add(1,9), "1+9 must not be 6");

    }

    @Test
    void testNullAndNotNull() {

        System.out.println("Running test: testNullAndNotNull");

        String str1 = null;
        String str2 = "test";

        assertNull(demoUtils.checkNull(str1), "Object should be null");
        assertNotNull(demoUtils.checkNull(str2), "Object should not be null");
    }
}
