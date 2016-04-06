package asgn1Tests;

import asgn1Election.PrefElection;
import asgn1Election.Vote;
import asgn1Election.VoteList;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Gigness on 31/03/2016.
 */
public class PrefElectionTests {

    private PrefElection elecA;
    private PrefElection elecB;
    private int numCandidates = 3;

    @Before
    public void setUp() throws Exception {
        elecA = new PrefElection("MinMorgulVale");
        elecA.loadDefs();
        elecA.loadVotes();
    }

    /** findWinner */
    @Test
    public void findWinnerTest() {
        elecA.findWinner();
    }

    /** isFormal tests */
    @Test
    public void isFormalTest() throws Exception {
        Vote v = new VoteList(numCandidates);
        v.addPref(1);
        v.addPref(2);
        v.addPref(3);
        assertTrue(elecA.isFormal(v));
    }

    @Test
    public void isFormalMisisngCandidateTest() {
        Vote v = new VoteList(numCandidates);
        v.addPref(3);
        v.addPref(2);
        v.addPref(4);
        assertFalse(elecA.isFormal(v));
    }

    @Test
    public void isFormalDupeCandidateTest() {
        Vote v = new VoteList(numCandidates);
        v.addPref(1);
        v.addPref(1);
        v.addPref(2);
        assertFalse(elecA.isFormal(v));
    }

    /** Test */

}