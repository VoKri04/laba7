package org.example;
import java.io.*;
import com.fasterxml.jackson.databind.*;

public class HouseSerialization {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static void serializeHouse(House house, OutputStream outputStream) throws IOException {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)) {
            objectOutputStream.writeObject(house);
        }
    }

    public static House deserializeHouse(InputStream inputStream) throws IOException, ClassNotFoundException {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(inputStream)) {
            return (House) objectInputStream.readObject();
        }
    }

    public static String serializeHouseJackson(House house) throws IOException {
        return objectMapper.writeValueAsString(house);
    }

    public static House deserializeHouseJackson(String json) throws IOException {
        return objectMapper.readValue(json, House.class);
    }
}
