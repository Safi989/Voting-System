/**
 * File name: STV.java
 *
 * Description: This class is responsible for running the STV election. It takes care of the
 * whole election like calculating the droop quota, and tiebreakers.
 *
 * Author: Horoom Bula
 */
import java.util.*;

public class STV extends Election {
    // Variables
    private Results results;
    private HashSet<Integer> gone = new HashSet<>();
    private int droop;
    private Random rand;
    private HashMap<Integer, Integer> orderMap = new HashMap<>();
    private int orderCounter = 1;
    private int seats;


    /**
     * Constructs the STV election
     * @param type Election type
     * @param candidates List of candidates in the election
     * @param date Date of the election
     * @param seats Number of seats available in the election
     * @return nothing
     * @throws None
     */
    public STV(String type, List<Candidate> candidates, Date date, int seats);

    /**
     * Used to distribute ballots throughout the election until all seats are filled
     *
     * @return nothing
     * @throws None
     */
    @Override
    public void distributeBallots();

    /**
     * Counts the votes for a certain preference level while the
     * winners list is not yet full
     * @param preferenceLevel current preference level
     * @param seats number of seats
     * @return returns a hash map (candidate ID: preference level * ballot order) of the order
     * of the first votes for the candidates which will be used for tiebreakers if need be
     * @throws None
     */
    public HashMap<Integer, Integer> countVotes(int preferenceLevel, int seats);

    /**
     * Calculates the droop quota
     *
     * @return nothing
     * @throws None
     */
    public void getQuota();

    /**
     * Determines the tiebreakers between candidates with the same number of votes
     * @param tiedCandidates List of the candidates that are tied
     * @param orderMap hashmap from the countVotes function
     * @return returns the candidate that lost the tiebreaker
     * @throws None
     */
    public Candidate tieBreaker(List<Candidate> tiedCandidates, HashMap<Integer, Integer> orderMap);