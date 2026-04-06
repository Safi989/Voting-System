/**
 * File name: Election
 *
 * Description: This class is responsible for running the selected election type. It also will hold
 * information like a list of candidates, a list of ballots, the type of election, and if shuffle was
 * enabled.
 *
 * Author: Surafiel Ghirmai
 */

import java.util.*;

public abstract class Election {
    // Variables
    protected String electionType;
    protected List<Candidate> candidates;
    protected List<Ballot> ballots;
    protected boolean shuffleEnabled;

    // Constructor
    public Election(String electionType, List<Candidate> candidates);

    /**
     * Adds ballots to the ballot list to be used during the election
     * @param ballot nex ballot being added to the list
     * @return nothing
     * @throws None
     */
    public void addBallot(Ballot ballot);


    /**
     * Creates all of the ballots in the file
     * @param filename name of the file that contains the ballots
     * @return nothing
     * @throws None
     */
    public void loadBallotsFromCSV(String filename)

    /**
     *  Abstract method implemented within either the STV or Plurality class
     *  Used to distribute ballots
     *
     *  @return nothing
     *  @throws None
     */
    public abstract void distributeBallots();



    /**
     * Shuffles the ballots so that they are received in a random order
     *
     * @return nothing
     * @throws None
     */
    public void shuffle();


}
