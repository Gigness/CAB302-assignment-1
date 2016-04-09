package asgn1Tests;

import asgn1Election.CandidateIndex;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Gigness on 28/03/2016.
 */
public class CandidateIndexTests {

    /** setup variables */
    private CandidateIndex index1;
    private CandidateIndex index2;
    private CandidateIndex index3;
    private CandidateIndex index4;

    /** setup */
    @Before
    public void setUp() {
        index1 = new CandidateIndex(1);
        index2 = new CandidateIndex(15);
        index3 = new CandidateIndex(8);
        index4 = new CandidateIndex(8);
    }

    /**
     * inRange Test
     */
    @Test
    public void inRangeTrueTest() {
        for(int i = 1; i <= 15; i++) {
            assertTrue(index1.inRange(i));
        }
    }

    @Test
    public void inRangeFalse() {
        assertFalse(index1.inRange(0));
        assertFalse(index1.inRange(16));
    }

    /** compareTo Tests */
    @Test
    public void compareToEqual() {
        assertEquals(index3.compareTo(index4), 0);
    }

    @Test
    public void compareToLessThan() {
        assertEquals(index1.compareTo(index2), -1);
    }

    @Test
    public void compareToGreaterThan() {
        assertEquals(index3.compareTo(index1), 1);
    }

    /** copy Tests */
    @Test public void deepCopyTest() {
        CandidateIndex indexCopy = index1.copy();
        assertEquals(index1.compareTo(indexCopy), 0);
        index1.setValue(10);
        assertEquals(indexCopy.toString(), "1"); // deep check
        assertEquals(index1.toString(), "10");
    }

    /** incrementIndex Tests */
    @Test
    public void incrementIndexTest() {
        CandidateIndex temp = new CandidateIndex(4);
        CandidateIndex temp1 = new CandidateIndex(11);
        index1.incrementIndex();
        index1.incrementIndex();
        index3.incrementIndex();
        index3.incrementIndex();
        index3.incrementIndex();
        index1.incrementIndex();
        assertEquals(temp.compareTo(index1), 0);
        assertEquals(temp1.compareTo(index3), 0);
    }
}