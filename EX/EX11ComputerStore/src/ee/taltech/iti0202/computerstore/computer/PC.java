package ee.taltech.iti0202.computerstore.computer;

import ee.taltech.iti0202.computerstore.components.Component;

public class PC extends Computer {

    /**
     * Constructor.
     * @param cpu
     * @param gpu
     * @param ram
     * @param motherboard
     * @param harddisk
     * @param psu
     * @param case1
     */
    public PC(Component cpu, Component gpu, Component ram, Component motherboard, Component harddisk,
              Component psu, Component case1) {
        this.setCase1(case1);
        this.setCpu(cpu);
        this.setGpu(gpu);
        this.setRam(ram);
        this.setMotherboard(motherboard);
        this.setHarddisk(harddisk);
        this.setPsu(psu);
    }
}
