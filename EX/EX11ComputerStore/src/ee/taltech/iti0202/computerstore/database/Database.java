package ee.taltech.iti0202.computerstore.database;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import ee.taltech.iti0202.computerstore.components.Component;
import ee.taltech.iti0202.computerstore.exceptions.OutOfStockException;
import ee.taltech.iti0202.computerstore.exceptions.ProductAlreadyExistsException;
import ee.taltech.iti0202.computerstore.exceptions.ProductNotFoundException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public final class Database {
    private final Map<Integer, Component> components = new HashMap<>();
    private static Database instance = null;

    private static final Type REVIEW_TYPE = new TypeToken<List<Component>>() {
    }.getType();

    private Database() { }

    /**
     * Instant.
     * @return
     */
    public static Database getInstance() {
        if (instance == null) instance = new Database();
        return instance;
    }

    /**
     * Save comp.
     *
     * @param component
     * @throws ProductAlreadyExistsException
     */
    public void saveComponent(Component component) throws ProductAlreadyExistsException {
        if (components.containsKey(component.getId())) {
            throw new ProductAlreadyExistsException();
        } else {
            components.put(component.getId(), component);
        }
    }

    /**
     * Delete component.
     * @param id
     * @throws ProductNotFoundException
     */
    public void deleteComponent(int id) throws ProductNotFoundException {
        if (components.containsKey(id)) {
            components.remove(id);
        } else throw new ProductNotFoundException();
    }

    /**
     * Make bigger.
     * @param id
     * @param amount
     * @throws ProductNotFoundException
     */
    public void increaseComponentStock(int id, int amount) throws ProductNotFoundException {
        if (components.containsKey(id)) {
            if (amount <= 0) {
                throw new IllegalArgumentException();
            } else components.get(id).setAmount(components.get(id).getAmount() + amount);
        } else throw new ProductNotFoundException();
    }

    /**
     * Make smaller.
     * @param id
     * @param amount
     * @throws OutOfStockException
     * @throws ProductNotFoundException
     */
    public void decreaseComponentStock(int id, int amount) throws OutOfStockException, ProductNotFoundException {
        if (components.containsKey(id)) {
            if (amount <= 0) {
                throw new IllegalArgumentException();
            } else if (amount > components.get(id).getAmount()) {
                throw new OutOfStockException();
            } else components.get(id).setAmount(components.get(id).getAmount() - amount);
        } else throw new ProductNotFoundException();
    }

    public Map<Integer, Component> getComponents() {
        return components;
    }

    /**
     * Reset data.
     */
    public void resetEntireDatabase() {
        Component.idCount = 0;
        components.clear();
    }

    /**
     * Save file.
     * @param location
     */
    public void saveToFile(String location) {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting().create();
        gson.toJson(components.values());
        String file = gson.toJson(components.values());
        try {
            FileWriter writer = new FileWriter(location);
            writer.write(file);
            writer.close();
        } catch (IOException e) {
            e.getMessage();
        }
    }

    /**
     * Load file.
     * @param location
     */
    public void loadFromFile(String location) {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting().create();
        resetEntireDatabase();
        try {
            JsonReader reader = new JsonReader(new FileReader(location));
            List<Component> data = gson.fromJson(reader, REVIEW_TYPE);
            for (Component component : data) {
                components.put(component.getId(), component);
            }
        } catch (FileNotFoundException e) {
            e.getMessage();
        }
    }
}
