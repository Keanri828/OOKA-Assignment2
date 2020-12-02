package org.hbrs.ooka.ws2020.uebung2.runtime;

import org.hbrs.ooka.ws2020.uebung2.assembler.ComponentAssembler;
import org.hbrs.ooka.ws2020.uebung2.assembler.ComponentThread;
import org.hbrs.ooka.ws2020.uebung2.component.Component;

import java.util.ArrayList;
import java.util.List;

public class RuntimeEnv {

    private List<ComponentThread> list = new ArrayList<>();

    public void initComponent(String name) throws Exception{
        ComponentAssembler comAss = new ComponentAssembler();
        Component com = comAss.loadClasses("Counter");
        ComponentThread thread = new ComponentThread(name, com);

        list.add(thread);



        /*String[] param = new String[0];
        System.out.println(com.getStart().getName());
        com.getStart().invoke(null, (Object) param);*/
    }

    public void deleteComponent(String name){
        for (ComponentThread com : list){
            if(com.getName().equals(name)){
                list.remove(com);
            }
        }
    }

    public void startComp(String name){
        for (ComponentThread com : list){
            if(com.getName().equals(name)){
                com.start();
            }
        }
    }

    public void stopComp(String name){
        for (ComponentThread com : list){
            if(com.getName().equals(name)){
                try {
                    com.stopComponentThread();
                }catch(Exception e){

                }
            }
        }
    }

    public void getState(String name){
        for (ComponentThread com : list){
            if(com.getName().equals(name)){
                com.stop();
            }
        }
    }
}
