package org.example;
import java.io.*;
import com.google.gson.Gson;

public class HouseSerialization {

    private static final Gson gson = new Gson();
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

    public static String serializeHouseGson(House house) {
        return gson.toJson(house);
    }

    public static House deserializeHouseGson(String json) {
        return gson.fromJson(json, House.class);
    }


}
