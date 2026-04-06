import com.sun.source.tree.UsesTree;

import java.util.*;

public class Municipal extends Election {
    public Results results;
    private int seats;
    private Map<Candidate, Integer> votes;
    private Random rand;

    public Municipal(String type, List<Candidate> candidates, int seats, Date date) {
        super(type, candidates);
        this.seats = seats;
        this.votes = new HashMap<>();
        this.rand = new Random();
        this.results = new Results(type, date, candidates, seats, "MV",0);
    }

    // Sorts the candidates by amount of votes
    private List<Candidate> sortCandidtatesByVote(Map<Candidate, Integer> votes) {
        List<Candidate> sorted = new ArrayList<>(candidates);

        // compares two candidates by the amount of votes each one got and orders them
        for (int i = 0; i < sorted.size() - 1; i++) {
            for (int j = 0; j < sorted.size() - 1 - i; j++) {
                int votes1 = votes.get(sorted.get(j));
                int votes2 = votes.get(sorted.get(j + 1));

                if (votes1 < votes2) {
                    Candidate temp = sorted.get(j);
                    sorted.set(j, sorted.get(j + 1));
                    sorted.set(j + 1, temp);
                }
            }
        }
        return sorted;
    }


    @Override
    public void distributeBallots() {
        // Set every candidates vote count to 0
        for (Candidate c : candidates) {
            votes.put(c, 0);
        }

        // Collecting votes for each candidate
        for (Ballot b : ballots) {
            List<Integer> choice = b.getPreferences();

            for (int j = 0; j < choice.size() && j < candidates.size(); j++) {
                if (choice.get(j) == 1) {
                    Candidate c = candidates.get(j);
                    votes.put(c, votes.get(c) + 1);
                }
            }
        }

        List<Candidate> sortedCand = sortCandidtatesByVote(votes);

        int seatsLeft = seats;
        int index = 0;
        while (seatsLeft > 0 && index < sortedCand.size()) {
            int currVote = votes.get(sortedCand.get(index));

            // finds all the tied candidates
            List<Candidate> ties = new ArrayList<>();
            while (index < sortedCand.size() && votes.get(sortedCand.get(index)) == currVote) {
                ties.add(sortedCand.get(index));
                index++;
            }

            // adds winners to the winners list
            if (ties.size() <= seatsLeft) {
                for (Candidate c : ties) {
                    results.addWinner(c);
                }
                seatsLeft -= ties.size();
            }
            else {
                Collections.shuffle(ties, rand);
                for (int i = 0; i < seatsLeft; i++) {
                    results.addWinner(ties.get(i));
                }
                seatsLeft = 0;
            }
        }
        // adds losers to the losers list
        for (Candidate c : candidates){
            if (!results.getWinners().contains(c)){
                results.addLoser(c);
            }
        }

        results.printReport();
    }

}
