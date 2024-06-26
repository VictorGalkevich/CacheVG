package cachevg.util;

import cachevg.db.processor.CommandMapper;
import cachevg.util.config.ServerConfig;
import cachevg.util.config.ServerStartupProperties;
import cachevg.connector.tcp.server.Server;
import cachevg.connector.tcp.parser.MessageParser;
import cachevg.db.parser.YamlParser;
import cachevg.util.runner.ServerStarter;
import com.sun.management.OperatingSystemMXBean;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.management.ManagementFactory;
import java.util.concurrent.ExecutorService;

public class Gate {

    private static final Logger log = LogManager.getLogger(Gate.class);

    public static void main(String[] args) {
        OperatingSystemMXBean os = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();

        log.info("availableProcessors:{}", Runtime.getRuntime().availableProcessors());
        log.info("TotalMemorySize, mb:{}", os.getTotalMemorySize() / 1024 / 1024);
        log.info("maxMemory, mb:{}", Runtime.getRuntime().maxMemory() / 1024 / 1024);
        log.info("freeMemory, mb:{}", Runtime.getRuntime().freeMemory() / 1024 / 1024);

        String propsPath = System.getenv("PROPS_PATH");
        ServerStartupProperties properties = new YamlParser().parse(propsPath);
        ServerConfig config = new ServerConfig(properties);

        Server server = config.server();
        MessageParser parser = config.parser();
        ExecutorService executorService = config.executorForProcessing();

        ServerStarter serverStarter = new ServerStarter(executorService, server, parser);

        serverStarter.run();
    }
}
