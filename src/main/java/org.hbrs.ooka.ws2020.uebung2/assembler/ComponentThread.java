package org.hbrs.ooka.ws2020.uebung2.assembler;

import org.hbrs.ooka.ws2020.uebung2.component.Component;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.lang.reflect.Method;

public class ComponentThread extends Thread{
    private volatile boolean stop = false;
    private Component comp;

    public ComponentThread(String name, Component c, ClassLoader cl) {
        this.setName(name);
        this.comp = c;
        this.setContextClassLoader(cl);
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
        // Start Objekt und Methode
        Method startMethod = comp.getStart();
        Class<?> startClass = startMethod.getDeclaringClass();
        Object startObject = startClass.getDeclaredConstructor().newInstance();
        Class<?> startReturnType = startMethod.getReturnType(); // if needed

        // End Objekt und Methode
        Method endMethod = comp.getEnd();
        Class<?> endClass = endMethod.getDeclaringClass();
        Object endObject = endClass.getDeclaredConstructor().newInstance();
        Class<?> endReturnType = endMethod.getReturnType(); // if needed

        try {
            // hier: Generierung eines Objektes der Klasse mit Start-Methode
            startMethod.invoke(startObject);
        } catch (InterruptedException e) {
            // hier: ... der Klasse mit End-Methode
            endMethod.invoke(endObject);
        }
    }

    /**
     * signal the Thread to stop (möglicherweise nicht benötigt)
     */
    public void setStop() {
        stop = true;
    }
}
