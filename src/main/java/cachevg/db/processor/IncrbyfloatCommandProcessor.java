package cachevg.db.processor;

import cachevg.db.storage.InitialStorage;
import cachevg.db.types.AbstractValue;
import cachevg.db.types.FloatingPoint;

public class IncrbyfloatCommandProcessor implements Processor {
    @Override
    public String process(String[] args) {
        if (args.length < 3) {
            String internal = String.join(" ", args);
            return "Command format must be: INCRBYFLOAT [key] [value], instead got: %s".formatted(internal);
        }
        AbstractValue<?> obj = InitialStorage.instance().getObj(args[1]);
        if (!(obj instanceof FloatingPoint) || obj.getValue() == null) {
            return "No value persistent by key: %s".formatted(args[0]);
        }
        double val;
        try {
            val = Integer.parseInt(args[2]);
            if (val < 0) {
                throw new Exception();
            }
        } catch (Exception e) {
            return "[value] might be a float64_t value";
        }
        return String.valueOf(((FloatingPoint) obj).incrementByValue(val));
    }
}
