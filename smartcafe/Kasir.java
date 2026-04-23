package smartcafe;

/**
 * Class Kasir: bertanggung jawab mencetak struk dan memproses tagihan.
 *
 * Menggunakan CLASS METHOD (static) karena Kasir tidak perlu dibuat
 * sebagai objek — fungsinya bersifat global untuk sistem, seperti
 * fungsi utilitas yang dipakai bersama. Tidak ada "state" spesifik
 * per kasir yang perlu disimpan.
 *
 * Relasi: Kasir "uses" (dependency) Pelanggan — hanya butuh objek
 * Pelanggan saat method dipanggil, tidak menyimpannya.
 */
public class Kasir {

    private static final double TARIF_PAJAK = 0.10;
    private static int totalTransaksi = 0;

    // Konstruktor private: class ini tidak perlu diinstansiasi
    private Kasir() {}

    /**
     * Mencetak struk lengkap berdasarkan data Pelanggan dan Pesanannya.
     * Static method: bisa dipanggil langsung Kasir.cetakStruk(...)
     */
    public static void cetakStruk(Pelanggan pelanggan) {
        Pesanan pesanan = pelanggan.getPesanan();

        System.out.println("\n========================================");
        System.out.println("          SMARTCAFE — STRUK PESANAN    ");
        System.out.println("========================================");
        System.out.println("ID Pesanan : " + pesanan.getIdPesanan());
        System.out.println("Pelanggan  : " + pelanggan.getNama());
        System.out.println("Meja       : " + pelanggan.getNomorMeja());
        System.out.println("----------------------------------------");
        System.out.println(" Item Pesanan:");

        if (pesanan.isEmpty()) {
            System.out.println("  (Tidak ada item)");
        } else {
            for (ItemPesanan item : pesanan.getDaftarItem()) {
                item.tampilkanItem();
            }
        }

        System.out.println("----------------------------------------");
        System.out.printf("  Subtotal   : Rp %.0f%n", pesanan.hitungSubtotal());
        System.out.printf("  Pajak 10%%  : Rp %.0f%n", pesanan.hitungPajak());
        System.out.println("----------------------------------------");
        System.out.printf("  TOTAL      : Rp %.0f%n", pesanan.hitungTotal());
        System.out.println("========================================");
        System.out.println("  * = item promo (sudah dipotong diskon)");
        System.out.println("  Terima kasih telah berkunjung!");
        System.out.println("========================================\n");

        totalTransaksi++;
        pesanan.setStatus("SELESAI");
    }

    /**
     * Memproses tagihan dan mengembalikan jumlah yang harus dibayar.
     */
    public static double prosesTagihan(Pesanan pesanan) {
        return pesanan.hitungTotal();
    }

    // --- Static Getters ---

    public static int getTotalTransaksi() {
        return totalTransaksi;
    }

    public static void resetCounter() {
        totalTransaksi = 0;
    }
}