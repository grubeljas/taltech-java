package ee.taltech.iti0202.webbrowser;

import java.util.LinkedList;
import java.util.Arrays;
import java.util.List;

public class WebBrowser {
    private String homePage = "google.com";
    List<String> history = Arrays.asList("google.com");
    List<String> bookmarks = new LinkedList<>();

    /**
     * Goes to homepage.
     */
    public void homePage() {
        if (history.get(history.size() - 1).equals(homePage)) {
            history.add(homePage);
        }
    }

    /**
     * Goes back to previous page.
     */
    public void back() {
        history.add(history.get(history.size() - 1));
    }

    /**
     * Goes forward to next page.
     */
    public void forward() {
        //TODO: implement
    }

    /**
     * Go to a webpage.
     *
     * @param url to go to
     */
    public void goTo(String url) {
        history.add(url);
    }

    /**
     * Add a webpage as a bookmark.
     */
    public void addAsBookmark() {
        //TODO: implement
    }

    /**
     * Remove a bookmark.
     *
     * @param bookmark to remove
     */
    public void removeBookmark(String bookmark) {
        //TODO: implement
    }

    public List<String> getBookmarks() {
        //TODO: implement
        return null;
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
        //TODO: implement
        return null;
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
        //TODO: implement
        return null;
    }


    /**
     * Returns the active web page (string).
     *
     * @return active web page
     */
    public String getCurrentUrl() {
        //TODO: implement
        return null;
    }
}
