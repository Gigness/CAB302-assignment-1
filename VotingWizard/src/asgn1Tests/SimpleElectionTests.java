package asgn1Tests;

import asgn1Election.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * Junit test class for SimpleElection
 * @author Paul Foo
 * @version 1.0
 *
 */
public class SimpleElectionTests {

    private SimpleElection elecA;
    private SimpleElection elecB;
    private SimpleElection elecC;
    private SimpleElection elecD;
    private SimpleElection elecE;
    private SimpleElection elecF;
    private SimpleElection elecG;
    private int numCandidates = 5;

    @Before
    public void setUp() throws Exception {
        elecA = new SimpleElection("MinMorgulValeSimple");
        elecA.loadDefs();
        elecA.loadVotes();

        elecD = new SimpleElection("MinMorgulValeTieSimple");
        elecD.loadDefs();
        elecD.loadVotes();

        elecE = new SimpleElection("MorgulValeSimple");
        elecE.loadDefs();
        elecE.loadVotes();

        elecB = new SimpleElection("SimpleElec_Tie");
        elecB.loadDefs();
        elecB.loadVotes();

        elecC = new SimpleElection("SimpleElec_majority");
        elecC.loadDefs();
        elecC.loadVotes();

        elecF = new SimpleElection("NoVotes");
        elecF.loadDefs();
        elecF.loadVotes();

        // Courtesy of Kieren Boal - CAB302 Facebook group
        elecG = new SimpleElection("LargeSimpsons");
        elecG.loadDefs();
        elecG.loadVotes();
    }

    /**
     * Test method for {@link asgn1Election.SimpleElection#isFormal(Vote)}
     */
    @Test
    public void isFormalValid_test() throws Exception {
        Vote v = new VoteList(numCandidates);
        v.addPref(1);
        v.addPref(2);
        v.addPref(3);
        v.addPref(4);
        v.addPref(5);
        assertTrue(elecB.isFormal(v));
    }

    @Test
    public void isFormalValid_duplicates_test() throws Exception {
        Vote v = new VoteList(numCandidates);
        v.addPref(1);
        v.addPref(3);
        v.addPref(3);
        v.addPref(3);
        v.addPref(3);
        assertTrue(elecB.isFormal(v));
    }

    @Test
    public void isFormalInvalid_duplicateFirstPrefs_test() throws Exception {
        Vote v = new VoteList(numCandidates);
        v.addPref(1);
        v.addPref(1);
        v.addPref(3);
        v.addPref(4);
        v.addPref(5);

        Vote v1 = new VoteList (numCandidates);
        v1.addPref(1);
        v1.addPref(5);
        v1.addPref(1);
        v1.addPref(2);
        v1.addPref(2);

        assertFalse(elecB.isFormal(v));
        assertFalse(elecB.isFormal(v1));
    }
    
    @Test
    public void isFormalInvalid_noFirstPref_test() {
    	Vote v = new VoteList(numCandidates);
    	v.addPref(3);
        v.addPref(5);
        v.addPref(4);
        v.addPref(2);
        v.addPref(2);
        assertFalse(elecB.isFormal(v));
    }

    @Test
    public void isFormalInvalid_CandidateIndexZero_test() {
    	Vote v = new VoteList(numCandidates);
    	v.addPref(3);
        v.addPref(0);
        v.addPref(4);
        v.addPref(1);
        v.addPref(2);
        assertFalse(elecB.isFormal(v));
    }
    
    @Test
    public void isFormalInvalid_CandidateIndexNegative_test() {
    	Vote v = new VoteList(numCandidates);
    	v.addPref(3);
        v.addPref(-1);
        v.addPref(4);
        v.addPref(1);
        v.addPref(2);
        assertFalse(elecB.isFormal(v));
    }
    
    @Test
    public void isFormalInvalid_candidateIndexOutOfRange_test() {
        Vote v = new VoteList(numCandidates);
        v.addPref(1);
        v.addPref(2);
        v.addPref(3);
        v.addPref(4);
        v.addPref(6);
        assertFalse(elecB.isFormal(v));
    }

    @Test
    public void isFormalInvalid_notEnoughCandidates_test() {
        Vote v1 = new VoteList (numCandidates);
        v1.addPref(1);
        v1.addPref(5);
        assertFalse(elecB.isFormal(v1));
    }

    /**
     * Test method for {@link asgn1Election.SimpleElection#findWinner()}
     */
    @Test
    public void findWinner_minMorgulValeSimple_test() {
        String statement = elecA.findWinner();
        String expected = "Results for election: MinMorgulValeSimple\n" +
                "Enrolment: 25\n" +
                "\n" +
                "Shelob              Monster Spider Party          (MSP)\n" +
                "Gorbag              Filthy Orc Party              (FOP)\n" +
                "Shagrat             Stinking Orc Party            (SOP)\n" +
                "\n" +
                "\n" +
                "Counting primary votes; 3 alternatives available\n" +
                "\n" +
                "Simple election: MinMorgulValeSimple\n" +
                "\n" +
                "Shelob (MSP)                 8\n" +
                "Gorbag (FOP)                 8\n" +
                "Shagrat (SOP)                3\n" +
                "\n" +
                "Informal                     4\n" +
                "\n" +
                "Votes Cast                  23\n" +
                "\n" +
                "\n" +
                "Candidate Shelob (Monster Spider Party) is the winner with 8 votes...\n";
        assertEquals(statement, expected);
    }

    @Test
    public void findWinner_minMorgulaValeTieSimple_test() {
        String statement = elecD.findWinner();
        String expected = "Results for election: MinMorgulValeTieSimple\n" +
                "Enrolment: 25\n" +
                "\n" +
                "Shelob              Monster Spider Party          (MSP)\n" +
                "Gorbag              Filthy Orc Party              (FOP)\n" +
                "Shagrat             Stinking Orc Party            (SOP)\n" +
                "\n" +
                "\n" +
                "Counting primary votes; 3 alternatives available\n" +
                "\n" +
                "Simple election: MinMorgulValeTieSimple\n" +
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
                "Candidate Shelob (Monster Spider Party) is the winner with 8 votes...\n";
        assertEquals(statement, expected);

    }

    @Test
    public void findWinner_MorgulValeSimple_test() {
        String statement = elecE.findWinner();
        String expected = "Results for election: MorgulValeSimple\n" +
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
                "Simple election: MorgulValeSimple\n" +
                "\n" +
                "Shelob (MSP)                10\n" +
                "Gorbag (FOP)                 5\n" +
                "Shagrat (SOP)                4\n" +
                "Black Rider (NP)             9\n" +
                "Mouth of Sauron (WSSP)       3\n" +
                "\n" +
                "Informal                     0\n" +
                "\n" +
                "Votes Cast                  31\n" +
                "\n" +
                "\n" +
                "Candidate Shelob (Monster Spider Party) is the winner with 10 votes...\n";
        assertEquals(statement, expected);
    }


    @Test
    public void findWinner_clearMajorityWin_test() {
        String statement = elecC.findWinner();
        String actual_statement = "Results for election: SimpleElec_majority\n" +
                "Enrolment: 10\n" +
                "\n" +
                "VapeNation          Vapenayshun                   (VPN)\n" +
                "SwagKings           SwagKings                     (SWG)\n" +
                "YoloSwag            YoloSwag Party                (YOL)\n" +
                "\n" +
                "\n" +
                "Counting primary votes; 3 alternatives available\n" +
                "\n" +
                "Simple election: SimpleElec_majority\n" +
                "\n" +
                "VapeNation (VPN)             3\n" +
                "SwagKings (SWG)              4\n" +
                "YoloSwag (YOL)               6\n" +
                "\n" +
                "Informal                     4\n" +
                "\n" +
                "Votes Cast                  17\n" +
                "\n" +
                "\n" +
                "Candidate YoloSwag (YoloSwag Party) is the winner with 6 votes...\n";
        assertEquals(statement, actual_statement);
    }

    @Test
    public void findWinner_Tie_test() {
        String statement = elecB.findWinner();
        String actual_statement =
                "Results for election: SimpleElec_Tie\n" +
                "Enrolment: 20\n" +
                "\n" +
                "VapeNation          Vapenayshun                   (VPN)\n" +
                "SwagKings           SwagKings                     (SWG)\n" +
                "YoloSwag            YoloSwag Party                (YOL)\n" +
                "abc                 abc Party                     (ABC)\n" +
                "bleh                bleh Party                    (BLH)\n" +
                "\n" +
                "\n" +
                "Counting primary votes; 5 alternatives available\n" +
                "\n" +
                "Simple election: SimpleElec_Tie\n" +
                "\n" +
                "VapeNation (VPN)             5\n" +
                "SwagKings (SWG)              5\n" +
                "YoloSwag (YOL)               2\n" +
                "abc (ABC)                    5\n" +
                "bleh (BLH)                   3\n" +
                "\n" +
                "Informal                     0\n" +
                "\n" +
                "Votes Cast                  20\n" +
                "\n" +
                "\n" +
                "Candidate VapeNation (Vapenayshun) is the winner with 5 votes...\n";
        assertEquals(statement, actual_statement);
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
                "Simple election: LargeSimpsons\n" +
                "\n" +
                "Bart Simpson (NSP)         107\n" +
                "Lisa Simpson (SPP)         110\n" +
                "Homer Simpson (DFP)         97\n" +
                "Marge Simpson (BHP)        121\n" +
                "Maggie Simpson (PLP)       113\n" +
                "Montgomery Burns (MBP)     112\n" +
                "Comic Book Guy (CFP)       103\n" +
                "\n" +
                "Informal                    37\n" +
                "\n" +
                "Votes Cast                 800\n" +
                "\n" +
                "\n" +
                "Candidate Marge Simpson (Blue Hair Party) is the winner with 121 votes...\n";
        assertEquals(statement, expected);
    }

    @Test
    public void NoVotes_tie_test() {
        String statement = elecF.findWinner();
        String expected =
                "Results for election: NoVotes\n" +
                "Enrolment: 25\n" +
                "\n" +
                "Shelob              Monster Spider Party          (MSP)\n" +
                "Gorbag              Filthy Orc Party              (FOP)\n" +
                "Shagrat             Stinking Orc Party            (SOP)\n" +
                "\n" +
                "\n" +
                "Counting primary votes; 3 alternatives available\n" +
                "\n" +
                "Simple election: NoVotes\n" +
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
                "Candidate Shelob (Monster Spider Party) is the winner with 0 votes...\n";
        assertEquals(statement, expected);
    }
}