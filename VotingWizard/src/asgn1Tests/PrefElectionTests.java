/**
 *
 * This file is part of the testing suite for the VotingWizard Project
 * for CAB302, Semester 1, 2016
 * Junit tests for {@link asgn1Election.PrefElection}
 *
 */
package asgn1Tests;

import asgn1Election.*;
import asgn1Util.NumbersException;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
import java.util.ArrayList;
import static org.junit.Assert.*;

/**
 *
 * Testing Suite for PrefElection
 * @author Paul Foo
 * @version 1.0
 *
 */
public class PrefElectionTests {

    private PrefElection elecA;
    private PrefElection elecB;
    private PrefElection elecC;
    private PrefElection elecD;
    private ExtendedElection elecE;
    private PrefElection elecF;
    private PrefElection elecG;
    private int numCandidates = 3;

    @Before
    public void setUp() throws Exception {
        elecA = new PrefElection("MinMorgulVale");
        elecA.loadDefs();
        elecA.loadVotes();

        elecB = new PrefElection("MinMorgulValeTie");
        elecB.loadDefs();
        elecB.loadVotes();

        elecC = new PrefElection("MorgulVale");
        elecC.loadDefs();
        elecC.loadVotes();

        elecD = new PrefElection("AbsoluteMajority");
        elecD.loadDefs();
        elecD.loadVotes();

        elecE = new ExtendedElection("MorgulVale");
        elecE.loadDefs();
        elecE.loadVotes();

        elecF = new PrefElection("noVotes");
        elecF.loadDefs();
        elecF.loadVotes();

        // Courtesy of Kieren Boal - CAB302 Facebook group
        elecG = new PrefElection("LargeSimpsons");
        elecG.loadDefs();
        elecG.loadVotes();
    }

    /**
     * Test method for {@link asgn1Election.PrefElection#loadDefs()}
     */
    @Test(expected = ElectionException.class)
    public void notEnoughCandidates_ElectionException_test() throws NumbersException, IOException, ElectionException {
        PrefElection test = new PrefElection("loadDefs");
        test.loadDefs();
    }

    @Test(expected = ElectionException.class)
    public void invalidCandidateLine_ElectionException_test() throws NumbersException, IOException, ElectionException {
        PrefElection test = new PrefElection("loadDefs5");
        test.loadDefs();
    }

    @Test(expected = ElectionException.class)
    public void invalidCandidateLinePartyMissing_ElectionException_test() throws NumbersException, IOException, ElectionException {
        PrefElection test = new PrefElection("loadDefs6");
        test.loadDefs();
    }

    @Test(expected = ElectionException.class)
    public void nullSeatNameValue_ElectionException_test() throws NumbersException, IOException, ElectionException {
        PrefElection test = new PrefElection("loadDefs7");
        test.loadDefs();
    }

    @Test(expected = ElectionException.class)
    public void nullSeatNameLabel_ElectionException_test() throws NumbersException, IOException, ElectionException {
        PrefElection test = new PrefElection("loadDefs11");
        test.loadDefs();
    }

    @Test(expected = ElectionException.class)
    public void nullNumCandidatesValue_ElectionException_test() throws NumbersException, IOException, ElectionException {
        PrefElection test = new PrefElection("loadDefs8");
        test.loadDefs();
    }

    @Test(expected = ElectionException.class)
    public void nullNumCandidatesLabel_ElectionException_test() throws NumbersException, IOException, ElectionException {
        PrefElection test = new PrefElection("loadDefs12");
        test.loadDefs();
    }

    @Test(expected = ElectionException.class)
    public void nullEnrolmentValue_ElectionException_test() throws NumbersException, IOException, ElectionException {
        PrefElection test = new PrefElection("loadDefs9");
        test.loadDefs();
    }

    @Test(expected = ElectionException.class)
    public void nullEnrolmentLabel_ElectionException_test() throws NumbersException, IOException, ElectionException {
        PrefElection test = new PrefElection("loadDefs13");
        test.loadDefs();
    }

    @Test(expected = ElectionException.class)
    public void nullCandidates_ElectionException_test() throws NumbersException, IOException, ElectionException {
        PrefElection test = new PrefElection("loadDefs4");
        test.loadDefs();
    }

    @Test(expected = ElectionException.class)
    public void incorrectHeader_ElectionException_test() throws NumbersException, IOException, ElectionException {
        PrefElection test = new PrefElection("loadDefs1");
        test.loadDefs();
    }

    @Test(expected = ElectionException.class)
    public void nullHeader_ElectionException_test() throws NumbersException, IOException, ElectionException {
        PrefElection test = new PrefElection("loadDefs14");
        test.loadDefs();
    }

    @Test(expected = NumbersException.class)
    public void enrolmentNumberInvalid_NumbersException_test() throws NumbersException, IOException, ElectionException {
        PrefElection test = new PrefElection("loadDefs2");
        test.loadDefs();
    }

    @Test(expected = NumbersException.class)
    public void numCandidatesInvalidValue_NumbersException_test() throws NumbersException, IOException, ElectionException {
        PrefElection test = new PrefElection("loadDefs3");
        test.loadDefs();
    }

    @Test(expected = ElectionException.class)
    public void loadDefs_EmptyFile_ElectionException_test() throws NumbersException, IOException, ElectionException {
        PrefElection test = new PrefElection("loadDefs10");
        test.loadDefs();
    }

    @Test
    public void loadDefs_ElectionSettingsPrefElection_test() {
        assertEquals(elecA.getType(), 1);
    }

    @Test
    public void loadDefs_MinMorgulVale_test() throws ElectionException {
        java.util.Collection<Candidate> candidates = elecA.getCandidates();
        ArrayList<Candidate> expectedCandidates= new ArrayList<>();
        int counter = 0;

        Candidate shelob = new Candidate("Shelob", "Monster Spider Party", "MSP", 0 );
        Candidate gorbag = new Candidate("Gorbag", "Filthy Orc Party", "FOP", 0 );
        Candidate shagrat = new Candidate("Shagrat", "Stinking Orr Party", "SOP", 0 );

        expectedCandidates.add(shelob);
        expectedCandidates.add(gorbag);
        expectedCandidates.add(shagrat);

        for(Candidate c: candidates) {
            assertEquals(c.toString(), expectedCandidates.get(counter).toString());
            counter++;
        }
    }

    /**
     * Test method for {@link asgn1Election.PrefElection#loadVotes()}
     */
    @Test(expected = NumbersException.class)
    public void voteContainsLetter_NumbersExceptionTest() throws NumbersException, IOException, ElectionException {
        PrefElection test = new PrefElection("loadVot");
        test.loadDefs();
        test.loadVotes();
    }

    @Test(expected = ElectionException.class)
    public void tooManyVotes_InvalidVoteLine_ElectionExceptionTest() throws NumbersException, IOException, ElectionException {
        PrefElection test = new PrefElection("loadVot1");
        test.loadDefs();
        test.loadVotes();
    }

    @Test(expected = ElectionException.class)
    public void blankVoteLine_InvalidVoteLineExceptionTest() throws NumbersException, IOException, ElectionException {
        PrefElection test = new PrefElection("loadVot2");
        test.loadDefs();
        test.loadVotes();
    }

    @Test(expected = ElectionException.class)
    public void missingVoteToken_InvalidVoteLineExceptionTest() throws NumbersException, IOException, ElectionException {
        PrefElection test = new PrefElection("loadVot4");
        test.loadDefs();
        test.loadVotes();
    }

    @Test
    public void LoadVotesMorgulValeThirtyVotes_test() {
        VoteCollection vc = (VoteCollection) elecE.getVoteCollection();
        assertTrue((vc.getFormalCount()==30)&&(vc.getInformalCount()==0));
    }

    /**
     * Test method for {@link asgn1Election.PrefElection#findWinner()
     */
    @Test
    public void findWinner_MinMorgulVale_Test() {
        String statement = elecA.findWinner();
        String expected_statement = "Results for election: MinMorgulVale\n" +
                "Enrolment: 25\n" +
                "\n" +
                "Shelob              Monster Spider Party          (MSP)\n" +
                "Gorbag              Filthy Orc Party              (FOP)\n" +
                "Shagrat             Stinking Orc Party            (SOP)\n" +
                "\n" +
                "\n" +
                "Counting primary votes; 3 alternatives available\n" +
                "\n" +
                "Preferential election: MinMorgulVale\n" +
                "\n" +
                "Shelob (MSP)                 8\n" +
                "Gorbag (FOP)                 7\n" +
                "Shagrat (SOP)                3\n" +
                "\n" +
                "Informal                     3\n" +
                "\n" +
                "Votes Cast                  21\n" +
                "\n" +
                "\n" +
                "Preferences required: distributing Shagrat: 3 votes\n" +
                "\n" +
                "Preferential election: MinMorgulVale\n" +
                "\n" +
                "Shelob (MSP)                10\n" +
                "Gorbag (FOP)                 8\n" +
                "\n" +
                "Informal                     3\n" +
                "\n" +
                "Votes Cast                  21\n" +
                "\n" +
                "\n" +
                "Candidate Shelob (Monster Spider Party) is the winner with 10 votes...\n";
        assertEquals(statement, expected_statement);
    }

    @Test
    public void findWinner_MinMorgulValeTie_Test() {
        String statement = elecB.findWinner();
        String expected_statement = "Results for election: MinMorgulValeTie\n" +
                "Enrolment: 25\n" +
                "\n" +
                "Shelob              Monster Spider Party          (MSP)\n" +
                "Gorbag              Filthy Orc Party              (FOP)\n" +
                "Shagrat             Stinking Orc Party            (SOP)\n" +
                "\n" +
                "\n" +
                "Counting primary votes; 3 alternatives available\n" +
                "\n" +
                "Preferential election: MinMorgulValeTie\n" +
                "\n" +
                "Shelob (MSP)                 8\n" +
                "Gorbag (FOP)                 7\n" +
                "Shagrat (SOP)                3\n" +
                "\n" +
                "Informal                     3\n" +
                "\n" +
                "Votes Cast                  21\n" +
                "\n" +
                "\n" +
                "Preferences required: distributing Shagrat: 3 votes\n" +
                "\n" +
                "Preferential election: MinMorgulValeTie\n" +
                "\n" +
                "Shelob (MSP)                 9\n" +
                "Gorbag (FOP)                 9\n" +
                "\n" +
                "Informal                     3\n" +
                "\n" +
                "Votes Cast                  21\n" +
                "\n" +
                "\n" +
                "Preferences required: distributing Shelob: 9 votes\n" +
                "\n" +
                "Preferential election: MinMorgulValeTie\n" +
                "\n" +
                "Gorbag (FOP)                18\n" +
                "\n" +
                "Informal                     3\n" +
                "\n" +
                "Votes Cast                  21\n" +
                "\n" +
                "\n" +
                "Candidate Gorbag (Filthy Orc Party) is the winner with 18 votes...\n";
        assertEquals(statement, expected_statement);
    }

    @Test
    public void findWinner_MorgulVale_Test() {
        String statement = elecC.findWinner();
        String expected_statement = "Results for election: MorgulVale\n" +
                "Enrolment: 83483\n" +
                "\n" +
                "Shelob              Monster Spider Party          (MSP)\n" +
                "Gorbag              Filthy Orc Party              (FOP)\n" +
                "Shagrat             Stinking Orc Party            (SOP)\n" +
                "Black Rider         Nazgul Party                  (NP)\n" +
                "Mouth of Sauron     Whatever Sauron Says Party    (WSSP)\n" +
                "\n" +
                "\n" +
                "Counting primary votes; 5 alternatives available\n" +
                "\n" +
                "Preferential election: MorgulVale\n" +
                "\n" +
                "Shelob (MSP)                 9\n" +
                "Gorbag (FOP)                 5\n" +
                "Shagrat (SOP)                4\n" +
                "Black Rider (NP)             9\n" +
                "Mouth of Sauron (WSSP)       3\n" +
                "\n" +
                "Informal                     0\n" +
                "\n" +
                "Votes Cast                  30\n" +
                "\n" +
                "\n" +
                "Preferences required: distributing Mouth of Sauron: 3 votes\n" +
                "\n" +
                "Preferential election: MorgulVale\n" +
                "\n" +
                "Shelob (MSP)                 9\n" +
                "Gorbag (FOP)                 5\n" +
                "Shagrat (SOP)                6\n" +
                "Black Rider (NP)            10\n" +
                "\n" +
                "Informal                     0\n" +
                "\n" +
                "Votes Cast                  30\n" +
                "\n" +
                "\n" +
                "Preferences required: distributing Gorbag: 5 votes\n" +
                "\n" +
                "Preferential election: MorgulVale\n" +
                "\n" +
                "Shelob (MSP)                12\n" +
                "Shagrat (SOP)                7\n" +
                "Black Rider (NP)            11\n" +
                "\n" +
                "Informal                     0\n" +
                "\n" +
                "Votes Cast                  30\n" +
                "\n" +
                "\n" +
                "Preferences required: distributing Shagrat: 7 votes\n" +
                "\n" +
                "Preferential election: MorgulVale\n" +
                "\n" +
                "Shelob (MSP)                14\n" +
                "Black Rider (NP)            16\n" +
                "\n" +
                "Informal                     0\n" +
                "\n" +
                "Votes Cast                  30\n" +
                "\n" +
                "\n" +
                "Candidate Black Rider (Nazgul Party) is the winner with 16 votes...\n";
        assertEquals(statement, expected_statement);
    }

    @Test
    public void findWinner_AbsoluteMajority_Test() {
        String statement = elecD.findWinner();
        String expected_statement = "Results for election: AbsoluteMajority\n" +
                "Enrolment: 10\n" +
                "\n" +
                "VapeNation          Vapenayshun                   (VPN)\n" +
                "SwagKings           SwagKings                     (SWG)\n" +
                "YoloSwag            YoloSwag Party                (YOL)\n" +
                "\n" +
                "\n" +
                "Counting primary votes; 3 alternatives available\n" +
                "\n" +
                "Preferential election: AbsoluteMajority\n" +
                "\n" +
                "VapeNation (VPN)            13\n" +
                "SwagKings (SWG)             10\n" +
                "YoloSwag (YOL)               1\n" +
                "\n" +
                "Informal                     0\n" +
                "\n" +
                "Votes Cast                  24\n" +
                "\n" +
                "\n" +
                "Candidate VapeNation (Vapenayshun) is the winner with 13 votes...\n";
        assertEquals(statement, expected_statement);
    }

    @Test
    public void findWinner_LargeSimpsons_test() {
        String statement = elecG.findWinner();
        String expected = "Results for election: LargeSimpsons\n" +
                "Enrolment: 800\n" +
                "\n" +
                "Bart Simpson        No School Party               (NSP)\n" +
                "Lisa Simpson        Smarty Pants Party            (SPP)\n" +
                "Homer Simpson       Doughnuts For all Pary        (DFP)\n" +
                "Marge Simpson       Blue Hair Party               (BHP)\n" +
                "Maggie Simpson      Pacifier Lovers Party         (PLP)\n" +
                "Montgomery Burns    Money Bags Party              (MBP)\n" +
                "Comic Book Guy      Comics For All Party          (CFP)\n" +
                "\n" +
                "\n" +
                "Counting primary votes; 7 alternatives available\n" +
                "\n" +
                "Preferential election: LargeSimpsons\n" +
                "\n" +
                "Bart Simpson (NSP)         106\n" +
                "Lisa Simpson (SPP)         108\n" +
                "Homer Simpson (DFP)         97\n" +
                "Marge Simpson (BHP)        116\n" +
                "Maggie Simpson (PLP)       112\n" +
                "Montgomery Burns (MBP)     109\n" +
                "Comic Book Guy (CFP)       102\n" +
                "\n" +
                "Informal                    50\n" +
                "\n" +
                "Votes Cast                 800\n" +
                "\n" +
                "\n" +
                "Preferences required: distributing Homer Simpson: 97 votes\n" +
                "\n" +
                "Preferential election: LargeSimpsons\n" +
                "\n" +
                "Bart Simpson (NSP)         119\n" +
                "Lisa Simpson (SPP)         125\n" +
                "Marge Simpson (BHP)        130\n" +
                "Maggie Simpson (PLP)       131\n" +
                "Montgomery Burns (MBP)     127\n" +
                "Comic Book Guy (CFP)       118\n" +
                "\n" +
                "Informal                    50\n" +
                "\n" +
                "Votes Cast                 800\n" +
                "\n" +
                "\n" +
                "Preferences required: distributing Comic Book Guy: 118 votes\n" +
                "\n" +
                "Preferential election: LargeSimpsons\n" +
                "\n" +
                "Bart Simpson (NSP)         142\n" +
                "Lisa Simpson (SPP)         153\n" +
                "Marge Simpson (BHP)        157\n" +
                "Maggie Simpson (PLP)       150\n" +
                "Montgomery Burns (MBP)     148\n" +
                "\n" +
                "Informal                    50\n" +
                "\n" +
                "Votes Cast                 800\n" +
                "\n" +
                "\n" +
                "Preferences required: distributing Bart Simpson: 142 votes\n" +
                "\n" +
                "Preferential election: LargeSimpsons\n" +
                "\n" +
                "Lisa Simpson (SPP)         193\n" +
                "Marge Simpson (BHP)        195\n" +
                "Maggie Simpson (PLP)       183\n" +
                "Montgomery Burns (MBP)     179\n" +
                "\n" +
                "Informal                    50\n" +
                "\n" +
                "Votes Cast                 800\n" +
                "\n" +
                "\n" +
                "Preferences required: distributing Montgomery Burns: 179 votes\n" +
                "\n" +
                "Preferential election: LargeSimpsons\n" +
                "\n" +
                "Lisa Simpson (SPP)         258\n" +
                "Marge Simpson (BHP)        257\n" +
                "Maggie Simpson (PLP)       235\n" +
                "\n" +
                "Informal                    50\n" +
                "\n" +
                "Votes Cast                 800\n" +
                "\n" +
                "\n" +
                "Preferences required: distributing Maggie Simpson: 235 votes\n" +
                "\n" +
                "Preferential election: LargeSimpsons\n" +
                "\n" +
                "Lisa Simpson (SPP)         363\n" +
                "Marge Simpson (BHP)        387\n" +
                "\n" +
                "Informal                    50\n" +
                "\n" +
                "Votes Cast                 800\n" +
                "\n" +
                "\n" +
                "Candidate Marge Simpson (Blue Hair Party) is the winner with 387 votes...\n";
        assertEquals(statement, expected);
    }

    @Test
    public void findWinner_noVotes_test() {
        String statement = elecF.findWinner();
        String expected = "Results for election: NoVotes\n" +
                "Enrolment: 25\n" +
                "\n" +
                "Shelob              Monster Spider Party          (MSP)\n" +
                "Gorbag              Filthy Orc Party              (FOP)\n" +
                "Shagrat             Stinking Orc Party            (SOP)\n" +
                "\n" +
                "\n" +
                "Counting primary votes; 3 alternatives available\n" +
                "\n" +
                "Preferential election: NoVotes\n" +
                "\n" +
                "Shelob (MSP)                 0\n" +
                "Gorbag (FOP)                 0\n" +
                "Shagrat (SOP)                0\n" +
                "\n" +
                "Informal                     1\n" +
                "\n" +
                "Votes Cast                   1\n" +
                "\n" +
                "\n" +
                "Preferences required: distributing Shelob: 0 votes\n" +
                "\n" +
                "Preferential election: NoVotes\n" +
                "\n" +
                "Gorbag (FOP)                 0\n" +
                "Shagrat (SOP)                0\n" +
                "\n" +
                "Informal                     1\n" +
                "\n" +
                "Votes Cast                   1\n" +
                "\n" +
                "\n" +
                "Preferences required: distributing Gorbag: 0 votes\n" +
                "\n" +
                "Preferential election: NoVotes\n" +
                "\n" +
                "Shagrat (SOP)                0\n" +
                "\n" +
                "Informal                     1\n" +
                "\n" +
                "Votes Cast                   1\n" +
                "\n" +
                "\n" +
                "Candidate Shagrat (Stinking Orc Party) is the winner with 0 votes...\n";
        assertEquals(statement, expected);
    }

    /**
     * Test method for {@link asgn1Election.PrefElection#isFormal(Vote v)}
     */
    @Test
    public void isFormal_Test() throws Exception {
        Vote v = new VoteList(numCandidates);
        v.addPref(1);
        v.addPref(2);
        v.addPref(3);
        assertTrue(elecA.isFormal(v));
    }

    @Test
    public void isFormalMissingCandidate_Test() {
        Vote v = new VoteList(numCandidates);
        v.addPref(1);
        v.addPref(2);
        assertFalse(elecA.isFormal(v));
    }

    @Test
    public void isFormal_OutOfRange_Test() {
        Vote v = new VoteList(numCandidates);
        v.addPref(1);
        v.addPref(2);
        v.addPref(4);
        assertFalse(elecA.isFormal(v));
    }

    @Test
    public void isFormal_Duplicate_Test() {
        Vote v = new VoteList(numCandidates);
        v.addPref(1);
        v.addPref(2);
        v.addPref(2);
        assertFalse(elecA.isFormal(v));
    }
    @Test
    public void isFormal_DuplicateFirstPref_Test() {
        Vote v = new VoteList(numCandidates);
        v.addPref(1);
        v.addPref(1);
        v.addPref(2);
        assertFalse(elecA.isFormal(v));
    }
}