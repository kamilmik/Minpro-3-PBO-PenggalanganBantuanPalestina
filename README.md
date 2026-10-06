# Mini Project 3 PBO - Sistem Penggalangan Bantuan Palestina

Nama: Muhammad Ibrahim Kamil \
NIM: 2509116012

## Deskripsi Program
Program ini adalah aplikasi Terminal berbasis Java untuk mengelola donasi kemanusiaan yang terbagi menjadi dua kategori: \
Bantuan Dana dan Bantuan Logistik. Pada Mini Project 3, program telah di modifikasi menggunakan arsitektur MVC dan menerapkan prinsip-prinsip Object-Oriented Programming (OOP) \
tingkat mahir seperti Abstraction, Polymorphism (Overriding & Overloading), dan Interface. Proteksi data juga ditingkatkan dengan validasi input komprehensif, pencegahan angka negatif, \
serta proteksi ID duplikat menggunakan keyword final.

## Struktur MVC
Program ini dipecah ke dalam empat package utama untuk memenuhi standar arsitektur Model-View-Controller (MVC): \
- model: Berisi blueprint data aplikasi seperti class entitas (BantuanDana, BantuanLogistik, Lembaga), abstract class (Bantuan), dan interface (KategoriBantuan).
- controller: Berisi logika bisnis, validasi, dan operasi CRUD (DanaController, LogistikController). Controller bertugas mengolah input dari View dan memanipulasi Model.
- view: Berisi class BantuanView yang khusus menangani antarmuka terminal dan menangkap respon dari pengguna (meskipun tidak terlampir secara eksplisit di file sumber, class ini dipanggil di main class).
- main: Berisi BantuanPalestina sebagai class Entry Point yang murni bertugas menginisialisasi dummy data, menyambungkan Controller, dan menjalankan View.

## Alur Program
1. Saat program dijalankan, Main class menyuntikkan dummy data ke dalam Lembaga, lalu mengirimkan data tersebut ke DanaController dan LogistikController
2. Menu Utama & View, Pengguna dihadapkan pada menu BantuanView yang dibungkus dengan perulangan while.
3. Ketika pengguna masuk ke menu CRUD salah satu antara Dana atau Logistik, Controller akan memvalidasi input. Jika pengguna memasukkan huruf pada ID/Nominal, atau memasukkan angka negatif/nol, program akan menolak input tersebut dan memberikan peringatan tanpa mengalami crash. Program juga mengecek apakah ID sudah ada di dalam sistem untuk mencegah duplikasi.
4. Jika lolos validasi, Controller menyimpan objek baru ke dalam memori aplikasi, dan pengguna dapat melihatnya menggunakan fitur Tampilkan Data.

## Penerapan 4 Pilar
1. Enscapsulation \
Penerapan encapsulation dilakukan dengan memberikan access modifier private pada atribut-atribut penting, sehingga data tidak bisa diubah sembarangan dari luar class. Akses dan modifikasi hanya bisa dilakukan melalui method getter dan setter.
``` java
    private String namaLembaga;
    private String asalNegara;
    private String kontak;

    public String getNamaLembaga() { 
        return namaLembaga; 
    }
    public void setNamaLembaga(String namaLembaga) { 
        this.namaLembaga = namaLembaga; 
    }
```
2. Inheritance \
Class Bantuan bertindak sebagai Superclass, sementara BantuanDana dan BantuanLogistik bertindak sebagai Subclass. Subclass menggunakan keyword extends untuk mewarisi atribut induknya dan memakai super() pada konstruktor, sehingga kode lebih terpusat dan tidak berulang.
``` java
public class BantuanDana extends Bantuan {
    private double nominal;

    public BantuanDana(int idBantuan, String namaDonatur, double nominal, Lembaga lembagaPenyalur) {
        super(idBantuan, namaDonatur, lembagaPenyalur); // Memanggil constructor Superclass
        this.nominal = nominal;
    }
```
3. Polymorphism \
Program menerapkan dua bentuk Polymorphism sekaligus pada method tampilkanInfo di dalam subclass. \
- Overriding Menimpa method abstrak dari superclass.
``` java
@Override
    public void tampilkanInfo() {
        System.out.println("-------------------------");
        cetakKategori();
        System.out.println("ID Dana: " + idBantuan);
        System.out.println("Donatur: " + namaDonatur);
        System.out.println("Nominal: Rp" + nominal);
        System.out.println("-------------------------");
    }
```
- Overloading Membuat method tampilkanInfo, dengan parameter boolean tampilkanKontakLembaga
``` java
public void tampilkanInfo(boolean tampilkanKontakLembaga) {
        tampilkanInfo(); // Memanggil method override di atas
        if (tampilkanKontakLembaga) {
            System.out.println("Kontak Lembaga: " + lembagaPenyalur.getKontak());
            System.out.println("-------------------------");
        }
    }
```
4. Abstraction \
   Class Bantuan dideklarasikan sebagai abstract class, yang berarti class ini hanya menjadi cetakan dan tidak bisa dibuat menjadi objek langsung. Class ini memiliki abstract method bernama tampilkanInfo() yang tidak memiliki bodi
   ``` java
   public abstract class Bantuan implements KategoriBantuan {
   ```

## Nilai Tambah
program ini menggunakan Interface bernama KategoriBantuan. Interface ini mendefinisikan sebuah kontrak cetakKategori() yang di-implements oleh abstract class Bantuan dan diimplementasikan wujudnya secara spesifik di masing-masing subclass.
``` java
public interface KategoriBantuan {
    void cetakKategori(); 
}
```

## Output Program
Alur kerja Menu Logistik dan Menu Dana sangat identik, berikut adalah kedua tampilan CRUD dari program
1. Menu awal
2. READ \
- Dana
- Logistik
3. CREATE \
- Dana
- Logistik
4. UPDATE \
- Dana
- Logistik
5. DELETE \
- Dana
- Logistik
