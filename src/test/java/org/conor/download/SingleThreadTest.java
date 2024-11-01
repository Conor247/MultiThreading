package org.conor.download;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SingleThreadTest {

    @Test
    void singleThreadDownload() throws IOException {
        String fileURL = "http://ipv4.download.thinkbroadband.com/100MB.zip";
        String savePath = "100MB.zip";

        try {
            long startTime = System.currentTimeMillis();
            SingleThread.download(fileURL, savePath);
            long endTime = System.currentTimeMillis();
            System.out.println("Single-threaded download time: " + (endTime - startTime) + "ms");
        } finally {
            File file = new File(savePath);

            long fileSizeBytesExpected = 104857600;
            assertEquals(fileSizeBytesExpected, file.length());

            if (file.exists()) {
                if (file.delete()) {
                    System.out.println("Downloaded file deleted successfully.");
                } else {
                    System.out.println("Failed to delete the downloaded file.");
                }
            }
        }
    }
}