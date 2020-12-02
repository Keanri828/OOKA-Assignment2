package org.hbrs.ooka.ws2020.uebung2.assembler;

import org.hbrs.ooka.ws2020.uebung2.component.Component;
import org.hbrs.ooka.ws2020.uebung2.util.state.ComponentState;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ComponentThread extends Thread{
    private volatile boolean stop = false;
    private Component comp;
    private Method startMethod;
    private Method endMethod;

    public ComponentThread(String name, Component c) {
        this.setName(name);
        this.comp = c;
        this.setContextClassLoader(c.getClassLoader());
        this.startMethod = c.getStart();
        this.endMethod = c.getEnd();
    }

    public ComponentState getComponentState() {
        return this.comp.getState();
    }

    /**
     * Methode zur Bereitstellung von Interfaces für Komponenteninteraktion
     */
    public void getInterfaces() {
        // TODO: ggf. später, für Komponenteninteraktion
        throw new UnsupportedOperationException();
    }

    /**
     * Method to stop the underlying Component
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public void stopComponentThread() throws InvocationTargetException, IllegalAccessException {
        // End Objekt und Methode
        Object endObject = instantiateObjectForMethod(this.endMethod);
        Class<?> endReturnType = this.endMethod.getReturnType(); // if needed

        this.endMethod.invoke(endObject);
        this.comp.nextState();
    }

    /**
     * wird gestartet durch Thread.start(), (ursprüngliche Idee: kann von außen mit Thread.interrupt()
     * unterbrochen werden)
     * Statt interrupt bietet der Thread jetzt die End-Methode nach außen an - evtl. fehleranfällig je nach Component
     */
    public void run() {
        // Start Objekt und Methode
        Object startObject = instantiateObjectForMethod(this.startMethod);
        Class<?> startReturnType = this.startMethod.getReturnType(); // if needed

        try {
            // hier: Generierung eines Objektes der Klasse mit Start-Methode
            String[] param = new String[0];
            this.comp.nextState();
            Object startReturnedObject = this.startMethod.invoke(startObject, (Object) param);
            if (Thread.interrupted()) {
                throw new InterruptedException();
            }
            // ursprüngliche Idee: Beenden per thread.interrupt() - funktioniert aber bei endlosen start-Methoden nicht
            // startReturnType.cast(startReturnedObject).doSomethingAnnotated()
        } catch (InterruptedException e) {
            return;
        } catch (IllegalAccessException | InvocationTargetException e) {
           e.printStackTrace();
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
