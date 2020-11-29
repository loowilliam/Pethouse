package com.example.project.pethouse.activity;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.example.project.pethouse.R;
import com.example.project.pethouse.model.MsHotel;
import com.example.project.pethouse.model.MsPromo;
import com.example.project.pethouse.repository.MsHotelRepository;
import com.example.project.pethouse.repository.MsPromoRepository;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //Threading untuk melakukan jeda pada screen sleep 1000 = 1 detik
        Thread thread = new Thread(){
            public void run(){
                try{
                    sleep(2000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{
                    startActivity(new Intent(SplashActivity.this, StartActivity.class));
                    finish();
                }
            }
        };
        MsHotelRepository HotelRepository = new MsHotelRepository(this);
        MsPromoRepository PromoRepository = new MsPromoRepository(this);
        System.out.println("A"+PromoRepository.isInserted());
        if(PromoRepository.isInserted() == false){
            MsPromo MsPromo = new MsPromo();
            MsPromo.setPromoName("Promo Pengguna Baru");
            MsPromo.setPromoDesc("Dapatkan promo discount sebesar 50%* bagi Pengguna Baru Pethouse!\n" +
                    "S&K:\n" +
                    "- Tidak pernah melakukan transaksi sebelumnya\n" +
                    "- Promo hanya dapat digunakan sebanyak 1 kali\n" +
                    "- Promo berlaku baik pada transaksi Short Term maupun Long Term\n" +
                    "- Setiap transaksi minimum Rp.10000 mendapatkan diskon sebesar 50% hingga Rp. 10000,00 selama promo berlangsung\n" +
                    "- Syarat dan Ketentuan dapat berubah sewaktu - waktu");
            MsPromo.setPromoCode("NEWPETHOUSE");
            MsPromo.setValidDate("22/05/2022");
            PromoRepository.insertToMsPromo(MsPromo);
        }
        //jika true, tidak perlu di insert
        if(HotelRepository.checkInserted() == false){
            initializeMsHotel();
        }
        thread.start();

    }



    private void initializeMsHotel(){
        MsHotel hotel = new MsHotel();
        MsHotelRepository HotelRepository = new MsHotelRepository(this);

        //1
        hotel.setHotelName("Pet House Hotel Jakarta Barat");
        hotel.setHotelLocation("Jalan Kebon Jeruk No 81, Jakarta Barat, Jakarta, Indonesia");
        hotel.setHotelDesc("Pethouse Hotel kami menyediakan fasilitas:\n" +
                "- Penitipan Hewan Peliharan (Anjing, dan Kucing)\n" +
                "- Tempat bermain\n" +
                "- Pemberian makan 3 kali sehari\n" +
                "- Tempat Tidur\n" +
                "- Showering\n" +
                "Catatan: Perawatan tergantung jenis penitipan hewan\n");
        hotel.setRemainingSlot(10);
        hotel.setOwnerName("Jeffrey Marcelino");
        hotel.setOwnerEmail("jeffrey.marcellino@gmail.com");
        hotel.setOwnerPhone("+6289232873281");
        hotel.setOwnerPrice(100000);
        HotelRepository.insert(hotel);

        //2
        hotel.setHotelName("Pet House Hotel Jakarta Utara");
        hotel.setHotelLocation("Jalan Pesanggrahan 1 No 77, Jakarta Utara, Jakarta, Indonesia");
        hotel.setHotelDesc("Pethouse Hotel kami menyediakan fasilitas:\n" +
                        "- Penitipan Hewan Peliharan (Anjing, dan Kucing)\n" +
                        "- Tempat bermain\n" +
                        "- Pemberian makan 3 kali sehari\n" +
                        "- Tempat Tidur\n" +
                        "- Showering\n" +
                        "Catatan: Perawatan tergantung jenis penitipan hewan\n");
        hotel.setRemainingSlot(10);
        hotel.setOwnerName("William Loo");
        hotel.setOwnerEmail("williamloo@gmail.com");
        hotel.setOwnerPhone("+6282124849232");
        hotel.setOwnerPrice(100000);
        HotelRepository.insert(hotel);

        //3
        hotel.setHotelName("Pet House Hotel Jakarta Selatan");
        hotel.setHotelLocation("Jalan Blok M No 33, Jakarta Selatan, Jakarta, Indonesia");
        hotel.setHotelDesc("Pethouse Hotel kami menyediakan fasilitas:\n" +
                        "- Penitipan Hewan Peliharan (Anjing, dan Kucing)\n" +
                        "- Tempat bermain\n" +
                        "- Pemberian makan 3 kali sehari\n" +
                "- Tempat Tidur\n" +
                        "- Showering\n" +
                        "Catatan: Perawatan tergantung jenis penitipan hewan\n");
        hotel.setRemainingSlot(10);
        hotel.setOwnerName("Aileen Ghita");
        hotel.setOwnerEmail("aileenghita@gmail.com");
        hotel.setOwnerPhone("+6281852932832");
        hotel.setOwnerPrice(100000);
        HotelRepository.insert(hotel);

        //4
        hotel.setHotelName("Pet House Hotel Jakarta Timur");
        hotel.setHotelLocation("Jalan Jalak No 89, Jakarta Timur, Jakarta, Indonesia");
        hotel.setHotelDesc("Pethouse Hotel kami menyediakan fasilitas:\n" +
                        "- Penitipan Hewan Peliharan (Anjing, dan Kucing)\n" +
                        "- Tempat bermain\n" +
                        "- Pemberian makan 3 kali sehari\n" +
                        "- Tempat Tidur\n" +
                        "- Showering\n" +
                        "Catatan: Perawatan tergantung jenis penitipan hewan\n");
        hotel.setRemainingSlot(10);
        hotel.setOwnerName("Hansen Gregorius");
        hotel.setOwnerEmail("hansengregorius@gmail.com");
        hotel.setOwnerPhone("+6285102392102");
        hotel.setOwnerPrice(100000);
        HotelRepository.insert(hotel);

        //5
        hotel.setHotelName("Pet House Hotel Jakarta Pusat");
        hotel.setHotelLocation("Jalan M.H. Thamrin No 21, Jakarta Pusat, Jakarta, Indonesia");
        hotel.setHotelDesc("Pethouse Hotel kami menyediakan fasilitas:\n" +
                        "- Penitipan Hewan Peliharan (Anjing, dan Kucing)\n" +
                        "- Tempat bermain\n" +
                        "- Pemberian makan 3 kali sehari\n" +
                        "- Tempat Tidur\n" +
                        "- Showering\n" +
                        "Catatan: Perawatan tergantung jenis penitipan hewan\n");
        hotel.setRemainingSlot(10);
        hotel.setOwnerName("Eric Pangiawan");
        hotel.setOwnerEmail("eric.pangiawan@gmail.com");
        hotel.setOwnerPhone("+6282103298211");
        hotel.setOwnerPrice(100000);
        HotelRepository.insert(hotel);

    }

}
