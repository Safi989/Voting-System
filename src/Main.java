import java.io.*;
import java.util.*;


public class Main {
    public static List<Candidate> loadCandidatesFromCSV(String filename) {
        List<Candidate> candidates = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {

            // skips first two lines
            br.readLine();
            br.readLine();

            String firstLine = br.readLine();
            firstLine = firstLine.replace("[", "").replace("]", "");
            if (firstLine != null) {
                String[] names = firstLine.split(",");

                int id = 0;
                for (int i = 0; i < names.length; i+=2) {
                    String name = names[i].trim();
                    candidates.add(new Candidate(name, id + 1));
                    id++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return candidates;
    }
    public static void main(String[] args) throws FileNotFoundException {
            // get the filename
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the filename: ");
        String filename = scan.nextLine();
        String filepath = "test/" + filename;

        Input input = new Input(filepath);

        List<Candidate> candidates = loadCandidatesFromCSV(filepath);
        int numSeats = input.getNumSeats();
        boolean shuffleOption = input.isShuffle();
        int electionType = input.getSTV_Plu();
        Date electionDate = new Date();

        Election election;
        if (electionType == 1) {
            // STV election
            election = new STV("STV", candidates, electionDate, numSeats);
            election.loadBallotsFromCSV(filepath);
        } else if (electionType == 2){
            election = new Plurality("Plurality", candidates, electionDate, numSeats);
            election.loadBallotsFromCSV(filepath);
        } else {
            election = new Municipal("MV", candidates, numSeats, electionDate);
            election.loadBallotsFromCSV(filepath);
        }

        // Shuffle if user enabled
        if (shuffleOption) {
            election.shuffle();
        }

        // Run election
        election.distributeBallots();
    }
}
