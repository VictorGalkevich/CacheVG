package cachevg.db.processor;

import cachevg.db.storage.InitialStorage;
import cachevg.db.types.AbstractValue;
import cachevg.db.types.DefaultString;
import cachevg.db.types.IntegerValue;
import cachevg.db.types.NumericOperations;

public class FlipCommandProcessor implements Processor {
    @Override
    public String process(String[] args) {
        if (args.length < 3) {
            String internal = String.join(" ", args);
            return "Command format must be: FLIP [key] [index], instead got: %s".formatted(internal);
        }
        AbstractValue<?> obj = InitialStorage.instance().getObj(args[1]);
        if (!(obj instanceof DefaultString) || obj.getValue() == null) {
            return "No value persistent by key: %s".formatted(args[0]);
        }
        int val = 0;
        try {
            val = Integer.parseInt(args[2]);
            if (val < 0) {
                throw new Exception();
            }
        } catch (Exception e) {
            return "[index] might be a uint32_t value";
        }
        return ((DefaultString) obj).flip(val);
    }
}
