package com.if4a.kulinerkita.Model;

import java.util.List;

public class ModalResponse {
    private String kode, pesan;
    private List<Modelkuliner> data;

    public String getKode() {
        return kode;
    }

    public String getPesan() {
        return pesan;
    }

    public List<Modelkuliner> getData() {
        return data;
    }
}
