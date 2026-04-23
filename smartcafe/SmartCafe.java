package smartcafe;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Main class SmartCafe: entry point program.
 * Mendemonstrasikan semua konsep OOP:
 *   - Abstraction (Menu abstract class)
 *   - Inheritance (Makanan & Minuman extends Menu)
 *   - Interface (Diskonable)
 *   - Polymorphism (ArrayList<Menu>, tampilkanDetail())
 *   - Encapsulation (private fields + getters/setters)
 *   - Class Method / Static (Kasir, Pesanan.resetCounter())
 *   - Komposisi (Pesanan *-- ItemPesanan)
 *   - Asosiasi (Pelanggan --> Pesanan, ItemPesanan --> Menu)
 *   - Access Modifier: private, protected, public
 *
 * Tanggung jawab: Samuel Nelson Wabiser (103072400111) — Main System
 */
public class SmartCafe {

    // Daftar menu kafe (contoh data)
    private static ArrayList<Menu> menuKafe = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        inisialisasiMenu();
        jalankanProgram();
        scanner.close();
    }

    /**
     * Menyiapkan daftar menu awal kafe.
     * Polimorfisme: ArrayList<Menu> menampung Makanan & Minuman sekaligus.
     */
    private static void inisialisasiMenu() {
        // Makanan
        Makanan nasiGoreng = new Makanan("Nasi Goreng Spesial", 35000, 3, "Regular");
        Makanan miAyam     = new Makanan("Mie Ayam Bakso",      28000, 1, "Regular");
        Makanan kentang    = new Makanan("Kentang Goreng",       18000, 0, "Large");
        Makanan pizza      = new Makanan("Mini Pizza",           45000, 0, "Mini");

        // Minuman
        Minuman espresso   = new Minuman("Espresso",        20000, "S", true);
        Minuman matchaLatte = new Minuman("Matcha Latte",   32000, "M", false);
        Minuman jusAlpukat = new Minuman("Jus Alpukat",     25000, "L", false);
        Minuman tehManis   = new Minuman("Teh Manis",       10000, "M", true);

        // Set promo (item yang implementasi Diskonable)
        nasiGoreng.setPromo(true);
        matchaLatte.setPromo(true);

        menuKafe.add(nasiGoreng);
        menuKafe.add(miAyam);
        menuKafe.add(kentang);
        menuKafe.add(pizza);
        menuKafe.add(espresso);
        menuKafe.add(matchaLatte);
        menuKafe.add(jusAlpukat);
        menuKafe.add(tehManis);
    }

    private static void jalankanProgram() {
        System.out.println("╔══════════════════════════════════╗");
        System.out.println("║   Selamat Datang di SmartCafe!   ║");
        System.out.println("╚══════════════════════════════════╝");

        System.out.print("Masukkan nama Anda    : ");
        String nama = scanner.nextLine();
        System.out.print("Masukkan nomor meja   : ");
        String meja = scanner.nextLine();

        // Buat objek Pelanggan (asosiasi dengan Pesanan terbentuk di constructor)
        Pelanggan pelanggan = new Pelanggan(nama, meja);
        pelanggan.tampilkanInfo();

        boolean lanjut = true;
        while (lanjut) {
            tampilkanMenuUtama();
            System.out.print("Pilih menu (1-4): ");
            String input = scanner.nextLine().trim();

            switch (input) {
                case "1":
                    tampilkanDaftarMenu();
                    break;
                case "2":
                    tambahPesanan(pelanggan);
                    break;
                case "3":
                    lihatPesanan(pelanggan);
                    break;
                case "4":
                    // Static method Kasir dipanggil tanpa instansiasi
                    Kasir.cetakStruk(pelanggan);
                    System.out.println("Total transaksi hari ini: " + Kasir.getTotalTransaksi());
                    lanjut = false;
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Coba lagi.\n");
            }
        }
    }

    private static void tampilkanMenuUtama() {
        System.out.println("\n====== MENU UTAMA ======");
        System.out.println("1. Lihat Daftar Menu");
        System.out.println("2. Tambah Pesanan");
        System.out.println("3. Lihat Pesanan Saya");
        System.out.println("4. Checkout & Cetak Struk");
        System.out.println("========================");
    }

    /**
     * Menampilkan semua menu kafe.
     * Polimorfisme: tampilkanDetail() dipanggil pada setiap Menu,
     * tapi hasilnya berbeda tergantung tipenya (Makanan / Minuman).
     */
    private static void tampilkanDaftarMenu() {
        System.out.println("\n========== DAFTAR MENU SMARTCAFE ==========");
        for (int i = 0; i < menuKafe.size(); i++) {
            System.out.println("\n[" + (i + 1) + "]");
            menuKafe.get(i).tampilkanDetail(); // POLYMORPHISM
        }
    }

    private static void tambahPesanan(Pelanggan pelanggan) {
        tampilkanDaftarMenu();
        System.out.print("\nPilih nomor menu (1-" + menuKafe.size() + "): ");
        try {
            int nomor = Integer.parseInt(scanner.nextLine().trim()) - 1;
            if (nomor < 0 || nomor >= menuKafe.size()) {
                System.out.println("Nomor menu tidak valid.");
                return;
            }

            System.out.print("Jumlah: ");
            int jumlah = Integer.parseInt(scanner.nextLine().trim());
            System.out.print("Catatan khusus (tekan Enter jika tidak ada): ");
            String catatan = scanner.nextLine().trim();

            Menu dipilih = menuKafe.get(nomor);
            pelanggan.getPesanan().tambahItem(dipilih, jumlah, catatan);
            System.out.println("✓ " + dipilih.getNama() + " x" + jumlah + " berhasil ditambahkan!");

        } catch (NumberFormatException e) {
            System.out.println("Input tidak valid. Harap masukkan angka.");
        }
    }

    private static void lihatPesanan(Pelanggan pelanggan) {
        Pesanan p = pelanggan.getPesanan();
        System.out.println("\n=== PESANAN ANDA (" + p.getIdPesanan() + ") ===");
        if (p.isEmpty()) {
            System.out.println("Belum ada item di pesanan Anda.");
        } else {
            for (ItemPesanan item : p.getDaftarItem()) {
                item.tampilkanItem();
            }
            System.out.printf("%nEstimasi Total (belum pajak) : Rp %.0f%n", p.hitungSubtotal());
            System.out.printf("Pajak 10%%                     : Rp %.0f%n", p.hitungPajak());
            System.out.printf("Estimasi Bayar               : Rp %.0f%n", p.hitungTotal());
        }
    }
}