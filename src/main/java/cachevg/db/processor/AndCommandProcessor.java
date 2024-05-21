package cachevg.db.processor;

import static cachevg.db.processor.OrCommandProcessor.processFiltered;

public class AndCommandProcessor implements Processor {
    @Override
    public String process(String[] args) {
        if (args.length < 3) {
            String internal = String.join(" ", args);
            return "Command format must be: [key1] AND [key2], instead got: %s".formatted(internal);
        }
        return processFiltered(args);
    }
}
