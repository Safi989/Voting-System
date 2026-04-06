/**
 * File name: Input.java
 * This class is responsible for gathering all the user inputs needed to run the elections.
 * The values needed are the number of seats, election type, if shuffling should be enabled,
 * and if the user would like to see a help window
 *
 * Author: Surafiel Ghirmai
 */
import java.util.Scanner;

public class Input {
    // Variables
    private int num_seats;

    private boolean shuffle;

    private int STV_Plu;

    private boolean helpWindow;

    /**
     * Constructs the Inputs
     */
    public Input();

    /**
     * Gets the file name
     *
     * @return nothing
     * @throws None
     */
    private void readFromFile();

    /**
     * Get the number of seats needed to finish the election
     * @return number of seats needed to finish election
     * @throws None
     */
    public int getNum_seats();

    /**
     * Gets the type of election
     * @return Either STV or Plurality
     * @throws None
     */
    public int getSTV_Plu();

    /**
     * Gets the answer about if the user wants a help window
     * @return True or False
     * @throws None
     */
    public boolean isHelpWindow();

    /**
     * Gets the answer about if the user wants shuffling on or off
     * @return True or False
     * @throws None
     */
    public boolean isShuffle();

}
