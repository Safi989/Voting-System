import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class CandidateTest {

    private Candidate alice;

    @Before
    public void setUp() {
        // Name first, ID second
        alice = new Candidate("Alice", 1);
    }

    @Test
    public void testConstructor() {
        assertEquals("Alice", alice.getName());
        assertEquals(1, alice.getId());
        assertEquals(0, alice.getVotes()); // votes start at 0
    }

    @Test
    public void testAddVote() {
        assertEquals(0, alice.getVotes());
        alice.addVote();
        assertEquals(1, alice.getVotes());
        alice.addVote();
        assertEquals(2, alice.getVotes());
    }

    @Test
    public void testGetters() {
        // Check getName and getId
        assertEquals("Alice", alice.getName());
        assertEquals(1, alice.getId());

        // Check getVotes after adding votes
        alice.addVote();
        assertEquals(1, alice.getVotes());
    }
}
