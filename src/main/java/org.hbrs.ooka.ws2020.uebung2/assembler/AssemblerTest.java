package org.hbrs.ooka.ws2020.uebung2.assembler;

public class AssemblerTest {
    public static void main(String[] args) throws Exception {
        ComponentAssembler comAss = new ComponentAssembler();
        comAss.loadClasses("Counter");
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