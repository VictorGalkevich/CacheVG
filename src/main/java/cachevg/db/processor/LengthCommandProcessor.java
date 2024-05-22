package cachevg.db.processor;

import cachevg.db.storage.InitialStorage;

public class LengthCommandProcessor implements Processor {
    @Override
    public String process(String[] args) {
        if (args.length < 2) {
            String internal = String.join(" ", args);
            return "Command format must be: LENGTH [key], instead got: %s".formatted(internal);
        }
        String retrieved = InitialStorage.instance().get(args[1]);
        return retrieved == null ? "nil" : String.valueOf(retrieved.length());
    }
}
