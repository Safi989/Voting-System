/**
 * File name: Results.java
 *
 * Description: This class is responsible for showing the results of the election once it ends. This will
 * show the type of election, number of ballots, number of seats, and the number of candidates. It will also
 * include some additional information depending on the election type. Plurality it will include the percentage
 * of votes each candidate received and for STV it will show the order of winning and losing candidates.
 *
 * Author: Caleb Yosef
 */
import java.util.*;

public class Results {
    // Variables
    private String electionName;
    private Date electionDate;
    private List<Candidate> winners;
    private List<Candidate> losers;
    private List<Candidate> allCandidates;
    private int numSeats;
    private String votetype;

    /**
     * Constructs the results of the election
     * @param electionName Election Name
     * @param electionDate Election Date
     * @param allCandidates List of candidates
     * @param numSeats Number of seats open in the election
     * @param votetype Election Type
     * @return nothing
     * @throws None
     */
    public Results(String electionName, Date electionDate, List<Candidate> allCandidates, int numSeats, String votetype);

    /**
     * Adds winners to the winners list if they are not currently there
     * @param c candidate being added
     * @return nothing
     * @throws None
     */
    public void addWinner(Candidate c);

    /**
     * Adds loser to the losers list if they are not there yet
     * @param c candidate being added
     * @return nothing
     * @throws None
     */
    public void addLoser(Candidate c);

    /**
     * If there are any empty seats at the end of the election this will take
     * candidates from the top of the losers list and put them in the remaining seats
     *
     * @return nothing
     * @throws None
     */
    public void finalizeResults();


    /**
     * Prints the election results to the UI
     *
     * @return nothing
     * @throws None
     */
    public void printResults();

    /**
     * Gets the list of winners from the election
     * @return List of winners
     * @throws None
     */
    public List<Candidate> getWinners();

    /**
     * Gets the list of losers from the election
     * @return List of losers
     * @throws None
     */
    public List<Candidate> getLosers();
}
