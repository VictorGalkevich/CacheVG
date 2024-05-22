package cachevg.db.processor;

import cachevg.db.storage.InitialStorage;
import cachevg.db.types.AbstractValue;
import cachevg.db.types.DefaultString;

public class OrCommandProcessor implements Processor {
    @Override
    public String process(String[] args) {
        if (args.length < 3) {
            String internal = String.join(" ", args);
            return "Command format must be: [key1] OR [key2], instead got: %s".formatted(internal);
        }
        AbstractValue<?> obj = InitialStorage.instance().getObj(args[0]);
        if (!(obj instanceof DefaultString) || obj.getValue() == null) {
            return "No value persistent by key: %s".formatted(args[0]);
        }
        String second = InitialStorage.instance().get(args[2]);
        if (second == null) {
            return "No value persistent by key: %s".formatted(args[1]);
        }
        return ((DefaultString) obj).disjunction(second);
    }
}
