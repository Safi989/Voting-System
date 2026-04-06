import java.io.FileWriter;
import java.util.*;
import java.io.IOException;

public class STV extends Election {
    private Results results;
    private HashSet<Integer> gone = new HashSet<>();
    public int droop;
    private Random rand;
    private HashMap<Integer, Integer> orderMap = new HashMap<>();
    private HashMap<Integer, List<Integer>> ballot_owner = new HashMap<>();
    private HashSet<Integer> take_ballot = new HashSet();

    private int orderCounter = 1; // Tracks order for vote counting
    private int seats; // Number of seats to fill in this election

    // Constructor initializes STV election
    public STV(String type, List<Candidate> candidates, Date date, int seats) {
        super(type, candidates);
        this.rand = new Random();
        this.seats = seats;
        this.results = new Results(type, date, candidates, seats, "STV",0);
    }

    @Override
    public void distributeBallots(){
        try {
            new FileWriter("InValidBallots.txt", false).close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        getQuota(); // Calculate the droop quota needed to win
        int preferenceLevel = 0; // Start counting with first preference

        // Main STV counting loop:
        // Continue until all seats filled or all candidates accounted for, or we've gone through all preference levels
        while ((results.getWinners().size() != seats ||
                results.getWinners().size() + results.getLosers().size() < candidates.size()) &&
                preferenceLevel < candidates.size()) {

            System.out.println("Counting preference level " + preferenceLevel);

            // Count votes at this preference level and update order map
            HashMap<Integer, Integer> orderMap = countVotes(preferenceLevel, seats);

            // Determine candidates still in contention
            List<Candidate> available = new ArrayList<>();
            for (Candidate c : candidates) {
                if (!results.getWinners().contains(c) && !results.getLosers().contains(c)) {
                    available.add(c);
                }
            }

            // Find the minimum number of votes among remaining candidates
            int minVotes = Integer.MAX_VALUE;
            for (Candidate c : available) {
                minVotes = Math.min(minVotes, c.getVotes());
            }

            // Identify all candidates tied for the minimum votes
            List<Candidate> tied = new ArrayList<>();
            for (Candidate c : available) {
                if (c.getVotes() == minVotes) tied.add(c);
            }

            // Eliminate one candidate (loser) from this tie or the single candidate with fewest votes
            if (tied.size() > 0) {
                Candidate loser = (tied.size() > 1)
                        ? tieBreaker(tied, orderMap) // Use tie-breaker if multiple candidates tied
                        : tied.get(0);

                gone.add(loser.getId()); // Mark candidate as eliminated
                results.addLoser(loser); // Add to losers list
                List<Integer> ownedBallots = ballot_owner.get(loser.getId());

                // Remove ballots owned by eliminated candidate from counted ballots
                if (ownedBallots != null && !ownedBallots.isEmpty()) {
                    for (int ballotId : ownedBallots) {
                        take_ballot.remove(ballotId);
                    }
                }
            }

            preferenceLevel++; // Move to next preference level
        }

        results.printReport(); // Print the final election results
    }

    // Counts votes for a specific preference level
    public HashMap<Integer, Integer> countVotes(int preferenceLevel, int seats) {
        int id = 1; // Internal counter for order map

        for (Ballot ballot : ballots) {
            System.out.println("Ballot ID: " + ballot.getId() + ", Preferences: " + ballot.getPreferences());

            // Skip ballots already counted in previous rounds
            if (take_ballot.contains(ballot.getId())){
                continue;
            }

            List<Integer> prefs = ballot.getPreferences();
            if (preferenceLevel >= prefs.size()) continue; // Skip if preference level exceeds ballot length

            if (!ballot.validBallot()){
                take_ballot.add(ballot.getId());
                continue;
            }
            int candidateId = prefs.get(preferenceLevel);
            if (candidateId == 0){
                continue; // Skip if preference is invalid
            }

            // Find candidate object corresponding to candidateId
            Candidate candidate = null;
            for (Candidate c : candidates) {
                if (c.getId() == candidateId) {
                    candidate = c;
                    break;
                }
            }

            // Count vote if candidate is still in contention
            if (candidate != null && !gone.contains(candidate.getId()) && !results.getWinners().contains(candidate)) {
                candidate.addVote(); // Increment candidate vote
                take_ballot.add(ballot.getId()); // Mark this ballot as counted
                ballot_owner.computeIfAbsent(candidateId, k -> new ArrayList<>()).add(ballot.getId()); // Track which ballots voted for candidate

                // Record order for tiebreaking
                if (!orderMap.containsKey(candidateId)) {
                    orderMap.put(candidateId, id * (preferenceLevel + 1));
                }

                // If candidate reaches quota and hasn't already won, add to winners
                if (candidate.getVotes() >= droop && !results.getWinners().contains(candidate)) {
                    results.addWinner(candidate);
                    gone.add(candidate.getId()); // Remove from contention
                }
            }

            // Stop counting if all seats have winners
            if (results.getWinners().size() == seats){
                break;
            }
            id++;
        }

        // Debug print: show votes for this preference level
        System.out.println("Preference Level " + preferenceLevel);
        for (Candidate c : candidates) {
            System.out.println(c.getName() + ": " + c.getVotes() + " votes");
        }

        return orderMap; // Return updated order map for tie-breaking
    }

//    @Override
    public void getQuota() {
        int totalVotes = ballots.size(); // Total number of ballots cast
        droop = (totalVotes / (seats + 1)) + 1; // Droop quota formula
        System.out.println("Droop quota: " + droop);
    }

    // Resolves ties when multiple candidates have the same minimum votes
    public Candidate tieBreaker(List<Candidate> tiedCandidates, HashMap<Integer, Integer> orderMap) {
        Candidate firstCandidate = tiedCandidates.get(0);

        if (firstCandidate.getVotes() == 0) {
            // If all tied candidates have 0 votes, pick one randomly
            return tiedCandidates.get(rand.nextInt(tiedCandidates.size()));
        } else {
            // Otherwise pick the candidate with the highest order value
            Candidate loser = firstCandidate;
            int maxOrder = -1;
            for (Candidate c : tiedCandidates) {
                int order = orderMap.getOrDefault(c.getId(), 0);
                if (order > maxOrder) {
                    maxOrder = order;
                    loser = c;
                }
            }
            return loser;
        }
    }
}