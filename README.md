# MultiThreading

A simple Java application to explore multithreading and its benefits.

## Purpose

The goal of a multithreaded download is to enhance download speed and efficiency by dividing a large file into smaller segments that can be downloaded concurrently using multiple threads. In this test case, 4.

## Explanation

* 100mb download comes from https://www.thinkbroadband.com/download

### 1. Establish server connection
* Inside the MultiThread.Java class a connection to the file on the server is established in order to retrieve the total file size.

### 2. Local file creation and size allocation
* The file is opened at the specified file path with file name and the file size is pre-allocated.

### 3. Chunk division
* The file size (in bytes) is divided into equal parts based on the number of threads in use.
* Each thread will download its assigned chunk concurrently.

### 4. Thread creation
* Based on the thread number parameter each thread is created with its assigned chunk and the threads are started.

### 5. Thread methodology
* Inside MultiThreadedDownload.Java a connection is again established to the file url.
* A request property value is set to indicate to the server which byte range of the file the thread wants to download.
* A try-with-resources statement is used to automatically manage the closing of the input stream (data coming from the server) and RandomAccessFile (local file the bytes are downloaded to).
* output.seek(startByte); line moves the byte pointer to the specified startByte in the output file. This ensures the threads don't write the downloaded data over each other.
* byte[] buffer = new byte[1024]; is used to temporarily hold the data before it is written. Larger buffers reduce read operations while smaller buffers reduce memory usage.
* Now we enter a while loop to download the data. The input.read(buffer) method reads up to the buffer length bytes and returns the number of bytes read. If no more bytes are available, it returns -1, which indicates the end of the stream.
* Inside the loop output.write(buffer, 0, bytesRead) writes the bytes from the buffer into the local file we created earlier.

### 6. Tests

Running the tests clearly demonstrates the power of multithreaded downloading.

With my internet connection the single threaded download takes roughly 11,000ms whereas the multithreaded (using 4 threads) download takes roughly 70ms.

There are file size assertions in each test to ensure the complete file has been downloaded before the deletion of the dummy file.