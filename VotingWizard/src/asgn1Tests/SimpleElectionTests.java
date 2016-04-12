package asgn1Tests;

import asgn1Election.*;
import asgn1Util.NumbersException;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Created by Gigness on 30/03/2016.
 */
public class SimpleElectionTests {

    /** setup variables */
    private SimpleElection elecA;
    private SimpleElection elecB;
    private SimpleElection elecC;
    private SimpleElection elecTie;
    private SimpleElection elecTie1;
    private int numCandidates = 5;

    /** setup */
    @Before
    public void setUp() throws Exception {
        elecA = new SimpleElection("MinMorgulVale");
        elecA.loadDefs();
        elecA.loadVotes();

        elecB = new SimpleElection("SimpleElec_Tie");
        elecB.loadDefs();
        elecB.loadVotes();

        elecC = new SimpleElection("SimpleElec_easy_majority");
        elecC.loadDefs();
        elecC.loadVotes();

        elecTie = new SimpleElection("TestTie");
        elecTie.loadDefs();
        elecTie.loadVotes();

        elecTie1 = new SimpleElection("TestTie1");
        elecTie1.loadDefs();
        elecTie1.loadVotes();
    }

    /** constructor Test */
    @Test(expected = IOException.class)
    public void setUpFileNotFound() throws NumbersException, IOException, ElectionException {
        SimpleElection elecC = new SimpleElection("Swag");
        elecC.loadDefs();
    }

    /** isFormal Tests */
    @Test
    public void isFormalValidTest1() throws Exception {
        Vote v = new VoteList(numCandidates);
        v.addPref(1);
        v.addPref(2);
        v.addPref(3);
        v.addPref(4);
        v.addPref(5);
        assertTrue(elecB.isFormal(v));
    }

    @Test
    public void isFormalValidTest2() throws Exception {
        Vote v = new VoteList(numCandidates);
        v.addPref(1);
        v.addPref(3);
        v.addPref(3);
        v.addPref(3);
        v.addPref(3);
        assertTrue(elecB.isFormal(v));
    }

    @Test
    public void isFormalInvalid_tooManyFirstPrefsTest() throws Exception {
        Vote v = new VoteList(numCandidates);
        v.addPref(1);
        v.addPref(1);
        v.addPref(3);
        v.addPref(4);
        v.addPref(5);

        Vote v1 = new VoteList (numCandidates);
        v1.addPref(1);
        v1.addPref(5);
        v1.addPref(1);
        v1.addPref(2);
        v1.addPref(2);

        assertFalse(elecB.isFormal(v));
        assertFalse(elecB.isFormal(v1));
    }

    @Test
    public void isFormalInvalid_candOutOfRangeTest() {
        Vote v = new VoteList(numCandidates);
        v.addPref(1);
        v.addPref(2);
        v.addPref(3);
        v.addPref(4);
        v.addPref(6);
        assertFalse(elecB.isFormal(v));
    }

    @Test
    public void isFormalInvalid_notEnoughCandidatesTest() {
        Vote v1 = new VoteList (numCandidates);
        v1.addPref(1);
        v1.addPref(5);
        assertFalse(elecB.isFormal(v1));
    }



    @Test
    public void findWinner_majority_win_test() {
        String statement = elecC.findWinner();
        String actual_statement = "\nCandidate YoloSwag" +
                " (YoloSwag Party) is the winner with 6 votes...\n";
        assertEquals(statement, actual_statement);
    }

    @Test
    public void findWinner_Test() {
        String statement = elecB.findWinner();
        String actual_statement = "\nCandidate VapeNation" +
                " (Vapenayshun) is the winner with 5 votes...\n";
        assertEquals(statement, actual_statement);
    }


    @Test
    public void findWinner_Tie() throws Exception {
        String statement = elecTie.findWinner();
        String actual_statement = "\nCandidate VapeNation" +
                " (Vapenayshun) is the winner with 2 votes...\n";
        assertEquals(statement, actual_statement);
    }

    @Test
    public void findWinner_Tie1() {
        String statement = elecTie1.findWinner();
        String actual_statement = "\nCandidate SwagKings" +
                " (SwagKings) is the winner with 3 votes...\n";
        assertEquals(statement, actual_statement);
    }
}