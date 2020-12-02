/*package org.hbrs.ooka.ws2020.uebung2.deprecated;

import org.hbrs.ooka.ws2020.uebung2.component.Component;

import java.util.ArrayList;
import java.util.List;

public class ComponentContainer {
    private ComponentContainer(){}
    private static ComponentContainer instance;
    public static ComponentContainer getInstance(){
        if (instance == null){
            return new ComponentContainer();
        }else {
            return instance;
        }
    }
    private List<Component> con = new ArrayList<>();

    public List<Component> getCon() {
        return con;
    }

    public void setCon(List<Component> list) {
        this.con = con;
    }

    public Component search(String name){
        for (Component i : con ){
            if (i.getName().contains(name)) {
                return i;
            }
        }
        return null;
    }
    public void add(Component a){
        con.add(a);
    }
}
*/