import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public abstract class Election {
    protected String type;
    protected List<Candidate> candidates;
    protected List<Ballot> ballots;
    protected boolean shuffleEnabled;

    public Election(String type, List<Candidate> candidates) {
        this.type = type;
        this.candidates = candidates;
        this.ballots = new ArrayList<>();
        this.shuffleEnabled = true;
    }


    public void addBallot(Ballot ballot) {
        ballots.add(ballot);
    }
    public void loadBallotsFromCSV(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {

            // skips the header information
            for (int i = 0; i < 3; i++) {
                br.readLine();
            }
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("Test_Case") || line.startsWith("[")) continue; // skip headers

                // Example line: Ballot(1,(1, 2, 3, 4))
                line = line.replace("Ballot(", "").replace(")", ""); // → "1,(1, 2, 3, 4"
                String[] parts = line.split(",", 2); // split into ID and list part
                int id = Integer.parseInt(parts[0].trim());

                // clean list part
                String listStr = parts[1].replace("(", "").replace(")", "").trim();
                String[] numbers = listStr.split("[, ]+");
                List<Integer> prefs = new ArrayList<>();
                for (String num : numbers) {
                    if (!num.isBlank())
                        prefs.add(Integer.parseInt(num));
                }

                // Create and add the ballot
                this.addBallot(new Ballot(id, prefs));
            }
        } catch (IOException e) {
            System.err.println("Error reading ballots from file: " + e.getMessage());
        }
    }

    // Abstract methods — must be implemented by subclasses
    public abstract void distributeBallots();

//    public abstract void getQuota();
//    public abstract Candidate tieBreaker();

    public void shuffle() {
        if (shuffleEnabled) Collections.shuffle(ballots);
    }
}

