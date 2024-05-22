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

}
