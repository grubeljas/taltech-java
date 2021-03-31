package ee.taltech.iti0202.kt1.hotel;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Hotel {

    String name;
    List<Room> rooms;

    /**
     * Constructor.
     * @param name ma,e
     */
    public Hotel(String name) {
        this.name = name;
        this.rooms = new ArrayList<>();
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
        room.setHotel(this);
        return true;
    }

    /**
     * Cancel booking.
     * @param room
     * @return
     */
    public boolean unBookRoom(Room room) {
        if (room.booking) {
            room.bookRoom();
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
        Optional<Room> wishedRoom = rooms.stream()
                .filter(room -> room.lux == lux)
                .filter(room -> !room.booking)
                .min(Comparator.comparing(room -> Math.abs(room.size - size)));
        if (wishedRoom.isPresent()) {
            wishedRoom.get().bookRoom();
            return true;
        }
        return false;
    }
}
