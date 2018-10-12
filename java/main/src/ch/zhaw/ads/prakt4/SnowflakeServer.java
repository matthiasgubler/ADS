package ch.zhaw.ads.prakt4;

import ch.zhaw.ads.CommandExecutor;

public class SnowflakeServer implements CommandExecutor {
    private Turtle turtle;
    @Override
    public String execute(String command) throws Exception {
        int depth = Integer.parseInt(command);
        if (depth < 0) {
            throw new IllegalArgumentException("depth cant be smaller than 0");
        }
        turtle = new Turtle(0.1,0.7);
        schneeflocke(Integer.parseInt(command), 0.5);
        turtle.turn(-120);
        schneeflocke(Integer.parseInt(command), 0.5);
        turtle.turn(-120);
        schneeflocke(Integer.parseInt(command), 0.5);
        return turtle.getTrace();
    }

    void schneeflocke(int stufe, double dist) {
        if (stufe == 0) {
            turtle.move(dist);
        } else {
            stufe--;
            dist = dist / 3;
            schneeflocke(stufe, dist);
            turtle.turn(60);
            schneeflocke(stufe, dist);
            turtle.turn(-120);
            schneeflocke(stufe, dist);
            turtle.turn(60);
            schneeflocke(stufe, dist);
        }
    }

}
