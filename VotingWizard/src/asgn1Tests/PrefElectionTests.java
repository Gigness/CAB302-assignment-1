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
    private PrefElection elecC;
    private int numCandidates = 3;

    @Before
    public void setUp() throws Exception {
        elecA = new PrefElection("MinMorgulVale");
        elecA.loadDefs();
        elecA.loadVotes();

        elecC = new PrefElection("MinMorgulValeTie");
        elecC.loadDefs();
        elecC.loadVotes();
    }

    /** exceptions */
    public void election_NumbersExceptionTest() {
        PrefElection test = new PrefElection("");
    }

    /** findWinner */
//    @Test
//    public void findWinner_absoluteMajorityTest() {
//        elecA.findWinner();
//    }

//    @Test
//    public void findWinner_eliminationTest() {
//        elecA.findWinner();
//    }

//    @Test
//    public void findWinner_tieTest() {
//        elecA.findWinner();
//    }

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
        v.addPref(1);
        v.addPref(2);
        assertFalse(elecA.isFormal(v));
    }

    @Test
    public void isFormal_OutOfRangeTest() {
        Vote v = new VoteList(numCandidates);
        v.addPref(1);
        v.addPref(2);
        v.addPref(4);
        assertFalse(elecA.isFormal(v));
    }

    @Test
    public void isFormal_DupeTest() {
        Vote v = new VoteList(numCandidates);
        v.addPref(1);
        v.addPref(2);
        v.addPref(2);
        assertFalse(elecA.isFormal(v));
    }
    @Test
    public void isFormalDupeTest1() {
        Vote v = new VoteList(numCandidates);
        v.addPref(1);
        v.addPref(1);
        v.addPref(2);
        assertFalse(elecA.isFormal(v));
    }


}