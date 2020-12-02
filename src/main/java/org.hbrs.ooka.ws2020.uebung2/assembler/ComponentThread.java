package org.hbrs.ooka.ws2020.uebung2.assembler;

import org.hbrs.ooka.ws2020.uebung2.component.Component;

import java.lang.reflect.Method;

public class ComponentThread extends Thread{
    private volatile boolean stop = false;
    private Component comp;

    public ComponentThread(String name, Component c) {
        this.setName(name);
        this.comp = c;
    }

    public void run() {
        // der Code, den ein erzeugter Thread ausführen soll

    }

    /**
     * signal the Thread to stop
     */
    public void setStop() {
        stop = true;
    }
}
