package ch.zhaw.ads.prakt1;

import ch.zhaw.ads.CommandExecutor;

import static ch.zhaw.ads.prakt1.GgT.ggt;

public class KgvServer implements CommandExecutor {
    @Override
    public String execute(String command) throws Exception {
        String[] numbers = command.split(" ");
        int a = Integer.parseInt(numbers[0]);
        int b = Integer.parseInt(numbers[1]);
        return Integer.toString(kgv(a, b));
    }

    public int kgv(int m, int n) {
        return Math.abs(m * n) / ggt(m, n);
    }

}
