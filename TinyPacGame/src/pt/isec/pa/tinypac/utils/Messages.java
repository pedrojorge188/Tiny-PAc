package pt.isec.pa.tinypac.utils;

import java.util.HashSet;

/**
 * Esta classe utiliza o modelo Singleton
 * onde é gerido as logs do programa.
 * Esta classe apenas permite a criação de uma instancia no programa inteiro
 */

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

    /**
     * Limpa todas as logs localmente armazenadas
     */
    public void clearLogs(){
        logs.clear();
    }

    public void addLog(String str){
        logs.add(str);
    }

    /**
     * Lista todas as logs localmente armazenadas
     * @return
     */

    public String listLogs(){

        StringBuilder sb = new StringBuilder("");

        for(String e: logs){
            sb.append(e).append("\n");
        }

        return sb.toString();

    }

}
