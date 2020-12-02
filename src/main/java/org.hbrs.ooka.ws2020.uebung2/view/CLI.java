package org.hbrs.ooka.ws2020.uebung2.view;

import org.hbrs.ooka.ws2020.uebung2.runtime.RuntimeEnv;

import java.util.Scanner;

public class CLI{
    public void startCli()throws Exception{
        RuntimeEnv re = new RuntimeEnv();
        Scanner s = new Scanner(System.in);
        System.out.println("New RuntimeEnviroment started: \nPlease type command (init, delete, start, stop, state) followed by the Component Name");
        while(s.hasNext()){
            String command = s.next();
            if(command.equals("init")){
                re.initComponent(s.next());
            }
            else if(command.equals("delete")){
                re.deleteComponent(s.next());
            }
            else if(command.equals("start")){
                re.startComp(s.next());
            }
            else if(command.equals("stop")){
                re.stopComp(s.next());
            }
            else if(command.equals("state")){
                re.getState(s.next());
            }
        }



        s.close();
    }
}