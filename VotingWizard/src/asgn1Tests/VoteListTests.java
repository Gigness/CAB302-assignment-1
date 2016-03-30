package asgn1Tests;

import asgn1Election.CandidateIndex;
import asgn1Election.Vote;
import asgn1Election.VoteList;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by Gigness on 29/03/2016.
 */
public class VoteListTests {

    private VoteList a;
    private VoteList b;
    private int candidateNum = 7;

    @Before
    public void voteListConstuct() {
        a = new VoteList(candidateNum);
        b = new VoteList(candidateNum + 1);

        for(int i = 1; i <= candidateNum; i++) {
            assertTrue(a.addPref(i));
        }
    }

    /** addPref tests */
    @Test
    public void addPrefOne() {
        assertTrue(b.addPref(7));
        assertEquals(b.toString(), "7 ");
    }

    @Test
    public void addPrefFull() {
        assertEquals(a.toString(), "1 2 3 4 5 6 7 ");
    }

    @Test
    public void addPrefOverFill() {
        assertFalse(a.addPref(8));
        assertEquals(a.toString(), "1 2 3 4 5 6 7 ");
    }

    /** copy Test */
    @Test
    public void copyVoteTest() {
        Vote copyA = a.copyVote();
        assertEquals(a.toString(), copyA.toString());
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
        b.addPref(8);
        b.addPref(1);
        b.addPref(3);
        b.addPref(5);
        b.addPref(6);
        b.addPref(4);
        b.addPref(2);
        b.addPref(7);

        CandidateIndex firstPref = b.getPreference(1);
        assertEquals(firstPref.toString(), "2");

    }

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
        System.out.println(invertedVotesB.toString());
        assertEquals(invertedVotesB.toString(), "7 2 1 3 4 5 6 8 ");
    }

    @Test
    public void invertVoteTestDuplicate() {
        b.addPref(1);
        b.addPref(2);
        b.addPref(3);
        b.addPref(8);
        b.addPref(8);
        b.addPref(7);
        b.addPref(6);
        b.addPref(5);
        Vote invertedVotesB = b.invertVote();
        System.out.println(invertedVotesB.toString());
    }

    /** iterator Tests */
    public void iteratorTest() {

    }

    /** toString Tests */
    @Test
    public void toStringTest() {
        assertEquals(a.toString(), "1 2 3 4 5 6 7 ");
    }



}