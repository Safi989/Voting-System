import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Input {
        private int numSeats;
        private boolean shuffle;
        private int STV_Plu;
        private boolean helpWindow;

        // constructor
        public Input(String filename) throws FileNotFoundException {
            readFromFile(filename);
        }

        private void readFromFile(String filename) throws FileNotFoundException {
            // reads in the file
            Scanner scan = new Scanner(new File(filename));

            // looks at the first line and decides which election based on what is there
            String type = scan.nextLine();
            switch (type) {
                case "STV" -> STV_Plu = 1;
                case "PV" -> STV_Plu = 2;
                case "MV" -> STV_Plu = 3;
            }

            // reads next line and gets the number of seats
            numSeats = Integer.parseInt(scan.nextLine());

            scan.close();
        }



        // Getters
        public int getNumSeats() {
                return numSeats;
        }

        public int getSTV_Plu() {
                return STV_Plu;
        }

        public boolean isHelpWindow() {
                return helpWindow;
        }

        public boolean isShuffle() {
                return shuffle;
        }
}

