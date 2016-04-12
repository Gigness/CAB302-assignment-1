package asgn1Tests;

import asgn1Election.Candidate;
import asgn1Election.ElectionException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Gigness on 28/03/2016.
 */
public class CandidateTests {


    private Candidate testCandidate;
    private String name = "Paul Foo";
    private String party = "vapenation";
    private String abbrev = "VN";
    private int voteCount = 0;

    private Candidate candidate1;

    /** Set up */
    @Before
    public void setUp() throws ElectionException {
        testCandidate = new Candidate(name, party, abbrev, voteCount);
    }

    /** Constructor Exceptions Tests */
    @Test (expected = ElectionException.class)
    public void candidateNameNull() throws Exception {
        candidate1 = new Candidate(null, "YOLOSWAG", "YLO", 420);
    }

    @Test
    public void candidateNameOkay() throws Exception {
        candidate1 = new Candidate("Yolo", "YOLOSWAG", "YLO", 420);
    }

    @Test (expected = ElectionException.class)
    public void candidatePartyNull() throws Exception {
        candidate1 = new Candidate("Yolo", null, "YLO", 420);
    }

    @Test
    public void candidatePartyOkay() throws Exception {
        candidate1 = new Candidate("Yolo", "YOLOSWAG", "YLO", 420);
    }

    @Test (expected = ElectionException.class)
    public void candidateAbbrevNull() throws Exception {
        candidate1 = new Candidate("Yolo", "YOLOSWAG", null, 420);
    }

    @Test
    public void candidateAbbrevOkay() throws Exception {
        candidate1 = new Candidate("Yolo", "YOLOSWAG", "YLO", 420);
    }

    @Test (expected = ElectionException.class)
    public void candidateVoteNegative() throws Exception {
        candidate1 = new Candidate("Yolo", "YOLOSWAG", "YLO", -1);
    }

    @Test
    public void candidateVoteCountZero() throws ElectionException {
        candidate1 = new Candidate("yolo", "YOLOSWAG", "YLO", 0);
    }


    /** copy Test */
    @Test
    public void copy() throws Exception {
        Candidate candidate1 = new Candidate("a", "abc", "AAA", 0);
        Candidate copy1 = candidate1.copy();
        assertEquals(candidate1.getName(), copy1.getName());
        assertEquals(candidate1.getParty(), copy1.getParty());
        assertEquals(candidate1.getVoteCount(), copy1.getVoteCount());
        assertEquals(candidate1.getVoteCountString(), copy1.getVoteCountString());
    }

    @Test
    public void deepCopyTest() throws Exception {
        Candidate copy = testCandidate.copy();
        testCandidate.incrementVoteCount();
        assertFalse(copy.getVoteCount() == testCandidate.getVoteCount());
        assertEquals(copy.getVoteCount(), 0);
        assertEquals(testCandidate.getVoteCount(), 1);
    }

    /** Increment Vote */
    @Test
    public void incrementVoteCount() throws Exception {
       for(int i = 0; i < 10; i++) {
           testCandidate.incrementVoteCount();
       }
        assertEquals(testCandidate.getVoteCount(), 10);
    }
}