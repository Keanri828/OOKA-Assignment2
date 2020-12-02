package org.hbrs.ooka.ws2020.uebung2.assembler;

import org.hbrs.ooka.ws2020.uebung2.component.Component;
import org.hbrs.ooka.ws2020.uebung2.util.ComponentContainer;
import org.hbrs.ooka.ws2020.uebung2.util.Start;

import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.lang.ClassLoader.*;

public class AssemblerTest {
    public static void main(String[] args) throws Exception {
        //System.out.println("Test n Stuff");

        ComponentContainer con = ComponentContainer.getInstance();


        JarFile jarFile = new JarFile("Counter.jar");
        Enumeration<JarEntry> e = jarFile.entries();

        URL[] urls = {new URL("jar:file:" + "Counter.jar" + "!/")};
        URLClassLoader cl = URLClassLoader.newInstance(urls);

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
            Component com = new Component();
            com.setC(c);
            com.setClassLoader(cl);
            com.setName(className);
            con.add(com);
        }


        Component com = con.search("Client");
        Class cla = com.getC();
        Method method = null;
        Method[] meth = cla.getMethods();
        for (Method me : meth) {
            if (me.isAnnotationPresent(Start.class))
                method = me;
            System.out.println(me.getName());
        }
        //method = cla.getMethod("main", String[].class);
        System.out.println("Method was created successfully");

        String[] params = new String[0];

        method.invoke(null, (Object) params);
        System.out.println("Method run successfully");
        /*Component com = con.search("Client");
        Class cla = com.getC();
        Method method = null;
        Method[] meth = cla.getMethods();
        for (Method me : meth) {
            System.out.println(me.getName());
        }
        method = cla.getMethod("main", String[].class);
        System.out.println("Method was created successfully");

        String[] params = new String[0];

        method.invoke(null, (Object) params);
        System.out.println("Method run successfully");*/

    }
}