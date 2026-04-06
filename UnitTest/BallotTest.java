import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class BallotTest {

    private Ballot ballot;
    private List<Integer> preferences;

    @Before
    public void setUp() {
        preferences = Arrays.asList(1, 2, 3, 4);
        ballot = new Ballot(1, preferences);
    }

    @Test
    public void testConstructorAndGetters() {
        assertEquals(1, ballot.getId());
        assertEquals(preferences, ballot.getPreferences());
    }

    @Test
    public void testGetPreferencesImmutableReference() {
        List<Integer> prefs = ballot.getPreferences();
        assertSame(preferences, prefs);
    }

    @Test
    public void testGetId() {
        Ballot b = new Ballot(99, Arrays.asList(3, 1, 2));
        assertEquals(99, b.getId());
    }

    @Test
    public void testEmptyPreferences() {
        Ballot b = new Ballot(5, new ArrayList<>());
        assertTrue(b.getPreferences().isEmpty());
    }

    @Test
    public void testNullPreferences() {
        Ballot b = new Ballot(10, null);
        assertNull("Preferences should be null if passed null", b.getPreferences());
    }

    @Test
    public void testPreferenceListContentIntegrity() {
        List<Integer> prefs = ballot.getPreferences();
        assertEquals(Arrays.asList(1, 2, 3, 4), prefs);
    }

    @Test
    public void testUniqueIdsForDifferentBallots() {
        Ballot b1 = new Ballot(1, Arrays.asList(1, 2));
        Ballot b2 = new Ballot(2, Arrays.asList(3, 1));
        assertFalse(b1.getId() == b2.getId());
    }
}

