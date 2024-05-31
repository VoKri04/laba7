package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Stream {
    public static void writeIntArray(int[] intArray, OutputStream outputStream1) throws IOException {
        try (DataOutputStream outputStream = new DataOutputStream(outputStream1)) {
            for (int num : intArray) {
                outputStream.writeInt(num);
            }
        }
    }

    public static int[] readIntArray(InputStream inputStream1, int n) throws IOException {
        int[] intArray = new int[n];
        try (DataInputStream inputStream = new DataInputStream(inputStream1)) {
            for (int i = 0; i < n; i++) {
                intArray[i] = inputStream.readInt();
            }
        }
        return intArray;
    }


    public static void writeIntArrayToCharacterStream(int[] intArray, Writer writer1) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(writer1)) {
            for (int i = 0; i < intArray.length; i++) {
                writer.write(Integer.toString(intArray[i]));
                if (i < intArray.length - 1) {
                    writer.write(" ");
                }
            }
        }
    }

    public static int[] readIntArrayFromCharacterStream(BufferedReader reader1) throws IOException {
        try (BufferedReader reader = new BufferedReader(reader1)) {
            String line = reader.readLine();
            String[] tokens = line.split(" ");
            int[] intArray = new int[tokens.length];
            for (int i = 0; i < tokens.length; i++) {
                intArray[i] = Integer.parseInt(tokens[i]);
            }
            return intArray;
        }
    }
    public static int[] readIntArrayFromRandomAccessFile(File file, int position) throws IOException {
        int[] intArray;
        try (RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r")) {
            int lengthFile = (int)randomAccessFile.length() / 4;
            intArray = new int[lengthFile - position];
            randomAccessFile.seek(position * 4L);
            for (int i = 0; i < lengthFile - position; i++) {
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
