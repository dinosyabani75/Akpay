package com.proyek.akpay;

public class DataItem {

    String nam;
    String nim;
    String email;
    Integer saldo;

    public DataItem() {
    }

    public DataItem(String nam, String nim, String email, Integer saldo) {
        this.nam = nam;
        this.nim = nim;
        this.email = email;
        this.saldo = saldo;
    }

    public String getNam() {
        return nam;
    }

    public void setNam(String nam) {
        this.nam = nam;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getSaldo() {
        return saldo;
    }

    public void setSaldo(Integer saldo) {
        this.saldo = saldo;
    }
}
