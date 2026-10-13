package com.mycompany.flightpass;

// Meng-implements lebih dari satu interface
public class TiketBisnis extends Tiket implements DapatDiRefund, DapatFasilitasVIP {
    private boolean isAksesLounge;

    public TiketBisnis(String kodeBooking, String namaPenumpang, String rute, double hargaDasar, boolean aksesLounge) {
        super(kodeBooking, namaPenumpang, rute, hargaDasar);
        this.isAksesLounge = aksesLounge;
    }

    @Override
    public void tampilkanInfoTiket() {
        String lounge = isAksesLounge ? "Ya" : "Tidak";
        System.out.printf("| %-8s | %-15s | %-12s | Rp%-10.0f | Akses Lounge : %-5s |\n",
                super.getKodeBooking(), super.getNamaPenumpang(), super.getRute(), super.getHargaDasar(), lounge);
    }

    @Override
    public void prosesBoarding() {
        System.out.println("-> [Boarding] Penumpang kelas Bisnis masuk melalui Gate prioritas zona A.");
    }

    @Override
    public void prosesRefund() {
        System.out.println("-> [Refund] Tiket bisnis dapat di-refund penuh (100%) tanpa potongan.");
    }

    @Override
    public void nikmatiFasilitas() {
        if(isAksesLounge) {
            System.out.println("-> [VIP] Penumpang diarahkan ke ruang Executive Lounge sambil menunggu penerbangan.");
        }
    }
}