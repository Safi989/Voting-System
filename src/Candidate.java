public class Candidate {
    private String name;
    private int votes;
    private int id;

    public Candidate(String name, int id) {
        this.name = name;
        this.votes = 0;
        this.id = id;
    }

    public void addVote() {
        votes++;
    }

    public int getVotes() {
        return votes;
    }

    public String getName() {
        return name;
    }

    public int getId(){
        return id;
    }
}


