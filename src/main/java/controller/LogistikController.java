package controller;

import model.BantuanLogistik;
import model.Lembaga;
import java.util.ArrayList;
import java.util.Scanner;

public class LogistikController {
    private ArrayList<BantuanLogistik> listLogistik;
    private ArrayList<Lembaga> listLembaga;
    private Scanner scanner;

    public LogistikController(Scanner scanner, ArrayList<Lembaga> listLembaga) {
        this.scanner = scanner;
        this.listLembaga = listLembaga;
        this.listLogistik = new ArrayList<>();

        listLogistik.add(new BantuanLogistik(201, "Relawan Kaltim", "Pakaian & Selimut", 50, listLembaga.get(4)));
        listLogistik.add(new BantuanLogistik(202, "Rizky", "Tenda", 10, listLembaga.get(3)));
    }

    private boolean isIdLogistikAda(int id) {
        for (BantuanLogistik l : listLogistik) {
            if (l.getIdBantuan() == id) return true;
        }
        return false;
    }

    public void tambahLogistik() {
        System.out.print("ID Logistik (Angka): ");
        if (!scanner.hasNextInt()) {
            System.out.println("!! ID harus angka!!");
            scanner.nextLine(); return;
        }
        int id = scanner.nextInt();
        scanner.nextLine();

        if (isIdLogistikAda(id)) {
            System.out.println("!! ID " + id + " sudah dipakai!!");
            return;
        }

        System.out.print("Nama Donatur: ");
        String donatur = scanner.nextLine();
        System.out.print("Nama Barang: ");
        String barang = scanner.nextLine();

        System.out.print("Berat (Kg): ");
        if (!scanner.hasNextInt()) {
            System.out.println("!! Berat harus angka!!");
            scanner.nextLine(); return;
        }
        int berat = scanner.nextInt();
        scanner.nextLine();

        if (berat <= 0) {
            System.out.println("!! Berat tidak boleh minus/nol!");
            return;
        }
        
        tampilPilihanLembaga();
        if (!scanner.hasNextInt()) {
            System.out.println("!! Pilihan harus angka!!");
            scanner.nextLine(); return;
        }
        int indexLembaga = scanner.nextInt() - 1;
        scanner.nextLine();
        
        if (indexLembaga >= 0 && indexLembaga < listLembaga.size()) {
            listLogistik.add(new BantuanLogistik(id, donatur, barang, berat, listLembaga.get(indexLembaga)));
            System.out.println(">> Alhamdulillah Berhasil ditambah");
        } else {
            System.out.println("!! maaf, Pilihan salah");
        }
    }

    public void tampilkanLogistik() {
        if (listLogistik.isEmpty()) {
            System.out.println("Data kosong.");
            return;
        }
        for (BantuanLogistik l : listLogistik) {
            l.tampilkanInfo("Segera dikirimkan via udara.");
        }
    }

    public void ubahLogistik() {
        System.out.print("ID Logistik yang diubah: ");
        if (!scanner.hasNextInt()) {
            System.out.println("!! ID harus angka!!");
            scanner.nextLine(); return;
        }
        int idTarget = scanner.nextInt();
        scanner.nextLine();

        for (BantuanLogistik l : listLogistik) {
            if (l.getIdBantuan() == idTarget) {
                System.out.print("Berat Baru (Kg): ");
                if (!scanner.hasNextInt()) {
                    System.out.println("!! Berat harus angka!!");
                    scanner.nextLine(); return;
                }
                int beratBaru = scanner.nextInt();
                scanner.nextLine();

                if (beratBaru <= 0) {
                    System.out.println("!! Error: Berat tidak boleh minus/nol!");
                    return;
                }

                l.setBeratKg(beratBaru);
                System.out.println(">> Alhamdulillah Berhasil diupdate");
                return;
            }
        }
        System.out.println("ID tidak ditemukan.");
    }

    public void hapusLogistik() {
        System.out.print("ID Logistik yang dihapus: ");
        if (!scanner.hasNextInt()) {
            System.out.println("!! ID harus angka!!");
            scanner.nextLine(); return;
        }
        int idTarget = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < listLogistik.size(); i++) {
            if (listLogistik.get(i).getIdBantuan() == idTarget) {
                listLogistik.remove(i);
                System.out.println(">> Data dihapus");
                return;
            }
        }
        System.out.println("ID tidak ditemukan.");
    }

    private void tampilPilihanLembaga() {
        System.out.println("Pilih Lembaga Penyalur:");
        for (int i = 0; i < listLembaga.size(); i++) {
            System.out.println((i + 1) + ". " + listLembaga.get(i).getNamaLembaga());
        }
        System.out.print("Pilihan: ");
    }
}