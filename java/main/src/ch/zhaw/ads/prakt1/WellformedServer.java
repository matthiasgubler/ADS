package ch.zhaw.ads.prakt1;

import ch.zhaw.ads.CommandExecutor;

import static ch.zhaw.ads.prakt1.BracketResolver.OPENING_BRACKETS;
import static ch.zhaw.ads.prakt1.BracketResolver.isCorrespondingBracket;
import static ch.zhaw.ads.prakt1.XMLTagResolver.isCorrespondingTag;
import static ch.zhaw.ads.prakt1.XMLTagResolver.isOpeningTag;

public class WellformedServer implements CommandExecutor {

    private static final int STACK_CAPACITY = 50;

    private Stack stack = new ArrayStack(STACK_CAPACITY);

    @Override
    public String execute(String command) throws Exception {
        return "checkWellformed(\n" + command + "\") = " + checkWellformed(command) + "\n";
    }

    public boolean checkWellformed(String toCheck) {
        stack.reset();
        XMLTagResolver xmlTagResolver = new XMLTagResolver(toCheck);

        while (!xmlTagResolver.everythingRead()) {
            String nextToken = xmlTagResolver.nextToken();
            if (isOpeningTag(nextToken)) {
                stack.push(nextToken);
            } else {
                if (isCorrespondingTag((String) stack.peek(), nextToken)) {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }


}
