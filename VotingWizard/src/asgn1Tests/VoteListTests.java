/**
 *
 * This file is part of the testing suite for the VotingWizard Project
 * for CAB302, Semester 1, 2016
 * Junit tests for {@link asgn1Election.VoteList}
 *
 */
package asgn1Tests;

import asgn1Election.CandidateIndex;
import asgn1Election.Vote;
import asgn1Election.VoteList;
import org.junit.Before;
import org.junit.Test;
import java.util.Iterator;
import static org.junit.Assert.*;

/**
 *
 * Junit test class for VoteLists
 * @author Paul Foo
 * @version 1.0
 *
 */
public class VoteListTests {

    private VoteList a;
    private VoteList b;
    private int candidateNum = 7;

    @Before
    public void setUp() {
        a = new VoteList(candidateNum);
        b = new VoteList(candidateNum + 1);

        for(int i = 1; i <= candidateNum; i++) {
            assertTrue(a.addPref(i));
        }
    }

    /**
     * Test method for {@link asgn1Election.VoteList#addPref(int)}
     */
    @Test
    public void addPrefOne_test() {
        assertTrue(b.addPref(7));
        assertEquals(b.toString(), "7 ");
    }

    @Test
    public void addPrefFull_test() {
        assertEquals(a.toString(), "1 2 3 4 5 6 7 ");
    }

    @Test
    public void addPrefOverFill_test() {
        assertFalse(a.addPref(8));
        assertEquals(a.toString(), "1 2 3 4 5 6 7 ");
    }

    @Test
    public void addPrefDuplicates_test() {
        for(int i = 0; i < candidateNum + 1; i++) {
            assertTrue(b.addPref(8));
        }
        assertEquals(b.toString(), "8 8 8 8 8 8 8 8 ");
    }

    @Test
    public void addPrefoutOfRangeCandidate_test() {
        assertFalse(b.addPref(16));
        assertFalse(b.addPref(0));
        assertFalse(b.addPref(-1));
        assertFalse(b.addPref(20));
    }

    /**
     * Test method for {@link asgn1Election.VoteList#copyVote()}
     */
    @Test
    public void copyVote_test() {
        Vote copyA = a.copyVote();
        assertEquals(a.toString(), copyA.toString());
    }

    @Test
    public void deepCopyVote_test() {
        b.addPref(1);
        Vote copyB = b.copyVote();

        assertEquals(b.toString(), copyB.toString());

        copyB.addPref(5);
        b.addPref(8);

        assertNotEquals(b.toString(), copyB.toString());
        assertEquals(b.toString(), "1 8 ");
        assertEquals(copyB.toString(), "1 5 ");
    }


    /**
     * Test method for {@link asgn1Election.VoteList#getPreference(int)}
     */
    @Test
    public void getPreference_test() {
        CandidateIndex firstPref = a.getPreference(1);
        assertEquals(firstPref.toString(), "1");
        CandidateIndex lastPref = a.getPreference(candidateNum);
        assertEquals(lastPref.toString(), String.valueOf(candidateNum));
    }

    @Test
    public void getPreference_MixedVote_test() {
        // b = 8 1 3 5 6 3 2 7
        b.addPref(8);
        b.addPref(1);
        b.addPref(3);
        b.addPref(5);
        b.addPref(6);
        b.addPref(4);
        b.addPref(2);
        b.addPref(7);

        CandidateIndex firstPref = b.getPreference(1);
        CandidateIndex fourthPref = b.getPreference(4);
        assertEquals(firstPref.toString(), "2");
        assertEquals(fourthPref.toString(), "6");
    }


    @Test
    public void getPreference_DoesNotExist_test() {
        CandidateIndex badPref = a.getPreference(candidateNum + 1);
        CandidateIndex badPref1 = a.getPreference(100);
        assertNull(badPref);
        assertNull(badPref1);
    }

    /**
     * Test method for {@link asgn1Election.VoteList#invertVote()}
     */
    @Test
    public void invertVote_test() {
        b.addPref(2);
        b.addPref(6);
        b.addPref(5);
        b.addPref(3);
        b.addPref(4);
        b.addPref(7);
        b.addPref(1);
        b.addPref(8);
        Vote invertedVotesB = b.invertVote();
        assertEquals(invertedVotesB.toString(), "7 1 4 5 3 2 6 8 ");
    }

    @Test
    public void invertVote_test2() {
        b.addPref(3);
        b.addPref(2);
        b.addPref(4);
        b.addPref(5);
        b.addPref(6);
        b.addPref(7);
        b.addPref(1);
        b.addPref(8);
        Vote invertedVotesB = b.invertVote();
        assertEquals(invertedVotesB.toString(), "7 2 1 3 4 5 6 8 ");
    }

    /**
     * Test method for {@link asgn1Election.VoteList#iterator()}
     */
    @Test
    public void iterator_test() {
        Iterator<Integer> aIterator = a.iterator();
        int pref = 1;
        while(aIterator.hasNext()) {
            Object aPref = aIterator.next();
            assertEquals(aPref, pref);
            pref++;
        }
    }

    @Test
    public void iteratorNotNull_test() {
        Iterator<Integer> aIterator = a.iterator();
        assertNotNull(aIterator);
    }

    /**
     * Test method for {@link asgn1Election.VoteList#toString()}
     */
    @Test
    public void toStringTest() {
        assertEquals(a.toString(), "1 2 3 4 5 6 7 ");
    }
}