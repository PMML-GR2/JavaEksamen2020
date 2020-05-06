package client;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Bruker implements Comparable, Serializable {
    private int personID;
    private String fulltNavn;
    private String kjonn;
    private int alder;
    private List<String> interresser;
    private String bosted;
    private String tlfNr;
    private int poengSum;


    public Bruker(int personID, String fulltNavn, String kjonn, int alder, ArrayList interresser, String bosted, String tlfNr) {
        this.personID = personID;
        this.fulltNavn = fulltNavn;
        this.kjonn = kjonn;
        this.alder = alder;
        this.interresser = interresser;
        this.bosted = bosted;
        this.tlfNr = tlfNr;
    }

    public Bruker() {
    }

    public int getPersonID() {
        return personID;
    }

    public void setPersonID(int personID) {
        this.personID = personID;

    }

    public String getFulltNavn() {
        return fulltNavn;
    }

    public void setFulltNavn(String fulltNavn) {
        this.fulltNavn = fulltNavn;
    }


    public String getKjonn() {
        return kjonn;
    }

    public void setKjonn(String kjonn) {
        this.kjonn = kjonn;
    }

    public int getAlder() {
        return alder;
    }

    public void setAlder(int alder) {
        this.alder = alder;
    }

    public List<String> getInterresser() {
        return interresser;
    }

    public void setInterresser(List<String> interresser) {
        this.interresser = interresser;
    }

    public String getBosted() {
        return bosted;
    }

    public void setBosted(String bosted) {
        this.bosted = bosted;
    }

    public String getTlfNr() {
        return tlfNr;
    }

    public void setTlfNr(String tlfNr) {
        this.tlfNr = tlfNr;
    }

    public int getPoengSum() {
        return poengSum;
    }

    public void setPoengSum(int poengSum) {
        this.poengSum = poengSum;
    }

    @Override
    public String toString() {
        return "Bruker{" +
                "personID=" + personID +
                ", fullt navn='" + fulltNavn + '\'' +
                ", kjonn='" + kjonn + '\'' +
                ", alder=" + alder +
                ", interresser=" + interresser +
                ", bosted='" + bosted + '\'' +
                ", tlfNr='" + tlfNr + '\'' +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        Bruker bruker2 = (Bruker)o;
        if(poengSum > bruker2.getPoengSum()){
            return 1;
        }else if(poengSum == bruker2.getPoengSum()){
            return 0;
        }
        return -1;
    }
}
