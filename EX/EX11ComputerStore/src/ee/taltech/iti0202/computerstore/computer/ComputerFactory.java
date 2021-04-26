package ee.taltech.iti0202.computerstore.computer;

import ee.taltech.iti0202.computerstore.components.Component;
import ee.taltech.iti0202.computerstore.exceptions.CannotBuildComputerException;
import ee.taltech.iti0202.computerstore.store.Store;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ComputerFactory {
    private Component harddisk, keyboard, screen, battery, touchpad, cpu, gpu, ram, motherboard, psu, case1;
    public Store store;
    float processorWeight = 1.0f;
    float gpuWeight = 1.0f;
    final float wEIGHT = 1.3f;
    List<Component> psuList, gpuList, cpuList, ramList, motherboardList, casesList, harddiskList, keyboardList,
            screenList, batteryList, touchpadList;

    /**
     * Constructor.
     * @param store
     */
    public ComputerFactory(Store store) {
        this.store = store;
    }

    public void setWeights(Preferences preferences) {
        switch (preferences) {
            case WORKSTATION:
                processorWeight = wEIGHT;
                gpuWeight = 1.0f;
                break;
            case GAMING:
                gpuWeight = wEIGHT;
                processorWeight = 1.0f;
                break;
            default:
                gpuWeight = 1.0f;
                processorWeight = 1.0f;
        }
    }

    /**
     * Sort list by performance.
     * @param list
     * @return
     * @throws CannotBuildComputerException
     */
    public List<Component> getComponentsSortedByPerformance(List<Component> list) throws CannotBuildComputerException {
        if (list.isEmpty()) {
            throw new CannotBuildComputerException();
        }
        List<Component> components = list.stream()
                .sorted(Comparator.comparing(component -> component.getPerformancePoints()))
                .collect(Collectors.toList());
        return components;
    }

    /**
     * Build computer according to budget and type.
     * @param budget
     * @param preferences
     * @param type
     * @return
     * @throws CannotBuildComputerException
     */
    public Computer buildComputer(int budget, Preferences preferences, ComputerType type)
            throws CannotBuildComputerException {
        setWeights(preferences);
        Computer computer = null;
        psuList = store.filterByType(Component.Type.PSU);
        gpuList = getComponentsSortedByPerformance(store.filterByType(Component.Type.GPU));
        cpuList = getComponentsSortedByPerformance(store.filterByType(Component.Type.CPU));
        ramList = getComponentsSortedByPerformance(store.filterByType(Component.Type.RAM));
        motherboardList = getComponentsSortedByPerformance(store.filterByType(Component.Type.MOTHERBOARD));
        casesList = store.getComponentsSortedByPrice(store.filterByType(Component.Type.CASE));
        harddiskList = store.filterByType(Component.Type.HDD);
        harddiskList.addAll(store.filterByType(Component.Type.SSD));
        harddiskList = getComponentsSortedByPerformance(harddiskList);
        switch (type) {
            case LAPTOP:
                computer = buildPC(budget);
                break;
            case PC:
                computer = buildLaptop(budget);
                break;
            default:
                break;
        }
        return computer;
    }

    /**
     * Build PC.
     * @param budget
     * @return
     * @throws CannotBuildComputerException
     */
    public Computer buildPC(int budget) throws CannotBuildComputerException {
        List<Component> combination = null;
        int bestPerformance = 0;
        int performanceOfCombination;
        for (Component case1: casesList) {
            for (Component harddisk: harddiskList) {
                for (Component gpu : gpuList) {
                    for (Component cpu : cpuList) {
                        for (Component ram : ramList) {
                            for (Component motherboard : motherboardList) {
                                for (Component psu : psuList) {
                                    if (psu.getPowerConsumption() >= (gpu.getPowerConsumption()
                                            + cpu.getPowerConsumption() + ram.getPowerConsumption()
                                            + motherboard.getPowerConsumption() + harddisk.getPowerConsumption())
                                            && budget >= (case1.getPrice() + psu.getPrice() + gpu.getPrice()
                                            + cpu.getPrice() + ram.getPrice() + motherboard.getPrice()
                                            + harddisk.getPrice()) * store.getProfitMargin()) {
                                        performanceOfCombination = psu.getPerformancePoints()
                                                + gpu.getPerformancePoints() + cpu.getPerformancePoints()
                                                + ram.getPerformancePoints() + motherboard.getPerformancePoints()
                                                + harddisk.getPerformancePoints();
                                        if (performanceOfCombination > bestPerformance) {
                                            bestPerformance = performanceOfCombination;
                                            combination = List.of(cpu, gpu, ram, motherboard, harddisk, psu, case1);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (combination.isEmpty()) {
            throw new CannotBuildComputerException();
        }
        return new PC(combination.get(0), combination.get(1), combination.get(2), combination.get(3),
                combination.get(4), combination.get(5), combination.get(6));
    }

    /**
     * Build Laptop.
     * @param budget
     * @return
     * @throws CannotBuildComputerException
     */
    public Computer buildLaptop(int budget) throws CannotBuildComputerException {
        List<Component> combination = null;
        int bestPerformance = 0;
        int performanceOfCombination;
        keyboardList = store.getComponentsSortedByPrice(store.filterByType(Component.Type.KEYBOARD));
        screenList = store.getComponentsSortedByPrice(store.filterByType(Component.Type.SCREEN));
        batteryList = store.getComponentsSortedByPrice(store.filterByType(Component.Type.BATTERY));
        touchpadList = store.getComponentsSortedByPrice(store.filterByType(Component.Type.TOUCHPAD));
        for (Component case1: casesList) {
            for (Component harddisk: harddiskList) {
                for (Component gpu : gpuList) {
                    for (Component cpu : cpuList) {
                        for (Component ram : ramList) {
                            for (Component motherboard : motherboardList) {
                                for (Component psu : psuList) {
                                    for (Component keyboard: keyboardList) {
                                        for (Component touchpad: touchpadList) {
                                            for (Component battery: batteryList) {
                                                for (Component screen: screenList) {
                                                    if (psu.getPowerConsumption() + battery.getPowerConsumption()
                                                            >= (gpu.getPowerConsumption() + cpu.getPowerConsumption()
                                                            + ram.getPowerConsumption()
                                                            + motherboard.getPowerConsumption()
                                                            + harddisk.getPowerConsumption())
                                                            + keyboard.getPowerConsumption()
                                                            + screen.getPowerConsumption()
                                                            + touchpad.getPowerConsumption()
                                                            && budget >= (case1.getPrice() + psu.getPrice()
                                                            + gpu.getPrice() + battery.getPrice() + screen.getPrice()
                                                            + touchpad.getPrice() + keyboard.getPrice()
                                                            + cpu.getPrice() + ram.getPrice() + motherboard.getPrice()
                                                            + harddisk.getPrice()) * store.getProfitMargin()) {
                                                        performanceOfCombination = psu.getPerformancePoints()
                                                                + gpu.getPerformancePoints()
                                                                + cpu.getPerformancePoints()
                                                                + ram.getPerformancePoints()
                                                                + motherboard.getPerformancePoints()
                                                                + harddisk.getPerformancePoints();
                                                        if (performanceOfCombination > bestPerformance) {
                                                            bestPerformance = performanceOfCombination;
                                                            combination = List.of(screen, keyboard, battery, touchpad,
                                                                    cpu, gpu, ram, motherboard, harddisk, psu, case1);
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (combination.isEmpty()) {
            throw new CannotBuildComputerException();
        }
        Laptop laptop = new Laptop(combination.get(4), combination.get(5), combination.get(6), combination.get(7),
                combination.get(8), combination.get(9), combination.get(10))
                .setScreen(combination.get(0))
                .setKeyboard(combination.get(1))
                .setBattery(combination.get(2))
                .setTouchpad(combination.get(3));
        return laptop;
    }
}
