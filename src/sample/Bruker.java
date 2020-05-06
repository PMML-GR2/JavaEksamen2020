package sample;

import java.io.Serializable;
import java.util.ArrayList;

public class Bruker implements Comparable, Serializable {
    int personID;
    private String fornavn;
    private String etternavn;
    private String kjonn;
    private int alder;
    private ArrayList interresser;
    private String bosted;
    private String tlfNr;
    private int poengSum;


   
    public Bruker () {

    }

    public Bruker(int personID, String fornavn, String kjonn, int alder, ArrayList interresser, String bosted, String tlfNr) {
        this.personID = personID;
        this.fornavn = fornavn;
        this.kjonn = kjonn;
        this.alder = alder;
        this.interresser = interresser;
        this.bosted = bosted;
        this.tlfNr = tlfNr;
    }


    public int getPersonID() {
        return personID;
    }

    public void setPersonID(int personID) {
        this.personID = personID;

    }

    public String getFornavn() {
        return fornavn;
    }

    public void setFornavn(String fornavn) {
        this.fornavn = fornavn;
    }

    public String getEtternavn() {
        return etternavn;
    }

    public void setEtternavn(String etternavn) {
        this.etternavn = etternavn;
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

    public ArrayList getInterresser() {
        return interresser;
    }

    public void setInterresser(ArrayList interresser) {
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
                ", fornavn='" + fornavn + '\'' +
                ", etternavn='" + etternavn + '\'' +
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
