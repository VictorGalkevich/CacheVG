package cachevg.connection.tcp.parser;

public interface Parser <F, T> {
    T parse(F from);
}
