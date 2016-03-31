package asgn1Tests;

import asgn1Election.Candidate;
import asgn1Election.CandidateIndex;
import asgn1Election.ElectionException;
import asgn1Election.SimpleElection;
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

    @Before
    public void setUp() throws Exception {
        elecA = new SimpleElection("MinMorgulVale");
        elecA.loadDefs();
        elecA.loadVotes();
    }

    @Test(expected = IOException.class)
    public void setUpFileNotFound() throws NumbersException, IOException, ElectionException {
        elecB = new SimpleElection("Swag");
        elecB.loadDefs();
    }

    @Test
    public void testElec() {
        elecA.testSimpleElection();
    }

    @Test
    @Ignore
    public void findWinner() throws Exception {

    }

    @Test
    @Ignore
    public void isFormal() throws Exception {

    }

    @Test
    @Ignore
    public void clearWinner() throws Exception {

    }
}