package cachevg.db.processor;

public class UnknownCommandProcessor implements Processor {
    @Override
    public String process(String[] args) {
        if (args.length < 1) {
            return "Empty input";
        }
        return "Command " + args[0] + " is not supported";
    }
}
