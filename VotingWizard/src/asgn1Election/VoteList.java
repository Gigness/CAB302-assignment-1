/**
 * 
 * This file is part of the VotingWizard Project, written as 
 * part of the assessment for CAB302, Semester 1, 2016. 
 * 
 */
package asgn1Election;

import java.util.*;

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
		this.vote = new ArrayList<>();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see asgn1Election.Vote#addPref(asgn1Election.CandidateIndex)
	 */
	@Override
	public boolean addPref(int index) {
		if((this.currentCandidate < this.numCandidates) && (CandidateIndex.inRange(index))) {
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
		for(int pref: this.vote) {
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
		// Because of 0-index arrays and candidateIndexes must be > 0
		int preferredCand = (vote.indexOf(pref) + 1);
        if(preferredCand == 0) {
            return null;
        }
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
		Vote invertedVote = new VoteList(this.numCandidates);
		for(int i = 1; i <= this.numCandidates; i++) {
			// Because zero indexed arrays, and candidate Indexes are 1 based
			int candidate = this.vote.indexOf(i) + 1;
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
		return this.vote.iterator();
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
