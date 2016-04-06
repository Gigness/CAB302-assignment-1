package asgn1Tests;

import asgn1Election.*;
import asgn1Util.NumbersException;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

import static org.junit.Assert.*;

/**
 * Created by Gigness on 30/03/2016.
 */
public class SimpleElectionTests {

    private SimpleElection elecA;
    private SimpleElection elecB;
    private int numCandidates = 5;

    @Before
    public void setUp() throws Exception {
        elecA = new SimpleElection("MinMorgulVale");
        elecA.loadDefs();
        elecA.loadVotes();

        elecB = new SimpleElection("Test1");
        elecB.loadDefs();
        elecB.loadVotes();

    }

    @Test(expected = IOException.class)
    public void setUpFileNotFound() throws NumbersException, IOException, ElectionException {
        SimpleElection elecC = new SimpleElection("Swag");
        elecC.loadDefs();
    }


    @Test
    public void findWinner() throws Exception {
        elecA.findWinner();
    }

    @Test
    public void isFormalValidTest1() throws Exception {
        Vote v = new VoteList(numCandidates);
        v.addPref(1);
        v.addPref(2);
        v.addPref(3);
        v.addPref(4);
        v.addPref(5);

        Vote v1 = new VoteList (numCandidates);
        v1.addPref(1);
        v1.addPref(5);

        assertTrue(elecB.isFormal(v));
        assertTrue(elecB.isFormal(v1));
    }

    @Test
    public void isFormalValidTest2() throws Exception {
        Vote v = new VoteList(numCandidates);
        v.addPref(1);
        v.addPref(2);
        v.addPref(3);
        v.addPref(3);
        v.addPref(4);


        assertTrue(elecB.isFormal(v));
    }

    @Test
    public void isFormalInvalidTest1() throws Exception {
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

        assertFalse(elecB.isFormal(v));
        assertFalse(elecB.isFormal(v1));
    }

    @Test
    @Ignore
    public void isFormalInvalidTest2() throws Exception {
        Vote v = new VoteList(numCandidates);
        v.addPref(1);
        v.addPref(2);
        v.addPref(3);
        v.addPref(4);
        v.addPref(6);
        assertFalse(elecB.isFormal(v));
    }
}