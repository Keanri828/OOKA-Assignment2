package org.hbrs.ooka.ws2020.uebung2.assembler;

import org.hbrs.ooka.ws2020.uebung2.component.Component;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.lang.reflect.Method;

public class ComponentThread extends Thread{
    private volatile boolean stop = false;
    private Component comp;

    public ComponentThread(String name, Component c) {
        this.setName(name);
        this.comp = c;
    }

    /**
     * Methode zur Bereitstellung von Interfaces für Komponenteninteraktion
     */
    public void getInterfaces() {
        // TODO: ggf. später, für Komponenteninteraktion
        throw new NotImplementedException();
    }

    /**
     * wird gestartet durch Thread.start(), kann von außen mit Thread.interrupt()
     * unterbrochen werden
     */
    public void run() {
        Method startMethod = comp.getStart();
        Class<?> startClass = startMethod.getDeclaringClass();
        // TODO: Objekt generieren via ClassLoader
        Class<?> startReturnType = startMethod.getReturnType(); // if needed
        
        Method endMethod = comp.getEnd();
        Class<?> endClass = endMethod.getDeclaringClass();
        // TODO: Objekt generieren via ClassLoader
        Class<?> endReturnType = endMethod.getReturnType(); // if needed
        try {
            // hier: Generierung eines Objektes der Klasse mit Start-Methode
            startMethod.invoke(); //TODO: Invoke Object?!
        } catch (InterruptedException e) {
            // hier: ... der Klasse mit End-Methode
            endMethod.invoke(); //TODO: Invoke Object
        }
    }

    /**
     * signal the Thread to stop (möglicherweise nicht benötigt)
     */
    public void setStop() {
        stop = true;
    }
}
