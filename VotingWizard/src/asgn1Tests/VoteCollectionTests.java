package asgn1Tests;

import asgn1Election.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Map;
import java.util.TreeMap;

import static org.junit.Assert.*;

/**
 * Created by Gigness on 29/03/2016.
 */
public class VoteCollectionTests {

    private TreeMap<CandidateIndex, Candidate> cds;
    private int numCandidates = 5;
    private VoteCollection a;
    private int numFormalVotes = 12;

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

    /** setup **/
    @Before
    public void setUp() throws ElectionException {
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

        /* Votes
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
    }

    /** constructor Test */
    @Test (expected = ElectionException.class)
    public void constructorBadParameter() throws ElectionException {
        VoteCollection b = new VoteCollection(-1);
    }

    /** countPrimaryVotes Test*/
    @Test
    public void countPrimaryVotesTest() {
        a.countPrimaryVotes(cds);

        assertEquals(cds.get(candIndex1).getVoteCount(), 3);
        assertEquals(cds.get(candIndex2).getVoteCount(), 3);
        assertEquals(cds.get(candIndex3).getVoteCount(), 3);
        assertEquals(cds.get(candIndex4).getVoteCount(), 2);
        assertEquals(cds.get(candIndex5).getVoteCount(), 1);
    }

    /** countPrefVotes Tests */
    @Test
    public void countPrefVotesTestRound1() {
        a.countPrimaryVotes(cds);
        cds.remove(candIndex5);
        a.countPrefVotes(cds, candIndex5);
        assertEquals(cds.get(candIndex1).getVoteCount(), 3);
        assertEquals(cds.get(candIndex2).getVoteCount(), 3);
        assertEquals(cds.get(candIndex3).getVoteCount(), 4);
        assertEquals(cds.get(candIndex4).getVoteCount(), 2);

    }

    @Test
    public void countPrefVotesTestRound2() {
        countPrefVotesTestRound1();

        // eliminate candidateIndex1
        cds.remove(candIndex4);
        a.countPrefVotes(cds, candIndex4);
        assertEquals(cds.get(candIndex1).getVoteCount(), 3);
        assertEquals(cds.get(candIndex2).getVoteCount(), 5);
        assertEquals(cds.get(candIndex3).getVoteCount(), 4);
    }

    @Test
    public void countPrefVotesTestRound3() {
        countPrefVotesTestRound2();

        cds.remove(candIndex1);
        a.countPrefVotes(cds, candIndex1);
        assertEquals(cds.get(candIndex2).getVoteCount(), 8);
        assertEquals(cds.get(candIndex3).getVoteCount(), 4);
    }

    @Test
    public void countPrefVotesTestRound4() {
        countPrefVotesTestRound3();
        cds.remove(candIndex3);
        a.countPrefVotes(cds, candIndex3);
        assertEquals(cds.get(candIndex2).getVoteCount(), 12);
    }

    @Test
    public void emptyCollectionTest() {
        a.emptyTheCollection();
        System.out.println(a.toString());
    }

    /** getters Tests */
    @Test
    public void getFormalCount() {
        assertEquals(a.getFormalCount(), numFormalVotes);
    }

    @Test
    public void getFormalCountIncrement() {
        Vote vote = new VoteList(numCandidates);
        vote.addPref(1);
        vote.addPref(2);
        vote.addPref(3);
        vote.addPref(4);
        vote.addPref(5);
        a.includeFormalVote(vote);

        assertEquals(a.getFormalCount(), numFormalVotes + 1);
    }



}