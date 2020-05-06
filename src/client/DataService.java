package client;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class DataService {

    private static DataService dataService;

    protected List<Bruker> matchBrukere;
    private List<Bruker> lagtTilBruker;
    private List<Bruker> brukerHarLagtTil;
    private Bruker bruker;

    private DataService() {
        eksempelBrukere();
        eksempelLikerMeg();
        lagtTilBruker = new ArrayList<>();
    }

    public static DataService getInstance() {
        if (dataService == null) {
            dataService = new DataService();
        }
        return dataService;
    }

    public void registrerNyBruker(Bruker bruker) {
        this.bruker = bruker;
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

    private Bruker lagEksempelBruker(int personID, String kjonn, List<String> interesse, int alder, String bosted, String fulltNavn, String mobil) {
        Bruker bruker = new Bruker();
        bruker.setPersonID(personID);
        bruker.setKjonn(kjonn);
        bruker.setInterresser(interesse);
        bruker.setAlder(alder);
        bruker.setBosted(bosted);
        bruker.setFulltNavn(fulltNavn);
        bruker.setTlfNr(mobil);

        return bruker;
    }

    public void eksempelBrukere() {
        matchBrukere = new ArrayList<>();
        matchBrukere.add(lagEksempelBruker(1, "Kvinne", Arrays.asList("Hund", "Maling", "yoga"), 21, "Bø", "Olga", "99999999"));
        matchBrukere.add(lagEksempelBruker(2, "Kvinne", Arrays.asList("Ski", "fjellturer", "musikk"), 42, "Oslo", "Klara Klok", "1234356789"));
        matchBrukere.add(lagEksempelBruker(3, "Kvinne", Arrays.asList("musikk", "Fjellklatring", "ski"), 39, "Asker", "Kari McDuck", "777888666"));
        matchBrukere.add(lagEksempelBruker(4, "Mann", Arrays.asList("ski", "klatre", "vin"), 37, "Svelgfoss", "Tore", "66666666"));
        matchBrukere.add(lagEksempelBruker(5, "Mann", Arrays.asList("Hest", "Fotball", "Bil"), 19, "Svelgfoss", "Arne", "66666666"));
        matchBrukere.add(lagEksempelBruker(6, "Mann", Arrays.asList("Hest", "håndball", "Bil"), 37, "Svelgfoss", "Madt", "66666666"));
        matchBrukere.add(lagEksempelBruker(7, "Mann", Arrays.asList("Hest", "Fotball", "Bil"), 47, "Svelgfoss", "Knut", "66666666"));
        matchBrukere.add(lagEksempelBruker(8, "Mann", Arrays.asList("Hest", "karate", "Bil"), 39, "Svelgfoss", "Henrik", "66666666"));
        matchBrukere.add(lagEksempelBruker(9, "Mann", Arrays.asList("Hest", "Fotball", "Bil"), 37, "Svelgfoss", "Ole", "66666666"));
        matchBrukere.add(lagEksempelBruker(10, "Mann", Arrays.asList("Hest", "Fotball", "Bil"), 37, "Svelgfoss", "Jon", "66666666"));
    }


    private void eksempelLikerMeg() {
        brukerHarLagtTil = new ArrayList<>();
        brukerHarLagtTil.add(lagEksempelBruker(1, "Kvinne", Arrays.asList("Hund", "Maling", "Joga"), 21, "Bø", "Olga", "99999999"));
        brukerHarLagtTil.add(lagEksempelBruker(1, "Kvinne", Arrays.asList("Hund", "Maling", "Joga"), 21, "Bø", "Olga", "99999999"));
        brukerHarLagtTil.add(lagEksempelBruker(1, "Kvinne", Arrays.asList("Hund", "Maling", "Joga"), 21, "Bø", "Olga", "99999999"));
    }

    /**
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public Boolean brukerErRegistrert() {
        int id = 0;

        try {
            File fil = new File("personID.txt");
            Scanner skanner = new Scanner(fil);
            while (skanner.hasNext()) {
                id = skanner.nextInt();
            }
            System.out.println(id);
            skanner.close();
        } catch (FileNotFoundException ignored) {

        }
        return id > 0;


    }

}

