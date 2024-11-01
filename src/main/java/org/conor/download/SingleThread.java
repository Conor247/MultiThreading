package org.conor.download;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class SingleThread {

    public static void download(String fileURL, String savePath) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(fileURL).openConnection();
        connection.setRequestProperty("User-Agent", "Mozilla/5.0");

        try (InputStream input = connection.getInputStream();
             FileOutputStream output = new FileOutputStream(savePath)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = input.read(buffer)) != -1) {
                output.write(buffer, 0, bytesRead);
            }
        } finally {
            connection.disconnect();
        }
    }
}
