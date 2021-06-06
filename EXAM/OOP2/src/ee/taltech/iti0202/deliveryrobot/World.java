package ee.taltech.iti0202.deliveryrobot;

import ee.taltech.iti0202.deliveryrobot.company.Company;

import java.util.LinkedList;
import java.util.List;

public final class World {

    private List<Company> companyList = new LinkedList<>();
    private static World instance = null;

    /**
     * Constructor for singleton.
     */
    private World() { }

    /**
     * Instant.
     * @return
     */
    public static World getInstance() {
        if (instance == null) instance = new World();
        return instance;
    }

    /**
     * Clear all info.
     */
    public void clearInstance() {
        instance = null;
    }

    /**
     * Add company to world.
     * @param company
     */
    public void addCompany(Company company) {
        if (!companyList.contains(company)) {
            companyList.add(company);
        }
    }

    public List<Company> getCompanyList() {
        return companyList;
    }

    /**
     * Next day.
     */
    public void nextDay() {
        for (Company company: companyList) {
            company.nextDay();
        }
    }
}
