package org.hbrs.ooka.ws2020.uebung2.runtime;

import org.hbrs.ooka.ws2020.uebung2.assembler.ComponentAssembler;

public class RuntimeEnv {
    public init() {
        ComponentAssembler comAss = new ComponentAssembler();
        comAss.loadClasses("Counter");
    }
}
