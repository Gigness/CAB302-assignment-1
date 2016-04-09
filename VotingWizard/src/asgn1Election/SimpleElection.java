/**
 * 
 * This file is part of the VotingWizard Project, written as 
 * part of the assessment for CAB302, Semester 1, 2016. 
 * 
 */
package asgn1Election;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import asgn1Util.Strings;

/**
 * 
 * Subclass of <code>Election</code>, specialised to simple, first past the post voting
 * 
 * @author hogan
 * 
 */
public class SimpleElection extends Election {

	/**
	 * Simple Constructor for <code>SimpleElection</code>, takes name and also sets the 
	 * election type internally. 
	 * 
	 * @param name <code>String</code> containing the Election name
	 */
	public SimpleElection(String name) {
		super(name);
		this.type = SimpleVoting;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see asgn1Election.Election#findWinner()
	 */
	@Override
	public String findWinner() {

		Candidate winner = clearWinner(0);
		String results =  reportWinner(winner);
		return results;
	}

	public void testSimpleElection() {
		vc.countPrimaryVotes(cds);
	}

	/* 
	 * (non-Javadoc)
	 * @see asgn1Election.Election#isFormal(asgn1Election.Vote)
	 */
	@Override
	public boolean isFormal(Vote v) {
		int numPrefs = 0;
		int numOnes = 0;
		for(int pref: v) {
			numPrefs++;
			if(pref == 1) {
				numOnes++;
			}
			else if(pref > numCandidates) {
				return false;
			}
		}
		if(numOnes != 1 || numPrefs != numCandidates) {
			return false;
		} else {
			return true;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String str = this.name + " - Simple Voting";
		return str;
	}
	
	// Protected and Private/helper methods below///

	/*
	 * (non-Javadoc)
	 * 
	 * @see asgn1Election.Election#clearWinner(int)
	 */
	@Override
	protected Candidate clearWinner(int wVotes) {
		int highestVotes = 0;
		Integer votes;
		Candidate winner = null;

		vc.countPrimaryVotes(cds);

		for(Map.Entry<CandidateIndex, Candidate> entry: cds.entrySet()) {

			Candidate currentCandidate;

			currentCandidate = entry.getValue();
			votes = entry.getValue().getVoteCount();

			if(highestVotes == 0) {
				winner = currentCandidate;
				highestVotes = votes;
			}
			else if(votes > highestVotes) {
				highestVotes = votes;
				winner = currentCandidate;
			}
		}

		if(winner == null) {
			System.out.println("winner is never set...");
		}
		return winner;
	}

	/**
	 * Helper method to create a string reporting the count result
	 * 
	 * @return <code>String</code> containing summary of the count
	 */
	private String reportCountResult() {
		String str = "\nSimple election: " + this.name + "\n\n"
				+ candidateVoteSummary() + "\n";
		String inf = "Informal";
		String voteStr = "" + this.vc.getInformalCount();
		int length = ElectionManager.DisplayFieldWidth - inf.length()
				- voteStr.length();
		str += inf + Strings.createPadding(' ', length) + voteStr + "\n\n";

		String cast = "Votes Cast";
		voteStr = "" + this.numVotes;
		length = ElectionManager.DisplayFieldWidth - cast.length()
				- voteStr.length();
		str += cast + Strings.createPadding(' ', length) + voteStr + "\n\n";
		return str;
	}
}