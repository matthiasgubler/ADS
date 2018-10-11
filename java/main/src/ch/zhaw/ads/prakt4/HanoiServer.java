package ch.zhaw.ads.prakt4;

import ch.zhaw.ads.CommandExecutor;

public class HanoiServer implements CommandExecutor {
    @Override
    public String execute(String command) throws Exception {
        StringBuilder allHints = new StringBuilder();
        move(Integer.parseInt(command), "A", "B", "C", allHints);
        return allHints.toString();
    }

    void move (int n, String from, String to, String help, StringBuilder allHints) {
        if (n > 0) {
            move(n-1,from,help,to, allHints);
            addHint(from, to, allHints);
            move(n-1,help,to,from, allHints);
        }
    }

    void addHint(String from , String to, StringBuilder allHints) {
        allHints.append("move " + from + " to " + to + "\n");
    }
}
