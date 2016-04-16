package asgn1Tests;

import asgn1Election.CandidateIndex;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * Junit test class for CandidateIndex
 * @author Paul Foo
 * @version 1.0
 *
 */
public class CandidateIndexTests {

    private CandidateIndex index1;
    private CandidateIndex index2;
    private CandidateIndex index3;
    private CandidateIndex index4;

    @Before
    public void setUp() {
        index1 = new CandidateIndex(1);
        index2 = new CandidateIndex(15);
        index3 = new CandidateIndex(8);
        index4 = new CandidateIndex(8);
    }

    /**
     * Test method for {@link asgn1Election.CandidateIndex#inRange(int)}
     */
    @Test
    public void inRangeTrue_test() {
        for(int i = 1; i <= 15; i++) {
            assertTrue(CandidateIndex.inRange(i));
        }
    }

    @Test
    public void inRangeFalse_test() {
        assertFalse(CandidateIndex.inRange(0));
        assertFalse(CandidateIndex.inRange(16));
    }

    /**
     * Test method for {@link asgn1Election.CandidateIndex#compareTo(CandidateIndex)}
     */
    @Test
    public void compareToEqual_test() {
        assertEquals(index3.compareTo(index4), 0);
    }

    @Test
    public void compareToLessThan_test() {
        assertEquals(index1.compareTo(index2), -1);
    }

    @Test
    public void compareToGreaterThan_test() {
        assertEquals(index3.compareTo(index1), 1);
    }

    /**
     * Test method for {@link asgn1Election.CandidateIndex#copy()}
     */
    @Test public void deepCopyTest_test() {
        CandidateIndex indexCopy = index1.copy();
        assertEquals(index1.compareTo(indexCopy), 0);
        index1.setValue(10);
        assertEquals(indexCopy.toString(), "1"); // deep check
        assertEquals(index1.toString(), "10");
    }

    /**
     * Test method for {@link asgn1Election.CandidateIndex#incrementIndex()}
     */
    @Test
    public void incrementIndex_test() {
        CandidateIndex temp = new CandidateIndex(4);
        temp.incrementIndex();
        temp.incrementIndex();
        assertEquals(temp.toString(), "6");
    }
}