package com.mycompany.flightpass;

public class TiketBisnis extends Tiket {
    private boolean isAksesLounge;

    public TiketBisnis(String kodeBooking, String namaPenumpang, String rute, double hargaDasar, boolean aksesLounge) {
        super(kodeBooking, namaPenumpang, rute, hargaDasar);
        this.isAksesLounge = aksesLounge;
    }

    // Runtime Polymorphism: Method Overriding
    @Override
    public void tampilkanInfoTiket() {
        String lounge = isAksesLounge ? "Ya" : "Tidak";
        System.out.printf("| %-8s | %-15s | %-12s | Rp%-10.0f | Akses Lounge : %-5s |\n",
                super.getKodeBooking(), super.getNamaPenumpang(), super.getRute(), super.getHargaDasar(), lounge);
    }
}
