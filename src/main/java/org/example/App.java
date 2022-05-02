package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        String fileName = "data.json";
        String json = readString(fileName);

        List<Employee> employees = jsonToList(json);
        employees.forEach(System.out::println);
    }

    private static List<Employee> jsonToList(String json) {
        List<Employee> list = new ArrayList<>();

        JSONParser parser = new JSONParser();
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        try {
            Object obj = parser.parse(json);
            JSONArray array = (JSONArray) obj;

            for (Object employee : array) {
                JSONObject jsonObject = (JSONObject) employee;
                Employee emp = gson.fromJson(String.valueOf(jsonObject), Employee.class);
                list.add(emp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    private static String readString(String fileName) {
        StringBuilder sb = new StringBuilder();

        try (FileReader fr = new FileReader(fileName);
             BufferedReader br = new BufferedReader(fr)) {
            while (br.ready()) {
                sb.append(br.readLine());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sb.toString();
    }
}
