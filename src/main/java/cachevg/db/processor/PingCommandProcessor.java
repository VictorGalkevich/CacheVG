package cachevg.db.processor;

public class PingCommandProcessor implements Processor {
    @Override
    public String process(String[] args) {
        if (args.length < 1) {
            return "Empty input";
        }
        return "PONG";
    }
}
