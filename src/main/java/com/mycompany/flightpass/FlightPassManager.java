package com.mycompany.flightpass;

public class FlightPassManager {
    private Tiket[] manifest;
    private int totalTiket;
    private int kapasitasMaksimal;

    public FlightPassManager(int kapasitas) {
        this.kapasitasMaksimal = kapasitas;
        this.manifest = new Tiket[kapasitasMaksimal];
        this.totalTiket = 0;
    }

    public void tambahEntryTiket(Tiket tiketBaru) {
        for (int i = 0; i < totalTiket; i++) {
            if (manifest[i].getKodeBooking().equals(tiketBaru.getKodeBooking())) {
                System.out.println("Gagal! Tiket dengan kode '" + tiketBaru.getKodeBooking() + "' sudah ada.");
                return;
            }
        }

        if (totalTiket < kapasitasMaksimal) {
            manifest[totalTiket] = tiketBaru;
            totalTiket++;
            System.out.println("Tiket atas nama " + tiketBaru.getNamaPenumpang() + " berhasil diterbitkan.");
        } else {
            System.out.println("Gagal! Kapasitas penerbangan penuh.");
        }
    }

    // Method Menampilkan Data dengan Dynamic Binding
    public void tampilkanSemuaManifest() {
        System.out.println("\n=======================================================================================");
        System.out.printf("| %-8s | %-15s | %-12s | %-12s | %-20s |\n", "KODE", "NAMA PENUMPANG", "RUTE", "HARGA", "FASILITAS KHUSUS");
        System.out.println("=======================================================================================");

        if (totalTiket == 0) {
            System.out.printf("| %-81s |\n", "Manifest kosong. Belum ada tiket terjual.");
        } else {
            for (int i = 0; i < totalTiket; i++) {
                manifest[i].tampilkanInfoTiket();
            }
        }
        System.out.println("=======================================================================================");
    }

    public Tiket cariTiket(String kode) {
        for (int i = 0; i < totalTiket; i++) {
            if (manifest[i].getKodeBooking().equalsIgnoreCase(kode)) {
                return manifest[i];
            }
        }
        return null;
    }

    public void prosesBoardingPass(Tiket tiketPenumpang) {
        System.out.println("\n--- MEMPROSES BOARDING PASS ---");
        System.out.println("Memvalidasi kode booking: " + tiketPenumpang.getKodeBooking());
        
        tiketPenumpang.tampilkanInfoTiket();
        
        System.out.println("Status: Validasi Selesai. Penumpang dipersilakan masuk ke ruang tunggu.");
        System.out.println("-------------------------------");
    }

    public int getKapasitasMaksimal() { return kapasitasMaksimal; }
    public int getTotalTiket() { return totalTiket; }
}