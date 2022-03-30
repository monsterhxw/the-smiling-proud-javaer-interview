package com.github.monsterhxw.chapter02.section12;

import org.junit.jupiter.api.Test;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

/**
 * @author XueweiHuang
 * @created 2022-03-30
 */
class WordCountTest {

    private static final String FILE_PATH = "src/test/resources/section12/word_count";

    @Test
    public void wordCountWithSingleThread() throws IOException {
        var bis = new BufferedInputStream(new FileInputStream(FILE_PATH));
        var buffer = new byte[4 * 1024];

        var total = new HashMap<String, Integer>();
        var len = 0;

        var startTime = System.currentTimeMillis();

        while ((len = bis.read(buffer)) != -1) {
            var copyArr = Arrays.copyOfRange(buffer, 0, len);
            var copyArrStr = new String(copyArr);
            var map = WordCount.wordCountForStr(copyArrStr);
            for (var entry : map.entrySet()) {
                WordCount.incWordCountForMap(total, entry.getKey(), entry.getValue());
            }
        }

        System.out.println("Word count with single thread cost time is: " + (System.currentTimeMillis() - startTime) + "ms.");
        System.out.println("total size is: " + total.size());
        System.out.println("'ababb' word total is: " + total.get("ababb"));
    }

    @Test
    public void wordCountWithMultipleThread() throws ExecutionException, InterruptedException {
        var wordCount = new WordCount();
        System.out.println("Current processors is: " + Runtime.getRuntime().availableProcessors());
        wordCount.runWithMultipleThread(FILE_PATH, 2 * 1024 * 1024);
    }
}