package smartcafe;

/**
 * Class ItemPesanan: representasi satu baris dalam daftar pesanan.
 * Menyimpan referensi ke Menu + jumlah + catatan khusus pelanggan.
 *
 * Relasi: ItemPesanan berasosiasi dengan Menu (-->) karena
 * ItemPesanan "mengenal" Menu tapi tidak memilikinya secara eksklusif.
 * Satu Menu yang sama bisa dirujuk oleh banyak ItemPesanan.
 */
public class ItemPesanan {

    private Menu menu;       // asosiasi ke Menu (bisa Makanan atau Minuman)
    private int jumlah;
    private String catatan;  // misal: "tanpa bawang", "extra pedas"

    // Constructor tanpa catatan
    public ItemPesanan(Menu menu, int jumlah) {
        this(menu, jumlah, "-");
    }

    // Constructor lengkap
    public ItemPesanan(Menu menu, int jumlah, String catatan) {
        this.menu   = menu;
        this.jumlah = jumlah > 0 ? jumlah : 1;
        this.catatan = (catatan == null || catatan.isEmpty()) ? "-" : catatan;
    }

    // --- Getters ---
    public Menu getMenu() {
        return menu;
    }

    public int getJumlah() {
        return jumlah;
    }

    public String getCatatan() {
        return catatan;
    }

    /**
     * Subtotal = harga per item (setelah diskon jika promo) x jumlah
     * Polimorfisme: getHargaSetelahDiskon() dipanggil via interface Diskonable,
     * tapi kita cek dulu apakah menu implementasi Diskonable.
     */
    public double getSubtotal() {
        double hargaSatuan = menu.getHarga();
        if (menu instanceof Diskonable && menu.isPromo()) {
            hargaSatuan = ((Diskonable) menu).getHargaSetelahDiskon();
        }
        return hargaSatuan * jumlah;
    }

    /**
     * Menampilkan satu baris item di struk pesanan.
     */
    public void tampilkanItem() {
        String namaMenu  = menu.getNama();
        String labelPromo = (menu.isPromo()) ? "*" : " ";
        System.out.printf("  %s%-20s x%d  Rp %.0f%n",
                labelPromo, namaMenu, jumlah, getSubtotal());
        if (!catatan.equals("-")) {
            System.out.println("     Catatan: " + catatan);
        }
    }
}