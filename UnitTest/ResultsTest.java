import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.*;

public class ResultsTest {

    private Results results;
    private Candidate alice, bob, charlie, diana;
    private List<Candidate> allCandidates;
    private Date electionDate;

    @Before
    public void setUp() {
        alice = new Candidate("Alice", 1);
        bob = new Candidate("Bob", 2);
        charlie = new Candidate("Charlie", 3);
        diana = new Candidate("Diana", 4);

        allCandidates = Arrays.asList(alice, bob, charlie, diana);
        electionDate = new Date();

        // For STV with 2 seats
        results = new Results("STV", electionDate, allCandidates, 2, "STV", 0);
    }

    @Test
    public void testAddWinnerAndLoser() {
        results.addWinner(alice);
        results.addLoser(bob);

        List<Candidate> winners = results.getWinners();
        List<Candidate> losers = results.getLosers();

        assertTrue(winners.contains(alice));
        assertFalse(winners.contains(bob));

        assertTrue(losers.contains(bob));
        assertFalse(losers.contains(alice));
    }

    @Test
    public void testFinalizeResultsPromotesFromLosers() {
        // Only 1 winner added, need 2 seats
        results.addWinner(alice);
        results.addLoser(bob);
        results.addLoser(charlie);

        results.finalizeResults(); // should promote the last loser (Charlie) to winners

        List<Candidate> winners = results.getWinners();
        List<Candidate> losers = results.getLosers();

        assertEquals(2, winners.size());
        assertTrue(winners.contains(alice));
        assertTrue(winners.contains(charlie)); // promoted from losers

        assertEquals(1, losers.size());
        assertTrue(losers.contains(bob));
    }

    @Test
    public void testNoPromotionIfSeatsFilled() {
        results.addWinner(alice);
        results.addWinner(bob);
        results.addLoser(charlie);

        results.finalizeResults(); // winners already 2 seats, no promotion

        List<Candidate> winners = results.getWinners();
        List<Candidate> losers = results.getLosers();

        assertEquals(2, winners.size());
        assertTrue(winners.contains(alice));
        assertTrue(winners.contains(bob));

        assertEquals(1, losers.size());
        assertTrue(losers.contains(charlie));
    }
}

