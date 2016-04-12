package asgn1Tests;

import asgn1Election.CandidateIndex;
import asgn1Election.Vote;
import asgn1Election.VoteList;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

/**
 * Created by Gigness on 29/03/2016.
 */
public class VoteListTests {

    /** setup Variables */
    private VoteList a;
    private VoteList b;
    private int candidateNum = 7;

    /** setup */
    @Before
    public void voteListConstuctTest() {
        a = new VoteList(candidateNum);
        b = new VoteList(candidateNum + 1);

        for(int i = 1; i <= candidateNum; i++) {
            assertTrue(a.addPref(i));
        }
    }

    /** addPref tests */
    @Test
    public void addPrefOneTest() {
        assertTrue(b.addPref(7));
        assertEquals(b.toString(), "7 ");
    }

    @Test
    public void addPrefFullTest() {
        assertEquals(a.toString(), "1 2 3 4 5 6 7 ");
    }

    @Test
    public void addPrefOverFillTest() {
        assertFalse(a.addPref(8));
        assertEquals(a.toString(), "1 2 3 4 5 6 7 ");
        for(int i = 1; i <= candidateNum + 1; i++) {
            assertTrue(b.addPref(i));
        }
        assertEquals(b.toString(), "1 2 3 4 5 6 7 8 ");
    }

    @Test
    public void addPrefDuplicates() {
        for(int i = 0; i < candidateNum + 1; i++) {
            assertTrue(b.addPref(8));
        }
        assertFalse(b.addPref(90));
        assertEquals(b.toString(), "8 8 8 8 8 8 8 8 ");
    }

    @Test
    public void addPrefoutOfRangeCandidateTest() {
        assertFalse(b.addPref(16));
        assertFalse(b.addPref(0));
    }

    /** copy Test */
    @Test
    public void copyVoteTest() {
        Vote copyA = a.copyVote();
        assertEquals(a.toString(), copyA.toString());
    }

    @Test
    public void deepCopyVoteTest() {
        b.addPref(1);
        Vote copyB = b.copyVote();

        assertEquals(b.toString(), copyB.toString());

        copyB.addPref(5);
        b.addPref(8);

        assertNotEquals(b.toString(), copyB.toString());
        assertEquals(b.toString(), "1 8 ");
        assertEquals(copyB.toString(), "1 5 ");
    }


    /** getPref Tests */
    @Test
    public void preferredCandidateAvailableTest() {
        CandidateIndex firstPref = a.getPreference(1);
        assertEquals(firstPref.toString(), "1");
        CandidateIndex lastPref = a.getPreference(candidateNum);
        assertEquals(lastPref.toString(), String.valueOf(candidateNum));
    }

    @Test
    public void preferredCandidateMixedVoteTest() {
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

    // TODO is this okay?
    // Returns a CandidateIndex of 0 if preference is not found
    @Test
    public void preferredCandidateUnavailableTest() {
        CandidateIndex badPref = a.getPreference(candidateNum + 1);
        CandidateIndex badPref1 = a.getPreference(100);
        assertEquals(badPref.toString(), "0");
        assertEquals(badPref1.toString(), "0");
    }

    /** invertVote Tests */
    @Test
    public void invertVoteTest() {
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
    public void invertVoteTest2() {
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

    /** iterator Tests */
    @Test
    public void iteratorTest() {
        Iterator<Integer> aIterator = a.iterator();
        int pref = 1;
        while(aIterator.hasNext()) {
            Object aPref = aIterator.next();
            assertEquals(aPref, pref);
            pref++;
        }
    }

    /** toString Tests */
    @Test
    public void toStringTest() {
        assertEquals(a.toString(), "1 2 3 4 5 6 7 ");
    }
}