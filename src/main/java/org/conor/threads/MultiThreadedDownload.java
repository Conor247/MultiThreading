package org.conor.threads;

import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

public class MultiThreadedDownload implements Runnable {
    private final String fileURL;
    private final String savePath;
    private final int startByte;
    private final int endByte;

    public MultiThreadedDownload(String fileURL, String savePath, int startByte, int endByte) {
        this.fileURL = fileURL;
        this.savePath = savePath;
        this.startByte = startByte;
        this.endByte = endByte;
    }

    @Override
    public void run() {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(fileURL).openConnection();
            connection.setRequestProperty("User-Agent", "Mozilla/5.0");
            connection.setRequestProperty("Range", "bytes=" + startByte + "-" + endByte);

            try (InputStream input = connection.getInputStream();
                 RandomAccessFile output = new RandomAccessFile(savePath, "rw")) {
                output.seek(startByte);
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = input.read(buffer)) != -1) {
                    output.write(buffer, 0, bytesRead);
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
