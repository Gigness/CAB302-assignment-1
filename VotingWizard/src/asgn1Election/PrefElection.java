/**
 *
 * This file is part of the VotingWizard Project, written as
 * part of the assessment for CAB302, Semester 1, 2016.
 *
 */
package asgn1Election;

import java.util.BitSet;
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

	public void testMethodOnly() {
		vc.countPrimaryVotes(cds);

		for(Map.Entry<CandidateIndex, Candidate> entry: cds.entrySet()) {
			System.out.println(entry);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see asgn1Election.Election#findWinner()
	 */
	@Override
	public String findWinner() {
		// number of votes (double)
		double numWinVotesDouble = vc.getFormalCount() * 0.5;

		// number of votes required to win the pref. election
		int numWinVotes = (int) Math.ceil(numWinVotesDouble);

		Candidate winner = clearWinner(numWinVotes);

		return "";
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

//		System.out.println(voteSet);

		if(voteSet.size() != numCandidates) {
//			System.out.println("num votes in vote set invalid");
			return false;
		}

		for(int i = 1; i <= numCandidates; i++) {
			if(!voteSet.contains(i)) {
//				System.out.println("does not contain candidate: " + i);
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
//		System.out.println("Initialisation: Votes have not been counted yet \n");
//		for(Map.Entry<CandidateIndex, Candidate> entry: cds.entrySet()) {
//			System.out.println(entry);
//		}
//		 perform initial vote count
		vc.countPrimaryVotes(cds);

//		System.out.println("Primary votes have been counted. \n");
//		for(Map.Entry<CandidateIndex, Candidate> entry: cds.entrySet()) {
//			System.out.println(entry);
//		}
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

//		System.out.println("eliminated candidate is" + elimIndex);
//
//		cds.remove(elim);
//		System.out.println("after eliminated candidate has been removed");
//		for(Map.Entry<CandidateIndex, Candidate> entry: cds.entrySet()) {
//			System.out.println(entry);
//		}

		System.out.println("\n");
		vc.printVoteCollection();

		System.out.println("\n");
		vc.printVoteCollectionInvert();

		return null;

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
		return null;
	}
}