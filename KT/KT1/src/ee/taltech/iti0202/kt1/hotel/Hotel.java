package ee.taltech.iti0202.kt1.hotel;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Hotel {

    String name;
    List<Room> rooms;
    List<Room> booked;
    List<Room> empty;

    /**
     * Constructor.
     * @param name ma,e
     */
    public Hotel(String name) {
        this.name = name;
        this.rooms = new ArrayList<>();
        this.booked = new ArrayList<>();
        this.empty = new ArrayList<>();
    }

    /**
     * Add room if there is no room with same number.
     * @param room
     * @return
     */
    public boolean addRoom(Room room) {
        for (Room room1: rooms) {
            if (room.number == room1.number) {
                return false;
            }
        }
        rooms.add(room);
        empty.add(room);
        room.setHotel(this);
        return true;
    }

    public List<Room> getBooked() {
        return booked;
    }

    public List<Room> getEmpty() {
        return empty;
    }

    /**
     * Cancel booking.
     * @param room
     * @return
     */
    public boolean unBookRoom(Room room) {
        if (room.booking) {
            room.bookRoom();
            booked.remove(room);
            empty.remove(room);
            return true;
        }
        return false;
    }

    /**
     * Order book with that size and type.
     * @param lux
     * @param size
     * @return
     */
    public boolean orderRoom(boolean lux, Integer size) {
        Optional<Room> wishedRoom = empty.stream()
                .filter(room -> room.lux == lux)
                .min(Comparator.comparing(room -> Math.abs(room.size - size)));
        if (wishedRoom.isPresent()) {
            wishedRoom.get().bookRoom();
            getBooked().add(wishedRoom.get());
            getEmpty().remove(wishedRoom.get());
            return true;
        }
        return false;
    }
}
