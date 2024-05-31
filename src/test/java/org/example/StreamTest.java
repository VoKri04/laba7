package org.example;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;
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
    void testReadIntArray1(){
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
    void testReadIntArray2(){
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
    void testReadIntArray3() {
        assertThrows(NullPointerException.class, () -> Stream.readIntArray(null, 5));
    }

    @Test
    void testWriteIntArrayToCharacterStream1() {
        int[] testArray = {1, 2, 3, 4, 5};


        String expectedOutput = "1 2 3 4 5";

        StringWriter stringWriter = new StringWriter();
        try (BufferedWriter writer = new BufferedWriter(stringWriter)) {
            Stream.writeIntArrayToCharacterStream(testArray, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String actualOutput = stringWriter.toString();

        assertEquals(expectedOutput, actualOutput);

    }
    @Test
    void testWriteIntArrayToCharacterStream2() {
        int[] testArray = new int[0];

        String expectedOutput = "";

        StringWriter stringWriter = new StringWriter();
        try (BufferedWriter writer = new BufferedWriter(stringWriter)) {
            Stream.writeIntArrayToCharacterStream(testArray, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String actualOutput = stringWriter.toString();

        assertEquals(expectedOutput, actualOutput);
    }
    @Test
    void testWriteIntArrayToCharacterStream3() {
        int[] intArray = {1, 2, 3, 4, 5};

        BufferedWriter stringWriter = null;

        assertThrows(NullPointerException.class, () -> Stream.writeIntArrayToCharacterStream(intArray, stringWriter));
    }

    @Test
    void testReadIntArrayFromCharacterStream1() {
        String inputString = "1 2 3 4 5";

        int[] expectedArray = {1, 2, 3, 4, 5};

        try (BufferedReader reader = new BufferedReader(new StringReader(inputString))) {
            int[] actualArray = Stream.readIntArrayFromCharacterStream(reader);

            assertArrayEquals(expectedArray, actualArray);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @Test
    void testReadIntArrayFromCharacterStream2() {
        String inputString = " ";

        int[] expectedArray = {};

        try (BufferedReader reader = new BufferedReader(new StringReader(inputString))) {

            int[] actualArray = Stream.readIntArrayFromCharacterStream(reader);

            assertArrayEquals(expectedArray, actualArray);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    void testReadIntArrayFromCharacterStream3() {
        assertThrows(NullPointerException.class, () -> Stream.readIntArrayFromCharacterStream(null));
    }
    @Test
    void testReadIntArrayFromRandomAccessFile1() {
        try {
            File tempFile = new File("file.dat");
            try (RandomAccessFile randomAccessFile = new RandomAccessFile(tempFile, "rw")) {

                randomAccessFile.writeInt(1);
                randomAccessFile.writeInt(2);
                randomAccessFile.writeInt(3);
                randomAccessFile.writeInt(4);
                randomAccessFile.writeInt(5);
            }

            int[] actualArray = Stream.readIntArrayFromRandomAccessFile(tempFile, 0);

            int[] expectedArray = {1, 2, 3, 4, 5};

            assertArrayEquals(expectedArray, actualArray);
        } catch (IOException e) {e.printStackTrace();
        }
    }
    @Test
    void testReadIntArrayFromRandomAccessFile2() {
        try {
            File tempFile = new File("file.dat");
            try (RandomAccessFile randomAccessFile = new RandomAccessFile(tempFile, "rw")) {

                randomAccessFile.writeInt(1);
                randomAccessFile.writeInt(2);
                randomAccessFile.writeInt(3);
                randomAccessFile.writeInt(4);
                randomAccessFile.writeInt(5);
            }

            int[] actualArray = Stream.readIntArrayFromRandomAccessFile(tempFile, 3);

            int[] expectedArray = { 4, 5};

            assertArrayEquals(expectedArray, actualArray);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    void testGetFiles1() throws IOException {
        File tempDir = new File("/Users/vladimirkrivyh/IdeaProjects/laba77");

        File file1 = new File("/Users/vladimirkrivyh/IdeaProjects/laba77/text1.rtf");
        File file2 = new File("/Users/vladimirkrivyh/IdeaProjects/laba77/text2.rtf");

        List<File> actualFiles = Stream.getFiles(tempDir, ".rtf");

        List<File> expectedFiles = new ArrayList<>();
        expectedFiles.add(file1);
        expectedFiles.add(file2);

        assertEquals(expectedFiles, actualFiles);


    }
    @Test
    void testGetFiles3() throws IOException {
        File tempDir = new File("/Users/vladimirkrivyh/IdeaProjects/laba77/text1.rtf");


        List<File> actualFiles = Stream.getFiles(tempDir, ".rtf");

        List<File> expectedFiles = new ArrayList<>();

        assertEquals(expectedFiles, actualFiles);


    }

    @Test
    void testGetFiles2() {
        File tempDir = new File("/Users/vladimirkrivyh/IdeaProjects/laba77");

        File file1 = new File("/Users/vladimirkrivyh/IdeaProjects/laba77/text1.rtf");
        File file2 = new File("/Users/vladimirkrivyh/IdeaProjects/laba77/text2.rtf");
        File file3 = new File("/Users/vladimirkrivyh/IdeaProjects/laba77/file3.bin");

        List<File> actualFiles = Stream.getFiles(tempDir, ".pdf");

        List<File> expectedFiles = new ArrayList<>();

        assertEquals(expectedFiles, actualFiles);

    }
    @Test
    void testGetFiles4() {
        File tempDir = new File("/Users/vladimirkrivyh/IdeaProjects/laba77");

        File file1 = new File("/Users/vladimirkrivyh/IdeaProjects/laba77/text1.rtf");
        File file2 = new File("/Users/vladimirkrivyh/IdeaProjects/laba77/pom.xml");
        File file3 = new File("/Users/vladimirkrivyh/IdeaProjects/laba77/file3.bin");

        List<File> actualFiles = Stream.getFiles(tempDir, ".xml");

        List<File> expectedFiles = new ArrayList<>();

        expectedFiles.add(file2);

        assertEquals(expectedFiles, actualFiles);

    }

}