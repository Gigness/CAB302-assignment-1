/**
 *
 * This file is part of the VotingWizard Project, written as
 * part of the assessment for CAB302, Semester 1, 2016.
 *
 */
package asgn1Election;

import java.util.HashSet;
import java.util.Map;

import asgn1Util.Strings;

/**
 *
 * Subclass of <code>Election</code>, specialised to preferential, but not optional
 * preferential voting.
 *
 * @author hogan
 *
 */
public class PrefElection extends Election {

	/** Result string of the pref election */
	private String result;

	/**
	 * Simple Constructor for <code>PrefElection</code>, takes name and also sets the
	 * election type internally.
	 *
	 * @param name <code>String</code> containing the Election name
	 */
	public PrefElection(String name) {
		super(name);
		this.type = PrefVoting;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see asgn1Election.Election#findWinner()
	 */
	@Override
	public String findWinner() {
		int numWinVotes;
		double percentageNeeded;
		Candidate winner = null;
		result = showResultHeader();

		// perform primary vote count and append results to String result
		vc.countPrimaryVotes(cds);
		result += (reportPrimaryVote());
		result += (reportCountStatus());

		// calculate votes required to win
		percentageNeeded = vc.getFormalCount() * 0.5;
		numWinVotes = (int) Math.floor(percentageNeeded);

		// perform search for a winner, until a clear winner is found
		while(winner == null) {
			winner = clearWinner(numWinVotes);
		}

		result += reportWinner(winner);
		return result;
	}


	/*
	 * (non-Javadoc)
	 *
	 * @see asgn1Election.Election#isFormal(asgn1Election.Vote)
	 */
	@Override
	public boolean isFormal(Vote v) {
		HashSet<Integer> voteSet = new HashSet<>();
		for(int pref: v) {
			voteSet.add(pref);
		}
		if(voteSet.size() != numCandidates) {
			return false;
		}
		for(int i = 1; i <= numCandidates; i++) {
			if(!voteSet.contains(i)) {
				return false;
			}
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
        String str = this.name + " - Preferential Voting";
		return str;
	}

	// Protected and Private/helper methods below///

	/*
	 * (non-Javadoc)
	 *
	 * @see asgn1Election.Election#clearWinner(int)
	 */
	@Override
	protected Candidate clearWinner(int winVotes) {
		CandidateIndex elim;
		Candidate winner = null;

		// check for absolute majority
		for (Map.Entry<CandidateIndex, Candidate> entry : cds.entrySet()) {
			if (entry.getValue().getVoteCount() > winVotes) {
				winner = entry.getValue();
				return winner;
			}
		}

		// if not found, eliminate a candidate
		elim = selectLowestCandidate();
		result += prefDistMessage(cds.get(elim)) + "\n";
		cds.remove(elim);

		// perform a round of elimination
		vc.countPrefVotes(cds, elim);
		result += reportCountStatus();

		return winner;
	}

	/**
	 * Helper method to create a preference distribution message for display
	 *
	 * @param c <code>Candidate</code> to be eliminated
	 * @return <code>String</code> containing preference distribution message
	 */
	private String prefDistMessage(Candidate c) {
		String str = "\nPreferences required: distributing " + c.getName()
				+ ": " + c.getVoteCount() + " votes";
		return str;
	}

	/**
	 * Helper method to create a string reporting the count progress
	 *
	 * @return <code>String</code> containing count status
	 */
	private String reportCountStatus() {
		String str = "\nPreferential election: " + this.name + "\n\n"
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

	/**
	 * Helper method to select candidate with fewest votes
	 *
	 * @return <code>CandidateIndex</code> of candidate with fewest votes
	 */
	private CandidateIndex selectLowestCandidate() {
		CandidateIndex elim = new CandidateIndex(0);
		int elimIndex = 0;
		int lowestVotes = -1;

		for(Map.Entry<CandidateIndex, Candidate> entry: cds.entrySet()) {

			CandidateIndex currCandIndex = entry.getKey();
			Candidate currCand = entry.getValue();

			int currVotes = currCand.getVoteCount();
			if(lowestVotes == -1 || currVotes < lowestVotes) {
				lowestVotes = currVotes;
				elimIndex = Integer.parseInt(currCandIndex.toString());
			}
		}

		elim.setValue(elimIndex);
		return elim;
	}

	/**
	 * Helper method to create string to show results of a primary voting round
	 * @return String containing a description of the primary voting round
	 */
	private String reportPrimaryVote() {
		String str = "Counting primary votes; " + cds.size() + " alternatives available\n";
		return str;
	}
}