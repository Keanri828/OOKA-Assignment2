package org.hbrs.ooka.ws2020.uebung2.assembler;

import org.hbrs.ooka.ws2020.uebung2.component.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ComponentThread extends Thread{
    private volatile boolean stop = false;
    private Component comp;

    public ComponentThread(String name, Component c) {
        this.setName(name);
        this.comp = c;
        this.setContextClassLoader(c.getClassLoader());
    }

    /**
     * Methode zur Bereitstellung von Interfaces für Komponenteninteraktion
     */
    public void getInterfaces() {
        // TODO: ggf. später, für Komponenteninteraktion
        throw new UnsupportedOperationException();
    }

    /**
     * wird gestartet durch Thread.start(), kann von außen mit Thread.interrupt()
     * unterbrochen werden
     */
    public void run() {
        // Start Objekt und Methode
        Method startMethod = comp.getStart();
        Object startObject = instantiateObjectForMethod(startMethod);
        Class<?> startReturnType = startMethod.getReturnType(); // if needed

        // End Objekt und Methode
        Method endMethod = comp.getEnd();
        Object endObject = instantiateObjectForMethod(endMethod);
        Class<?> endReturnType = endMethod.getReturnType(); // if needed

        try {
            // hier: Generierung eines Objektes der Klasse mit Start-Methode
            Object startReturnedObject = startMethod.invoke(startObject); // aber wie hier unterbrechen, falls die Start-Methode endlos ist?!
            // TODO ??? rufe eine Methode endlos auf, wenn obige nur init UND ENDLICH ist
            // startReturnType.cast(startReturnedObject).doSomethingAnnotated()
            if (Thread.interrupted())
                throw new InterruptedException();
        } catch (InterruptedException | IllegalAccessException | InvocationTargetException e) {
            // hier: ... der Klasse mit End-Methode
            try {
                Object endReturnedObject = endMethod.invoke(endObject);
            } catch (IllegalAccessException | InvocationTargetException illegalAccessException) {
                illegalAccessException.printStackTrace();
            }
        }
    }

    /**
     * Generate instantiation of an object for given
     * @param m Method
     * @return mObject
     */
    private Object instantiateObjectForMethod(Method m) {
        String mClassName = m.getDeclaringClass().getName(); // get Class-name of declaring class
        Object mObject = null;
        try {
            mObject = this.getContextClassLoader().loadClass(mClassName).getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return mObject;
    }

    /**
     * signal the Thread to stop (möglicherweise nicht benötigt)
     */
    public void setStop() {
        stop = true;
    }
}
