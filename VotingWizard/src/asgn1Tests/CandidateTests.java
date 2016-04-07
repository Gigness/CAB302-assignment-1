package asgn1Tests;

import asgn1Election.Candidate;
import asgn1Election.ElectionException;
import asgn1Election.ElectionManager;
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

    /** Set up */
    @Before
    public void setUp() throws ElectionException {
        testCandidate = new Candidate(name, party, abbrev, voteCount);
    }

    /** Constructor Tests */
    @Test (expected = ElectionException.class)
    public void candidateNameNull() throws Exception {
        Candidate candidate1 = new Candidate(null, "YOLOSWAG", "YLO", 420);
    }

    @Test (expected = ElectionException.class)
    public void candidatePartyNull() throws Exception {
        Candidate candidate1 = new Candidate("Yolo", null, "YLO", 420);
    }

    @Test (expected = ElectionException.class)
    public void candidateAbbrevNull() throws Exception {
        Candidate candidate1 = new Candidate("Yolo", "YOLOSWAG", null, 420);
    }

    @Test (expected = ElectionException.class)
    public void candidateVoteNegative() throws Exception {
        Candidate candidate1 = new Candidate("Yolo", "YOLOSWAG", "YLO", -1);
    }

    /** Candidate Listing Test */
    @Test
    public void candidateListing() throws Exception {

        System.out.println(testCandidate.candidateListing());
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

        assertEquals(copy.getName(), testCandidate.getName());
        assertEquals(copy.getParty(), testCandidate.getParty());
        assertEquals(copy.getVoteCount(), testCandidate.getVoteCount());
        assertEquals(copy.getVoteCountString(), testCandidate.getVoteCountString());

        testCandidate.incrementVoteCount();

        assertFalse(copy.getVoteCount() == testCandidate.getVoteCount());
        assertEquals(copy.getVoteCount(), 0);
        assertEquals(testCandidate.getVoteCount(), 1);
    }

    @Test (expected = ElectionException.class)
    public void invalidCopy() throws Exception {
        Candidate candidate = new Candidate("a", null, "aaa", 0);
        Candidate copy1 = candidate.copy();
    }

    /** Getters Test */
    @Test
    public void getName() {
        assertEquals(testCandidate.getName(), name);
    }

    @Test
    public void getParty() {
        assertEquals(testCandidate.getParty(), party);
    }

    @Test
    public void getVoteCount() {
        assertEquals(testCandidate.getVoteCount(), voteCount);
    }

    @Test
    public void getVoteCountString() {
        assertEquals(testCandidate.getVoteCountString(), String.valueOf(voteCount));
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