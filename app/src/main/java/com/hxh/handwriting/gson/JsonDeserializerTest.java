package com.hxh.handwriting.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HXH at 2019/7/3
 * Gson容错测试
 */
public class JsonDeserializerTest {

    public static void main(String[] args) {
        // 正例
        //String jsonStr = "{\"name\":\"承香墨影\", \"age\":1}";
        // 容错反例
        String jsonStr = "{\"name\":\"承香墨影\", \"age\":\"\"}";

        User user = new GsonBuilder()
                .registerTypeAdapter(Integer.TYPE, new TypeAdapter<Integer>() {
                    @Override
                    public void write(JsonWriter out, Integer value) throws IOException {
                        out.value(String.valueOf(value));
                    }

                    @Override
                    public Integer read(JsonReader in) throws IOException {
                        try {
                            return Integer.parseInt(in.nextString());
                        } catch (NumberFormatException e) {
                            return -1;
                        }
                    }
                })
                .create()
                .fromJson(jsonStr, User.class);
        System.out.println("test:" + user.toString());
        System.out.println("=====================================");

        user = new GsonBuilder()
                .registerTypeAdapter(Integer.TYPE, new JsonDeserializer<Integer>() {

                    @Override
                    public Integer deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                        if (json.getAsString().equals("")) {
                            return -1;
                        }
                        return json.getAsInt();
                    }
                })
                .create()
                .fromJson(jsonStr, User.class);
        System.out.println("test:" + user.toString());
        System.out.println("=====================================");

        // 正例
        //jsonStr = "{\"name\":\"承香墨影\", \"age\":1, \"languages\":[\"aaa\"]}";
        // 容错反例1
        //jsonStr = "{\"name\":\"承香墨影\", \"age\":1, \"languages\":\"\"}";
        // 容错反例2-这个null转换失败,根本不走JsonDeserializer#deserialize TODO 如何解决？
        jsonStr = "{\"name\":\"承香墨影\", \"age\":1, \"languages\":null}";
        // 容错反例3
        //jsonStr = "{\"name\":\"承香墨影\", \"age\":1, \"languages\":{}}";
        User2 user2 = new GsonBuilder()
                .serializeNulls()
                .registerTypeHierarchyAdapter(List.class, new JsonDeserializer<List<String>>() {
                    @Override
                    public List<String> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                        if (json == null || json.isJsonNull()) {
                            return new ArrayList<>();
                        }
                        if (json.isJsonArray()) {
                            return new Gson().fromJson(json, typeOfT);
                        }
                        return new ArrayList<>();
                    }
                })
                .create()
                .fromJson(jsonStr, User2.class);
        System.out.println("test:" + user2.toString());
        System.out.println("=====================================");

        // 容错反例4
        jsonStr = "{\"name\":\"承香墨影\", \"age\":\"\", \"languages\":{}}";
        // 容错反例5-同样这个null转换失败,根本不走JsonDeserializer#deserialize TODO 如何解决？
        //jsonStr = "{\"name\":\"承香墨影\", \"age\":null, \"languages\":{}}";
        user2 = new GsonBuilder()
                .serializeNulls()
                .registerTypeAdapter(Integer.TYPE, new JsonDeserializer<Integer>() {

                    @Override
                    public Integer deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                        //System.out.println("...........");
                        if (json.getAsString().equals("")) {
                            return -1;
                        }
                        return json.getAsInt();
                    }
                })
                .registerTypeHierarchyAdapter(List.class, new JsonDeserializer<List<String>>() {
                    @Override
                    public List<String> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                        if (json == null || json.isJsonNull()) {
                            return new ArrayList<>();
                        }
                        if (json.isJsonArray()) {
                            return new Gson().fromJson(json, typeOfT);
                        }
                        return new ArrayList<>();
                    }
                })
                .create()
                .fromJson(jsonStr, User2.class);
        System.out.println("test:" + user2.toString());
        System.out.println("=====================================");
    }

    ///////////////////////////////////////////////////////////////////////////
    // bean
    ///////////////////////////////////////////////////////////////////////////
    private static class User {
        private String name;
        private int age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }

    private static class User2 extends User {
        private List<String> languages;

        public List<String> getLanguages() {
            return languages;
        }

        public void setLanguages(List<String> languages) {
            this.languages = languages;
        }

        @Override
        public String toString() {
            return "User2{" +
                    "name='" + getName() + '\'' +
                    ", age=" + getAge() +
                    ", languages=" + languages +
                    "} ";
        }
    }

}
