package lee.project.sample.convert.service;

import java.nio.ByteBuffer;

public class BufferTest {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(30);
        buffer.put((byte) 4);
        buffer.put((byte) 3);
        buffer.put((byte) 1);
        System.out.println(buffer);
        buffer.rewind();
        System.out.println(buffer);
        System.out.println(buffer.get());
        System.out.println(buffer.get());
        System.out.println(buffer.get());
        System.out.println(buffer.get());
        buffer.rewind();
        System.out.println(buffer);
        System.out.println(buffer.get());
        System.out.println(buffer.get());
        System.out.println(buffer.get());
        System.out.println(buffer.get());
        buffer.flip();
        System.out.println(buffer);
        System.out.println(buffer.get());
        System.out.println(buffer.get());
        System.out.println(buffer.get());
        System.out.println(buffer.get());
    }
}
