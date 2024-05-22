package cachevg.db.processor;

import cachevg.db.storage.InitialStorage;
import cachevg.db.types.AbstractValue;
import cachevg.db.types.IntegerValue;

public class IncrbyCommandProcessor implements Processor {
    @Override
    public String process(String[] args) {
        if (args.length < 3) {
            String internal = String.join(" ", args);
            return "Command format must be: INCRBY [key] [value], instead got: %s".formatted(internal);
        }
        AbstractValue<?> obj = InitialStorage.instance().getObj(args[1]);
        if (!(obj instanceof IntegerValue) || obj.getValue() == null) {
            return "No value persistent by key: %s".formatted(args[0]);
        }
        int val;
        try {
            val = Integer.parseInt(args[2]);
            if (val < 0) {
                throw new Exception();
            }
        } catch (Exception e) {
            return "[value] might be a int64_t value";
        }
        return String.valueOf(((IntegerValue) obj).incrementByValue(val));
    }
}
