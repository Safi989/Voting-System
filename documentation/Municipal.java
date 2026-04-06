/**
 * File name: Plurality.java
 *
 * Description: This class is responsible for implementing and running the plurality election
 *
 * Author: Caleb Yosef
 */

import java.util.*;

public class Municipal extends Election {
    // SDD attributes
    public Results results;
    private int seats;
    private Map<Candidate, Integer> votes;
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
    public Municipal(String type, List<Candidate> candidates, int seats, Date date);


    /**
     * Sorts the candidates by the amount of votes they got
     * @param votes amount of votes from a certain candidate
     * @return nothing
     * @throws None
     */
    private List<Candidate> sortCandidtatesByVote(Map<Candidate, Integer> votes);


    /**
     * Used to distribute ballots throughout the election until all seats are filled
     *
     * @return nothing
     * @throws None
     */
    public void distributeBallots();

