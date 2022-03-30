package com.github.monsterhxw.chapter02.section10;

import org.junit.jupiter.api.*;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author XueweiHuang
 * @created 2022-03-30
 */
class BufferPracticeTest {

    private static final String RESOURCES_PATH = "src/test/resources/section10";
    private static final String DEFAULT_FILE_NAME = "word";
    private static final int BUFFER_SIZE = 4 * 1024;
    private static final int THRESHOLD = 1_000_000;

    @BeforeAll
    static void setUp() throws IOException {
        if (Files.notExists(Paths.get(RESOURCES_PATH))) {
            Files.createDirectories(Path.of(RESOURCES_PATH));
        }
        // generate file in resources
        Random random = new Random();
        // default buffer size is 8K
        var bos = new BufferedOutputStream(new FileOutputStream(filePath()), BUFFER_SIZE);
        var startTime = System.currentTimeMillis();
        for (int i = 0; i < THRESHOLD; i++) {
            for (int j = 0; j < 5; j++) {
                bos.write(97 + random.nextInt(5));
            }
            bos.write(' ');
        }
        bos.close();
        System.out.println("Generate file cost time is: " + (System.currentTimeMillis() - startTime) + "ms.");
    }

    @AfterAll
    static void cleanUp() throws IOException {
        Files.delete(Paths.get(filePath()));
    }

    @Test
    public void testRead() throws IOException {
        var fis = new FileInputStream(filePath());
        var startTime = System.currentTimeMillis();
        while (fis.read() != -1) {
        }
        System.out.println("File input stream read cost time: " + (System.currentTimeMillis() - startTime) + "ms.");
        fis.close();
    }

    @Test
    public void testReadWithBuffer() throws IOException {
        var bis = new BufferedInputStream(new BufferedInputStream(new FileInputStream(filePath())));
        var startTime = System.currentTimeMillis();
        var buffer = new byte[8 * 1024];
        while (bis.read(buffer) != -1) {
        }
        System.out.println("Buffered input stream read cost time: " + (System.currentTimeMillis() - startTime) + "ms.");
        bis.close();
    }

    @Test
    public void testReadUsingNio() throws IOException {
        var channel = new FileInputStream(filePath()).getChannel();
        var buffer = ByteBuffer.allocate(8 * 1024);
        var startTime = System.currentTimeMillis();
        while (channel.read(buffer) != -1) {
            buffer.flip();
            buffer.clear();
        }
        System.out.println("New I/O read cost time: " + (System.currentTimeMillis() - startTime) + "ms.");
        channel.close();
    }

    @Test
    public void testReadUsingAsync() throws IOException, ExecutionException, InterruptedException {
        var channel = AsynchronousFileChannel.open(Path.of(filePath()), StandardOpenOption.READ);
        var buffer = ByteBuffer.allocate(8 * 1024);

        var startTime = System.currentTimeMillis();
        Future<Integer> operation = channel.read(buffer, 0);
        var numRead = operation.get();
        buffer.flip();
        var chars = new String(buffer.slice().array());
        buffer.clear();
        System.out.println("Asynchronous file channel read cost time: " + (System.currentTimeMillis() - startTime) + "ms, character array length is: " + chars.length() + ".");
        channel.close();
    }

    @Test
    public void testChinese() {
        var raw = "长坂桥头杀气生，横枪立马眼圆睁。一声好似轰雷震，独退曹家百万兵。";
        var utf8Charset = StandardCharsets.UTF_8;

        var bytes = utf8Charset.encode(raw).array();
        var copyBytes = Arrays.copyOfRange(bytes, 0, 11);

        var byteBuff = ByteBuffer.allocate(12);
        var charBuff = CharBuffer.allocate(12);

        byteBuff.put(copyBytes);
        byteBuff.flip();

        utf8Charset.newDecoder().decode(byteBuff, charBuff, true);
        charBuff.flip();

        var charArr = new char[charBuff.length()];
        if (charBuff.hasRemaining()) {
            charBuff.get(charArr);
            System.out.println("here: " + Arrays.toString(charArr));
        }

        System.out.println("Byte buffer limit - position = " + (byteBuff.limit() - byteBuff.position()));
        bytes = Arrays.copyOfRange(byteBuff.array(), byteBuff.position(), byteBuff.limit());
        System.out.println(Arrays.toString(bytes));
    }

    private static String filePath() {
        return RESOURCES_PATH + "/" + DEFAULT_FILE_NAME;
    }
}