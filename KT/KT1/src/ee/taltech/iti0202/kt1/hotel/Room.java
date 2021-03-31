package ee.taltech.iti0202.kt1.hotel;

public class Room {

    Integer number, size;
    boolean booking, lux;
    Hotel hotel;

    public Room(Integer number, Integer size, boolean lux) {
        this.number = number;
        this.size = size;
        this.booking = false;
        this.lux = lux;
        this.hotel = null;
    }

    public Integer getNumber() {
        return number;
    }

    public Integer getSize() {
        return size;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public boolean bookRoom() {
        booking = !booking;
        return booking;
    }

    public boolean unBook() {
        if (lux || !booking) return false;
        return bookRoom();
    }
}
