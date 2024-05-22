package org.example;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StreamTest {
    @Test
    void testWriteIntArray1() {

        int[] testArray = {1, 2, 3, 4, 5};


        byte[] expectedOutput = {0, 0, 0, 1, 0, 0, 0, 2, 0, 0, 0, 3, 0, 0, 0, 4, 0, 0, 0, 5};

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try (DataOutputStream outputStream = new DataOutputStream(baos)) {
            Stream.writeIntArray(testArray, outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        byte[] actualOutput = baos.toByteArray();

        assertArrayEquals(expectedOutput, actualOutput);

    }
    @Test
    void testWriteIntArray2() {

        int[] testArray = new int[0];


        byte[] expectedOutput = {};

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try (DataOutputStream outputStream = new DataOutputStream(baos)) {
            Stream.writeIntArray(testArray, outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        byte[] actualOutput = baos.toByteArray();

        assertArrayEquals(expectedOutput, actualOutput);

    }

    @Test
    public void testWriteIntArray3() {
        int[] intArray = {1, 2, 3, 4, 5};

        DataOutputStream nullOutputStream = null;

        assertThrows(NullPointerException.class, () -> Stream.writeIntArray(intArray, nullOutputStream));
    }

    @Test
    void readIntArray1(){
        byte[] testData = {0, 0, 0, 1, 0, 0, 0, 2, 0, 0, 0, 3, 0, 0, 0, 4, 0, 0, 0, 5};

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(testData);

        try (DataInputStream inputStream = new DataInputStream(byteArrayInputStream)) {
            int n = 5;
            int[] actualIntArray = Stream.readIntArray(inputStream, n);
            int[] expectedIntArray = {1, 2, 3, 4, 5};
            assertArrayEquals(actualIntArray, expectedIntArray);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void readIntArray2(){
        byte[] testData = {};
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(testData);
        try (DataInputStream inputStream = new DataInputStream(byteArrayInputStream)) {

            int n = 0;
            int[] actualIntArray = Stream.readIntArray(inputStream, n);

            int[] expectedIntArray = {};

            assertArrayEquals(actualIntArray, expectedIntArray);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    void readIntArray3() {
        assertThrows(NullPointerException.class, () -> Stream.readIntArray(null, 5));
    }


}