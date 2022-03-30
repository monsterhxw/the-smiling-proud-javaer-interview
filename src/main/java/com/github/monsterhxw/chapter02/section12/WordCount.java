package com.github.monsterhxw.chapter02.section12;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.concurrent.*;

/**
 * @author XueweiHuang
 * @created 2022-03-30
 */
public class WordCount {

    private static final ForkJoinPool POOL = ForkJoinPool.commonPool();

    public static Map<String, Integer> wordCountForStr(String str) {
        var resultMap = new HashMap<String, Integer>();
        StringTokenizer tokenizer = new StringTokenizer(str);
        while (tokenizer.hasMoreTokens()) {
            var word = tokenizer.nextToken();
            incWordCountForMap(resultMap, word, 1);
        }
        return resultMap;
    }

    public static void incWordCountForMap(Map<String, Integer> map, String word, Integer count) {
        map.merge(word, count, Integer::sum);
    }

    public void runWithMultipleThread(String filePath, long chunkSize) throws ExecutionException, InterruptedException {
        var file = new File(filePath);
        var fileLen = file.length();

        var startTime = System.currentTimeMillis();
        long startPos = 0;

        var tasks = new ArrayList<Future<Map<String, Integer>>>();

        while (startPos < fileLen) {
            var endPos = Math.min(startPos + chunkSize, fileLen);
            var task = new WordCountTask(startPos, endPos, filePath);
            startPos = endPos;
            var future = POOL.submit(task);
            tasks.add(future);
        }
        System.out.println("Split to " + tasks.size() + " tasks.");

        var totalMap = new HashMap<String, Integer>();
        for (var future : tasks) {
            var map = future.get();
            for (var entry : map.entrySet()) {
                incWordCountForMap(totalMap, entry.getKey(), entry.getValue());
            }
        }
        System.out.println("Using multiple thread cost time is: " + (System.currentTimeMillis() - startTime) + "ms.");
        System.out.println("total size is: " + totalMap.size());
        System.out.println("'ababb' word total is: " + totalMap.get("ababb"));
    }

    public class WordCountTask implements Callable<Map<String, Integer>> {

        private final long startPos;
        private final long endPos;
        private final String filePath;

        public WordCountTask(long startPos, long endPos, String filePath) {
            this.startPos = startPos;
            this.endPos = endPos;
            this.filePath = filePath;
        }

        @Override
        public Map<String, Integer> call() throws Exception {
            var channel = new RandomAccessFile(this.filePath, "rw").getChannel();
            // [startPos, endPos] -> Memory
            // Device -> Kernel Space -> User Space(buffer) -> Thread
            var mappedByteBuffer = channel.map(FileChannel.MapMode.READ_ONLY, this.startPos, (endPos - startPos));
            var decodeStr = StandardCharsets.US_ASCII.decode(mappedByteBuffer).toString();
            return wordCountForStr(decodeStr);
        }
    }
}
