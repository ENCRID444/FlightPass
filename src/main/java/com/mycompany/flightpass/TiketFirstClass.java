package com.mycompany.flightpass;

public class TiketFirstClass extends Tiket {
    private String platLimoPenjemputan;

    public TiketFirstClass(String kodeBooking, String namaPenumpang, String rute, double hargaDasar, String platLimo) {
        super(kodeBooking, namaPenumpang, rute, hargaDasar);
        this.platLimoPenjemputan = platLimo;
    }

    // Runtime Polymorphism: Method Overriding
    @Override
    public void tampilkanInfoTiket() {
        System.out.printf("| %-8s | %-15s | %-12s | Rp%-10.0f | Jemput Limo  : %-5s |\n",
                super.getKodeBooking(), super.getNamaPenumpang(), super.getRute(), super.getHargaDasar(), this.platLimoPenjemputan);
    }
}