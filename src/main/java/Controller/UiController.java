package Controller;

import Model.Aktivitas;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.Optional; 
import javafx.scene.control.ButtonType; 

public class UiController implements Initializable {

    @FXML private DatePicker datePicker;
    @FXML private ComboBox<String> comboJenis;
    @FXML private TextField fieldDurasi;
    @FXML private TextArea areaCatatan;
    @FXML private TableView<Aktivitas> tableView;
    @FXML private Button btnSimpan;
    
    @FXML private Button btnUpdate;
    @FXML private Button btnDelete;

    @FXML private TableColumn<Aktivitas, LocalDate> colTanggal;
    @FXML private TableColumn<Aktivitas, String> colJenis;
    @FXML private TableColumn<Aktivitas, Integer> colDurasi;
    @FXML private TableColumn<Aktivitas, String> colCatatan;

    private ObservableList<Aktivitas> daftarAktivitas = FXCollections.observableArrayList();
    
    private Aktivitas aktivitasTerpilih = null;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comboJenis.getItems().addAll("Lari", "Gym", "Sepeda", "Renang", "Lainnya");
        comboJenis.setValue("Lari");
        
        colTanggal.setCellValueFactory(new PropertyValueFactory<>("tanggal"));
        colJenis.setCellValueFactory(new PropertyValueFactory<>("jenisOlahraga"));
        colDurasi.setCellValueFactory(new PropertyValueFactory<>("durasiMenit"));
        colCatatan.setCellValueFactory(new PropertyValueFactory<>("catatan"));
        
        tableView.setItems(daftarAktivitas);
        
        
        tableView.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> {
                aktivitasTerpilih = newValue; 
                
                tampilkanDetailAktivitas(newValue);
            }
        );
        
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);
    }    

    
    private void tampilkanDetailAktivitas(Aktivitas aktivitas) {
        if (aktivitas != null) {
            datePicker.setValue(aktivitas.getTanggal());
            comboJenis.setValue(aktivitas.getJenisOlahraga());
            fieldDurasi.setText(String.valueOf(aktivitas.getDurasiMenit())); 
            areaCatatan.setText(aktivitas.getCatatan());
            
            btnSimpan.setDisable(true); 
            btnUpdate.setDisable(false); 
            btnDelete.setDisable(false); 
            
        } else {
            clearForm();
        }
    }
    
    
    @FXML
    private void handleSimpan() {
        LocalDate tanggal = datePicker.getValue();
        String jenis = comboJenis.getValue();
        String durasiStr = fieldDurasi.getText();
        String catatan = areaCatatan.getText();

        if (tanggal == null || jenis == null || durasiStr.isEmpty()) {
            tampilkanAlertError("Tanggal, Jenis, dan Durasi tidak boleh kosong!");
            return;
        }

        int durasi;
        try {
            durasi = Integer.parseInt(durasiStr);
        } catch (NumberFormatException e) {
            tampilkanAlertError("Durasi harus berupa angka (menit)!");
            return;
        }
        
        Aktivitas aktivitasBaru = new Aktivitas(tanggal, jenis, durasi, catatan);
        daftarAktivitas.add(aktivitasBaru);
        
        clearForm();
    }
    
    
    @FXML
    private void handleUpdate() {
        if (aktivitasTerpilih == null) {
            tampilkanAlertError("Pilih data di tabel terlebih dahulu untuk di-update.");
            return;
        }
        
        LocalDate tanggal = datePicker.getValue();
        String jenis = comboJenis.getValue();
        String durasiStr = fieldDurasi.getText();
        String catatan = areaCatatan.getText();

        if (tanggal == null || jenis == null || durasiStr.isEmpty()) {
            tampilkanAlertError("Tanggal, Jenis, dan Durasi tidak boleh kosong!");
            return;
        }
        int durasi;
        try {
            durasi = Integer.parseInt(durasiStr);
        } catch (NumberFormatException e) {
            tampilkanAlertError("Durasi harus berupa angka (menit)!");
            return;
        }
        
        
        aktivitasTerpilih.setTanggal(tanggal);
        aktivitasTerpilih.setJenisOlahraga(jenis);
        aktivitasTerpilih.setDurasiMenit(durasi);
        aktivitasTerpilih.setCatatan(catatan);
        
        
        clearForm();
        
        tableView.refresh(); 
    }

    
    @FXML
    private void handleDelete() {
        if (aktivitasTerpilih == null) {
            tampilkanAlertError("Pilih data di tabel terlebih dahulu untuk dihapus.");
            return;
        }
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Konfirmasi Hapus");
        alert.setHeaderText("Hapus Aktivitas");
        alert.setContentText("Apakah Anda yakin ingin menghapus data ini?\n" + 
                             aktivitasTerpilih.getJenisOlahraga() + " pada " + aktivitasTerpilih.getTanggal());
        
        Optional<ButtonType> result = alert.showAndWait();
        
        if (result.isPresent() && result.get() == ButtonType.OK) {
            daftarAktivitas.remove(aktivitasTerpilih);
            clearForm();
        }
    }

        private void clearForm() {
        datePicker.setValue(null);
        comboJenis.setValue("Lari");
        fieldDurasi.clear();
        areaCatatan.clear();
        
        aktivitasTerpilih = null; 
        tableView.getSelectionModel().clearSelection(); 
        
        btnSimpan.setDisable(false); 
        btnUpdate.setDisable(true); 
        btnDelete.setDisable(true); 
    }
    
    private void tampilkanAlertError(String pesan) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Input");
        alert.setHeaderText(null);
        alert.setContentText(pesan);
        alert.showAndWait();
    }
}