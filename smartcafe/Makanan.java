package smartcafe;

/**
 * Class Makanan: turunan dari Menu (Inheritance).
 * Memiliki atribut tambahan levelPedas dan porsi
 * yang tidak dimiliki oleh Minuman.
 * Mengimplementasi Diskonable agar bisa mendapat promo.
 *
 * Tanggung jawab: Naufal Fudhail (103072400013) — Food Module
 */
public class Makanan extends Menu implements Diskonable {

    // Atribut khusus Makanan (protected agar subclass bisa akses jika di-extend)
    private int levelPedas;   // 0 = tidak pedas, 1-5 = tingkat kepedasan
    protected String porsi;   // "Regular", "Large", "Mini"

    // Constructor
    public Makanan(String nama, double harga, int levelPedas, String porsi) {
        super(nama, harga); // panggil constructor parent (Menu)
        this.levelPedas = validasiLevelPedas(levelPedas);
        this.porsi = porsi;
    }

    // Constructor tanpa level pedas (default 0 = tidak pedas)
    public Makanan(String nama, double harga, String porsi) {
        this(nama, harga, 0, porsi);
    }

    // --- Validasi ---
    private int validasiLevelPedas(int level) {
        if (level < 0) return 0;
        if (level > 5) return 5;
        return level;
    }

    // --- Getters ---
    public int getLevelPedas() {
        return levelPedas;
    }

    public String getPorsi() {
        return porsi;
    }

    /**
     * Implementasi method abstrak dari Menu.
     * Menampilkan detail spesifik makanan: nama, harga, pedas, porsi.
     * Polimorfisme: method ini dipanggil berbeda dari Minuman.tampilkanDetail()
     */
    @Override
    public void tampilkanDetail() {
        String labelPedas = levelPedas == 0 ? "Tidak Pedas" : "Level " + levelPedas;
        String labelPromo = isPromo() ? " [PROMO -10%]" : "";

        System.out.println("+---------------------------------+");
        System.out.println("  [MAKANAN] " + getNama() + labelPromo);
        System.out.printf ("  Harga    : Rp %.0f%n", getHarga());
        System.out.println("  Porsi    : " + porsi);
        System.out.println("  Pedas    : " + labelPedas);
        if (isPromo()) {
            System.out.printf("  Diskon   : Rp %.0f%n", hitungDiskon());
            System.out.printf("  Bayar    : Rp %.0f%n", getHargaSetelahDiskon());
        }
        System.out.println("+---------------------------------+");
    }

    // --- Implementasi interface Diskonable ---

    @Override
    public double hitungDiskon() {
        // Diskon hanya berlaku jika item sedang promo
        if (isPromo()) {
            return getHarga() * PERSEN_DISKON;
        }
        return 0;
    }

    @Override
    public double getHargaSetelahDiskon() {
        return getHarga() - hitungDiskon();
    }
}