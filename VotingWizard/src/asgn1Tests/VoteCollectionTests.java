package asgn1Tests;

import asgn1Election.*;
import org.junit.After;
import org.junit.Before;
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


    /** Votes **/
    private Vote vote1;
    private Vote vote2;
    private Vote vote3;
    private Vote vote4;
    private Vote vote5;



    @Before
    public void setUp() throws ElectionException {
        a = new VoteCollection(numCandidates);
        cds = new TreeMap<>();

        // Set up dummy votes
        vote1 = new VoteList(numCandidates);
        vote2 = new VoteList(numCandidates);
        vote3 = new VoteList(numCandidates);
        vote4 = new VoteList(numCandidates);
        vote5 = new VoteList(numCandidates);

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

        // special vote 1
        vote1.addPref(2);
        vote1.addPref(3);
        vote1.addPref(4);
        vote1.addPref(1);
        vote1.addPref(5);

        // Set up VoteLists to be added to VoteCollection
        for(int i = 1; i <= numCandidates; i++) {
            vote2.addPref(i);
            vote3.addPref(i);
            vote4.addPref(numCandidates - i + 1);
            vote5.addPref(numCandidates - i + 1);
        }

        // Add votes to VoteCollection
        a.includeFormalVote(vote1);
        a.includeFormalVote(vote2);
        a.includeFormalVote(vote3);
        a.includeFormalVote(vote4);
        a.includeFormalVote(vote5);

    }

    @Test (expected = ElectionException.class)
    public void constructorBadParameter() throws ElectionException {
        VoteCollection b = new VoteCollection(-1);
    }

    @Test
    public void countPrimaryVotesTest() {
        a.countPrimaryVotes(cds);
//        for(Map.Entry<CandidateIndex, Candidate> entry:  cds.entrySet()) {
//            System.out.println(entry.toString());
//        }


        assertEquals(cds.get(candIndex1).getVoteCount(), 2);
        assertEquals(cds.get(candIndex2).getVoteCount(), 0);
        assertEquals(cds.get(candIndex3).getVoteCount(), 0);
        assertEquals(cds.get(candIndex4).getVoteCount(), 1);
        assertEquals(cds.get(candIndex5).getVoteCount(), 2);


    }

    @Test
    public void countPrefVotesTest() {
        a.countPrimaryVotes(cds);
        a.countPrefVotes(cds, candIndex1);
    }






}