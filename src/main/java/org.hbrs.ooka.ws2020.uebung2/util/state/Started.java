package org.hbrs.ooka.ws2020.uebung2.util.state;


import org.hbrs.ooka.ws2020.uebung2.component.Component;

public class Started implements ComponentState{
    @Override
    public void next(Component com) {
        com.setState(new Stopped());
    }

    @Override
    public void prev(Component com) {
        com.setState(new Stopped());
    }

    @Override
    public String printStatus() {
        return("Component Started");
    }

}
