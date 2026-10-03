package com.mycompany.flightpass;

public class TiketEkonomi extends Tiket {
    private int beratBagasiEkstraKg;

    public TiketEkonomi(String kodeBooking, String namaPenumpang, String rute, double hargaDasar, int bagasiEkstra) {
        super(kodeBooking, namaPenumpang, rute, hargaDasar);
        this.beratBagasiEkstraKg = bagasiEkstra;
    }

    // Runtime Polymorphism: Method Overriding
    @Override
    public void tampilkanInfoTiket() {
        System.out.printf("| %-8s | %-15s | %-12s | Rp%-10.0f | Bagasi Ekstra: %-2d Kg |\n",
                super.getKodeBooking(), super.getNamaPenumpang(), super.getRute(), super.getHargaDasar(), this.beratBagasiEkstraKg);
    }
}
