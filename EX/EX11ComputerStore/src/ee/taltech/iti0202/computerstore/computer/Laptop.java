package ee.taltech.iti0202.computerstore.computer;

import ee.taltech.iti0202.computerstore.components.Component;

public class Laptop extends Computer{
    private Component keyboard, screen, battery, touchpad;

    /**
     * Laptop.
     * @param screen
     * @param keyboard
     * @param battery
     * @param touchpad
     * @param cpu
     * @param gpu
     * @param ram
     * @param motherboard
     * @param harddisk
     * @param psu
     * @param case1
     */
    public Laptop(Component cpu, Component gpu, Component ram, Component motherboard,
                  Component harddisk, Component psu, Component case1) {
        this.setCase1(case1);
        this.setCpu(cpu);
        this.setGpu(gpu);
        this.setRam(ram);
        this.setMotherboard(motherboard);
        this.setHarddisk(harddisk);
        this.setPsu(psu);
    }

    public Component getTouchpad() {
        return touchpad;
    }

    public Computer setTouchpad(Component touchpad) {
        this.touchpad = touchpad;
        componentList.add(touchpad);
        return this;
    }

    public Component getBattery() {
        return battery;
    }

    public Computer setBattery(Component battery) {
        this.battery = battery;
        componentList.add(battery);
        return this;
    }

    public Component getScreen() {
        return screen;
    }

    public Computer setScreen(Component screen) {
        this.screen = screen;
        componentList.add(screen);
        return this;
    }

    public Component getKeyboard() {
        return keyboard;
    }

    public Computer setKeyboard(Component keyboard) {
        this.keyboard = keyboard;
        componentList.add(keyboard);
        return this;
    }
}
