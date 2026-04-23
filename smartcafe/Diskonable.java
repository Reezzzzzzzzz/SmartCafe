package smartcafe;

/**
 * Interface Diskonable: kontrak yang harus dipenuhi oleh menu yang
 * bisa mendapat diskon promo. Dipisah dari Menu agar tidak semua
 * menu dipaksa memiliki logika diskon (Interface Segregation).
 */
public interface Diskonable {

    double PERSEN_DISKON = 0.10; // Diskon promo default 10%

    /**
     * Menghitung nilai diskon berdasarkan harga asli item.
     * @return nilai potongan harga (bukan harga akhir)
     */
    double hitungDiskon();

    /**
     * Menghitung harga setelah diskon diterapkan.
     * @return harga final setelah dipotong diskon
     */
    double getHargaSetelahDiskon();
}