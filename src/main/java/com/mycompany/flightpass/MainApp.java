package com.mycompany.flightpass;

import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        FlightPassManager sistemPenerbangan = new FlightPassManager(10);
        boolean aplikasiJalan = true;

        // Inisialisasi Data Awal
        sistemPenerbangan.tambahEntryTiket(new TiketEkonomi("ECO01", "Budi Santoso", "CGK - DPS", 850000, 5));
        sistemPenerbangan.tambahEntryTiket(new TiketBisnis("BIZ01", "Siti Aminah", "CGK - SUB", 2500000, true));
        sistemPenerbangan.tambahEntryTiket(new TiketFirstClass("FST01", "Andi Sultan", "CGK - SIN", 8000000, "B 1 VIP"));

        System.out.println("========================================");
        System.out.println("    SELAMAT DATANG DI FLIGHTPASS");
        System.out.println("========================================");

        while (aplikasiJalan) {
            System.out.println("\n--- MAIN MENU FLIGHTPASS ---");
            System.out.println("1. Entry Tiket Baru");
            System.out.println("2. Tampilkan Seluruh Manifest (Runtime Polymorphism)");
            System.out.println("3. Terapkan Diskon Khusus (Compile-Time Polymorphism)");
            System.out.println("4. Simulasi Boarding Pass (Upcasting Parameter)");
            System.out.println("0. Keluar");
            System.out.print("Pilih menu: ");

            int pilihan = input.nextInt();
            input.nextLine();

            switch (pilihan) {
                case 1:
                    System.out.println("\n[ ENTRY TIKET BARU ]");
                    if (sistemPenerbangan.getTotalTiket() >= sistemPenerbangan.getKapasitasMaksimal()) {
                        System.out.println("Kapasitas Penuh!");
                        break;
                    }

                    System.out.println("Pilih Kelas Penerbangan:");
                    System.out.println("1. Ekonomi");
                    System.out.println("2. Bisnis");
                    System.out.println("3. First Class");
                    System.out.print("Pilihan (1/2/3): ");
                    int kelas = input.nextInt();
                    input.nextLine();

                    System.out.print("Masukkan Kode Booking : ");
                    String kode = input.nextLine();
                    System.out.print("Masukkan Nama         : ");
                    String nama = input.nextLine();
                    System.out.print("Masukkan Rute         : ");
                    String rute = input.nextLine();
                    System.out.print("Masukkan Harga Dasar  : ");
                    double harga = input.nextDouble();
                    input.nextLine();

                    if (kelas == 1) {
                        System.out.print("Berat Bagasi Ekstra (Kg): ");
                        int bagasi = input.nextInt();
                        sistemPenerbangan.tambahEntryTiket(new TiketEkonomi(kode, nama, rute, harga, bagasi));
                    } else if (kelas == 2) {
                        System.out.print("Akses Lounge? (true/false): ");
                        boolean lounge = input.nextBoolean();
                        sistemPenerbangan.tambahEntryTiket(new TiketBisnis(kode, nama, rute, harga, lounge));
                    } else if (kelas == 3) {
                        System.out.print("Plat Nomor Mobil Penjemput (Limo): ");
                        String plat = input.nextLine();
                        sistemPenerbangan.tambahEntryTiket(new TiketFirstClass(kode, nama, rute, harga, plat));
                    } else {
                        System.out.println("Kelas penerbangan tidak valid.");
                    }
                    break;

                case 2:
                    System.out.println("\n[ DATA MANIFEST PENUMPANG ]");
                    sistemPenerbangan.tampilkanSemuaManifest();
                    break;

                case 3:
                    System.out.println("\n[ TERAPKAN DISKON TIKET ]");
                    System.out.print("Masukkan Kode Booking: ");
                    String cariKode = input.nextLine();
                    Tiket tiketDiskon = sistemPenerbangan.cariTiket(cariKode);

                    if (tiketDiskon != null) {
                        System.out.print("Jenis diskon (1 = Persentase, 2 = Nominal + Voucher): ");
                        int jnsDiskon = input.nextInt();
                        input.nextLine();

                        if (jnsDiskon == 1) {
                            System.out.print("Masukkan Persentase Diskon (ex: 10): ");
                            double persen = input.nextDouble();
                            tiketDiskon.terapkanDiskon(persen); 
                        } else {
                            System.out.print("Masukkan Kode Voucher: ");
                            String voucher = input.nextLine();
                            System.out.print("Masukkan Nominal Potongan (Rp): ");
                            double nominal = input.nextDouble();
                            tiketDiskon.terapkanDiskon(nominal, voucher);
                        }
                    } else {
                        System.out.println("Tiket tidak ditemukan.");
                    }
                    break;

                case 4:
                    System.out.println("\n[ SIMULASI BOARDING PASS ]");
                    System.out.print("Masukkan Kode Booking Penumpang: ");
                    String idCek = input.nextLine();
                    Tiket tiketBoarding = sistemPenerbangan.cariTiket(idCek);

                    if (tiketBoarding != null) {
                        sistemPenerbangan.prosesBoardingPass(tiketBoarding);
                    } else {
                        System.out.println("Tiket dengan kode tersebut tidak ditemukan.");
                    }
                    break;

                case 0:
                    aplikasiJalan = false;
                    System.out.println("Menutup sistem FlightPass.");
                    break;

                default:
                    System.out.println("Menu tidak valid.");
            }
        }
        input.close();
    }
}