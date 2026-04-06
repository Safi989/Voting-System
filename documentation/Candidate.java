/**
 * File name: Candidate.java
 *
 * Description: This class is responsible for keeping track of all the candidates and the
 * amount of votes that they have. It will also give the candidates a unique ID.
 *
 * Author: Horoom Bula
 */

public class Candidate {
    // Variables
    private String name;
    private int votes;
    private int id;

    /** Constructs the Candidates
     * @param name Name of the candidate
     * @param id ID of the candidate
     * @return nothing
     * @throws None
     */
    public Candidate(String name, int id);

    /**
     *  This will add a vote to the candidate everytime they receive one
     *  @return nothing
     *  @throws None
     */
    public void addVote();

    /**
     * Gets the total vote count of the candidate
     * @return Vote count of the candidate
     * @throws None
     */
    public int getVotes();

    /**
     * Gets the name of the candidates
     * @return Name of the Candidate
     * @throws None
     */
    public String getName();

    /**
     * Gets the ID of the candidate
     * @return ID of the Candidate
     * @throws None
     */
    public int getId();

}