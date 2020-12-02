package org.hbrs.ooka.ws2020.uebung2.assembler;

import org.hbrs.ooka.ws2020.uebung2.component.Component;
import org.hbrs.ooka.ws2020.uebung2.runtime.RuntimeEnv;
import org.hbrs.ooka.ws2020.uebung2.view.CLI;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ComponentAssembler {
    public Component loadClasses(String compName) throws ClassNotFoundException, IOException {
        JarFile jarFile = new JarFile(compName + ".jar");
        Enumeration<JarEntry> e = jarFile.entries();

        URL[] urls = {new URL("jar:file:" + compName + ".jar" + "!/")};
        URLClassLoader cl = URLClassLoader.newInstance(urls);
        Component com = new Component();
        com.setClassLoader(cl);

        while (e.hasMoreElements()) {
            JarEntry je = e.nextElement();
            if (je.isDirectory() || !je.getName().endsWith(".class")) {
                continue;
            }
            // -6 because of .class
            String className = je.getName().substring(0, je.getName().length() - 6);
            className = className.replace('/', '.');
            System.out.println(className);
            Class c = cl.loadClass(className);
            com.setClass(className, c);
        }


        Class startAno = com.getClass("Start");
        Class stopAno = com.getClass("Stop");

        System.out.println();
        Method startMethod = null;
        Method stopMethod = null;
        for (Class cla : com.getKlass()) {
            for (Method me : cla.getMethods()) {
                if (me.isAnnotationPresent(startAno)){
                    startMethod = me;}
                if (me.isAnnotationPresent(stopAno)){
                    stopMethod = me;}
                //System.out.println(me.getAnnotations().getClass().getName());
            }
        }
        //System.out.print(startMethod.getName());
        com.setStart(startMethod);
        com.setStop(stopMethod);
        com.setName(compName);
        return com;
    }

}
