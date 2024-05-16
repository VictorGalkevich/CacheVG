package cachevg.connector.tcp.parser;

public interface Parser <F, T> {
    T parse(F from);
}
