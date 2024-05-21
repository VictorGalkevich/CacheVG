package cachevg.db.processor;

import static cachevg.db.processor.OrCommandProcessor.processFiltered;

public class XorCommandProcessor implements Processor {
    @Override
    public String process(String[] args) {
        if (args.length < 3) {
            String internal = String.join(" ", args);
            return "Command format must be: [key1] XOR [key2], instead got: %s".formatted(internal);
        }
        return processFiltered(args);
    }
}
