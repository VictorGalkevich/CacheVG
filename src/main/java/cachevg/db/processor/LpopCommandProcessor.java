package cachevg.db.processor;

import cachevg.db.storage.InitialStorage;
import cachevg.db.types.AbstractValue;
import cachevg.db.types.ConnectedList;

public class LpopCommandProcessor implements Processor {
    @Override
    public String process(String[] args) {
        if (args.length < 2) {
            String internal = String.join(" ", args);
            return "Command format must be: LPOP [key], instead got: %s".formatted(internal);
        }
        AbstractValue<?> obj = InitialStorage.instance().getObj(args[1]);
        if (!(obj instanceof ConnectedList) || obj.getValue() == null) {
            return "No value persistent by key: %s".formatted(args[0]);
        }
        return ((ConnectedList) obj).leftPop();
    }
}
