package ch.zhaw.ads.prakt1;

import ch.zhaw.ads.CommandExecutor;

import static ch.zhaw.ads.prakt1.BracketResolver.OPENING_BRACKETS;
import static ch.zhaw.ads.prakt1.BracketResolver.isCorrespondingBracket;

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
            char nextValue = bracketResolver.nextBracket();
            String nextValueString = nextValue + "";
            if (OPENING_BRACKETS.contains(nextValueString)) {
                stack.push(nextValue);
            } else {
                if(!stack.isEmpty() && isCorrespondingBracket((Character)stack.peek(), nextValue)){
                    stack.pop();
                }else{
                    return false;
                }
            }

        }


        return stack.isEmpty();
    }


}
