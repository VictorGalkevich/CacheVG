package cachevg.db.processor;

import cachevg.db.storage.InitialStorage;
import cachevg.db.types.AbstractValue;
import cachevg.db.types.DefaultString;

public class SbitCommandProcessor implements Processor {
    @Override
    public String process(String[] args) {
        if (args.length < 3) {
            String internal = String.join(" ", args);
            return "Command format must be: SET [key] [index] [newBit], instead got: %s".formatted(internal);
        }
        AbstractValue<?> obj = InitialStorage.instance().getObj(args[1]);
        if (!(obj instanceof DefaultString) || obj.getValue() == null) {
            return "No value persistent by key: %s".formatted(args[0]);
        }
        int index;
        int val;
        try {
            index = Integer.parseInt(args[2]);
            val = Integer.parseInt(args[3]);
            if (index < 0) {
                throw new Exception();
            }
            if (val != 0 && val != 1) {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e) {
            return "[newBit] might be either 1 or 0";
        } catch (Exception e) {
            return "[index] might be a uint32_t value";
        }
        return String.valueOf(((DefaultString) obj).setBit(index, val == 0 ? '0' : '1'));
    }
}
