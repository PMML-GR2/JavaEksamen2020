package client;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataService {

    private static DataService dataService;

     private List<Bruker> matchBrukere = new ArrayList<>();
     private List<Bruker> lagtTilBruker = new ArrayList<>();
    private List<Bruker> brukerHarLagtTil = new ArrayList<>();

    private Bruker bruker;

    private DataService() {

    }

    public static DataService getInstance() {
        if (dataService == null) {
            dataService = new DataService();
        }
        return dataService;
    }


    private Bruker lagEksempelBruker() {
        return this.bruker;
    }

    private List<Bruker> brukerLike() {
        return this.lagtTilBruker;
    }

    private List<Bruker> eksempelLikerMeg() {
       return this.brukerHarLagtTil;
    }


    public void registrerNyBruker(Bruker bruker) {
        this.bruker = bruker;
    }

    public Bruker getBruker() {
        return bruker;
    }

     public List<Bruker> getMatchBrukere() {
        return matchBrukere;
    }

    public List<Bruker> getLagtTilBruker() {
        return lagtTilBruker;
    }

    public List<Bruker> getBrukerHarLagtTil() {
        return brukerHarLagtTil;
    }

    public void setMatchBrukere(List<Bruker> matchBrukere) {
        this.matchBrukere = matchBrukere;
    }

    public  void setLagtTilBruker(List<Bruker> lagtTilBruker) {
        this.lagtTilBruker = lagtTilBruker;
    }

    public  void setBrukerHarLagtTil(List<Bruker> brukerHarLagtTil) {
        this.brukerHarLagtTil = brukerHarLagtTil;

    }

    public  void setBruker(Bruker bruker) {
        this.bruker = bruker;
    }

    /**
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */



}

