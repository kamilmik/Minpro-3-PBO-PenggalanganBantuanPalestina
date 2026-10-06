package com.mycompany.bantuanpalestina3;

import controller.DanaController;
import controller.LogistikController;
import model.Lembaga;
import view.BantuanView;

import java.util.ArrayList;
import java.util.Scanner;

public class BantuanPalestina {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        
        ArrayList<Lembaga> listLembaga = new ArrayList<>();
        listLembaga.add(new Lembaga("BAZNAS", "Indonesia", "11111"));
        listLembaga.add(new Lembaga("PMI", "Indonesia", "222222"));
        listLembaga.add(new Lembaga("UNICEF", "Internasional", "333333"));
        listLembaga.add(new Lembaga("INFORSA", "Indonesia", "444444"));
        listLembaga.add(new Lembaga("AKSI BERSAMA", "Nasional", "555555"));

        DanaController danaController = new DanaController(scanner, listLembaga);
        LogistikController logistikController = new LogistikController(scanner, listLembaga);
        
        BantuanView view = new BantuanView(scanner, danaController, logistikController);
        view.tampilkanMenuUtama();
    }
}