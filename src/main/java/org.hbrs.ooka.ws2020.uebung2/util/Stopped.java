package org.hbrs.ooka.ws2020.uebung2.util;

import org.hbrs.ooka.ws2020.uebung2.component.Component;

public class Stopped implements ComponentState{
    @Override
    public void next(Component com) {
        com.setState(new Started());
    }

    @Override
    public void prev(Component com) {
        com.setState(new Started());
    }

    @Override
    public void printStatus() {
        System.out.println("Component Stopped");
    }
}
