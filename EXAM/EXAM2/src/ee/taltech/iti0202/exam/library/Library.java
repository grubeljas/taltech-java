package ee.taltech.iti0202.exam.library;

import java.util.*;
import java.util.stream.Collectors;

public class Library {

    public List<Book> books = new ArrayList<>();
    public List<Book> availableBooks = new ArrayList<>();
    public List<Book> lentBooks = new ArrayList<>();
    public Map<Book, Integer> lentCounter = new HashMap<>();

    /**
     * Adds a book to the library.
     *
     * If the same book (same instance) is already in the library, return false.
     * Otherwise the book is added to the library and return true.
     */
    public boolean addBook(Book book) {
        if (books.contains(book)) {
            return false;
        } else {
            books.add(book);
            availableBooks.add(book);
            lentCounter.put(book, 0);
            return true;
        }
    }

    /**
     * Tries to lend a book.
     *
     * If there is an available book with the exact name, then this book is returned.
     * Otherwise, if there is an available book where the title contains the name,
     * then this book is returned.
     * Otherwise, nothing is returned.
     *
     * If there is a book, then this book will not be available any more (it is lent out).
     */
    public Optional<Book> lendBook(String name) {
        Optional<Book> book;
        book = books.stream()
                .filter(book1 -> book1.getTitle() == name && availableBooks.contains(book1))
                .findAny();
        if (!book.isPresent()) {
            book = books.stream()
                    .filter(book1 -> book1.getTitle().contains(name) && availableBooks.contains(book1))
                    .findAny();
        }
        if (book.isPresent()) {
            availableBooks.remove(book.get());
            lentBooks.add(book.get());
            lentCounter.put(book.get(), lentCounter.get(book.get()) + 1);
            return book;
        }
        return Optional.empty();
    }

    /**
     * Returns a book.
     *
     * If the given book is lent from the library, returns it and returns true.
     * Otherwise returns false.
     *
     * After returning, the book can be lent again.
     */
    public boolean returnBook(Book book) {
        if (lentBooks.contains(book)) {
            lentBooks.remove(book);
            availableBooks.add(book);
            return true;
        }
        return false;
    }

    /**
     * Returns a map of ISBN and corresponding count of available books.
     */
    public Map<String, Integer> getBookAmounts() {
        Map<String, Integer> map = new HashMap<>();
        for (Book book: availableBooks) {
            map.put(book.getIsbn(), map.getOrDefault(book.getIsbn(), 0) + 1);
        }
        return map;
    }

    /**
     * Returns how many times the book has been lent.
     *
     * If the book is not in the library, returns -1.
     */
    public int getBookLendCount(Book book) {
        if (books.contains(book)) {
            return lentCounter.get(book);
        }
        return -1;
    }
}
