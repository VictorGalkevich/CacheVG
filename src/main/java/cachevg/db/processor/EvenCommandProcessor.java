package cachevg.db.processor;

import cachevg.db.storage.InitialStorage;
import cachevg.db.types.AbstractValue;
import cachevg.db.types.IntegerValue;
import cachevg.db.types.NumericOperations;

public class EvenCommandProcessor implements Processor {
    @Override
    public String process(String[] args) {
        if (args.length < 2) {
            String internal = String.join(" ", args);
            return "Command format must be: EVEN [key], instead got: %s".formatted(internal);
        }
        AbstractValue<?> obj = InitialStorage.instance().getObj(args[1]);
        if (!(obj instanceof NumericOperations) || obj.getValue() == null) {
            return "No value persistent by key: %s".formatted(args[0]);
        }
        return ((IntegerValue) obj).isEven() ? "TRUE" : "FALSE";
    }
}
