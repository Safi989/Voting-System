import java.util.*;

public class Plurality extends Election {
    // SDD attributes
    private int[] voteTotals;
    private List<Integer> winnerList;
    private List<Integer> tiedCandidates;
    public Results results;
    private int seats;
    private Random rand;
    private Date date;
    private List<Candidate> ties;

    public Plurality(String type, List<Candidate> candidates, Date date, int seats) {
        // Plurality itself is not a real voter, so give it a dummy ballot
        super(type, candidates);
        this.rand = new Random();
        this.seats = seats;
        this.date = date;

    }

    // countVotes(): iterates through all ballots and adds one vote to the selected candidate.
    public void countVotes() {
        System.out.println("Size of ballots:" + ballots.size());
        for (Ballot ballot : ballots) {
            System.out.println("Ballot ID: " + ballot.getId() + ", Preferences: " + ballot.getPreferences());
            List<Integer> prefs = ballot.getPreferences();
            int candidateId = prefs.indexOf(1) + 1;
            Candidate candidate = null;
            for (Candidate c : candidates) {
                if (c.getId() == candidateId) {
                    candidate = c;
                    break;
                }
            }
            if (candidate == null) {
                continue;
            }
            candidate.addVote();
        }
        determineWinners();
    }

    public void distributeBallots() {
        this.results = new Results(type, date, candidates, seats, "Plurality", ballots.size());
        countVotes();
        results.printReport();
    }

    // tiebreaker(): randomly selects a winner among tied candidates if necessary.
    public List<Candidate> tiebreaker(List<Candidate> tiedCandidates, int seatsAvailable) {
        List<Candidate> copy = new ArrayList<>(tiedCandidates);
        Collections.shuffle(copy, new Random());
        return copy.subList(0, seatsAvailable);
    }

    // determineWinners(): identifies top candidates according to the number of seats available.
    public void determineWinners() {
        // Step 1: Build list of [candidateId, voteCount]
        List<int[]> candidateVotes = new ArrayList<>();
        for (Candidate c : candidates) {
            candidateVotes.add(new int[]{c.getId(), c.getVotes()});
        }

        // Step 2: Sort descending by votes
        candidateVotes.sort((a, b) -> Integer.compare(b[1], a[1]));


        int seatsFilled = 0;

        int cutoffVotes = candidateVotes.get(seats - 1)[1];

        // Separate into groups
        List<Candidate> guaranteedWinners = new ArrayList<>();
        List<Candidate> tieGroup = new ArrayList<>();
        List<Candidate> guaranteedLosers = new ArrayList<>();

        for (int[] cv : candidateVotes) {
            Candidate c = getCandidateById(cv[0]);
            if (c.getVotes() > cutoffVotes) {
                guaranteedWinners.add(c);
            } else if (c.getVotes() == cutoffVotes) {
                tieGroup.add(c);
            } else {
                guaranteedLosers.add(c);
            }
        }

        int remainingSeats = seats - guaranteedWinners.size();

        List<Candidate> tieWinners;
        List<Candidate> tieLosers = new ArrayList<>();

        if (tieGroup.size() > remainingSeats) {
            // Use the tiebreaker function here!
            tieWinners = tiebreaker(tieGroup, remainingSeats);

            // Losers are those not in tieWinners
            for (Candidate c : tieGroup) {
                if (!tieWinners.contains(c)) {
                    tieLosers.add(c);
                }
            }
        } else {
            // All tie candidates fit
            tieWinners = new ArrayList<>(tieGroup);
        }

        // Final lists
        for (Candidate c : guaranteedWinners) results.addWinner(c);
        for (Candidate c : tieWinners) results.addWinner(c);
        for (Candidate c : tieLosers) results.addLoser(c);
        for (Candidate c : guaranteedLosers) results.addLoser(c);

    }
    // Helper method
    private Candidate getCandidateById(int id) {
        for (Candidate c : candidates) {
            if (c.getId() == id) return c;
        }
        return null;
    }
}