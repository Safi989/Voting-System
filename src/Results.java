import java.util.*;
import java.io.FileWriter;
import java.io.IOException;
public class Results {
    private String electionName;
    private Date electionDate;
    private List<Candidate> winners;
    private List<Candidate> losers;
    private List<Candidate> allCandidates;
    private int numSeats;
    private String votetype;
    private int tot_votes;

    public Results(String electionName, Date electionDate, List<Candidate> allCandidates, int numSeats, String votetype, int tot_votes) {
        this.electionName = electionName;
        this.electionDate = electionDate;
        this.allCandidates = allCandidates;
        this.numSeats = numSeats;
        this.winners = new ArrayList<>();
        this.losers = new ArrayList<>();
        this.votetype = votetype;
        this.tot_votes = tot_votes;
    }

    public void addWinner(Candidate c) {
        if (!winners.contains(c)) winners.add(c);
    }

    public void addLoser(Candidate c) {
        if (!losers.contains(c)) losers.add(c);
    }

//  Calculate how many winners are still needed
//   Traverse the losers list backward
//   Move enough candidates from losers to winners
    public void finalizeResults() {
        // Fill remaining seats from losers if not enough winners
        if (votetype == "STV"){
            int needed = numSeats - winners.size();
            if (needed > 0) {
                ListIterator<Candidate> it = losers.listIterator(losers.size());
                while (it.hasPrevious() && needed > 0) {
                    Candidate promoted = it.previous();
                    winners.add(promoted);
                    it.remove();
                    needed--;
                }
            }
        }
    }
    public void printReport() {
        finalizeResults();
        String filename = "Election_Report.txt";

        try (FileWriter writer = new FileWriter(filename)) {
            StringBuilder report = new StringBuilder();

            report.append("========== Election Results ==========\n");
            report.append("Election Date: ").append(electionDate).append("\n");
            report.append("Election Type: ").append(votetype).append("\n");
            report.append("Total Candidates: ").append(allCandidates.size()).append("\n");
            report.append("Seats to Fill: ").append(numSeats).append("\n\n");

            report.append("Candidates:\n");
            for (Candidate c : allCandidates) {
                report.append(" - ").append(c.getName()).append("\n");
            }
            if (votetype.equals("STV") || votetype.equals("MV")) {
                report.append("\nElected:\n");
                for (Candidate c : winners) {
                    report.append(" * ").append(c.getName()).append("\n");
                }

                report.append("\nNon-Elected:\n");
                for (Candidate c : losers) {
                    report.append(" - ").append(c.getName()).append("\n");
                }
            }
            else{

                System.out.println("these are ballots:" + tot_votes);
                report.append("\nElected:\n");
                for (Candidate c : winners) {
                    double pct = (c.getVotes() * 100.0) / tot_votes;
                    report.append(" * ")
                            .append(c.getName())
                            .append(" (Votes: ")
                            .append(String.format("%.2f%%", pct))
                            .append(")\n");
                }

                report.append("\nNon-Elected:\n");
                for (Candidate c : losers) {
                    double pct = (c.getVotes() * 100.0) / tot_votes;
                    report.append(" * ")
                            .append(c.getName())
                            .append(" (Votes: ")
                            .append(String.format("%.2f%%", pct))
                            .append(")\n");
                }

            }

            report.append("==========================================\n");

            // Print to console
            System.out.println(report.toString());

            // Write to file
            writer.write(report.toString());
            System.out.println("Report successfully written to " + filename);

        } catch (IOException e) {
            System.err.println("Error writing report file: " + e.getMessage());
        }
    }

    public List<Candidate> getWinners() { return winners; }
    public List<Candidate> getLosers() { return losers; }
}
