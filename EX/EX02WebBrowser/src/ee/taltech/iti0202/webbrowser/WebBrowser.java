package ee.taltech.iti0202.webbrowser;

import java.util.List;
import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.HashMap;

public class WebBrowser {

    private String homePage = "google.com";

    List<String> history = new ArrayList<>();

    Deque<String> backstack = new ArrayDeque<>();

    Deque<String> fstack = new ArrayDeque<>();

    List<String> bookmarks = new ArrayList<>();

    /**
     * Construct.
     */
    public WebBrowser() {
        history.add("google.com");
    }

    /**
     * Goes to homepage.
     */
    public void homePage() {
        if (!getCurrentUrl().equals(homePage)) {
            backstack.push(getCurrentUrl());
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
            backstack.push(getCurrentUrl());
            history.add(url);
            reset();
        }
    }

    /**
     * Reset parameters.
     */
    public void reset() {
        if (!fstack.isEmpty()) {
            /** backstack.clear();
            List reverse = new ArrayList(history);
            Collections.reverse(reverse);
            backstack = new ArrayDeque<>(reverse);
            backstack.pop(); **/
            fstack.clear();
        }
    }

    /**
     * Add a webpage as a bookmark.
     */
    public void addAsBookmark() {
        if (!bookmarks.contains(getCurrentUrl())) {
            bookmarks.add(getCurrentUrl());
        }
    }

    /**
     * Remove a bookmark.
     *
     * @param bookmark to remove
     */
    public void removeBookmark(String bookmark) {
        bookmarks.remove(bookmark);
    }

    /**
     * Get a bookmark.
     *
     * @return list of bookmarks
     */
    public List<String> getBookmarks() {
        return bookmarks;
    }

    /**
     * Set a new homepage.
     *
     * @param homePage to remove
     */
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
        List<String> top = new ArrayList<>();
        List<Integer> number = new ArrayList<>();
        while (!map.isEmpty() && top.size() < 3) {
            int best = 0;
            String pop = "";
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
        StringBuilder message = new StringBuilder();
        for (int i = 0; i < top.size(); i++) {
            String s = "";
            if (number.get(i) > 1) {
                s = "s";
            }
            message.append(String.format("%1$s - %2$s visit%3$s\n", top.get(i), number.get(i), s));
        }
        return message.toString();
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
