package asgn1Tests;

import asgn1Election.Candidate;
import asgn1Election.ElectionException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Junit tests for Candidate class
 * @author Paul Foo
 * @version 1.0
 */
public class CandidateTests {

    private Candidate testCandidate;
    private String name = "Paul Foo";
    private String party = "vapenation";
    private String abbrev = "VN";
    private int voteCount = 0;
    private Candidate candidate1;

    @Before
    public void setUp() throws ElectionException {
        testCandidate = new Candidate(name, party, abbrev, voteCount);
    }

    /**
     * Test method for {@link asgn1Election.Candidate#Candidate(String, String, String, int)}
     */
    @Test (expected = ElectionException.class)
    public void candidateNameNull_test() throws Exception {
        candidate1 = new Candidate(null, "YOLOSWAG", "YLO", 420);
    }

    @Test
    public void candidateName_test() throws Exception {
        candidate1 = new Candidate("Yolo", "YOLOSWAG", "YLO", 420);
    }

    @Test (expected = ElectionException.class)
    public void candidatePartyNull_test() throws Exception {
        candidate1 = new Candidate("Yolo", null, "YLO", 420);
    }

    @Test
    public void candidateParty_test() throws Exception {
        candidate1 = new Candidate("Yolo", "YOLOSWAG", "YLO", 420);
    }

    @Test (expected = ElectionException.class)
    public void candidateAbbrevNull_test() throws Exception {
        candidate1 = new Candidate("Yolo", "YOLOSWAG", null, 420);
    }

    @Test
    public void candidateAbbrev_test() throws Exception {
        candidate1 = new Candidate("Yolo", "YOLOSWAG", "YLO", 420);
    }

    @Test (expected = ElectionException.class)
    public void candidateVoteNegative_ElectionException_test() throws Exception {
        candidate1 = new Candidate("Yolo", "YOLOSWAG", "YLO", -1);
    }

    @Test
    public void candidateVoteCountZero_ElectionException_test() throws ElectionException {
        candidate1 = new Candidate("yolo", "YOLOSWAG", "YLO", 0);
    }

    /**
     * Test method for {@link asgn1Election.Candidate#copy()}
     */
    @Test
    public void copy_test() throws Exception {
        Candidate candidate1 = new Candidate("a", "abc", "AAA", 0);
        Candidate copy1 = candidate1.copy();
        assertEquals(candidate1.getName(), copy1.getName());
        assertEquals(candidate1.getParty(), copy1.getParty());
        assertEquals(candidate1.getVoteCount(), copy1.getVoteCount());
        assertEquals(candidate1.getVoteCountString(), copy1.getVoteCountString());
    }

    @Test
    public void deepCopy_test() throws Exception {
        Candidate copy = testCandidate.copy();
        testCandidate.incrementVoteCount();
        assertFalse(copy.getVoteCount() == testCandidate.getVoteCount());
        assertEquals(copy.getVoteCount(), 0);
        assertEquals(testCandidate.getVoteCount(), 1);
    }

    /**
     * Test method for {@link asgn1Election.Candidate#incrementVoteCount()}
     */
    @Test
    public void incrementVoteCount_test() throws Exception {
       for(int i = 0; i < 10; i++) {
           testCandidate.incrementVoteCount();
       }
        assertEquals(testCandidate.getVoteCount(), 10);
    }
}