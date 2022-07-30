package com.proyek.akpay;

public class DataItem2 {
    String kode;
    String nim;
    String bank;
    String keterangan;
    String berita;
    String total;

    public String getKode() {    return kode;  }

    public void setKode(String kode) {  this.kode = kode;  }

    public String getBerita() {  return berita;  }

    public void setBerita(String berita) {  this.berita = berita;  }

    public String getNim() {     return nim;   }

    public void setNim(String nim) {   this.nim = nim;   }

    public String getBank() {   return bank;   }

    public void setBank(String bank) {    this.bank = bank;   }

    public String getKeterangan() {   return keterangan;   }

    public void setKeterangan(String keterangan) {   this.keterangan = keterangan;  }

    public String getTotal() {    return total;  }

    public void setTotal(String total) {  total = total;  }


    public DataItem2() {
    }

    public DataItem2(String kode, String nim, String bank, String keterangan, String total, String berita) {
        this.kode = kode;
        this.nim = nim;
        this.bank = bank;
        this.keterangan = keterangan;
        this.total = total;
        this.berita = berita;

    }

}

