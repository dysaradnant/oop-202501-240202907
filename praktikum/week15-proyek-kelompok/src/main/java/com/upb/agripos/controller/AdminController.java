package com.upb.agripos.controller;

import java.io.IOException;

import com.upb.agripos.product.JdbcProductRepository;
import com.upb.agripos.product.Product;
import com.upb.agripos.product.ProductService;
import com.upb.agripos.report.ReportService;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class AdminController {
    @FXML private TextField txtKode, txtNama, txtKategori, txtHarga, txtStok;
    @FXML private TableView<Product> tblRak;
    @FXML private Label lblOmzet;

    private ProductService service = new ProductService(new JdbcProductRepository());
    private ReportService report = new ReportService();

    @FXML
    public void initialize() {
        // Inisialisasi kolom tabel agar tidak muncul pesan "No columns in table"
        TableColumn<Product, String> colKode = new TableColumn<>("Kode");
        colKode.setCellValueFactory(new PropertyValueFactory<>("kode"));

        TableColumn<Product, String> colNama = new TableColumn<>("Nama");
        colNama.setCellValueFactory(new PropertyValueFactory<>("nama"));

        TableColumn<Product, Double> colHarga = new TableColumn<>("Harga");
        colHarga.setCellValueFactory(new PropertyValueFactory<>("harga"));

        TableColumn<Product, Integer> colStok = new TableColumn<>("Stok");
        colStok.setCellValueFactory(new PropertyValueFactory<>("stok"));

        tblRak.getColumns().setAll(colKode, colNama, colHarga, colStok);
        refresh();
    }

    @FXML public void handleSave() {
        service.add(new Product(txtKode.getText(), txtNama.getText(), txtKategori.getText(),
                Double.parseDouble(txtHarga.getText()), Integer.parseInt(txtStok.getText())));
        refresh();
    }

    @FXML public void handleUpdate() {
        service.update(new Product(txtKode.getText(), txtNama.getText(), txtKategori.getText(),
                Double.parseDouble(txtHarga.getText()), Integer.parseInt(txtStok.getText())));
        refresh();
    }

    @FXML public void handleDelete() {
        service.delete(txtKode.getText());
        refresh();
    }

    @FXML public void handleReport() {
        lblOmzet.setText("Total Keuangan: Rp " + report.getDailyTurnover());
    }

    private void refresh() {
        tblRak.setItems(FXCollections.observableArrayList(service.list()));
    }
    @FXML
    public void handleLogout() {
        try {
            // 1. Load file Login
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
            Parent root = loader.load();

            // 2. Ambil Stage (Jendela) saat ini dari salah satu komponen (misal: txtKode)
            Stage stage = (Stage) txtKode.getScene().getWindow();

            // 3. Ganti Scene ke Login
            stage.setScene(new Scene(root));
            stage.setTitle("Agri-POS Integrated System");
            stage.centerOnScreen();
            System.out.println("[INFO] Admin berhasil Logout.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
