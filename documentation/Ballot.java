/**
 * File name: Ballot.java
 *
 * Description: This class is about breaking down the ballots to specify the preferences and
 * give an ID
 *
 * Author: Caleb Yosef
 */
import java.util.*;

public class Ballot {
    // Variables
    private int id;
    private List<Integer> preferences;

    /**
     * Constructs the Ballots
     * @param id Ballots ID
     * @param preferences Ballots preference
     * @return nothing
     * @throws None
     */
    public Ballot(int id, List<Integer> preferences);

    /**
     * Gets the current preference we are on during the STV election
     * @return current preference
     * @throws None
     */
    public List<Integer> getPreferences();

    /**
     * Gets the ID of the Ballot we are working on
     * @return Current ballots ID
     * @throws None
     */
    public int getId();

    public boolean validBallot();

    public void invalidBallot(List<Integer> prefs);
    
}