package cachevg.db.processor;

import cachevg.db.storage.InitialStorage;
import cachevg.db.types.AbstractValue;
import cachevg.db.types.DefaultString;

public class AppendCommandProcessor implements Processor {
    @Override
    public String process(String[] args) {
        if (args.length < 3) {
            String internal = String.join(" ", args);
            return "Command format must be: APPEND [key] [value], instead got: %s".formatted(internal);
        }
        AbstractValue<?> obj = InitialStorage.instance().getObj(args[1]);
        if (!(obj instanceof DefaultString) || obj.getValue() == null) {
            return "No value persistent by key: %s".formatted(args[0]);
        }
        return ((DefaultString) obj).append(args[2]);
    }
}
