package Server;

import java.util.ArrayList;

public class DatingDB {

   static public void insertData(String navn,char k, int alder, ArrayList<String> interesser, String bosted, String tlf){
        System.out.println(navn + k + alder + bosted + tlf);
        for(String s: interesser) {
            System.out.println(s);
        }
    }

    static public void  selectTableWhere(int personID, char k, ArrayList alder){
       System.out.println("Fant en " + k + " i alder fra " + alder.get(0)+ " - " + alder.get(1)+ " til person " + personID);
    }
}
