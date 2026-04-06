import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.*;

public class STVTest {

    private STV stv;
    private Candidate alice, bob, charlie;
    private List<Candidate> candidates;
    private Date electionDate;

    @Before
    public void setUp() {
        alice = new Candidate("Alice", 1);
        bob = new Candidate("Bob", 2);
        charlie = new Candidate("Charlie", 3);


        candidates = Arrays.asList(alice, bob, charlie);
        electionDate = new Date();

        stv = new STV("STV", candidates, electionDate, 1);

        // Add ballots
        stv.addBallot(new Ballot(1, Arrays.asList(1, 2, 3)));
        stv.addBallot(new Ballot(2, Arrays.asList(2, 1, 3)));
        stv.addBallot(new Ballot(3, Arrays.asList(3, 1, 2)));
    }

    @Test
    public void testGetQuota() throws Exception {
        stv.getQuota();
        int droop = (int) STV.class.getDeclaredField("droop").get(stv);
        assertEquals("Droop quota should be 2", 2, droop);
    }

    @Test
    public void testTieBreaker() {
        List<Candidate> tied = Arrays.asList(alice, bob);
        alice.addVote();
        bob.addVote();

        HashMap<Integer, Integer> orderMap = new HashMap<>();
        orderMap.put(alice.getId(), 5);
        orderMap.put(bob.getId(), 3);

        Candidate loser = stv.tieBreaker(tied, orderMap);
        assertEquals("Alice should be chosen as loser because higher order", alice, loser);
    }
}
