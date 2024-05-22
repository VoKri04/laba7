package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Stream {
    public static void writeIntArray(int[] intArray, DataOutputStream outputStream) throws IOException {
        try (outputStream) {
            for (int num : intArray) {
                outputStream.writeInt(num);
            }
        }
    }

    public static int[] readIntArray(DataInputStream inputStream, int n) throws IOException {
        int[] intArray = new int[n];
        try (inputStream) {
            for (int i = 0; i < n; i++) {
                intArray[i] = inputStream.readInt();
            }
        }
        return intArray;
    }


    public static void writeIntArrayToCharacterStream(int[] intArray, BufferedWriter writer) throws IOException {
        try (writer) {
            for (int i = 0; i < intArray.length; i++) {
                writer.write(Integer.toString(intArray[i]));
                if (i < intArray.length - 1) {
                    writer.write(" ");
                }
            }
        }
    }

    public static int[] readIntArrayFromCharacterStream(BufferedReader reader) throws IOException {
        try (reader) {
            String line = reader.readLine();
            String[] tokens = line.split(" ");
            int[] intArray = new int[tokens.length];
            for (int i = 0; i < tokens.length; i++) {
                intArray[i] = Integer.parseInt(tokens[i]);
            }
            return intArray;
        }
    }
    public static int[] readIntArrayFromRandomAccessFile(File file, long position, int n) throws IOException {
        int[] intArray = new int[n];
        try (RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r")) {
            randomAccessFile.seek(position*4);
            for (int i = 0; i < n; i++) {
                intArray[i] = randomAccessFile.readInt();
            }
        }
        return intArray;
    }

    public static List<File> getFiles(File directory, String extension) {
        List<File> filesWithExtension = new ArrayList<>();
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile() && file.getName().endsWith(extension)) {
                    filesWithExtension.add(file);
                }
            }
        }
        return filesWithExtension;
    }

}
