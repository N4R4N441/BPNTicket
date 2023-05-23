package com.example.bpnentryticket;

public class Tiket {
    int Hari, NoAntrian;

    public Tiket() {
    }

    public Tiket(int hari, int noAntrian) {
        Hari = hari;
        NoAntrian = noAntrian;
    }

    public int getHari() {
        return Hari;
    }

    public void setHari(int hari) {
        Hari = hari;
    }

    public int getNoAntrian() {
        return NoAntrian;
    }

    public void setNoAntrian(int noAntrian) {
        NoAntrian = noAntrian;
    }
}
