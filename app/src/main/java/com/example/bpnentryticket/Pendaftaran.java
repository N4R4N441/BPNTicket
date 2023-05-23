package com.example.bpnentryticket;

public class Pendaftaran {
    String dokumen_pendaftaran, jenis_pendaftara, jumlah_surat, jenis_surat, nomor_antrian, nomor_booking, status, tanggal, uuid;

    public Pendaftaran() {
    }

    public Pendaftaran(String dokumen_pendaftaran, String jenis_pendaftara, String jumlah_surat, String jenis_surat, String nomor_antrian, String nomor_booking, String status, String tanggal, String uuid) {
        this.dokumen_pendaftaran = dokumen_pendaftaran;
        this.jenis_pendaftara = jenis_pendaftara;
        this.jumlah_surat = jumlah_surat;
        this.jenis_surat = jenis_surat;
        this.nomor_antrian = nomor_antrian;
        this.nomor_booking = nomor_booking;
        this.status = status;
        this.tanggal = tanggal;
        this.uuid = uuid;
    }

    public String getDokumen_pendaftaran() {
        return dokumen_pendaftaran;
    }

    public void setDokumen_pendaftaran(String dokumen_pendaftaran) {
        this.dokumen_pendaftaran = dokumen_pendaftaran;
    }


    public String getJenis_surat() {
        return jenis_surat;
    }

    public void setJenis_surat(String jenis_surat) {
        this.jenis_surat = jenis_surat;
    }

    public String getNomor_antrian() {
        return nomor_antrian;
    }

    public void setNomor_antrian(String nomor_antrian) {
        this.nomor_antrian = nomor_antrian;
    }

    public String getNomor_booking() {
        return nomor_booking;
    }

    public void setNomor_booking(String nomor_booking) {
        this.nomor_booking = nomor_booking;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getJenis_pendaftara() {
        return jenis_pendaftara;
    }

    public void setJenis_pendaftara(String jenis_pendaftara) {
        this.jenis_pendaftara = jenis_pendaftara;
    }

    public String getJumlah_surat() {
        return jumlah_surat;
    }

    public void setJumlah_surat(String jumlah_surat) {
        this.jumlah_surat = jumlah_surat;
    }
}
