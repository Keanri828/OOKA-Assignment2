package org.hbrs.ooka.ws2020.uebung2.runtime;

import org.hbrs.ooka.ws2020.uebung2.assembler.ComponentAssembler;
import org.hbrs.ooka.ws2020.uebung2.assembler.ComponentThread;
import org.hbrs.ooka.ws2020.uebung2.component.Component;

import java.util.ArrayList;
import java.util.List;

public class RuntimeEnv {

    private List<Component> comps = new ArrayList<>();

    public void initComponent(String name) throws Exception{
        ComponentAssembler comAss = new ComponentAssembler();
        Component com = comAss.loadClasses("Counter");
        ComponentThread thread = new ComponentThread(name, com);

        String[] param = new String[0];
        System.out.println(com.getStart().getName());
        com.getStart().invoke(null, (Object) param);
    }

    public void deleteComponent(String name){
        for (Component com : comps){
            if(com.getName().equals(name)){
                comps.remove(com);
            }
        }
    }

    public void startComp(String name){

    }

    public void stopComp(String name){

    }

    public void getState(String name){

    }
}
