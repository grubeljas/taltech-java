package ee.taltech.iti0202.webbrowser;

import java.util.*;

public class WebBrowser {

    private String homePage = "google.com";

    LinkedList<String> history = new LinkedList<>();

    Deque<String> backstack = new ArrayDeque<>();

    Deque<String> fstack = new ArrayDeque<>();

    List<String> bookmarks = new LinkedList<>();

    public WebBrowser() {
        history.add("google.com");
    }

    /**
     * Goes to homepage.
     */
    public void homePage() {
        if (!getCurrentUrl().equals(homePage)) {
            history.add(homePage);
            reset();
        }
    }

    /**
     * Goes back to previous page.
     */
    public void back() {
        if (backstack.size() > 0) {
            String backUrl = backstack.pop();
            if (!getCurrentUrl().equals(backUrl)) {
                fstack.push(getCurrentUrl());
                history.add(backUrl);
            } else {
                backstack.push(backUrl);
            }
        }
    }

    /**
     * Goes forward to next page.
     */
    public void forward() {
        if (!fstack.isEmpty()) {
            String forwardUrl =  fstack.pop();
            backstack.push(getCurrentUrl());
            history.add(forwardUrl);
        }
    }

    /**
     * Go to a webpage.
     *
     * @param url to go to
     */
    public void goTo(String url) {
        if (!getCurrentUrl().equals(url)) {
            history.add(url);
            reset();
        }
    }

    /**
     * Reset parameters.
     */
    public void reset() {
        while (!backstack.isEmpty()) {
            backstack.pop();
        }
        for (int i = 0; i < history.size() - 1; i++) {
            backstack.push(history.get(i));
        }
        while (!fstack.isEmpty()) {
            fstack.pop();
        }
    }

    /**
     * Add a webpage as a bookmark.
     */
    public void addAsBookmark() {
        bookmarks.add(getCurrentUrl());
    }

    /**
     * Remove a bookmark.
     *
     * @param bookmark to remove
     */
    public void removeBookmark(String bookmark) {
        bookmarks.remove(bookmark);
    }

    public List<String> getBookmarks() {
        return bookmarks;
    }

    public void setHomePage(String homePage) {
        this.homePage = homePage;
    }

    /**
     * Get top 3 visited pages.
     *
     * @return a String that contains top three visited pages separated with a newline "\n"
     */
    public String getTop3VisitedPages() {
        Map<String, Integer> map = new HashMap<>();
        for (String name : getHistory()) {
            if (map.containsKey(name)) {
                map.put(name, map.get(name) + 1);
            } else {
                map.put(name, 1);
            }
        }
        List<String> top = new LinkedList<>();
        List<Integer> number = new LinkedList<>();
        while (!map.isEmpty() && top.size() < 3) {
            int best = 0;
            String pop = new String();
            for (String i : map.keySet()) {
                if (map.get(i) > best || map.get(i) == best && history.indexOf(i) < history.indexOf(pop)) {
                    best = map.get(i);
                    pop = i;
                }
            }
            top.add(pop);
            number.add(best);
            map.remove(pop);
        }
        String message = new String();
        for (int i = 0; i < top.size(); i++) {
            String s = "";
            if (number.get(i) > 1){
                s = "s";
            }
            message = message + String.format("%1$s - %2$s visit%3$s\n", top.get(i), number.get(i), s);
        }
        return message;
    }

    /**
     * Returns a list of all visited pages.
     *
     * Not to be confused with pages in your back-history.
     *
     * For example, if you visit "facebook.com" and hit back(),
     * then the whole history would be: ["google.com", "facebook.com", "google.com"]
     * @return list of all visited pages
     */
    public List<String> getHistory() {
        return history;
    }


    /**
     * Returns the active web page (string).
     *
     * @return active web page
     */
    public String getCurrentUrl() {
        return history.get(history.size() - 1);
    }
}
