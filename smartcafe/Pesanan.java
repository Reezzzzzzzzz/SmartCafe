package smartcafe;

import java.util.ArrayList;

/**
 * Class Pesanan: mengelola seluruh daftar item yang dipesan pelanggan.
 *
 * Relasi KOMPOSISI dengan ItemPesanan: daftar item "hidup dan mati" bersama
 * objek Pesanan ini. Jika Pesanan dihapus, semua ItemPesanan-nya pun ikut
 * terhapus (tidak bisa berdiri sendiri di luar Pesanan).
 *
 * Tanggung jawab: Rizqi Rahmatullah (103072400018) — Order Logic
 */
public class Pesanan {

    private static final double TARIF_PAJAK = 0.10; // Pajak 10%
    private static int counter = 0;                  // class variable (static)

    private String idPesanan;
    private ArrayList<ItemPesanan> daftarItem;        // komposisi
    private String statusPesanan;

    // Constructor: otomatis generate ID pesanan
    public Pesanan() {
        counter++;
        this.idPesanan    = String.format("ORD-%04d", counter);
        this.daftarItem   = new ArrayList<>();
        this.statusPesanan = "BARU";
    }

    // --- Tambah & Hapus Item ---

    public void tambahItem(Menu menu, int jumlah) {
        daftarItem.add(new ItemPesanan(menu, jumlah));
    }

    public void tambahItem(Menu menu, int jumlah, String catatan) {
        daftarItem.add(new ItemPesanan(menu, jumlah, catatan));
    }

    public void hapusItem(int index) {
        if (index >= 0 && index < daftarItem.size()) {
            daftarItem.remove(index);
            System.out.println("Item ke-" + (index + 1) + " berhasil dihapus.");
        } else {
            System.out.println("Indeks tidak valid.");
        }
    }

    // --- Kalkulasi Harga ---

    /**
     * Subtotal: jumlah dari semua subtotal item (sebelum pajak).
     * Polimorfisme via ArrayList<Menu>: tiap item dihitung sendiri-sendiri
     * terlepas dari apakah itu Makanan atau Minuman.
     */
    public double hitungSubtotal() {
        double total = 0;
        for (ItemPesanan item : daftarItem) {
            total += item.getSubtotal();
        }
        return total;
    }

    public double hitungPajak() {
        return hitungSubtotal() * TARIF_PAJAK;
    }

    public double hitungTotal() {
        return hitungSubtotal() + hitungPajak();
    }

    // --- Getters & Setters ---

    public String getIdPesanan() {
        return idPesanan;
    }

    public String getStatus() {
        return statusPesanan;
    }

    public void setStatus(String status) {
        this.statusPesanan = status;
    }

    public ArrayList<ItemPesanan> getDaftarItem() {
        return daftarItem;
    }

    public boolean isEmpty() {
        return daftarItem.isEmpty();
    }

    // --- Class Method (Static) ---

    /**
     * Static method: mereset counter ID pesanan.
     * Berguna untuk unit testing atau reset sesi harian.
     * Static karena bekerja pada level class, bukan instance.
     */
    public static void resetCounter() {
        counter = 0;
    }

    public static int getTotalPesananDibuat() {
        return counter;
    }
}