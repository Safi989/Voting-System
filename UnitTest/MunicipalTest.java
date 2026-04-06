
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;
public class MunicipalTest {
    private Candidate alice, bob, charlie;
    private List<Candidate> candidates;
    private Municipal municipal;
    private Date electionDate;


    @Before
    public void setUp() {
        alice = new Candidate("Alice", 1);
        bob = new Candidate( "Bob", 2);
        charlie = new Candidate("Charlie", 3);
        candidates = Arrays.asList(alice, bob, charlie);
        electionDate = new Date();
        municipal = new Municipal("MV", candidates, 2, electionDate);
    }


    @Test
    public void winnersandloserscheck() {
        // Add some ballots
        List<Ballot> ballots = Arrays.asList(
                new Ballot(1, Arrays.asList(1, 1, 0)),
                new Ballot(2, Arrays.asList(0, 1, 0)),
                new Ballot(3, Arrays.asList(1, 0, 0)),
                new Ballot(4, Arrays.asList(0, 0, 1))
        );

        for (Ballot b : ballots) {
            municipal.addBallot(b);
        }
        municipal.distributeBallots();

        List<Candidate> winners = municipal.results.getWinners();
        assertTrue(winners.contains(alice));
        assertTrue(winners.contains(bob));

        // Check losers
        List<Candidate> losers = municipal.results.getLosers();
        assertTrue(losers.contains(charlie));

    }

    @Test
    public void randomtiebreak() {
        List<Ballot> ballots = Arrays.asList(
                new Ballot(1, Arrays.asList(1, 1, 0)),
                new Ballot(2, Arrays.asList(0, 1, 0)),
                new Ballot(3, Arrays.asList(1, 0, 1)),
                new Ballot(4, Arrays.asList(0, 0, 1))
        );
        for (Ballot b : ballots) {
            municipal.addBallot(b);
        }

        municipal.distributeBallots();

        List<Candidate> winners = municipal.results.getWinners();
        assertEquals(winners.size(), 2 );

        // Check losers
        List<Candidate> losers = municipal.results.getLosers();
        assertEquals(losers.size() , 1);

    }


}
