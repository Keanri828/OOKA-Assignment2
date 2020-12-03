package org.hbrs.ooka.ws2020.uebung2.runtime;

import org.hbrs.ooka.ws2020.uebung2.assembler.ComponentAssembler;
import org.hbrs.ooka.ws2020.uebung2.assembler.ComponentThread;
import org.hbrs.ooka.ws2020.uebung2.component.Component;

import java.util.ArrayList;
import java.util.List;

public class RuntimeEnv {

    private List<ComponentThread> list = new ArrayList<>();

    public String initComponent(String name, String path) throws Exception{
        ComponentAssembler comAss = new ComponentAssembler();
        Component com = comAss.loadClasses(name, path);
        ComponentThread thread = new ComponentThread(name, com);

        list.add(thread);
        return "Init: "+name;


        /*String[] param = new String[0];
        System.out.println(com.getStart().getName());
        com.getStart().invoke(null, (Object) param);*/
    }

    public String deleteComponent(String name){
        for (ComponentThread com : list){
            if(com.getName().equals(name)){
                list.remove(com);
                return "Deleted: "+name;
            }
        }
        return "Nothing Deleted: "+name;
    }

    public String startComp(String name){
        for (ComponentThread com : list){
            if(com.getName().equals(name)){
                try {
                    com.start();
                }catch (IllegalThreadStateException e){
                    list.remove(com);
                    ComponentThread newCom = new ComponentThread(com.getName(), com.getComp());
                    list.add(newCom);
                    newCom.start();
                }
                return "Started: "+name;
            }
        }
        return "Nothing Started: "+name;
    }

    public String stopComp(String name){
        for (ComponentThread com : list){
            if(com.getName().equals(name)){
                try {
                    com.stopComponentThread();
                    com.interrupt();
                    return "Stopped: "+name;
                }catch(Exception e){

                }
            }
        }
        return "Not Working did not Stop: "+name;
    }

    public String getState(String name){
        for (ComponentThread com : list){
            if(com.getName().equals(name)){
                return "State of Component: "+name+" is: "+com.getComponentState().printStatus();

            }
        }
        return "No State found to Component: "+name;
    }

    public String getThreadListString() {
        StringBuilder sBuilder = new StringBuilder();
        for (ComponentThread currThread : list) {
            String substring = "ID: " + String.valueOf(currThread.getId())
                    + ", Name: " + currThread.getName()
                    + ", State: " + currThread.getComponentState().printStatus()
                    + "\n";
            sBuilder.append(substring);
        }
        return sBuilder.toString();
    }
}
