package cachevg.db.processor;

import cachevg.db.storage.InitialStorage;
import cachevg.db.types.AbstractValue;
import cachevg.db.types.DefaultString;

public class SetrngCommandProcessor implements Processor {
    @Override
    public String process(String[] args) {
        if (args.length < 5) {
            String internal = String.join(" ", args);
            return "Command format must be: GET [key] [start] [replacement], instead got: %s".formatted(internal);
        }
        AbstractValue<?> obj = InitialStorage.instance().getObj(args[1]);
        if (!(obj instanceof DefaultString) || obj.getValue() == null) {
            return "No value persistent by key: %s".formatted(args[0]);
        }
        int start;
        try {
            start = Integer.parseInt(args[2]);
            if (start < 0 || ((DefaultString) obj).length() > start) {
                throw new Exception();
            }
        } catch (Exception e) {
            return "[start] might be a uint32_t value";
        }
        return String.valueOf(((DefaultString) obj).setRange(start, args[3]));
    }
}
