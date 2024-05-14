package cachevg.types;

import java.time.OffsetDateTime;

public class DefaultString extends AbstractValue<String>{
    public DefaultString(String value, OffsetDateTime ttl, OffsetDateTime createdAt) {
        super(value, ttl, createdAt);
    }

    public int length() {
        return this.value.length();
    }

    public String substring(int from, int to) {
        if (to == -1) {
            return this.value.substring(from);
        }
        return this.value.substring(from, to + 1);
    }

    public String setRange(int from, String value) {
        return this.value.substring(0, from) + value;
    }

    public String append(String str) {
        return this.value.concat(str);
    }

    public String prepend(String str) {
        return str.concat(this.value);
    }

    public String reverse() {
        return new StringBuilder(this.value).reverse().toString();
    }

    //BitMap operations

    public int countZeroes() {
        byte[] bytes = this.value.getBytes();
        int count = 0;
        for (byte b : bytes) {
            for (int i = 0; i < 8; i++) {
                if ((b & (1 << i)) == 0) {
                    count++;
                }
            }
        }
        return count;
    }

    public int countOnes() {
        byte[] bytes = this.value.getBytes();
        int count = 0;
        for (byte b : bytes) {
            for (int i = 0; i < 8; i++) {
                if ((b & (1 << i)) != 0) {
                    count++;
                }
            }
        }
        return count;
    }

    public String setBit(int index, char bit) {
        byte[] bytes = this.value.getBytes();
        int byteIndex = index / 8;
        int bitIndex = 7 - index % 8;
        if (bit == '1') {
            bytes[byteIndex] = (byte) (bytes[byteIndex] | (1 << bitIndex));
        } else {
            bytes[byteIndex] = (byte) (bytes[byteIndex] & ~(1 << bitIndex));
        }
        this.value = new String(bytes);
        return this.value;
    }

    public char getBit(int index) {
        byte[] bytes = this.value.getBytes();
        int byteIndex = index / 8;
        int bitIndex = 7 - index % 8;
        return (bytes[byteIndex] & (1 << bitIndex)) != 0 ? '1' : '0';
    }

    public String flip(int index) {
        byte[] bytes = this.value.getBytes();
        int byteIndex = index / 8;
        int bitIndex = 7 - index % 8;
        bytes[byteIndex] = (byte) (bytes[byteIndex] ^ (1 << bitIndex));
        this.value = new String(bytes);
        return this.value;
    }

    public String not() {
        byte[] bytes = this.value.getBytes();
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) ~bytes[i];
        }
        this.value = new String(bytes);
        return this.value;
    }

    public String conjunction(String other) {
        byte[] bytes = this.value.getBytes();
        byte[] otherBytes = other.getBytes();
        byte[] result = new byte[bytes.length];
        for (int i = 0; i < bytes.length; i++) {
            result[i] = (byte) (bytes[i] & otherBytes[i]);
        }
        return new String(result);
    }

    public String disjunction(String other) {
        byte[] bytes = this.value.getBytes();
        byte[] otherBytes = other.getBytes();
        byte[] result = new byte[bytes.length];
        for (int i = 0; i < bytes.length; i++) {
            result[i] = (byte) (bytes[i] | otherBytes[i]);
        }
        return new String(result);
    }

    public String xor(String other) {
        byte[] bytes = this.value.getBytes();
        byte[] otherBytes = other.getBytes();
        byte[] result = new byte[bytes.length];
        for (int i = 0; i < bytes.length; i++) {
            result[i] = (byte) (bytes[i] ^ otherBytes[i]);
        }
        return new String(result);
    }

    public String shiftLeft(int shift) {
        byte[] bytes = this.value.getBytes();
        byte[] shifted = new byte[bytes.length];
        for (int i = 0; i < bytes.length; i++) {
            shifted[i] = (byte) (bytes[i] << shift);
        }
        this.value = new String(shifted);
        return this.value;
    }

    public String shiftRight(int shift) {
        byte[] bytes = this.value.getBytes();
        byte[] shifted = new byte[bytes.length];
        for (int i = 0; i < bytes.length; i++) {
            shifted[i] = (byte) (bytes[i] >> shift);
        }
        this.value = new String(shifted);
        return this.value;
    }
}
