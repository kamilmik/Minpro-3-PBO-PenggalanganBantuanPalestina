package controller;

import model.BantuanDana;
import model.Lembaga;
import java.util.ArrayList;
import java.util.Scanner;

public class DanaController {
    private ArrayList<BantuanDana> listDana;
    private ArrayList<Lembaga> listLembaga;
    private Scanner scanner;

    public DanaController(Scanner scanner, ArrayList<Lembaga> listLembaga) {
        this.scanner = scanner;
        this.listLembaga = listLembaga;
        this.listDana = new ArrayList<>();

        listDana.add(new BantuanDana(101, "Hamba Allah", 500000, listLembaga.get(0)));
        listDana.add(new BantuanDana(102, "Fikri Abiyu", 1000000, listLembaga.get(3)));
    }

    private boolean isIdDanaAda(int id) {
        for (BantuanDana d : listDana) {
            if (d.getIdBantuan() == id) return true;
        }
        return false;
    }

    public void tambahDana() {
        System.out.print("ID Dana (Angka): ");
        if (!scanner.hasNextInt()) {
            System.out.println("!! ID harus angka!!");
            scanner.nextLine(); return; 
        }
        int id = scanner.nextInt();
        scanner.nextLine();

        if (isIdDanaAda(id)) {
            System.out.println("!! Error: ID " + id + " sudah dipakai!");
            return;
        }

        System.out.print("Nama Donatur: ");
        String nama = scanner.nextLine();

        System.out.print("Nominal (Angka): ");
        if (!scanner.hasNextDouble()) {
            System.out.println("!! Nominal harus angka!!");
            scanner.nextLine(); return;
        }
        double nominal = scanner.nextDouble();
        scanner.nextLine();

        if (nominal <= 0) {
            System.out.println("!! Error: Nominal tidak boleh minus/nol!");
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
            listDana.add(new BantuanDana(id, nama, nominal, listLembaga.get(indexLembaga)));
            System.out.println(">> Alhamdulillah Berhasil ditambah");
        } else {
            System.out.println("!! Maaf, Pilihan lembaga salah!!");
        }
    }

    public void tampilkanDana() {
        if (listDana.isEmpty()) {
            System.out.println("syafakillah Data kosong.");
            return;
        }
        for (BantuanDana d : listDana) {
            d.tampilkanInfo(true); 
        }
    }

    public void ubahDana() {
        System.out.print("ID Dana yang diubah: ");
        if (!scanner.hasNextInt()) {
            System.out.println("!! ID harus angka!!");
            scanner.nextLine(); return;
        }
        int idTarget = scanner.nextInt();
        scanner.nextLine();

        for (BantuanDana d : listDana) {
            if (d.getIdBantuan() == idTarget) {
                System.out.print("Nominal Baru: ");
                if (!scanner.hasNextDouble()) {
                    System.out.println("!! Nominal harus angka!!");
                    scanner.nextLine(); return;
                }
                double nominalBaru = scanner.nextDouble();
                scanner.nextLine();

                if (nominalBaru <= 0) {
                    System.out.println("!! Nominal tidak boleh minus/nol!!");
                    return;
                }
                
                d.setNominal(nominalBaru);
                System.out.println(">> Alhamdulillah Berhasil diupdate");
                return;
            }
        }
        System.out.println("ID tidak ditemukan.");
    }

    public void hapusDana() {
        System.out.print("ID Dana yang dihapus: ");
        if (!scanner.hasNextInt()) {
            System.out.println("!! ID harus angka!!");
            scanner.nextLine(); return;
        }
        int idTarget = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < listDana.size(); i++) {
            if (listDana.get(i).getIdBantuan() == idTarget) {
                listDana.remove(i);
                System.out.println(">> data dihapus");
                return;
            }
        }
        System.out.println("!!ID tidak ditemukan.!!");
    }

    private void tampilPilihanLembaga() {
        System.out.println("Pilih Lembaga Penyalur:");
        for (int i = 0; i < listLembaga.size(); i++) {
            System.out.println((i + 1) + ". " + listLembaga.get(i).getNamaLembaga());
        }
        System.out.print("Pilihan: ");
    }
}