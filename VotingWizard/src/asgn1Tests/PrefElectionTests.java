/**
 * Contains junit tests for asgn1Election.PrefElection
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
 * Class contains the the junit tests for PrefElection
 * @author Paul Foo
 * @version 1.0
 */
public class PrefElectionTests {

    private PrefElection elecA;
    private PrefElection elecB;
    private PrefElection elecC;
    private PrefElection elecD;
    private ExtendedElection elecE;
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
    }

    /**
     * Test method for {@link PrefElection#loadDefs()}
     */
    @Test(expected = ElectionException.class)
    public void notEnoughCandidates_ElectionExceptionTest() throws NumbersException, IOException, ElectionException {
        PrefElection test = new PrefElection("loadDefs");
        test.loadDefs();
    }

    @Test(expected = ElectionException.class)
    public void invalidCandidateLine_ElectionExceptionTest() throws NumbersException, IOException, ElectionException {
        PrefElection test = new PrefElection("loadDefs5");
        test.loadDefs();
    }

    @Test(expected = ElectionException.class)
    public void nullCandidateToken_ElectionExceptionTest() throws NumbersException, IOException, ElectionException {
        PrefElection test = new PrefElection("loadDefs6");
        test.loadDefs();
    }

    @Test(expected = ElectionException.class)
    public void nullSeatName_ElectionExceptionTest() throws NumbersException, IOException, ElectionException {
        PrefElection test = new PrefElection("loadDefs7");
        test.loadDefs();
    }

    @Test(expected = ElectionException.class)
    public void nullNumCandidates_ElectionExceptionTest() throws NumbersException, IOException, ElectionException {
        PrefElection test = new PrefElection("loadDefs8");
        test.loadDefs();
    }

    @Test(expected = ElectionException.class)
    public void nullEnrolment_ElectionExceptionTest() throws NumbersException, IOException, ElectionException {
        PrefElection test = new PrefElection("loadDefs9");
        test.loadDefs();
    }


    @Test(expected = ElectionException.class)
    public void nullCandidates_ElectionExceptionTest() throws NumbersException, IOException, ElectionException {
        PrefElection test = new PrefElection("loadDefs4");
        test.loadDefs();
    }

    @Test(expected = ElectionException.class)
    public void incorrectHeader_ElectionExceptionTest() throws NumbersException, IOException, ElectionException {
        PrefElection test = new PrefElection("loadDefs1");
        test.loadDefs();
    }

    @Test(expected = NumbersException.class)
    public void enrolmentNumberInvalid_NumbersExceptionTest() throws NumbersException, IOException, ElectionException {
        PrefElection test = new PrefElection("loadDefs2");
        test.loadDefs();
    }

    @Test(expected = NumbersException.class)
    public void numCandidatesInvalidValue_NumbersExceptionTest() throws NumbersException, IOException, ElectionException {
        PrefElection test = new PrefElection("loadDefs3");
        test.loadDefs();
    }

    @Test
    public void loadDefs_ElectionSettingsPrefElectionTest() {
        assertEquals(elecA.getType(), 1);
    }

    @Test
    public void loadDefs_MinMorgulValeTest() throws ElectionException {
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
    public void testLoadVotesMorgulValeThirtyVotes() {
        VoteCollection vc = (VoteCollection) elecE.getVoteCollection();
        assertTrue((vc.getFormalCount()==30)&&(vc.getInformalCount()==0));
    }

    /**
     * Test method for {@link asgn1Election.PrefElection#findWinner()
     */
    @Test
    public void findWinner_MinMorgulValeTest() {
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
    public void findWinner_MinMorgulValeTieTest() {
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
    public void findWinner_MorgulValeTest() {
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
    public void findWinner_AbsoluteMajorityTest() {
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