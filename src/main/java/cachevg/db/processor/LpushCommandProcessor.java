package cachevg.db.processor;

import cachevg.db.storage.InitialStorage;
import cachevg.db.types.AbstractValue;
import cachevg.db.types.ConnectedList;

public class LpushCommandProcessor implements Processor {
    @Override
    public String process(String[] args) {
        if (args.length < 2) {
            String internal = String.join(" ", args);
            return "Command format must be: LPUSH [key] [value], instead got: %s".formatted(internal);
        }
        AbstractValue<?> obj = InitialStorage.instance().getObj(args[1]);
        if (obj == null) {
            return InitialStorage.instance().putList(args[1], args[2]);
        } else if (!(obj instanceof ConnectedList) || obj.getValue() == null) {
            return "No value persistent by key: %s".formatted(args[0]);
        }
        return String.valueOf(((ConnectedList) obj).leftPush(args[2]));
    }
}
