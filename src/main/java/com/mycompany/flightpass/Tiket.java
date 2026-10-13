package com.mycompany.flightpass;

public abstract class Tiket {
    private String kodeBooking;
    private String namaPenumpang;
    private String rute;
    private double hargaDasar;

    private static int totalTiketDibuat = 0;

    public Tiket(String kodeBooking, String namaPenumpang, String rute, double hargaDasar) {
        this.kodeBooking = kodeBooking;
        this.namaPenumpang = namaPenumpang;
        this.rute = rute;
        this.setHargaDasar(hargaDasar);

        totalTiketDibuat++;
    }

    public String getKodeBooking() { return this.kodeBooking; }
    public String getNamaPenumpang() { return this.namaPenumpang; }
    public String getRute() { return this.rute; }
    public double getHargaDasar() { return this.hargaDasar; }

    public void setHargaDasar(double hargaDasar) {
        if (hargaDasar >= 0) {
            this.hargaDasar = hargaDasar;
        } else {
            System.out.println("Error: Harga tiket tidak boleh bernilai negatif!");
        }
    }

    public static int getTotalTiketDibuat() {
        return totalTiketDibuat;
    }

    public void terapkanDiskon(double persen) {
        if (persen > 0 && persen <= 100) {
            double potongan = this.hargaDasar * (persen / 100);
            this.hargaDasar -= potongan;
            System.out.println("Diskon " + persen + "% berhasil diterapkan.");
        }
    }

    public void terapkanDiskon(double nominal, String kodeVoucher) {
        if (nominal > 0 && this.hargaDasar >= nominal) {
            this.hargaDasar -= nominal;
            System.out.println("Voucher '" + kodeVoucher + "' berhasil! Potongan: Rp" + nominal);
        } else {
            System.out.println("Gagal! Nominal diskon tidak valid.");
        }
    }

    // Method abstract bawaan
    public abstract void tampilkanInfoTiket();
    
    // TAMBAHAN MODUL 7: Method abstract baru yang wajib di-override subclass
    public abstract void prosesBoarding();
}