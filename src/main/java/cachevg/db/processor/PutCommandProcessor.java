package cachevg.db.processor;

import cachevg.db.storage.InitialStorage;

public class PutCommandProcessor implements Processor {
    @Override
    public String process(String[] args) {
        if (args.length < 3) {
            String internal = String.join(" ", args);
            return "Command format must be: PUT [key] [value], instead got: %s".formatted(internal);
        }
        return InitialStorage.instance().put(args[1], args[2]);
    }
}
