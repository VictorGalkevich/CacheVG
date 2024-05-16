package cachevg.db.processor;

import cachevg.db.storage.InitialStorage;

public class RemoveCommandProcessor implements Processor {
    @Override
    public String process(String[] args) {
        if (args.length < 2) {
            String internal = String.join(" ", args);
            return "Command format must be: REMOVE [key], instead got: %s".formatted(internal);
        }
        String removed = InitialStorage.instance().remove(args[1]);
        return removed == null ? "nil" : removed;
    }
}
