package smartcafe;

/**
 * Abstract class Menu sebagai fondasi dari semua item di kafe.
 * Menggunakan abstraction karena "Menu" secara umum tidak bisa
 * berdiri sendiri — harus berupa Makanan atau Minuman yang konkret.
 */
public abstract class Menu {

    // Access modifier private: encapsulation, hanya diakses via getter/setter
    private String nama;
    private double harga;
    private boolean isPromo;

    // Constructor
    public Menu(String nama, double harga) {
        this.nama = nama;
        this.harga = harga;
        this.isPromo = false;
    }

    // --- Getters & Setters ---
    public String getNama() {
        return nama;
    }

    public double getHarga() {
        return harga;
    }

    public boolean isPromo() {
        return isPromo;
    }

    public void setPromo(boolean isPromo) {
        this.isPromo = isPromo;
    }

    /**
     * Method abstrak: setiap subclass WAJIB mengimplementasikan
     * cara mereka menampilkan detail yang berbeda-beda.
     * Ini adalah inti dari Abstraction di proyek ini.
     */
    public abstract void tampilkanDetail();

    @Override
    public String toString() {
        return String.format("%s - Rp %.0f", nama, harga);
    }
}