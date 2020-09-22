package Lab1;

import org.junit.Test;
import static org.junit.Assert.*;

/** Tests for the Year class.
 */
public class TestYear {

    @Test
    public void test400() {
        String msg = "should be leap year";
        assertTrue(msg, Year.isLeapYear(2000));
        assertTrue(msg, Year.isLeapYear(2400));
    }

    @Test
    public void testLeapYearNotDivisible100() {
        String msg = "should be leap year";
        assertTrue(msg, Year.isLeapYear(2004));
        assertTrue(msg, Year.isLeapYear(2008));
    }

    @Test
    public void testDivisible400And100() {
        String msg = "should not be leap year";
        assertFalse(msg, Year.isLeapYear(1900));
        assertFalse(msg, Year.isLeapYear(2100));
        assertFalse(msg, Year.isLeapYear(2300));
    }

    @Test
    public void testDivisibleNot4() {
        String msg = "should not be leap year";
        assertFalse(msg, Year.isLeapYear(1901));
        assertFalse(msg, Year.isLeapYear(2003));
    }

    public static void main(String[] args) {
        TestYear year = new TestYear();
        year.test400(); 
        year.testLeapYearNotDivisible100();
        year.testDivisible400And100();
        year.testDivisibleNot4();
        
    }

}
