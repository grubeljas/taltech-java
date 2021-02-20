package ee.taltech.iti0202.tk0.cat;

import java.util.LinkedList;
import java.util.List;

public class Person {

    public List<Cat> cats = new LinkedList<>();

    /**
     * Add cat to person.
     *
     * @param cat cat
     * @return bool
     */
    public boolean addCat(Cat cat){
        if (!cats.contains(cat)) {
            cats.add(cat);
            return true;
        } else {
            return false;
        }
    }

    /**
     * get cats.
     *
     * @return list
     */
    public List<Cat> getCats() {
        return cats;
    }

    /**
     * sell cat.
     *
     * @param sellTo person
     * @param cat cat
     * @return bool
     */
    public boolean sellCat(Person sellTo, Cat cat) {
        if (cats.contains(cat) && !sellTo.equals(this)) {
            cats.remove(cat);
            sellTo.addCat(cat);
            return true;
        } else {
            return false;
        }
    }
}
