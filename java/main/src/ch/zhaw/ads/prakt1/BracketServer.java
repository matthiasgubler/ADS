package ch.zhaw.ads.prakt1;

import ch.zhaw.ads.CommandExecutor;

import static ch.zhaw.ads.prakt1.BracketResolver.isCorrespondingBracket;
import static ch.zhaw.ads.prakt1.BracketResolver.isOpeningBracket;

public class BracketServer implements CommandExecutor {

    private static final int STACK_CAPACITY = 50;

    private Stack stack = new ArrayStack(STACK_CAPACITY);

    @Override
    public String execute(String command) throws Exception {
        return "checkBrackets(\n" + command + "\") = " + checkBrackets(command) + "\n";
    }

    public boolean checkBrackets(String toCheck) {
        stack.reset();
        BracketResolver bracketResolver = new BracketResolver(toCheck);

        while (!bracketResolver.everythingRead()) {
            String nextValue = bracketResolver.nextBracket();
            if (isOpeningBracket(nextValue)) {
                stack.push(nextValue);
            } else {
                if (!stack.isEmpty() && isCorrespondingBracket((String) stack.peek(), nextValue)) {
                    stack.pop();
                } else {
                    return false;
                }
            }

        }


        return stack.isEmpty();
    }


}
