package org.hbrs.ooka.ws2020.uebung2.component;

import org.hbrs.ooka.ws2020.uebung2.util.ComponentState;
import org.hbrs.ooka.ws2020.uebung2.util.Stopped;

import java.lang.reflect.Method;

public class Component {
    private String name;
    private ComponentState state = new Stopped();
    private ClassLoader classLoader;
    private Class c;
    private Method start;
    private Method end;

    public Class getC() {
        return c;
    }

    public void setC(Class c) {
        this.c = c;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
