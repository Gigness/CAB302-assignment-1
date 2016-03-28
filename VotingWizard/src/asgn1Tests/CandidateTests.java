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

    /* Set up */
    @Before
    public void setUp() throws ElectionException {
        testCandidate = new Candidate(name, party, abbrev, voteCount);
    }

    /* Constructor Tests */
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
    /* End */


//    @Test
//    public void candidateListing() throws Exception {
//
//    }

    /* Copy Tests */
    @Test
    public void copy() throws Exception {
        Candidate candidate1 = new Candidate("a", "abc", "AAA", 0);
        Candidate copy1 = candidate1.copy();
        assertEquals(candidate1.getName(), copy1.getName());
        assertEquals(candidate1.getParty(), copy1.getParty());
        assertEquals(candidate1.getVoteCount(), copy1.getVoteCount());
        assertEquals(candidate1.getVoteCountString(), copy1.getVoteCountString());
    }

    @Test (expected = ElectionException.class)
    public void invalidCopy() throws Exception {
        Candidate candidate = new Candidate("a", null, "aaa", 0);
        Candidate copy1 = candidate.copy();
    }
    /* End */

    /* Getters */
    @Test
    public void getName() throws Exception {
        assertEquals(testCandidate.getName(), name);
    }

    @Test
    public void getParty() throws Exception {
        assertEquals(testCandidate.getParty(), party);
    }

    @Test
    public void getVoteCount() throws Exception {
        assertEquals(testCandidate.getVoteCount(), voteCount);
    }

    @Test
    public void getVoteCountString() throws Exception {
        assertEquals(testCandidate.getVoteCountString(), String.valueOf(voteCount));
    }
    /* End */

    /* Increment Vote */
    @Test
    public void incrementVoteCount() throws Exception {
       for(int i = 0; i < 10; i++) {
           testCandidate.incrementVoteCount();
       }
        assertEquals(testCandidate.getVoteCount(), 10);
    }
    /* End */
//
//    @Test
//    public void toString() throws Exception {
//
//    }
}