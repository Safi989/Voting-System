import java.util.*;
import java.io.FileWriter;
import java.io.IOException;

public class Ballot {
    private int id;
    private List<Integer> preferences;

    public Ballot(int id, List<Integer> preferences) {
        this.id = id;
        this.preferences = preferences;
    }

    public List<Integer> getPreferences() {
        return preferences;
    }

    public int getId() {
        return id;
    }

    public boolean validBallot() {
        int count = 0;
        for (int num : preferences) {
            if (num != 0) {
                count++;
            }
        }
        double ratio = (double) count / preferences.size();
        if (ratio < 0.6) {
            invalidBallot(preferences);
            return false;
        } else {
            return true;
        }
    }

    public void invalidBallot(List<Integer> prefs) {
        String filename = "InValidBallots.txt";

        try (FileWriter writer = new FileWriter(filename)) {
            StringBuilder report = new StringBuilder();

            report.append("Invalid Ballot Detected\n");
            report.append("Ballot ID: ").append(this.id).append("\n");
            report.append("Preferences: ").append(prefs.toString()).append("\n");
            report.append("-----------------------------\n");

            writer.write(report.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}