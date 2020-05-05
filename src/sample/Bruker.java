package sample;

import java.util.ArrayList;

public class Bruker {
    private String fornavn;
    private String etternavn;
    private Character kjonn;
    private int alder;
    private ArrayList interresser;
    private String bosted;
    private String tlfNr;

    public Bruker() {

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

    public Character getKjonn() {
        return kjonn;
    }

    public void setKjonn(Character kjonn) {
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
}
