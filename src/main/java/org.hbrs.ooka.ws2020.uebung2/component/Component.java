package org.hbrs.ooka.ws2020.uebung2.component;

import org.hbrs.ooka.ws2020.uebung2.util.ComponentState;
import org.hbrs.ooka.ws2020.uebung2.util.Stopped;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;

public class Component {
    private ComponentState state = new Stopped();
    private ClassLoader classLoader;
    //private List<String> name;
    //private List<Class> c;
    private HashMap<String, Class> map= new HashMap<>();
    private Method start;
    private Method end;

    public Class getClass(String name){
        return map.get(name);
    }
    public Class[] getKlass(){
        return map.values().toArray(new Class[map.values().size()]);
    }
    public void setClass(String name, Class c){
        map.put(name, c);
    }

    public ComponentState getState() {
        return state;
    }

    public void setState(ComponentState state) {
        this.state = state;
    }

    public ClassLoader getClassLoader() {
        return classLoader;
    }

    public void setClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    public Method getStart() {
        return start;
    }

    public Method getEnd() { return end; }

    public void setStart(Method start) {
        this.start = start;
    }

    public void previousState() {
        state.prev(this);
    }

    public void nextState() {
        state.next(this);
    }

    public void printStatus() {
        state.printStatus();
    }

}
