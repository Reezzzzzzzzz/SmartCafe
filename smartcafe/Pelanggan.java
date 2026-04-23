package smartcafe;

/**
 * Class Pelanggan: merepresentasikan pelanggan yang datang ke kafe.
 *
 * Relasi ASOSIASI dengan Pesanan: Pelanggan "punya" pesanan aktif,
 * tapi Pesanan bisa berdiri sendiri dan berpindah tangan (misalnya
 * disimpan di database). Berbeda dengan komposisi — Pelanggan pergi,
 * Pesanan tetap ada di arsip.
 *
 * Tanggung jawab: Samuel Nelson Wabiser (103072400111) — Main System
 */
public class Pelanggan {

    private String nama;
    private String nomorMeja;
    private Pesanan pesananAktif;  // asosiasi (bukan komposisi)

    // Constructor
    public Pelanggan(String nama, String nomorMeja) {
        this.nama       = nama;
        this.nomorMeja  = nomorMeja;
        this.pesananAktif = new Pesanan(); // langsung siapkan pesanan baru
    }

    // --- Getters ---
    public String getNama() {
        return nama;
    }

    public String getNomorMeja() {
        return nomorMeja;
    }

    public Pesanan getPesanan() {
        return pesananAktif;
    }

    /**
     * Membuat pesanan baru (misal: pelanggan mau order lagi setelah bayar).
     * Pesanan lama tidak ikut dihapus — bisa disimpan di luar (arsip).
     */
    public void buatPesananBaru() {
        this.pesananAktif = new Pesanan();
        System.out.println("Pesanan baru dibuat untuk " + nama);
    }

    /**
     * Menampilkan informasi singkat pelanggan.
     */
    public void tampilkanInfo() {
        System.out.println("=== INFO PELANGGAN ===");
        System.out.println("Nama      : " + nama);
        System.out.println("Meja      : " + nomorMeja);
        System.out.println("ID Pesan  : " + pesananAktif.getIdPesanan());
        System.out.println("Status    : " + pesananAktif.getStatus());
    }
}