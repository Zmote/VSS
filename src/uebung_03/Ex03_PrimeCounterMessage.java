package uebung_03;

import java.io.*;

public class Ex03_PrimeCounterMessage implements Serializable {
    public static final long serialVersionUID = 1L;
    public final long start;
    public final long end;

    public Ex03_PrimeCounterMessage(long start, long end) {
        this.start = start;
        this.end = end;
    }

    public String toString() {
        return String.format("Prime numbers from %d to %d", start, end);
    }

    public byte[] toBytes() throws IOException {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            oos.writeObject(this);
            oos.flush();
            return baos.toByteArray();
        }
    }

    public static Ex03_PrimeCounterMessage fromBytes(byte[] body) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(body))) {
            Object msg = ois.readObject();
            if (!(msg instanceof Ex03_PrimeCounterMessage)) {
                throw new IllegalArgumentException("Invalid message type");
            }
            return (Ex03_PrimeCounterMessage) msg;
        }
    }
}