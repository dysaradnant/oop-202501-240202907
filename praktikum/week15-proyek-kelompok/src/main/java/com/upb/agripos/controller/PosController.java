package com.upb.agripos.controller;

import com.upb.agripos.auth.User;
import com.upb.agripos.product.*;
import com.upb.agripos.transaction.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.geometry.Pos;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

public class PosController {
    @FXML private TextField txtCari;
    @FXML private TableView<Product> tblKatalog;
    @FXML private TextField txtKode, txtQty;
    @FXML private TableView<CartItem> tblCart; // Pastikan fx:id di FXML adalah tblCart
    @FXML private Label lblTotal;

    private ProductService prodService = new ProductService(new JdbcProductRepository());
    private TransactionRepository transRepo = new TransactionRepository();
    private Cart cart = new Cart();
    private User currentUser;
    private ObservableList<Product> masterData = FXCollections.observableArrayList();

    public void setUser(User user) { this.currentUser = user; }

    @FXML
    public void initialize() {
        setupKatalog();
        setupKeranjang();
    }

    private void setupKatalog() {
        // Reset kolom dulu biar tidak duplikat
        tblKatalog.getColumns().clear();

        TableColumn<Product, String> colKode = new TableColumn<>("Kode");
        colKode.setCellValueFactory(new PropertyValueFactory<>("kode"));

        TableColumn<Product, String> colNama = new TableColumn<>("Nama");
        colNama.setCellValueFactory(new PropertyValueFactory<>("nama"));

        TableColumn<Product, Double> colHarga = new TableColumn<>("Harga");
        colHarga.setCellValueFactory(new PropertyValueFactory<>("harga"));

        TableColumn<Product, Integer> colStok = new TableColumn<>("Stok");
        colStok.setCellValueFactory(new PropertyValueFactory<>("stok"));

        tblKatalog.getColumns().addAll(colKode, colNama, colHarga, colStok);

        // Load Data
        masterData.setAll(prodService.list());

        // Filter Pencarian
        FilteredList<Product> filteredData = new FilteredList<>(masterData, p -> true);
        txtCari.textProperty().addListener((obs, oldVal, newVal) -> {
            filteredData.setPredicate(p -> {
                if (newVal == null || newVal.isEmpty()) return true;
                String lower = newVal.toLowerCase();
                return p.getNama().toLowerCase().contains(lower) || p.getKode().toLowerCase().contains(lower);
            });
        });
        tblKatalog.setItems(filteredData);

        // Klik 2x masuk keranjang
        tblKatalog.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2 && tblKatalog.getSelectionModel().getSelectedItem() != null) {
                addToCartLogic(tblKatalog.getSelectionModel().getSelectedItem(), 1);
            }
        });
    }

    private void setupKeranjang() {
        tblCart.getColumns().clear(); // Hapus kolom bawaan FXML jika ada

        TableColumn<CartItem, String> colItem = new TableColumn<>("Item");
        colItem.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getProduct().getNama()));
        colItem.setPrefWidth(120);

        TableColumn<CartItem, Integer> colQty = new TableColumn<>("Qty");
        colQty.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colQty.setStyle("-fx-alignment: CENTER;");

        TableColumn<CartItem, Double> colSub = new TableColumn<>("Subtotal");
        colSub.setCellValueFactory(new PropertyValueFactory<>("subtotal"));

        // KOLOM AKSI (Tombol +, -, Hapus)
        TableColumn<CartItem, Void> colAction = new TableColumn<>("Aksi");
        colAction.setPrefWidth(140);

        Callback<TableColumn<CartItem, Void>, TableCell<CartItem, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<CartItem, Void> call(final TableColumn<CartItem, Void> param) {
                return new TableCell<>() {
                    private final Button btnMinus = new Button("-");
                    private final Button btnPlus = new Button("+");
                    private final Button btnDel = new Button("X");

                    {
                        // Styling Tombol
                        btnMinus.setStyle("-fx-background-color: #f39c12; -fx-text-fill: white; -fx-min-width: 30px;");
                        btnPlus.setStyle("-fx-background-color: #27ae60; -fx-text-fill: white; -fx-min-width: 30px;");
                        btnDel.setStyle("-fx-background-color: #c0392b; -fx-text-fill: white; -fx-min-width: 30px;");

                        // Logika Tombol (-)
                        btnMinus.setOnAction(event -> {
                            CartItem item = getTableView().getItems().get(getIndex());
                            if (item.getQuantity() > 1) {
                                cart.updateQty(item.getProduct().getKode(), item.getQuantity() - 1);
                            } else {
                                cart.removeItem(item.getProduct().getKode());
                            }
                            refreshCartUI();
                        });

                        // Logika Tombol (+)
                        btnPlus.setOnAction(event -> {
                            CartItem item = getTableView().getItems().get(getIndex());
                            // Cek stok database/gudang dulu
                            if (item.getProduct().getStok() > item.getQuantity()) {
                                cart.updateQty(item.getProduct().getKode(), item.getQuantity() + 1);
                                refreshCartUI();
                            } else {
                                new Alert(Alert.AlertType.WARNING, "Stok Gudang Habis!").show();
                            }
                        });

                        // Logika Tombol (X)
                        btnDel.setOnAction(event -> {
                            CartItem item = getTableView().getItems().get(getIndex());
                            cart.removeItem(item.getProduct().getKode());
                            refreshCartUI();
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            HBox pane = new HBox(5, btnMinus, btnPlus, btnDel);
                            pane.setAlignment(Pos.CENTER);
                            setGraphic(pane);
                        }
                    }
                };
            }
        };

        colAction.setCellFactory(cellFactory);
        tblCart.getColumns().addAll(colItem, colQty, colSub, colAction);
    }

    // Refresh Tampilan Tabel & Total Harga
    private void refreshCartUI() {
        tblCart.setItems(FXCollections.observableArrayList(cart.getItems()));
        lblTotal.setText("Rp " + String.format("%.0f", cart.getTotal()));
        tblCart.refresh();
    }

    @FXML
    public void handleAdd() {
        String kode = txtKode.getText();
        if (kode.isEmpty()) return;
        Product p = prodService.get(kode);
        int qty = 1;
        try { qty = Integer.parseInt(txtQty.getText()); } catch (Exception e) {}

        addToCartLogic(p, qty);
    }

    private void addToCartLogic(Product p, int qty) {
        if (p != null) {
            if (p.getStok() >= qty) {
                cart.addItem(p, qty);
                refreshCartUI();
                txtKode.clear();
                txtQty.setText("1");
            } else {
                new Alert(Alert.AlertType.WARNING, "Stok Tidak Cukup!").show();
            }
        } else {
            new Alert(Alert.AlertType.WARNING, "Barang Tidak Ditemukan!").show();
        }
    }

    @FXML
    public void handleCheckout() {
        if (cart.getItems().isEmpty()) return;
        double total = cart.getTotal();

        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Pembayaran");
        dialog.setHeaderText("Total: Rp " + String.format("%.0f", total));
        dialog.setContentText("Masukkan Nominal Uang:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(uang -> {
            try {
                double bayar = Double.parseDouble(uang);
                if (bayar >= total) {
                    processTransaction(total, bayar, bayar - total);
                } else {
                    new Alert(Alert.AlertType.ERROR, "Uang Kurang!").show();
                }
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "Input Salah!").show();
            }
        });
    }

    private void processTransaction(double total, double bayar, double kembali) {
        String id = "TRX-" + UUID.randomUUID().toString().substring(0,5).toUpperCase();
        transRepo.save(id, total, currentUser != null ? currentUser.getUsername() : "Kasir");

        for (CartItem item : cart.getItems()) {
            prodService.reduceStock(item.getProduct().getKode(), item.getQuantity());
        }

        cart.clear();
        refreshCartUI();
        masterData.setAll(prodService.list()); // Refresh stok katalog

        Alert info = new Alert(Alert.AlertType.INFORMATION);
        info.setTitle("Sukses");
        info.setHeaderText("Kembalian: Rp " + String.format("%.0f", kembali));
        info.setContentText("Transaksi Berhasil Disimpan.");
        info.show();
    }

    @FXML
    public void handleLogout() {
        try {
            Stage stage = (Stage) lblTotal.getScene().getWindow();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/fxml/login.fxml"))));
            stage.centerOnScreen();
        } catch (IOException e) { e.printStackTrace(); }
    }
}