package smartcafe;

/**
 * Class Minuman: turunan dari Menu (Inheritance).
 * Memiliki atribut tambahan ukuran dan isHot
 * yang tidak dimiliki oleh Makanan.
 * Mengimplementasi Diskonable agar bisa mendapat promo.
 *
 * Tanggung jawab: Krisna Wahyudi Pratama (103072400048) — Drink Module
 */
public class Minuman extends Menu implements Diskonable {

    // Atribut khusus Minuman
    private String ukuran;   // "S", "M", "L"
    private boolean isHot;   // true = panas, false = dingin

    // Constructor lengkap
    public Minuman(String nama, double harga, String ukuran, boolean isHot) {
        super(nama, harga);
        this.ukuran = validasiUkuran(ukuran);
        this.isHot = isHot;
    }

    // Constructor default: ukuran M, dingin
    public Minuman(String nama, double harga) {
        this(nama, harga, "M", false);
    }

    // --- Validasi ---
    private String validasiUkuran(String ukuran) {
        String u = ukuran.toUpperCase();
        if (u.equals("S") || u.equals("M") || u.equals("L")) return u;
        return "M"; // default M jika input tidak valid
    }

    // --- Getters ---
    public String getUkuran() {
        return ukuran;
    }

    public boolean isHot() {
        return isHot;
    }

    /**
     * Implementasi method abstrak dari Menu.
     * Menampilkan detail spesifik minuman: nama, harga, ukuran, suhu.
     * Polimorfisme: berbeda dengan Makanan.tampilkanDetail()
     */
    @Override
    public void tampilkanDetail() {
        String labelSuhu  = isHot ? "Panas" : "Dingin";
        String labelPromo = isPromo() ? " [PROMO -10%]" : "";

        System.out.println("+---------------------------------+");
        System.out.println("  [MINUMAN] " + getNama() + labelPromo);
        System.out.printf ("  Harga    : Rp %.0f%n", getHarga());
        System.out.println("  Ukuran   : " + ukuran);
        System.out.println("  Suhu     : " + labelSuhu);
        if (isPromo()) {
            System.out.printf("  Diskon   : Rp %.0f%n", hitungDiskon());
            System.out.printf("  Bayar    : Rp %.0f%n", getHargaSetelahDiskon());
        }
        System.out.println("+---------------------------------+");
    }

    // --- Implementasi interface Diskonable ---

    @Override
    public double hitungDiskon() {
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