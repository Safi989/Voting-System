/**
 * File name: Plurality.java
 *
 * Description: This class is responsible for implementing and running the plurality election
 *
 * Author: Caleb Yosef
 */

import java.util.*;

public class Plurality extends Election {
    // SDD attributes
    private int[] voteTotals;
    private List<Integer> winnerList;
    private List<Integer> tiedCandidates;
    private Results results;
    private int seats;
    private Random rand;

    /**
     * Constructs the Plurality election
     * @param type election type
     * @param candidates List of Candidates
     * @param date Date of the election
     * @param seats Number of seats needed to win
     * @return None
     * @throws None
     */
    public Plurality(String type, List<Candidate> candidates, Date date, int seats);


    /**
     * Iterates through all ballots and adds one vote to the selected candidate.
     *
     * @return nothing
     * @throws None
     */
    public void countVotes();


    /**
     * Used to distribute ballots throughout the election until all seats are filled
     *
     * @return nothing
     * @throws None
     */
    public void distributeBallots();



    /**
     * Identifies top candidates according to the number of seats available.
     *
     * @return nothing
     * @throws None
     */
    public void determineWinners();

    // Helper method

    /**
     * Looks for the candidate based on the ID that was provided to them
     * @param id ID of the candidate we are searching for
     * @return candidate that we are searching for
     * @throws None
     */
    private Candidate getCandidateById(int id);
