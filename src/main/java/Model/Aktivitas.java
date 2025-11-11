/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.time.LocalDate;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author user
 */
public class Aktivitas {
    
    
    private final ObjectProperty<LocalDate> tanggal; 
    private final StringProperty jenisOlahraga;    
    private final IntegerProperty durasiMenit;      
    private final StringProperty catatan;            

    
   
    public Aktivitas(LocalDate tanggal, String jenisOlahraga, int durasiMenit, String catatan) {
        this.tanggal = new SimpleObjectProperty<>(tanggal);
        this.jenisOlahraga = new SimpleStringProperty(jenisOlahraga);
        this.durasiMenit = new SimpleIntegerProperty(durasiMenit);
        this.catatan = new SimpleStringProperty(catatan);
    }

    public LocalDate getTanggal() { return tanggal.get(); }
    public void setTanggal(LocalDate tanggal) { this.tanggal.set(tanggal); }
    public ObjectProperty<LocalDate> tanggalProperty() { return tanggal; }

    public String getJenisOlahraga() { return jenisOlahraga.get(); }
    public void setJenisOlahraga(String jenis) { this.jenisOlahraga.set(jenis); }
    public StringProperty jenisOlahragaProperty() { return jenisOlahraga; }

    public int getDurasiMenit() { return durasiMenit.get(); }
    public void setDurasiMenit(int durasi) { this.durasiMenit.set(durasi); }
    public IntegerProperty durasiMenitProperty() { return durasiMenit; }

    public String getCatatan() { return catatan.get(); }
    public void setCatatan(String cat) { this.catatan.set(cat); }
    public StringProperty catatanProperty() { return catatan; }
}
