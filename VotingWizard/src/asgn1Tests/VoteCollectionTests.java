/**
 *
 * This file is part of the testing suite for the VotingWizard Project
 * for CAB302, Semester 1, 2016
 * Junit tests for {@link asgn1Election.VoteCollection}
 *
 */
package asgn1Tests;

import asgn1Election.*;
import org.junit.Before;
import org.junit.Test;

import java.util.TreeMap;

import static org.junit.Assert.*;

/**
 *
 * Junit test class for VoteCollection
 * @author Paul Foo
 * @version 1.0
 *
 */
public class VoteCollectionTests {

    private TreeMap<CandidateIndex, Candidate> cds;
    private int numCandidates = 5;
    private VoteCollection a;
    private int numFormalVotes = 12;
    private int numInformalVotes = 3;

    /** Candidates */
    private Candidate cand1;
    private Candidate cand2;
    private Candidate cand3;
    private Candidate cand4;
    private Candidate cand5;


    /** Candidate Indexes */
    private CandidateIndex candIndex1;
    private CandidateIndex candIndex2;
    private CandidateIndex candIndex3;
    private CandidateIndex candIndex4;
    private CandidateIndex candIndex5;

    @Before
    public void setUp() throws ElectionException {
        /* set up dummy votes of the following format:

        1 2 3 4 5
        1 2 3 4 5
        1 2 3 4 5
        2 1 5 3 4
        2 1 5 3 4
        2 1 5 3 4
        3 5 1 2 4
        3 5 1 2 4
        3 5 1 2 4
        4 3 5 1 2
        4 3 5 1 2
        5 3 2 4 1

        */

        a = new VoteCollection(numCandidates);
        cds = new TreeMap<>();

        // Set up dummy candidates
        cand1 = new Candidate("a", "AAA", "party A", 0);
        cand2 = new Candidate("b", "BBB", "party B", 0);
        cand3 = new Candidate("c", "CCC", "party C", 0);
        cand4 = new Candidate("d", "DDD", "party D", 0);
        cand5 = new Candidate("e", "EEE", "party E", 0);


        // Set up Candidate Indexes
        candIndex1 = new CandidateIndex(1);
        candIndex2 = new CandidateIndex(2);
        candIndex3 = new CandidateIndex(3);
        candIndex4 = new CandidateIndex(4);
        candIndex5 = new CandidateIndex(5);

        // Populate cds
        cds.put(candIndex1, cand1);
        cds.put(candIndex2, cand2);
        cds.put(candIndex3, cand3);
        cds.put(candIndex4, cand4);
        cds.put(candIndex5, cand5);

        for(int i = 0; i < numFormalVotes; i++) {
            Vote vote = new VoteList(numCandidates);
            if(i < 3) {
                vote.addPref(1);
                vote.addPref(2);
                vote.addPref(3);
                vote.addPref(4);
                vote.addPref(5);
                a.includeFormalVote(vote);
            }
            else if (i >=3 && i < 6) {
                vote.addPref(2);
                vote.addPref(1);
                vote.addPref(5);
                vote.addPref(3);
                vote.addPref(4);
                a.includeFormalVote(vote);
            }
            else if(i >= 6 && i < 9) {
                vote.addPref(3);
                vote.addPref(5);
                vote.addPref(1);
                vote.addPref(2);
                vote.addPref(4);
                a.includeFormalVote(vote);
            }
            else if(i >= 9 && i < 11) {
                vote.addPref(4);
                vote.addPref(3);
                vote.addPref(5);
                vote.addPref(1);
                vote.addPref(2);
                a.includeFormalVote(vote);
            }
            else {
                vote.addPref(5);
                vote.addPref(3);
                vote.addPref(2);
                vote.addPref(4);
                vote.addPref(1);
                a.includeFormalVote(vote);
            }
        }
    }

    /**
     * Test method for {@link asgn1Election.VoteCollection#VoteCollection(int)}
     */
    @Test(expected = ElectionException.class)
    public void negativeParam_VoteCollection_test() throws ElectionException {
        VoteCollection b = new VoteCollection(-1);
    }

    /**
     * Test method for {@link asgn1Election.VoteCollection#countPrimaryVotes(TreeMap)}
     */
    @Test
    public void countPrimaryVotesTest() {
        a.countPrimaryVotes(cds);

        assertEquals(cds.get(candIndex1).getVoteCount(), 3);
        assertEquals(cds.get(candIndex2).getVoteCount(), 3);
        assertEquals(cds.get(candIndex3).getVoteCount(), 3);
        assertEquals(cds.get(candIndex4).getVoteCount(), 2);
        assertEquals(cds.get(candIndex5).getVoteCount(), 1);
    }

    /**
     * Test method for {@link asgn1Election.VoteCollection#countPrefVotes(TreeMap, CandidateIndex)}
     */
    @Test
    public void countPrefVotesTestRound1() {
        // count primary votes
        a.countPrimaryVotes(cds);

        // eliminate the candidate with lowest votes
        cds.remove(candIndex5);

        a.countPrefVotes(cds, candIndex5);
        assertEquals(cds.get(candIndex1).getVoteCount(), 3);
        assertEquals(cds.get(candIndex2).getVoteCount(), 3);
        assertEquals(cds.get(candIndex3).getVoteCount(), 4);
        assertEquals(cds.get(candIndex4).getVoteCount(), 2);
    }

    @Test
    public void countPrefVotesTestRound2() {
        // continue from previous test, to simulate round 2 of a preference distribution
        countPrefVotesTestRound1();

        // eliminate candidateIndex4 which has the lowest votes
        cds.remove(candIndex4);
        a.countPrefVotes(cds, candIndex4);
        assertEquals(cds.get(candIndex1).getVoteCount(), 3);
        assertEquals(cds.get(candIndex2).getVoteCount(), 5);
        assertEquals(cds.get(candIndex3).getVoteCount(), 4);
    }

    @Test
    public void countPrefVotesTestRound3() {
        // continue from previous test, to simulate round 3 of a preference distribution
        countPrefVotesTestRound2();

        cds.remove(candIndex1);
        a.countPrefVotes(cds, candIndex1);
        assertEquals(cds.get(candIndex2).getVoteCount(), 8);
        assertEquals(cds.get(candIndex3).getVoteCount(), 4);
    }

    @Test
    public void countPrefVotesTestRound4() {
        // continue from previous test, to simulate round 4 of a preference distribution
        countPrefVotesTestRound3();

        cds.remove(candIndex3);
        a.countPrefVotes(cds, candIndex3);
        assertEquals(cds.get(candIndex2).getVoteCount(), 12);
    }


    /**
     * Test for method {@link asgn1Election.VoteCollection#emptyTheCollection()}
     */
    @Test
    public void emptyCollection_test() {
        a.emptyTheCollection();
        assertEquals(a.getInformalCount(), 0);
        assertEquals(a.getFormalCount(), 0);
    }

    @Test
    public void emptyCollection_VoteListEmpty_test() {
        // The votes have been loaded during the setup
        // initiating emptyTheCollection followed by a primary count will no votes incremented on each candidate
        a.emptyTheCollection();
        a.countPrimaryVotes(cds);
        assertEquals(cds.get(candIndex1).getVoteCount(), 0);
        assertEquals(cds.get(candIndex2).getVoteCount(), 0);
        assertEquals(cds.get(candIndex3).getVoteCount(), 0);
        assertEquals(cds.get(candIndex4).getVoteCount(), 0);
        assertEquals(cds.get(candIndex5).getVoteCount(), 0);
    }


    /**
     * Test for method {@link asgn1Election.VoteCollection#includeFormalVote(Vote)}
     */
    @Test
    public void includeFormalVoteTest() {
        Vote dummyVote = new VoteList(numCandidates);
        dummyVote.addPref(1);
        a.includeFormalVote(dummyVote);

        // Originally cand1 has 3 votes, will have 4 after dummy Vote proving includeFormalVote correct
        a.countPrimaryVotes(cds);
        assertEquals(cds.get(candIndex1).getVoteCount(), 4);
    }

    /**
     * Test for method {@link asgn1Election.VoteCollection#getFormalCount()}
     */
    @Test
    public void getFormalCount() {
        assertEquals(a.getFormalCount(), numFormalVotes);
    }

    @Test
    public void getFormalCountIncrement_test() {
        Vote vote = new VoteList(numCandidates);
        vote.addPref(1);
        a.includeFormalVote(vote);
        assertEquals(a.getFormalCount(), numFormalVotes + 1);
    }

    /**
     * Test for method {@link asgn1Election.VoteCollection#updateInformalCount()}
     */
    @Test
    public void updateInformalCountTest() {
        assertEquals(a.getInformalCount(), 0);
    }

    @Test
    public void updateInformalCountIncrement() {
        for(int i = 0; i < numInformalVotes; i++) {
            a.updateInformalCount();
        }
        assertEquals(a.getInformalCount(), numInformalVotes);
    }


}