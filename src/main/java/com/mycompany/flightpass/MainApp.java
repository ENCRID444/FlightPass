package com.mycompany.flightpass;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        FlightPassManager sistemPenerbangan = new FlightPassManager();
        
        // Fitur HashMap: Menyimpan data Member FlightPass (Key: ID Member, Value: Nama)
        HashMap<String, String> dataMember = new HashMap<>();
        dataMember.put("MEM01", "Budi Santoso");
        dataMember.put("MEM02", "Siti Aminah");
        dataMember.put("MEM03", "Andi Sultan");

        boolean aplikasiJalan = true;

        // Inisialisasi Data Awal (3-5 Objek ArrayList)
        sistemPenerbangan.tambahEntryTiket(new TiketEkonomi("ECO01", "Budi Santoso", "CGK - DPS", 850000, 5));
        sistemPenerbangan.tambahEntryTiket(new TiketBisnis("BIZ01", "Siti Aminah", "CGK - SUB", 2500000, true));
        sistemPenerbangan.tambahEntryTiket(new TiketFirstClass("FST01", "Andi Sultan", "CGK - SIN", 8000000, "B 1 VIP"));

        System.out.println("========================================");
        System.out.println("    SELAMAT DATANG DI FLIGHTPASS");
        System.out.println("========================================");

        while (aplikasiJalan) {
            System.out.println("\n--- MAIN MENU FLIGHTPASS ---");
            System.out.println("1. Entry Tiket Baru");
            System.out.println("2. Tampilkan Seluruh Manifest");
            System.out.println("3. Terapkan Diskon Khusus (Demo Exception)");
            System.out.println("4. Simulasi Boarding Pass (Demo Exception)");
            System.out.println("5. Kelola Member Maskapai (Demo HashMap)");
            System.out.println("0. Keluar");
            System.out.print("Pilih menu: ");

            try {
                int pilihan = input.nextInt();
                input.nextLine();

                switch (pilihan) {
                    case 1 -> {
                        System.out.println("\n[ ENTRY TIKET BARU ]");
                        System.out.println("Pilih Kelas Penerbangan:");
                        System.out.println("1. Ekonomi\n2. Bisnis\n3. First Class");
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
                    }
                    case 2 -> {
                        System.out.println("\n[ DATA MANIFEST PENUMPANG ]");
                        sistemPenerbangan.tampilkanSemuaManifest();
                    }
                    case 3 -> {
                        System.out.println("\n[ TERAPKAN DISKON TIKET ]");
                        System.out.print("Masukkan Kode Booking: ");
                        String cariKode = input.nextLine();
                        // Berpotensi melempar TiketTidakDitemukanException
                        Tiket tiketDiskon = sistemPenerbangan.cariTiket(cariKode);

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
                    }
                    case 4 -> {
                        System.out.println("\n[ SIMULASI BOARDING PASS ]");
                        System.out.print("Masukkan Kode Booking Penumpang: ");
                        String idCek = input.nextLine();
                        // Berpotensi melempar TiketTidakDitemukanException
                        Tiket tiketBoarding = sistemPenerbangan.cariTiket(idCek);
                        sistemPenerbangan.prosesBoardingPass(tiketBoarding);
                    }
                    case 5 -> {
                        System.out.println("\n--- Kelola Member (HashMap) ---");
                        System.out.println("1. Tambah Member Baru");
                        System.out.println("2. Cari Member via ID");
                        System.out.println("3. Tampilkan Semua Member");
                        System.out.print("Pilih: ");
                        int menuMember = input.nextInt();
                        input.nextLine();

                        if (menuMember == 1) {
                            System.out.print("Masukkan ID Member (Key): ");
                            String id = input.nextLine();
                            System.out.print("Masukkan Nama Lengkap (Value): ");
                            String nama = input.nextLine();
                            dataMember.put(id, nama);
                            System.out.println("Berhasil menambahkan member!");
                        } else if (menuMember == 2) {
                            System.out.print("Masukkan ID yang dicari: ");
                            String searchId = input.nextLine();
                            if (dataMember.containsKey(searchId)) {
                                System.out.println("Ditemukan! Nama: " + dataMember.get(searchId));
                            } else {
                                System.out.println("Member dengan ID tersebut tidak terdaftar.");
                            }
                        } else if (menuMember == 3) {
                            System.out.println("Total Member Terdaftar: " + dataMember.size());
                            for (String keyId : dataMember.keySet()) {
                                System.out.println("- ID: " + keyId + " | Nama: " + dataMember.get(keyId));
                            }
                        }
                    }
                    case 0 -> {
                        aplikasiJalan = false;
                        System.out.println("Menutup sistem FlightPass.");
                    }
                    default -> System.out.println("Menu tidak valid.");
                }
            } catch (InputMismatchException e) {
                // Menangkap error jika user menginputkan teks saat diminta angka
                System.out.println("\n[FATAL ERROR] Input tidak valid! Anda harus memasukkan angka.");
                input.nextLine(); // Membersihkan sisa buffer yang error
            } catch (TiketTidakDitemukanException e) {
                // Menangkap custom exception yang kita buat
                System.out.println("\n[PERINGATAN] " + e.getMessage());
            } catch (Exception e) {
                System.out.println("\n[ERROR SISTEM] Terjadi kegagalan: " + e.getMessage());
            } finally {
                // Blok ini pasti dieksekusi di akhir, error ataupun tidak
                System.out.println("-> Sesi sistem operasi selesai dieksekusi.");
            }
        }
        input.close();
    }
}