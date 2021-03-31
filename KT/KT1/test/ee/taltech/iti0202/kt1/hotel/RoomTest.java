package ee.taltech.iti0202.kt1.hotel;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RoomTest {

    /**
     * Test all methods of room.
     */
    @Test
    public void testUsualRoom() {
        Hotel testHotel = new Hotel("test");
        Room room1 = new Room(1, 3, false);
        assertEquals(1, room1.getNumber());
        assertEquals(3, room1.getSize());
        room1.setHotel(testHotel);
        assertEquals(testHotel, room1.hotel);
        assertTrue(!room1.booking);
        assertTrue(room1.bookRoom());
        assertTrue(room1.unBook());
    }
}
