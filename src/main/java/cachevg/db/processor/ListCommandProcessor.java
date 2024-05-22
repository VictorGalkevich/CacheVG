package cachevg.db.processor;

import cachevg.db.storage.InitialStorage;
import cachevg.db.types.AbstractValue;
import cachevg.db.types.ConnectedList;

public class ListCommandProcessor implements Processor {
    @Override
    public String process(String[] args) {
        if (args.length < 2) {
            String internal = String.join(" ", args);
            return "Command format must be: LIST [key] [from] [to], instead got: %s".formatted(internal);
        }
        AbstractValue<?> obj = InitialStorage.instance().getObj(args[1]);
        if (!(obj instanceof ConnectedList) || obj.getValue() == null) {
            return "No value persistent by key: %s".formatted(args[0]);
        }
        int start;
        int end;
        try {
            start = Integer.parseInt(args[2]);
            end = Integer.parseInt(args[3]);
            if (start < 0
                || ((ConnectedList) obj).length() > start
                || ((end < 0
                     || ((ConnectedList) obj).length() > end) && end != -1)) {
                throw new Exception();
            }
            if (start > end) {
                return "Impossible range (from > to)";
            }
        } catch (Exception e) {
            return "[from] and [to] might be a uint32_t values (or either [to] can be a -1)";
        }
        return ((ConnectedList) obj).list(start, end);
    }
}
