package org.example;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import com.google.gson.Gson;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


class HouseSerializationTest {

    @Test
    void testHouseSerialization1() throws IOException, ClassNotFoundException {
        Person person = new Person("Владимир", "Кривых", "Павлович", "30.07.2004");
        List<Flat> flats = new ArrayList<>();
        House originalHouse = new House("12345", "ул.Мира", person, flats);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        HouseSerialization.serializeHouse(originalHouse, outputStream);

        ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
        House deserializedHouse = HouseSerialization.deserializeHouse(inputStream);

        assertEquals(originalHouse, deserializedHouse);
    }
    @Test
    void testHouseSerialization2() throws IOException, ClassNotFoundException {

        House originalHouse = new House("12345", "ул.Мира", null, new ArrayList<>());


        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        HouseSerialization.serializeHouse(originalHouse, outputStream);

        ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
        House deserializedHouse = HouseSerialization.deserializeHouse(inputStream);

        assertEquals(originalHouse, deserializedHouse);
    }
    @Test
    void testHouseSerialization3() {

        Person person = new Person("Владимир", "Кривых", "Павлович", "30.07.2004");
        Flat flat1 = new Flat(110, 75, List.of(person));
        Flat flat2 = new Flat(111, 90, List.of());
        House originalHouse = new House("12345", "ул. Мира", person, Arrays.asList(flat1, flat2));

        String json = HouseSerialization.serializeHouseGson(originalHouse);

        assertNotNull(json);
    }

    @Test
    void testHouseSerialization4() {

        Person person = new Person("Владимир", "Кривых", "Павлович", "30.07.2004");
        Person person1 = new Person("Артем", "Бухта", "Владимирович", "29.04.2004");

        Flat flat1 = new Flat(110, 75, List.of(person));
        Flat flat2 = new Flat(111, 90, List.of(person1));

        House originalHouse = new House("123456789", "ул. Мира", person, Arrays.asList(flat1, flat2));

        String json = HouseSerialization.serializeHouseGson(originalHouse);


        House deserializedHouse = HouseSerialization.deserializeHouseGson(json);

        assertEquals(originalHouse, deserializedHouse);
    }

    @Test
    void testHouseSerialization5() {
        Person person = new Person("Владимир", "Кривых", "Павлович", "30.07.2004");
        Person person1 = new Person("Артем", "Бухта", "Владимирович", "29.04.2004");

        Flat flat1 = new Flat(110, 75.0, List.of(person));
        Flat flat2 = new Flat(111, 90.0, List.of(person1));

        House originalHouse = new House("12345", "ул. Мира", person, Arrays.asList(flat1, flat2));

        String json = "{\"cadastralNumber\":\"12345\",\"address\":\"ул. Мира\",\"main\":{\"name\":\"Владимир\",\"lastName\":\"Кривых\",\"middleName\":\"Павлович\",\"birthday\":\"30.07.2004\"}," +
                "\"flats\":[{\"number\":110,\"square\":75.0,\"owners\":[{\"name\":\"Владимир\",\"lastName\":\"Кривых\",\"middleName\":\"Павлович\",\"birthday\":\"30.07.2004\"}]},{\"number\":111,\"square\":90.0," +
                "\"owners\":[{\"name\":\"Артем\",\"lastName\":\"Бухта\",\"middleName\":\"Владимирович\",\"birthday\":\"29.04.2004\"}]}]}";

        String serializedJson = HouseSerialization.serializeHouseGson(originalHouse);

        assertEquals(json, serializedJson);
    }

    @Test
    void testHouseSerialization6() {
        Person person = new Person("Владимир", "Кривых", "Павлович", "30.07.2004");
        Person person1 = new Person("Артем", "Бухта", "Владимирович", "29.04.2004");

        Flat flat1 = new Flat(110, 75.0, List.of(person));
        Flat flat2 = new Flat(111, 90.0, List.of(person1));

        House originalHouse = new House("12345", "ул. Мира", person, Arrays.asList(flat1, flat2));

        String json = "{\"cadastralNumber\":\"12345\",\"address\":\"ул. Мира\",\"main\":{\"name\":\"Владимир\",\"lastName\":\"Кривых\",\"middleName\":\"Павлович\",\"birthday\":\"30.07.2004\"}," +
                "\"flats\":[{\"number\":110,\"square\":75.0,\"owners\":[{\"name\":\"Владимир\",\"lastName\":\"Кривых\",\"middleName\":\"Павлович\",\"birthday\":\"30.07.2004\"}]},{\"number\":111,\"square\":90.0," +
                "\"owners\":[{\"name\":\"Артем\",\"lastName\":\"Бухта\",\"middleName\":\"Владимирович\",\"birthday\":\"29.04.2004\"}]}]}";


        House deserializedHouse = HouseSerialization.deserializeHouseGson(json);

        assertEquals(deserializedHouse, originalHouse);

    }
    @Test
    void testHouseSerialization7() {
        House originalHouse = new House("", "", null, Collections.emptyList());

        String json = HouseSerialization.serializeHouseGson(originalHouse);

        assertNotNull(json);

        House deserializedHouse = HouseSerialization.deserializeHouseGson(json);

        assertNotNull(deserializedHouse);
        assertEquals(originalHouse, deserializedHouse);
    }
}