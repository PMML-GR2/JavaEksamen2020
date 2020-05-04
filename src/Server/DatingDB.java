package Server;

import java.util.ArrayList;

public class DatingDB {

   static public void insertData(String navn,char k, int alder, ArrayList<String> interesser, String bosted, String tlf){
        System.out.println(navn + k + alder + bosted + tlf);
        for(String s: interesser) {
            System.out.println(s);
        }
    }
}
