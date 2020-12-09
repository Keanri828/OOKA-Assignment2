package org.hbrs.ooka.ws2020.uebung2.logger;

import java.sql.Timestamp;

public class Logger implements LoggerInterface{

    public void sendLog(String str){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println("+++ Runtime-Log: Meldung aus Component: "+str+"("+timestamp+")");

    }
}