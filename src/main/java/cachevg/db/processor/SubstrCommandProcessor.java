package cachevg.db.processor;

import cachevg.db.storage.InitialStorage;
import cachevg.db.types.AbstractValue;
import cachevg.db.types.DefaultString;

public class SubstrCommandProcessor implements Processor {
    @Override
    public String process(String[] args) {
        if (args.length < 4) {
            String internal = String.join(" ", args);
            return "Command format must be: GET [key] [start] [end], instead got: %s".formatted(internal);
        }
        AbstractValue<?> obj = InitialStorage.instance().getObj(args[1]);
        if (!(obj instanceof DefaultString) || obj.getValue() == null) {
            return "No value persistent by key: %s".formatted(args[0]);
        }
        int start;
        int end;
        try {
            start = Integer.parseInt(args[2]);
            end = Integer.parseInt(args[3]);
            if (start < 0
                || ((DefaultString) obj).length() > start
                || ((end < 0
                     || ((DefaultString) obj).length() > end) && end != -1)) {
                throw new Exception();
            }
            if (start > end) {
                return "Impossible range (start > end)";
            }
        } catch (Exception e) {
            return "[start] and [end] might be a uint32_t values (or either end can be a -1)";
        }
        return String.valueOf(((DefaultString) obj).substring(start, end));
    }
}
