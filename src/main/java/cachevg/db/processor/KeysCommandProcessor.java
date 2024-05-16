package cachevg.db.processor;

import cachevg.db.storage.InitialStorage;

import java.util.List;

public class KeysCommandProcessor implements Processor {
    @Override
    public String process(String[] args) {
        if (args.length < 1) {
            String internal = String.join(" ", args);
            return "Command format must be: KEYS ([pattern]), instead got: %s".formatted(internal);
        }
        List<String> keys = InitialStorage.instance().keys();
        StringBuilder sb = new StringBuilder();
        int counter = 0;
        String regex = ".*";
        if (args.length == 2) {
            regex = args[1].replaceAll("[*]", ".+");
        }
        for (String key : keys) {
            if (key.matches(regex)) {
                sb.append("%d) %s".formatted(++counter, key)).append("\n");
            }
        }
        return sb.toString();
    }
}
