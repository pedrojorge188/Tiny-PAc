package pt.isec.pa.tinypac.utils;

import java.util.HashSet;

public class Messages {

    private static Messages instance = null;

    public static Messages getInstance() {
        if(instance == null)
            instance = new Messages();
        return instance;
    }

    private HashSet<String> logs ;


    private Messages(){
        logs = new HashSet<>();
    }

    public void clearLogs(){
        logs.clear();
    }

    public void addLog(String str){
        logs.add(str);
    }

    public String listLogs(){

        StringBuilder sb = new StringBuilder("Logs States:\n");

        for(String e: logs){
            sb.append(e).append("\n");
        }

        return sb.toString();

    }

}
