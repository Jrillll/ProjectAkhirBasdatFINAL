# 📖 Sistem Informasi Booking Lapangan Olahraga

Sistem ini dibuat untuk mengelola proses pemesanan lapangan olahraga secara digital mulai dari manajemen data pelanggan, jenis olahraga, lapangan, hingga transaksi pembayaran, jadwal booking, dan laporan-laporan.

---

## 📌 Fitur Utama

- **Manajemen Data Master**
  - Data Jenis Olahraga
  - Data Lapangan
  - Data Pemesan
  - Data Admin

- **Booking Lapangan & Transaksi**
  - Pemesanan booking lapangan berdasarkan jadwal, jenis olahraga, dan durasi.
  - Pembayaran booking via metode pembayaran yang dipilih.
  - Cek status booking.

- **Trigger Otomatis**
  - Mengubah status lapangan menjadi `terpakai` saat booking dibuat.
  - Mengubah status lapangan menjadi `tersedia` saat booking dibatalkan/selesai.

- **Stored Procedure**
  - Mengecek ketersediaan slot booking pada rentang waktu tertentu.
  - Melakukan pembayaran booking dengan status berhasil/tidak berhasil.

- **Laporan View & Report**
  - Tabel laporan data booking yang dapat ditampilkan di menu admin.
  - 3 Laporan: CrossTab, CTE, SubQuery (untuk keperluan analisis).

---

## 📑 Biodata Kelompok

| No | Nama Lengkap                          | NPM         |
|:--:|:--------------------------------------|:------------|
| 1  | Muhammad Nazriel Nararya Arianto      | 24082010068 |
| 2  | Meineza Dilga Putri                   | 24082010051 |
| 3  | Maulana Ahmad Andika                  | 24082010052 |
| 4  | Irfan Baihaqi                         | 24082010083 |

---

## 📑 Struktur Tabel (Ringkasan Non-SQL)

| Tabel               | Kolom                                                                                           |
|:--------------------|:------------------------------------------------------------------------------------------------|
| **jenis_olahraga**    | `id_jenisolahraga`, `nama_olahraga`, `tarif`                                                    |
| **lapangan**          | `id_lapangan`, `id_jenisolahraga`, `nama_lapangan`, `fasilitas`, `status`                     |
| **pemesan**           | `id_pemesan`, `id_roleuser`, `nama_pemesan`, `no_telepon`, `email`, `alamat`                  |
| **admin**             | `id_admin`, `nama_pengguna`, `password`, `id_roleuser`                                         |
| **role_user**         | `id_roleuser`, `role`                                                                          |
| **jadwal_booking**    | `id_booking`, `id_lapangan`, `id_pemesan`, `tanggal`, `jam_mulai`, `jam_selesai`, `durasi`, `status` |
| **transaksi**         | `id_transaksi`, `id_booking`, `jumlah_bayar`, `metode_pembayaran`, `status_pembayaran`, `waktu_transaksi`, `bukti_pembayaran` |
| **view_daftar_booking** | `id_booking`, `nama_pemesan`, `nama_lapangan`, `nama_olahraga`, `tanggal`, `jam_mulai`, `jam_selesai`, `status` |

---

## 📦 Struktur SQL Database

Query Table DATABASE

-- Tabel role_user

CREATE TABLE role_user (
  id_roleuser INT AUTO_INCREMENT PRIMARY KEY,
  role VARCHAR(20)
);

-- Tabel admin

CREATE TABLE admin (
  id_admin INT AUTO_INCREMENT PRIMARY KEY,
  nama_pengguna VARCHAR(100),
  password VARCHAR(20),
  id_roleuser INT,
  FOREIGN KEY (id_roleuser) REFERENCES role_user(id_roleuser)
);

-- Tabel pemesan

CREATE TABLE pemesan (
  id_pemesan INT AUTO_INCREMENT PRIMARY KEY,
  id_roleuser INT,
  nama_pemesan VARCHAR(100),
  no_telepon VARCHAR(20),
  email VARCHAR(50),
  alamat TEXT,
  FOREIGN KEY (id_roleuser) REFERENCES role_user(id_roleuser)
);

-- Tabel jenis olahraga

CREATE TABLE jenis_olahraga (
  id_jenisolahraga INT AUTO_INCREMENT PRIMARY KEY,
  nama_olahraga VARCHAR(50),
  tarif DECIMAL(10,2)
);

-- Tabel lapangan

CREATE TABLE lapangan (
  id_lapangan INT AUTO_INCREMENT PRIMARY KEY,
  id_jenisolahraga INT,
  nama_lapangan VARCHAR(100),
  fasilitas TEXT,
  status ENUM('terpakai', 'tersedia'),
  FOREIGN KEY (id_jenisolahraga) REFERENCES jenis_olahraga(id_jenisolahraga)
);

-- Tabel jadwal_booking

CREATE TABLE jadwal_booking (
  id_booking INT AUTO_INCREMENT PRIMARY KEY,
  id_lapangan INT,
  id_pemesan INT,
  tanggal DATE,
  jam_mulai TIME,
  jam_selesai TIME,
  durasi INT,
  status ENUM('booking', 'dibatalkan', 'selesai'),
  FOREIGN KEY (id_lapangan) REFERENCES lapangan(id_lapangan),
  FOREIGN KEY (id_pemesan) REFERENCES pemesan(id_pemesan)
);

-- Tabel transaksi

CREATE TABLE transaksi (
  id_transaksi INT AUTO_INCREMENT PRIMARY KEY,
  id_booking INT,
  jumlah_bayar DECIMAL(10,2),
  metode_pembayaran VARCHAR(20),
  status_pembayaran ENUM('berhasil','tidak berhasil'),
  waktu_transaksi TIME,
  bukti_pembayaran TEXT,
  FOREIGN KEY (id_booking) REFERENCES jadwal_booking(id_booking)
);

---

## Trigger
Trigger Booking Digunakan untuk mengubah status lapangan menjadi “terpakai” secara otomatis setelah booking baru ditambahkan

Query: 

DELIMITER $$

CREATE TRIGGER trg_booking_dibuat
AFTER INSERT ON jadwal_booking
FOR EACH ROW
BEGIN
    UPDATE lapangan
    SET status = 'terpakai'
    WHERE id_lapangan = NEW.id_lapangan;
END$$

DELIMITER ;

Penjelasan: Setelah pelanggan menambahkan booking, trigger ini langsung aktif, status lapangan menjadi otomatis “terpakai”.


----

## Stored Procedure Cek Ketersediaan Lapangan
Ini digunakan untuk mengecek apakah lapangan yang dipilih sudah dibooking pada tanggal dan jam tertentu. 

Query: 

DELIMITER $$

CREATE PROCEDURE cek_ketersediaan_lapangan (
    IN p_id_lapangan VARCHAR(10),
    IN p_tanggal DATE,
    IN p_jam_mulai TIME,
    IN p_jam_selesai TIME
)
BEGIN
    SELECT *
    FROM jadwal_booking
    WHERE id_lapangan = p_id_lapangan
      AND tanggal = p_tanggal
      AND status = 'booking'
      AND (
          (jam_mulai < p_jam_selesai AND jam_selesai > p_jam_mulai)
      );
END$$

DELIMITER ;

Penjelasannya: Mengecek apakah lapangan yang dipilih sudah ada booking aktif (status booking) di tanggal dan waktu yang sama. Kalau ada hasil, maka waktu tersebut bentrok dan tidak tersedia lapangan.

---

## 🚀 Cara Menjalankan Program Java

### 📌 Step 1: Compile Semua File Java

Pastikan kamu berada di direktori project, lalu jalankan perintah berikut di terminal:

```bash
javac -d bin -cp "lib/mysql-connector-j-8.3.0.jar" -sourcepath src src/DAO/*.java src/menu/*.java src/*.java bash
```

### Step 2: Jalankan Program
Gunakan perintah berikut untuk menjalankan program:

```bash
java -cp "bin;lib/mysql-connector-j-8.3.0.jar" MainApp
