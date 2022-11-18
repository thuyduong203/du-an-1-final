/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.view;

import com.mycompany.customModel.MonAnKhuyenMaiResponse;
import com.mycompany.domainModel.KhuyenMai;
import com.mycompany.domainModel.MonAn;
import com.mycompany.domainModel.NhanVien;
import com.mycompany.repository.impl.KhuyenMaiRepository;
//import com.mycompany.domainModel.KhuyenMaiChiTiet;
//import com.mycompany.service.impl.KhuyenMaiChiTietService;
import com.mycompany.service.impl.KhuyenMaiService;
import com.mycompany.service.impl.MonAnService;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class ViewKhuyenMai extends javax.swing.JFrame {

    private DefaultTableModel dtmKhuyenMai = new DefaultTableModel();
    private DefaultTableModel dtmKMCT = new DefaultTableModel();
    private List<KhuyenMai> listKM = new ArrayList<>();
    private KhuyenMaiRepository khuyenMaiRepository = new KhuyenMaiRepository();
    private DefaultComboBoxModel dcbmLoaiKM = new DefaultComboBoxModel();
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//    private List<KhuyenMaiChiTiet> listKMCT = new ArrayList<>();
    private KhuyenMaiService khuyenMaiService = new KhuyenMaiService();
//    private KhuyenMaiChiTietService khuyenMaiChiTietService = new KhuyenMaiChiTietService();
    // private DefaultComboBoxModel
    private NhanVien nhanV;
    private java.util.Date today = new java.util.Date();
    private List<MonAn> listMonAn = new ArrayList<>();
    private MonAnService monAnService = new MonAnService();
    private DefaultTableModel dtmMonAn = new DefaultTableModel();
    private List<MonAn> listCTKM = new ArrayList<>();
    private List<String> listHinhThucTT = new ArrayList<>();
    private List<MonAn> listThemKM_MA = new ArrayList<>();

    public ViewKhuyenMai(NhanVien nhanV) {
        initComponents();
        this.nhanV = nhanV;
        tbKhuyenMai.setModel(dtmKhuyenMai);
        String headers[] = {"STT", "Mã KM", "Tên KM", "TrangThai"};
        dtmKhuyenMai.setColumnIdentifiers(headers);
        listKM = khuyenMaiRepository.getAll();
        showData(listKM, 1);
        radioDangApDung.setSelected(true);
        cbbLoaiKM.setModel(dcbmLoaiKM);
        String headerMonAn[] = {"STT", "Mã món ăn", "Tên món ăn", "Đơn giá", "Đv tính"};
        dtmMonAn.setColumnIdentifiers(headerMonAn);
        tbMonAn.setModel(dtmMonAn);
        listMonAn = monAnService.getAll();
        showDataMonAn(listMonAn, 1);
        tbKMCT.setModel(dtmKMCT);
        String headersKMCT[] = {"STT", "Mã KM", "Mã món ăn", "Tên món ăn"};
        dtmKMCT.setColumnIdentifiers(headersKMCT);
        loadLoaiKM();
    }

    private void showDataCTKM(List<MonAn> listKMCT, int stt) {
        dtmKMCT.setRowCount(0);
        for (MonAn monAn : listKMCT) {
            dtmKMCT.addRow(monAn.toDataRowViewKM(stt));
        }
    }

    private void showData(List<KhuyenMai> listKM, int stt) {
        dtmKhuyenMai.setRowCount(0);
        for (KhuyenMai khuyenMai : listKM) {
            dtmKhuyenMai.addRow(khuyenMai.toDataRowViewKM(stt));
            stt++;
        }
    }

    private void showDataMonAn(List<MonAn> listMonAn, int stt) {
        dtmMonAn.setRowCount(0);
        for (MonAn monAn : listMonAn) {
            dtmMonAn.addRow(monAn.toDataRow(stt));
            stt++;
        }
    }

    private void loadLoaiKM() {
        dcbmLoaiKM.addElement("Phần trăm");
        dcbmLoaiKM.addElement("Tiền mặt");
    }

    private void fillKM(int index, List<KhuyenMai> listKM) {
        KhuyenMai khuyenMai = listKM.get(index);
        txTenKhuyenMai.setText(khuyenMai.getMaKhuyenMai());
        txtDonVi.setText(String.valueOf(khuyenMai.getGiaTriKM()));
        dcbmLoaiKM.setSelectedItem(khuyenMai.getLoaiKhuyenMai());
        txtThoiGianBatDau.setDate(khuyenMai.getThoiGianBD());
        txtThoiGianKetThuc.setDate(khuyenMai.getThoiGianKT());
        txtMaKM.setText(khuyenMai.getMaKhuyenMai());
        txtMaKM.setEnabled(false);
        if (khuyenMai.getTrangThai() == 0) {
            radioDangApDung.setSelected(true);
        } else {
            radioNgungApDung.setSelected(true);
        }
    }

    private KhuyenMai newKM() {
        KhuyenMai khuyenMai = new KhuyenMai();
        khuyenMai.setMaKhuyenMai(txtMaKM.getText());
        khuyenMai.setGhiChu(txtGhiChu.getText());
        khuyenMai.setLoaiKhuyenMai(dcbmLoaiKM.getSelectedItem().toString());
        khuyenMai.setNhanVien(nhanV);
        khuyenMai.setTenKhuyenMai(txTenKhuyenMai.getText());
        //Date.valueOf(dateFormat.format(date))
        khuyenMai.setThoiGianBD(Date.valueOf(dateFormat.format(txtThoiGianBatDau.getDate())));
        khuyenMai.setThoiGianKT(Date.valueOf(dateFormat.format(txtThoiGianKetThuc.getDate())));
        khuyenMai.setGiaTriKM(BigDecimal.valueOf(Double.valueOf(txtDonVi.getText())));
        if (radioDangApDung.isSelected()) {
            khuyenMai.setTrangThai(0);
        } else {
            khuyenMai.setTrangThai(1);
        }
        return khuyenMai;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        radiotrangThaiMonAn = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        lbNhanVien = new javax.swing.JLabel();
        lbNgayGio = new javax.swing.JLabel();
        btnTrangChu = new javax.swing.JButton();
        btnHoaDon = new javax.swing.JButton();
        btnKhachHang = new javax.swing.JButton();
        btnBan = new javax.swing.JButton();
        btnKhuyenMai = new javax.swing.JButton();
        btnSanPham = new javax.swing.JButton();
        btnThongKe = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbKhuyenMai = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtThoiGianBatDau = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        txtThoiGianKetThuc = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        txTenKhuyenMai = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtDonVi = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        radioDangApDung = new javax.swing.JRadioButton();
        radioNgungApDung = new javax.swing.JRadioButton();
        jLabel13 = new javax.swing.JLabel();
        txtMaKM = new javax.swing.JTextField();
        cbbLoaiKM = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        txtGhiChu = new javax.swing.JTextField();
        btnAddKhuyenMai = new javax.swing.JButton();
        btnUpdateKhuyenMai = new javax.swing.JButton();
        btnRemoveKhuyenMai = new javax.swing.JButton();
        btnClearKhuyenMai = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbMonAn = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbKMCT = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        btnApDung = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        radioTatCa = new javax.swing.JRadioButton();
        radioDangApDungKM = new javax.swing.JRadioButton();
        radioChuaApDungKM = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 204, 204));

        jPanel2.setBackground(new java.awt.Color(153, 255, 255));

        lbNhanVien.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbNhanVien.setText("Welcome: Nguyễn Đức Dụng - Nhân Viên");

        lbNgayGio.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbNgayGio.setText("12:05:13 10-11-2022");

        btnTrangChu.setBackground(new java.awt.Color(204, 204, 204));
        btnTrangChu.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnTrangChu.setText("TRANG CHỦ");
        btnTrangChu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTrangChuActionPerformed(evt);
            }
        });

        btnHoaDon.setBackground(new java.awt.Color(204, 204, 204));
        btnHoaDon.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnHoaDon.setText("HÓA ĐƠN");
        btnHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHoaDonActionPerformed(evt);
            }
        });

        btnKhachHang.setBackground(new java.awt.Color(204, 204, 204));
        btnKhachHang.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnKhachHang.setText("KHÁCH HÀNG");
        btnKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKhachHangActionPerformed(evt);
            }
        });

        btnBan.setBackground(new java.awt.Color(204, 204, 204));
        btnBan.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnBan.setText("BÀN");
        btnBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBanActionPerformed(evt);
            }
        });

        btnKhuyenMai.setBackground(new java.awt.Color(255, 255, 153));
        btnKhuyenMai.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnKhuyenMai.setText("KHUYẾN MÃI");

        btnSanPham.setBackground(new java.awt.Color(204, 204, 204));
        btnSanPham.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSanPham.setText("SẢN PHẨM");
        btnSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSanPhamActionPerformed(evt);
            }
        });

        btnThongKe.setBackground(new java.awt.Color(204, 204, 204));
        btnThongKe.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnThongKe.setText("THỐNG KÊ");
        btnThongKe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThongKeActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(204, 204, 204));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton1.setText("NHÂN VIÊN");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbNgayGio, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnTrangChu)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnHoaDon)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnKhachHang)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnKhuyenMai)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSanPham)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnThongKe)
                .addGap(10, 10, 10))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbNgayGio, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSanPham)
                            .addComponent(btnKhuyenMai)
                            .addComponent(btnBan)
                            .addComponent(btnKhachHang)
                            .addComponent(btnHoaDon)
                            .addComponent(btnTrangChu)
                            .addComponent(btnThongKe)
                            .addComponent(jButton1))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel2.setText("KHUYẾN MÃI");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Tìm kiếm theo tên hoặc theo mã:");

        btnSearch.setBackground(new java.awt.Color(102, 255, 102));
        btnSearch.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSearch.setText("Seach");

        tbKhuyenMai.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbKhuyenMai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbKhuyenMaiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbKhuyenMai);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Thời gian bắt đầu :");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Thời gian kết thúc :");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Tên khuyến mãi     :");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Loại khuyến mãi    :");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Đơn vị                    :");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("Trạng thái              :");

        buttonGroup1.add(radioDangApDung);
        radioDangApDung.setText("Đang áp dụng");

        buttonGroup1.add(radioNgungApDung);
        radioNgungApDung.setText("Ngừng áp dụng");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setText("Mã KM:");

        cbbLoaiKM.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbLoaiKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbLoaiKMActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel14.setText("Ghi chú:");

        btnAddKhuyenMai.setText("Add");
        btnAddKhuyenMai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddKhuyenMaiActionPerformed(evt);
            }
        });

        btnUpdateKhuyenMai.setText("Update");
        btnUpdateKhuyenMai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateKhuyenMaiActionPerformed(evt);
            }
        });

        btnRemoveKhuyenMai.setText("Remove");
        btnRemoveKhuyenMai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveKhuyenMaiActionPerformed(evt);
            }
        });

        btnClearKhuyenMai.setText("Clear");
        btnClearKhuyenMai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearKhuyenMaiActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setText("Khuyến mãi:");

        tbMonAn.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbMonAn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbMonAnMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbMonAn);

        tbKMCT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(tbKMCT);

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setText("Áp dụng KM:");

        btnApDung.setText("Áp dụng");
        btnApDung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnApDungActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setText("Món ăn:");

        radioTatCa.setText("Tất cả");

        radioDangApDungKM.setText("Đang áp dụng KM");

        radioChuaApDungKM.setText("Chưa áp dụng KM");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1202, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(537, 537, 537)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 597, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel4)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1)
                                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtMaKM, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(txtThoiGianKetThuc, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                                        .addComponent(txtThoiGianBatDau, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(txTenKhuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel7)
                                            .addComponent(jLabel8)
                                            .addComponent(jLabel14))
                                        .addGap(19, 19, 19)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtDonVi, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(10, 10, 10)
                                                .addComponent(radioDangApDung, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(radioNgungApDung))
                                            .addComponent(txtGhiChu, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 478, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addGap(18, 18, 18)
                                        .addComponent(cbbLoaiKM, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(radioTatCa)
                                        .addGap(18, 18, 18)
                                        .addComponent(radioDangApDungKM)
                                        .addGap(26, 26, 26)
                                        .addComponent(radioChuaApDungKM)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 633, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(58, 58, 58)
                                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(76, 76, 76)
                                                .addComponent(btnApDung)))
                                        .addGap(0, 0, Short.MAX_VALUE))))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(568, 568, 568)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(46, 46, 46)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtSearch))
                .addGap(100, 100, 100)
                .addComponent(btnSearch)
                .addGap(103, 103, 103))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addComponent(btnAddKhuyenMai)
                .addGap(43, 43, 43)
                .addComponent(btnUpdateKhuyenMai)
                .addGap(52, 52, 52)
                .addComponent(btnRemoveKhuyenMai)
                .addGap(31, 31, 31)
                .addComponent(btnClearKhuyenMai)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch)
                    .addComponent(jLabel2))
                .addGap(19, 19, 19)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMaKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(txtThoiGianBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(txtThoiGianKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txTenKhuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cbbLoaiKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtDonVi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(radioDangApDung)
                            .addComponent(radioNgungApDung))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtGhiChu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(radioTatCa)
                            .addComponent(radioDangApDungKM)
                            .addComponent(radioChuaApDungKM))
                        .addGap(11, 11, 11)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAddKhuyenMai)
                            .addComponent(btnUpdateKhuyenMai)
                            .addComponent(btnRemoveKhuyenMai)
                            .addComponent(btnClearKhuyenMai)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(btnApDung)))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnTrangChuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTrangChuActionPerformed
        // TODO add your handling code here:
        ViewTrangChu viewTrangChu = new ViewTrangChu(nhanV);
        this.dispose();
        viewTrangChu.setVisible(true);
    }//GEN-LAST:event_btnTrangChuActionPerformed

    private void btnHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHoaDonActionPerformed
        // TODO add your handling code here:
        ViewHoaDon viewHoaDon = new ViewHoaDon(nhanV);
        this.dispose();
        viewHoaDon.setVisible(true);
    }//GEN-LAST:event_btnHoaDonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        ViewNhanVien viewNhanVien = new ViewNhanVien(nhanV);
        this.dispose();
        viewNhanVien.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKhachHangActionPerformed
        // TODO add your handling code here:
        ViewKhachHang viewKhachHang = new ViewKhachHang(nhanV);
        this.dispose();
        viewKhachHang.setVisible(true);
    }//GEN-LAST:event_btnKhachHangActionPerformed

    private void btnBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBanActionPerformed
        // TODO add your handling code here:
        ViewBan viewBan = new ViewBan(nhanV);
        this.dispose();
        viewBan.setVisible(true);
    }//GEN-LAST:event_btnBanActionPerformed

    private void btnSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSanPhamActionPerformed
        // TODO add your handling code here:
        ViewSanPham viewSanPham = new ViewSanPham(nhanV);
        this.dispose();
        viewSanPham.setVisible(true);
    }//GEN-LAST:event_btnSanPhamActionPerformed

    private void btnThongKeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThongKeActionPerformed
        // TODO add your handling code here:
        ViewThongKe viewThongKe = new ViewThongKe(nhanV);
        this.dispose();
        viewThongKe.setVisible(true);
    }//GEN-LAST:event_btnThongKeActionPerformed

    private void tbKhuyenMaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbKhuyenMaiMouseClicked
        int index = tbKhuyenMai.getSelectedRow();
        fillKM(index, listKM);
        KhuyenMai khuyenMai = khuyenMaiService.getOne(txtMaKM.getText());
        listCTKM = monAnService.getMonAnByKhuyenMai(khuyenMai);
        showDataCTKM(listCTKM, 1);
    }//GEN-LAST:event_tbKhuyenMaiMouseClicked

    private void btnAddKhuyenMaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddKhuyenMaiActionPerformed
        KhuyenMai khuyenMai = newKM();
        JOptionPane.showMessageDialog(this, khuyenMaiService.add(khuyenMai));
        listKM = khuyenMaiService.getAll();
        showData(listKM, 1);
    }//GEN-LAST:event_btnAddKhuyenMaiActionPerformed

    private void btnUpdateKhuyenMaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateKhuyenMaiActionPerformed
        int index = tbKhuyenMai.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Chọn data!");
        } else {
            KhuyenMai khuyenMai = newKM();
            JOptionPane.showMessageDialog(this, khuyenMaiService.update(khuyenMai, txtMaKM.getText()));
            listKM = khuyenMaiService.getAll();
            showData(listKM, WIDTH);
        }
    }//GEN-LAST:event_btnUpdateKhuyenMaiActionPerformed

    private void btnClearKhuyenMaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearKhuyenMaiActionPerformed
        txTenKhuyenMai.setText("");
        txtDonVi.setText("");
        dcbmLoaiKM.setSelectedItem("Phần trăm");
        txtThoiGianBatDau.setDate(Date.valueOf(dateFormat.format(today)));
        txtThoiGianKetThuc.setDate(Date.valueOf(dateFormat.format(today)));
        txtMaKM.setText("");
        txtMaKM.setEnabled(true);
    }//GEN-LAST:event_btnClearKhuyenMaiActionPerformed

    private void btnRemoveKhuyenMaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveKhuyenMaiActionPerformed
        int index = tbKhuyenMai.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Chọn data!");
        } else {
            JOptionPane.showMessageDialog(this, khuyenMaiService.remove(txtMaKM.getText()));
            listKM = khuyenMaiService.getAll();
            showData(listKM, 1);
        }
    }//GEN-LAST:event_btnRemoveKhuyenMaiActionPerformed

    private void cbbLoaiKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbLoaiKMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbLoaiKMActionPerformed

    private void tbMonAnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbMonAnMouseClicked
        int index = tbMonAn.getSelectedRow();
        MonAn monAn = listMonAn.get(index);
        String maKM = txtMaKM.getText();
        if (maKM.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn KM!");
        } else if (monAn.getKhuyenMai() != null) {
            JOptionPane.showMessageDialog(this, "Sản phẩm này đã được áp dụng KM!");
        } else {
            listThemKM_MA.add(monAn);
            showDataCTKM(listThemKM_MA, 1);
        }
    }//GEN-LAST:event_tbMonAnMouseClicked

    private void btnApDungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApDungActionPerformed
        String maKM = txtMaKM.getText();
        int szListThemKM_Ma = listThemKM_MA.size();
        if (maKM.isEmpty() || szListThemKM_Ma <= 0) {
            JOptionPane.showMessageDialog(this, "??");
        } else {
            KhuyenMai khuyenMai = khuyenMaiService.getOne(maKM);
            try {
                for (int i = 0; i < szListThemKM_Ma; i++) {
                    String maMA = listThemKM_MA.get(i).getMaMonAn();
                    String updateKM_MA = monAnService.themKMChoMonAn(khuyenMai, maMA);
                }
                listThemKM_MA.removeAll(listThemKM_MA);
                JOptionPane.showMessageDialog(this, "Áp dụng KM thành công!");
                showDataCTKM(listThemKM_MA, 1);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Áp dụng KM ko thành công!");
            }
        }
    }//GEN-LAST:event_btnApDungActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ViewKhuyenMai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewKhuyenMai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewKhuyenMai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewKhuyenMai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new ViewKhuyenMai().setVisible(true);
//            }
//        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddKhuyenMai;
    private javax.swing.JButton btnApDung;
    private javax.swing.JButton btnBan;
    private javax.swing.JButton btnClearKhuyenMai;
    private javax.swing.JButton btnHoaDon;
    private javax.swing.JButton btnKhachHang;
    private javax.swing.JButton btnKhuyenMai;
    private javax.swing.JButton btnRemoveKhuyenMai;
    private javax.swing.JButton btnSanPham;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnThongKe;
    private javax.swing.JButton btnTrangChu;
    private javax.swing.JButton btnUpdateKhuyenMai;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbbLoaiKM;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lbNgayGio;
    private javax.swing.JLabel lbNhanVien;
    private javax.swing.JRadioButton radioChuaApDungKM;
    private javax.swing.JRadioButton radioDangApDung;
    private javax.swing.JRadioButton radioDangApDungKM;
    private javax.swing.JRadioButton radioNgungApDung;
    private javax.swing.JRadioButton radioTatCa;
    private javax.swing.ButtonGroup radiotrangThaiMonAn;
    private javax.swing.JTable tbKMCT;
    private javax.swing.JTable tbKhuyenMai;
    private javax.swing.JTable tbMonAn;
    private javax.swing.JTextField txTenKhuyenMai;
    private javax.swing.JTextField txtDonVi;
    private javax.swing.JTextField txtGhiChu;
    private javax.swing.JTextField txtMaKM;
    private javax.swing.JTextField txtSearch;
    private com.toedter.calendar.JDateChooser txtThoiGianBatDau;
    private com.toedter.calendar.JDateChooser txtThoiGianKetThuc;
    // End of variables declaration//GEN-END:variables
}
