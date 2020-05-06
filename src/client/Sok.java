package client;

public class Sok {
    private boolean mann;
    private boolean kvinne;
    private boolean beggeKjonn;
    private int fraAlder;
    private int tilAlder;

    public boolean isMann() {
        return mann;
    }

    public void setMann(boolean mann) {
        this.mann = mann;
    }

    public boolean isKvinne() {
        return kvinne;
    }

    public void setKvinne(boolean kvinne) {
        this.kvinne = kvinne;
    }

    public boolean isBeggeKjonn() {
        return beggeKjonn;
    }

    public void setBeggeKjonn(boolean beggeKjonn) {
        this.beggeKjonn = beggeKjonn;
    }

    public int getFraAlder() {
        return fraAlder;
    }

    public void setFraAlder(int fraAlder) {
        this.fraAlder = fraAlder;
    }

    public int getTilAlder() {
        return tilAlder;
    }

    public void setTilAlder(int tilAlder) {
        this.tilAlder = tilAlder;
    }
}
