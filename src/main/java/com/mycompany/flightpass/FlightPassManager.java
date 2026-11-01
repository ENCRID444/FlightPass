package com.mycompany.flightpass;

import java.util.ArrayList; // Import ArrayList

public class FlightPassManager {
    // Mengubah Array biasa menjadi ArrayList bertipe Superclass (Tiket)
    private ArrayList<Tiket> manifest;

    public FlightPassManager() {
        this.manifest = new ArrayList<>();
    }

    public void tambahEntryTiket(Tiket tiketBaru) {
        for (int i = 0; i < manifest.size(); i++) {
            if (manifest.get(i).getKodeBooking().equalsIgnoreCase(tiketBaru.getKodeBooking())) {
                System.out.println("Gagal! Tiket dengan kode '" + tiketBaru.getKodeBooking() + "' sudah ada.");
                return;
            }
        }
        manifest.add(tiketBaru); // Menggunakan method bawaan .add()
        System.out.println("Tiket atas nama " + tiketBaru.getNamaPenumpang() + " berhasil diterbitkan.");
    }

    public void tampilkanSemuaManifest() {
        System.out.println("\n--- Manifest Penumpang & Informasi Layanan ---");
        
        if (manifest.isEmpty()) { // Menggunakan method bawaan .isEmpty()
            System.out.println("Belum ada tiket terjual.");
        } else {
            for (int i = 0; i < manifest.size(); i++) { // Menggunakan .size()
                System.out.print((i + 1) + ". ");
                manifest.get(i).tampilkanInfoTiket(); // Menggunakan .get(i)
                manifest.get(i).prosesBoarding();

                if (manifest.get(i) instanceof DapatDiRefund) {
                    DapatDiRefund tiketRefund = (DapatDiRefund) manifest.get(i);
                    tiketRefund.prosesRefund();
                }
                
                if (manifest.get(i) instanceof DapatFasilitasVIP) {
                    DapatFasilitasVIP tiketVIP = (DapatFasilitasVIP) manifest.get(i);
                    tiketVIP.nikmatiFasilitas();
                }
                System.out.println();
            }
        }
        System.out.println("* Total Tiket Terbit: " + Tiket.getTotalTiketDibuat());
    }

    // Menggunakan kata kunci throws pada deklarasi method
    public Tiket cariTiket(String kode) throws TiketTidakDitemukanException {
        for (int i = 0; i < manifest.size(); i++) {
            if (manifest.get(i).getKodeBooking().equalsIgnoreCase(kode)) {
                return manifest.get(i);
            }
        }
        // Menggunakan kata kunci throw untuk melempar error
        throw new TiketTidakDitemukanException("Gagal: Tiket dengan kode '" + kode + "' tidak terdaftar di sistem.");
    }

    public void prosesBoardingPass(Tiket tiketPenumpang) {
        System.out.println("\n--- MEMPROSES BOARDING PASS ---");
        System.out.println("Memvalidasi kode booking: " + tiketPenumpang.getKodeBooking());
        tiketPenumpang.tampilkanInfoTiket();
        System.out.println("Status: Validasi Selesai. Penumpang dipersilakan masuk ke ruang tunggu.");
        System.out.println("-------------------------------");
    }
}