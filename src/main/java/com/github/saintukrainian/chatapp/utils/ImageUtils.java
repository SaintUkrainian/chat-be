package com.github.saintukrainian.chatapp.utils;

import java.io.ByteArrayOutputStream;
import java.util.zip.Deflater;
import java.util.zip.Inflater;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ImageUtils {

  public static byte[] compressImage(byte[] data) {
    // Create a Deflater object to compress data
    Deflater compressor = new Deflater(Deflater.BEST_SPEED, false);

    // Set the input for the compressor
    compressor.setInput(data);

    // Call the finish() method to indicate that we have
    // no more input for the compressor object
    compressor.finish();

    // Compress the data
    ByteArrayOutputStream bao = new ByteArrayOutputStream();
    byte[] readBuffer = new byte[1024];
    int readCount = 0;

    while (!compressor.finished()) {
      readCount = compressor.deflate(readBuffer);
      if (readCount > 0) {
        // Write compressed data to the output stream
        bao.write(readBuffer, 0, readCount);
      }
    }

    // End the compressor
    compressor.end();

    // Return the written bytes from output stream
    return bao.toByteArray();
  }

  @SneakyThrows
  public static byte[] decompress(byte[] data) {
    // Create an Inflater object to compress the data
    Inflater decompressor = new Inflater(false);

    // Set the data for the decompressor
    decompressor.setInput(data);

    // Decompress data
    ByteArrayOutputStream bao = new ByteArrayOutputStream();
    byte[] readBuffer = new byte[1024];
    int readCount = 0;

    while (!decompressor.finished()) {
      readCount = decompressor.inflate(readBuffer);
      if (readCount > 0) {
        // Write the data to the output stream
        bao.write(readBuffer, 0, readCount);
      }
    }

    // End the decompressor
    decompressor.end();

    // Return the written bytes from the output stream
    return bao.toByteArray();
  }
}
