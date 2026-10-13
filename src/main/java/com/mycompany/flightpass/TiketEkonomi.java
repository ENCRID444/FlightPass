package com.mycompany.flightpass;

public class TiketEkonomi extends Tiket implements DapatDiRefund {
    private int beratBagasiEkstraKg;

    public TiketEkonomi(String kodeBooking, String namaPenumpang, String rute, double hargaDasar, int bagasiEkstra) {
        super(kodeBooking, namaPenumpang, rute, hargaDasar);
        this.beratBagasiEkstraKg = bagasiEkstra;
    }

    @Override
    public void tampilkanInfoTiket() {
        System.out.printf("| %-8s | %-15s | %-12s | Rp%-10.0f | Bagasi Ekstra: %-2d Kg |\n",
                super.getKodeBooking(), super.getNamaPenumpang(), super.getRute(), super.getHargaDasar(), this.beratBagasiEkstraKg);
    }

    // Overriding method abstract dari Superclass
    @Override
    public void prosesBoarding() {
        System.out.println("-> [Boarding] Penumpang kelas Ekonomi masuk melalui Gate reguler zona B.");
    }

    // Overriding method dari Interface DapatDiRefund
    @Override
    public void prosesRefund() {
        System.out.println("-> [Refund] Tiket ekonomi dikenakan potongan biaya refund 50%.");
    }
}