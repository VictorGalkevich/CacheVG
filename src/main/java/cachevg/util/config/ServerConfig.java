package cachevg.util.config;

import cachevg.connector.tcp.server.NIOServer;
import cachevg.connector.tcp.server.Server;
import cachevg.connector.tcp.parser.AutomataParser;
import cachevg.connector.tcp.parser.MessageParser;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerConfig {
    private final ServerStartupProperties properties;

    public Server server() {
        var server = new NIOServer(properties.getPort());
        Runtime.getRuntime().addShutdownHook(new Thread(server::stop));
        return server;
    }

    public ExecutorService executorForProcessing() {
        var factory = Thread.ofPlatform().name("processor-", 0).factory();
        return Executors.newSingleThreadExecutor(factory);
    }

    public MessageParser parser() {
        return new AutomataParser();
    }

    public ServerConfig(ServerStartupProperties properties) {
        this.properties = properties;
    }
}
