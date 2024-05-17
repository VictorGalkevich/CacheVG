package cachevg.db.processor;

import cachevg.db.command.CommandNames;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import static cachevg.db.command.CommandNames.*;

public class CommandMapper {
    private static final Map<CommandNames, Processor> processors = new HashMap<>();

    {
        CommandNames[] values = values();
        try {
            for (CommandNames value : values) {
                String cmdUpper = value.name().toLowerCase();
                String cmdCamel = cmdUpper.substring(0, 1).toUpperCase() + cmdUpper.substring(1).toLowerCase();
                String className = this.getClass().getPackageName() + "." + cmdCamel + "CommandProcessor";
                Constructor<?>[] pcp = Class.forName(className).getConstructors();
                Processor o = (Processor) pcp[0].newInstance();
                processors.put(value, o);
            }
        } catch (ClassNotFoundException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static Processor mapCommandToProcessor(String name) {
        Processor processor = processors.get(CommandNames.valueOf(name.toUpperCase()));
        return processor == null ? new UnknownCommandProcessor() : processor;
    }

}
