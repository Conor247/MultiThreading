package org.conor.download;

import org.conor.threads.MultiThreadedDownload;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

public class MultiThread {

    public static void download(String fileURL, String savePath, int numberOfThreads) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(fileURL).openConnection();
        connection.setRequestProperty("User-Agent", "Mozilla/5.0");
        int fileSize = connection.getContentLength();
        connection.disconnect();

        try (RandomAccessFile outputFile = new RandomAccessFile(savePath, "rw")) {
            outputFile.setLength(fileSize);
        }

        int chunkSize = fileSize / numberOfThreads;
        for (int i = 0; i < numberOfThreads; i++) {
            int startByte = i * chunkSize;
            int endByte = (i == numberOfThreads - 1) ? fileSize : (startByte + chunkSize - 1);
            Thread thread = new Thread(new MultiThreadedDownload(fileURL, savePath, startByte, endByte));
            thread.start();
        }
    }
}
