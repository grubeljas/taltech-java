package ee.taltech.iti0202.kt1.hotel;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HotelTest {

    /**
     * Test all methods of hotel.
     */
    @Test
    public void testNewHotel() {
        Hotel hotel = new Hotel("noob");
        Room room1 = new Room(1, 3, false);
        assertTrue(hotel.addRoom(room1));
        assertTrue(!hotel.addRoom(room1));
        Room room2 = new Room(2, 5, true);
        assertTrue(hotel.addRoom(room2));
        Room room3 = new Room(1, 3, false);
        assertTrue(!hotel.addRoom(room3));
        assertTrue(hotel.orderRoom(false, 4));
        assertTrue(room1.booking);
        assertTrue(!hotel.orderRoom(false, 5));
        assertTrue(hotel.unBookRoom(room1));
    }
}
