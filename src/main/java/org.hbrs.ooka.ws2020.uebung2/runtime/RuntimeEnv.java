package org.hbrs.ooka.ws2020.uebung2.runtime;

import org.hbrs.ooka.ws2020.uebung2.assembler.ComponentAssembler;
import org.hbrs.ooka.ws2020.uebung2.component.Component;

public class RuntimeEnv {
    public void initComponent(String name) throws Exception{
        ComponentAssembler comAss = new ComponentAssembler();
        Component com = comAss.loadClasses("Counter");
        String[] param = new String[0];
        System.out.println(com.getStart().getName());
        com.getStart().invoke(null, (Object) param);
    }
}
