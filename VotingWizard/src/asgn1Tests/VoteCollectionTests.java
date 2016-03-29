package asgn1Tests;

import asgn1Election.ElectionException;
import asgn1Election.VoteCollection;
import org.junit.Before;

import static org.junit.Assert.*;

/**
 * Created by Gigness on 29/03/2016.
 */
public class VoteCollectionTests {

    private int numCandidates = 8;
    private VoteCollection a;

    @Before
    public void setUp() throws ElectionException {
        a = new VoteCollection(numCandidates);
    }



}