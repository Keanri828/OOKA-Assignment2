package org.hbrs.ooka.ws2020.uebung2.assembler;

import org.hbrs.ooka.ws2020.uebung2.component.Component;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ComponentAssembler {
    public Component loadClasses(String compName) throws ClassNotFoundException, IOException {
        JarFile jarFile = new JarFile("Counter.jar");
        Enumeration<JarEntry> e = jarFile.entries();

        URL[] urls = {new URL("jar:file:" + "Counter.jar" + "!/")};
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
        return com;
    }
}
