import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class PluralityTest {

    private Candidate alice, bob, charlie;
    private List<Candidate> candidates;
    private Plurality plurality;
    private Date electionDate;

    @Before
    public void setUp() {
        alice = new Candidate("Alice", 1);
        bob = new Candidate( "Bob", 2);
        charlie = new Candidate("Charlie", 3);
        candidates = Arrays.asList(alice, bob, charlie);
        electionDate = new Date();
        plurality = new Plurality("Plurality", candidates, electionDate, 2);

        // Add some ballots
        List<Ballot> ballots = Arrays.asList(
                new Ballot(1, Arrays.asList(1, 2, 3)),
                new Ballot(2, Arrays.asList(2, 1, 3)),
                new Ballot(3, Arrays.asList(1, 3, 2)),
                new Ballot(4, Arrays.asList(3, 2, 1))
        );

        for (Ballot b : ballots) {
            plurality.addBallot(b);
        }
    }

    @Test
    public void testCountVotes() {
        plurality.countVotes();

        // Alice should have 2 votes, Bob 1, Charlie 1
        assertEquals(2, alice.getVotes());
        assertEquals(1, bob.getVotes());
        assertEquals(1, charlie.getVotes());
    }

    @Test
    public void testDetermineWinners() {
        plurality.countVotes();
        plurality.determineWinners();


        List<Candidate> winners = plurality.results.getWinners();
        assertTrue(winners.contains(alice));
        assertTrue(winners.contains(bob));
        assertFalse(winners.contains(charlie));

        // Check losers
        List<Candidate> losers = plurality.results.getLosers();
        assertTrue(losers.contains(charlie));
    }

    @Test
    public void testDistributeBallots() {

        plurality.distributeBallots();

        assertEquals(2, alice.getVotes());
        assertEquals(1, bob.getVotes());
        assertEquals(1, charlie.getVotes());

        assertEquals(2, plurality.results.getWinners().size());
        assertEquals(1, plurality.results.getLosers().size());
    }

    @Test
    public void testGetCandidateById() throws Exception {

        java.lang.reflect.Method method = Plurality.class.getDeclaredMethod("getCandidateById", int.class);
        method.setAccessible(true);
        Candidate c = (Candidate) method.invoke(plurality, 2);
        assertEquals(bob, c);

        Candidate c2 = (Candidate) method.invoke(plurality, 999);
        assertNull(c2);
    }

}

