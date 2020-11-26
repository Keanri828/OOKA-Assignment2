package org.hbrs.ooka.ws2020.uebung2.util;

import org.hbrs.ooka.ws2020.uebung2.component.Component;

public interface ComponentState {
    void next(Component com);
    void prev(Component com);
    void printStatus();
}
