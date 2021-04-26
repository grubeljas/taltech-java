package ee.taltech.iti0202.computerstore.computer;

import ee.taltech.iti0202.computerstore.components.Component;

import java.util.ArrayList;
import java.util.List;

public abstract class Computer {
    private Component harddisk, cpu, gpu, ram, motherboard, psu, case1;
    public boolean isLaptop;
    List<Component> componentList = new ArrayList<>();


    public Component getHarddisk() {
        return harddisk;
    }

    public Computer setHarddisk(Component harddisk) {
        this.harddisk = harddisk;
        componentList.add(harddisk);
        return this;
    }

    public Component getRam() {
        return ram;
    }

    public Computer setRam(Component ram) {
        this.ram = ram;
        componentList.add(ram);
        return this;
    }

    public Component getGpu() {
        return gpu;
    }

    public Computer setGpu(Component gpu) {
        this.gpu = gpu;
        componentList.add(gpu);
        return this;
    }

    public Component getCpu() {
        return cpu;
    }

    public Computer setCpu(Component cpu) {
        this.cpu = cpu;
        componentList.add(cpu);
        return this;
    }

    public Component getMotherboard() {
        return motherboard;
    }

    public Computer setMotherboard(Component motherboard) {
        this.motherboard = motherboard;
        componentList.add(motherboard);
        return this;
    }

    public Component getPsu() {
        return psu;
    }

    public Computer setPsu(Component psu) {
        this.psu = psu;
        componentList.add(psu);
        return this;
    }

    public Component getCase1() {
        return case1;
    }

    public Computer setCase1(Component case1) {
        this.case1 = case1;
        componentList.add(case1);
        return this;
    }
}
