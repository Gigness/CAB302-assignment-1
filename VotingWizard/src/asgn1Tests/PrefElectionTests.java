package asgn1Tests;

import asgn1Election.*;
import asgn1Util.NumbersException;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

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

    /** loadDefs */
    @Test(expected = ElectionException.class)
    public void notEnoughCandidates_ExceptionTest() throws NumbersException, IOException, ElectionException {
        PrefElection test = new PrefElection("loadDefs");
        test.loadDefs();
    }

    @Test(expected = ElectionException.class)
    public void incorrectHeader_ExceptionTest() throws NumbersException, IOException, ElectionException {
        PrefElection test = new PrefElection("loadDefs1");
        test.loadDefs();
    }

    @Test
    public void loadDefs_MinMorguleVale() throws ElectionException {
        java.util.Collection<Candidate> candidates = elecA.getCandidates();
        ArrayList<Candidate> expectedCandidates= new ArrayList<>();
        int counter = 0;

        Candidate shelob = new Candidate("Shelob", "Monster Spider Party", "MSP", 0 );
        Candidate gorbag = new Candidate("Gorbag", "Filthy Orc Party", "FOP", 0 );
        Candidate shagrat = new Candidate("Shagrat", "Stinking Orr Party", "SOP", 0 );

        expectedCandidates.add(shelob);
        expectedCandidates.add(gorbag);
        expectedCandidates.add(shagrat);


        for(Candidate c: candidates) {
            assertEquals(c.toString(), expectedCandidates.get(counter).toString());
            counter++;
        }
    }

    /** loadVotes */
    @Test (expected = ElectionException.class)
    public void incorrectVoteFormat_ExceptionTest() throws NumbersException, IOException, ElectionException {
        PrefElection test = new PrefElection("loadVotes");
        test.loadDefs();
        test.loadVotes();
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