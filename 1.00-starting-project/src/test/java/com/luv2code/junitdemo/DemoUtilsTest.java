package com.luv2code.junitdemo;

import org.junit.jupiter.api.*;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//@DisplayNameGeneration(DisplayNameGenerator.Simple.class)
//@TestMethodOrder(MethodOrderer.DisplayName.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DemoUtilsTest {

    DemoUtils demoUtils;

    @BeforeAll
    static void setupBeforeEachClass(){
    }

    @BeforeEach
    void setupBeforeEach() {
        demoUtils = new DemoUtils();
    }

    @DisplayName("Multiply")
    @Test
    void testMultiply(){
        assertEquals(12, demoUtils.multiply(4,3), "4 multiple 3 should be equals 12");
    }

    @Test
    @DisplayName("Equals and Not Equals")
    @Order(1)
    void testEqualsAndNotEquals() {

        assertEquals(6, demoUtils.add(2,4), "2+4 must be 6");
        assertNotEquals(6, demoUtils.add(1,9), "1+9 must not be 6");
    }

    @Test
    @DisplayName("Null and Not null")
    @Order(0)
    void testNullAndNotNull() {

        String str1 = null;
        String str2 = "test";

        assertNull(demoUtils.checkNull(str1), "Object should be null");
        assertNotNull(demoUtils.checkNull(str2), "Object should not be null");
    }

    @DisplayName("Same and Not Same")
    @Test
    void testSameAndNotSame() {

        String str = "random text";

        assertSame(demoUtils.getAcademy(), demoUtils.getAcademyDuplicate(), "Object should refer to the same object");
        assertNotSame(str, demoUtils.getAcademy(), "Object should not refer to the same object");
    }

    @DisplayName("True and False")
    @Test
    @Order(30)
    void testTrueFalse(){
        int gradeOne = 10;
        int gradeTwo = 5;

        assertTrue(demoUtils.isGreater(gradeOne, gradeTwo), "This should return true");
        assertFalse(demoUtils.isGreater(gradeTwo, gradeOne), "This should return false");
    }

    @DisplayName("Array Equals")
    @Test
    void testArrayEquals() {
        String[] stringArray = {"A", "B", "C"};

        assertArrayEquals(stringArray, demoUtils.getFirstThreeLettersOfAlphabet(), "Arrays should be the same");

    }

    @DisplayName("Iterable equals")
    @Test
    void testIterableEquals() {
        List<String> list1 = List.of("love", "2", "code");

        assertIterableEquals(list1, demoUtils.getAcademyInList(), "Expected list should be same as actual list");
    }

    @DisplayName("Lines match")
    @Test
    @Order(50)
    void testLinesMatch(){
        List<String> list1 = List.of("love", "2", "code");

        assertLinesMatch(list1, demoUtils.getAcademyInList(), "Lines should match");
    }

    @DisplayName("Throws and Does Not Throw")
    @Test
    void testThrowsAndDoesNotThrow(){
        assertThrows(Exception.class, () -> {demoUtils.throwException(-5); }, "Should throw exception");

        assertDoesNotThrow(() -> {demoUtils.throwException(5); }, "Should not throw exception");
    }

    @DisplayName("Timeout")
    @Test
    void testTimeout(){
        assertTimeoutPreemptively(Duration.ofSeconds(3), () -> { demoUtils.checkTimeout(); }, "Method should execute in 3 seconds");
    }

//    @BeforeEach
//    void setupBeforeEach() {
//        demoUtils = new DemoUtils();
//        System.out.println("@BeforeEach execute before the execution of each test method");
//    }
//
//    // Normaly after each use to clean up
//    @AfterEach
//    void tearDownAfterEach() {
//        System.out.println("Running @After Each");
//        System.out.println();
//    }
//
//    @AfterAll
//    static void setupAfterEachClass(){
//        System.out.println("@AfterAll executes only once after all test methods");
//    }
}
