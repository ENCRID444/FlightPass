package com.mycompany.flightpass;

public class TiketFirstClass extends Tiket implements DapatFasilitasVIP {
    private String platLimoPenjemputan;

    public TiketFirstClass(String kodeBooking, String namaPenumpang, String rute, double hargaDasar, String platLimo) {
        super(kodeBooking, namaPenumpang, rute, hargaDasar);
        this.platLimoPenjemputan = platLimo;
    }

    @Override
    public void tampilkanInfoTiket() {
        System.out.printf("| %-8s | %-15s | %-12s | Rp%-10.0f | Jemput Limo  : %-5s |\n",
                super.getKodeBooking(), super.getNamaPenumpang(), super.getRute(), super.getHargaDasar(), this.platLimoPenjemputan);
    }

    @Override
    public void prosesBoarding() {
        System.out.println("-> [Boarding] Penumpang First Class diantar langsung menuju pesawat via Limousine pribadi.");
    }

    @Override
    public void nikmatiFasilitas() {
        System.out.println("-> [VIP] Mendapatkan layanan kabin private suite dan menu makanan dari chef Michelin Star.");
    }
}