package org.conor.download;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class MultiThreadTest {

    @Test
    void multiThreadDownloadTest() throws IOException {
        String fileURL = "http://ipv4.download.thinkbroadband.com/100MB.zip";
        String savePath = "100MB.zip";

        try {
            long startTime = System.currentTimeMillis();
            MultiThread.download(fileURL, savePath, 4);
            long endTime = System.currentTimeMillis();
            System.out.println("Multi-threaded download time: " + (endTime - startTime) + "ms");
        }
        finally {
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