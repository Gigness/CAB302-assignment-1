/**
 * 
 * This file is part of the VotingWizard Project, written as 
 * part of the assessment for CAB302, Semester 1, 2016. 
 * 
 */
package asgn1Election;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 
 * <p>Implementing class for the {@link asgn1Election.Vote} interface. <code>Vote</code> 
 * should be implemented as some sort of <code>List</code>, with 
 * <code>ArrayList<Integer></code> the default choice.</p>
 * 
 * @author hogan
 * 
 */
public class VoteList implements Vote {
	/** Holds the information that comprises a single vote */
	private List<Integer> vote;

	/** Number of candidates in the election */
	private int numCandidates;

	/**	Current candidate */
	private int currentCandidate = 0;

	/**
	 * <p>Simple Constructor for the <code>VoteList</code> class. <code>numCandidates</code> 
	 * is known to be in range through check on <code>VoteCollection</code>. 
	 * 
	 * @param numCandidates <code>int</code> number of candidates competing for 
	 * this seat. 
	 */
	public VoteList(int numCandidates) {
		this.numCandidates = numCandidates;
		vote = new ArrayList<>();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see asgn1Election.Vote#addPref(asgn1Election.CandidateIndex)
	 */
	@Override
	public boolean addPref(int index) {
		if(currentCandidate < numCandidates) {
			vote.add(index);
			currentCandidate++;
			return true;
		} else {
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see asgn1Election.Vote#copyVote()
	 */
	@Override
	public Vote copyVote() {
		Vote copyVote = new VoteList(numCandidates);
		for(int pref: vote) {
			copyVote.addPref(pref);
		}
		return copyVote;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see asgn1Election.Vote#getPreference(int)
	 */
	@Override
	public CandidateIndex getPreference(int pref) {
		int preferredCand = (vote.indexOf(pref) + 1); // Because of 0 index and candidateIndexes must be > 0
//		if(preferredCand > 0) {
//
//		} else {
//			return null;
//		}
		CandidateIndex candIndex = new CandidateIndex(preferredCand);
		return candIndex;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see asgn1Election.Vote#invertVote()
	 */
	@Override
	public Vote invertVote() {
		Vote invertedVote = new VoteList(numCandidates);

		for(int i = 1; i <= numCandidates; i++) {
			int candidate = vote.indexOf(i) + 1; // Because zero indexed arrays, and candidate Indexes are 1 based
			invertedVote.addPref(candidate);
		}
		return invertedVote;
	}

	/* 
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<Integer> iterator() {
		return vote.iterator();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String str = "";
		for (Integer index : this.vote) {
			str += index.intValue() + " ";
		}
		return str;
	}
}
