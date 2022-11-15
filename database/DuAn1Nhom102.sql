﻿CREATE DATABASE DuAn1Nhom102
GO
USE DuAn1Nhom102
--thêm bảng loại món ăn, bỏ đệ quy
--1. Loai
CREATE TABLE Loai
	(
		IdLoai UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
		IdDanhMuc UNIQUEIDENTIFIER NOT NULL,
		MaLoai  VARCHAR(20) UNIQUE NOT NULL,
		TenLoai NVARCHAR(150) NOT NULL,
		TrangThai INT DEFAULT NULL
	)
--2.TABLE KHÁCH HÀNG
CREATE TABLE Khach_Hang
	(
		IdKH UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
		MaKH VARCHAR(20) UNIQUE NOT NULL,
		Ho NVARCHAR(20) DEFAULT NULL,
		TenDem NVARCHAR(100) DEFAULT NULL,
		Ten NVARCHAR(20) DEFAULT NULL,
		GioiTinh NVARCHAR(20) DEFAULT NULL,
		NgaySinh DATE DEFAULT NULL,
		Sdt VARCHAR(15) DEFAULT NULL,
		DiaChi NVARCHAR(150) DEFAULT NULL,
		ThanhPho NVARCHAR(100) DEFAULT NULL,
		QuocGia NVARCHAR(100) DEFAULT NULL,
		TrangThai INT DEFAULT 0
	)
--3.TABLE BÀN
CREATE TABLE Ban
	(
		IdBan UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
		IdKhuVuc UNIQUEIDENTIFIER NOT NULL,
		MaBan INT UNIQUE NOT NULL,
		SoLuongChoNgoi INT NOT NULL,
		TrangThai INT DEFAULT 0
	)
--4.Thêm bảng Khu vực (3)
CREATE TABLE Khu_Vuc
	(
		IdKhuVuc UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
		MaKhuVuc VARCHAR(20) UNIQUE NOT NULL,
		TenKhuVuc NVARCHAR(100) NOT NULL,
		TrangThai INT DEFAULT 0
	)
--5.Hoá Đơn
CREATE TABLE Hoa_Don
	(
		IdHD UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
		IdNV UNIQUEIDENTIFIER NOT NULL,
		IdKH UNIQUEIDENTIFIER DEFAULT NULL,
		IdBan UNIQUEIDENTIFIER NOT NULL,
		MaHD VARCHAR(20) UNIQUE NOT NULL,
		NgayTao DATE NOT NULL,
		NgayThanhToan DATE DEFAULT NULL,
		TongTien DECIMAL(20,0) DEFAULT 0,
		GhiChu NVARCHAR(200) DEFAULT NULL,
		TrangThai INT DEFAULT 0
	)
	--6.Giao dịch
CREATE TABLE Giao_Dich
	(
		Id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
		IdHD UNIQUEIDENTIFIER NOT NULL,
		HinhThucThanhToan NVARCHAR(200) NOT NULL,
		SoTienThanhToan DECIMAL(20,0) DEFAULT 0
	)
--7.Combo --run r nma có cần thêm số combo sẽ bán ko-- ko cần thêm đâu ngu shiii
CREATE TABLE Combo
	(
		IdCombo UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
		IdNV UNIQUEIDENTIFIER NOT NULL,
		MaCB VARCHAR(20) UNIQUE NOT NULL,
		TenCB NVARCHAR(150) NOT NULL,
		HinhAnh VARCHAR(255) DEFAULT NULL,
		DonGia DECIMAL(20,0) NOT NULL,
		TrangThai INT DEFAULT 0
	)
--8.Chi_Tiet_Combo
CREATE TABLE  Chi_Tiet_Combo
	(
		Id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
		IdCombo UNIQUEIDENTIFIER NOT NULL,
		IdMonAn UNIQUEIDENTIFIER NOT NULL,
		SoLuongMonAn INT NOT NULL,
	)
--9.Hoa_Don_Chi_Tiet 
CREATE TABLE Hoa_Don_Chi_Tiet
	(
		IdHDCT UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
		IdMonAn UNIQUEIDENTIFIER DEFAULT NULL, 
		IdHD UNIQUEIDENTIFIER NOT NULL,
		IdCombo UNIQUEIDENTIFIER DEFAULT NULL,
		SoLuongMonAn INT NOT NULL,
		DonGiaMonAn DECIMAL(20,0) NOT NULL,
		SoLuongCombo INT NOT NULL,
		DonGiaCombo DECIMAL(20,0) NOT NULL
	)
--10.Nhan_Vien
CREATE TABLE Nhan_Vien
	(
		IdNV UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
		IdCV UNIQUEIDENTIFIER NOT NULL,
		MaNV VARCHAR(20) UNIQUE NOT NULL,
		Ho NVARCHAR(20) NOT NULL,
		TenDem NVARCHAR(100) NOT NULL,
		Ten NVARCHAR(20) NOT NULL,
		GioiTinh NVARCHAR(20) DEFAULT NULL,
		Sdt VARCHAR(15) NOT NULL,
		Email VARCHAR(100) DEFAULT NULL,
		NgaySinh DATE NOT NULL,
		DiaChi NVARCHAR(200) NOT NULL,
		MatKhau VARCHAR(70) NOT NULL,
		TrangThai INT DEFAULT 0
	)
--11.Mon_An
CREATE TABLE Mon_An 
	(
		IdMonAn UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
		IdLoai UNIQUEIDENTIFIER NOT NULL,
		IdKM UNIQUEIDENTIFIER DEFAULT NULL,
		MaMonAn VARCHAR(20) UNIQUE NOT NULL,
		TenMonAn NVARCHAR(150) NOT NULL,
		HinhAnh VARCHAR(255) DEFAULT NULL,
		DonGia DECIMAL(20,0) NOT NULL,
		DonViTinh NVARCHAR(50) NOT NULL,
		TrangThai INT DEFAULT 0
	)
--12.KhuyenMai 
CREATE TABLE Khuyen_Mai
	(
		IdKM UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
		IdNV UNIQUEIDENTIFIER NOT NULL,
		MaKM VARCHAR(20) UNIQUE NOT NULL,
		TenKM NVARCHAR(50) NOT NULL,
		ThoiGianBD DATE NOT NULL,
		ThoiGianKT DATE NOT NULL,
		LoaiKM NVARCHAR(50) NOT NULL,
		GtriKM DECIMAL(20,0) DEFAULT 0,
		GhiChu NVARCHAR(200) DEFAULT NULL,
		TrangThai INT DEFAULT 0 
	)
----12. Ca_Lam_Viec
--CREATE TABLE Ca_Lam_Viec
--	(
--		IdCaLamViec UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
--		IdNV UNIQUEIDENTIFIER NOT NULL,
--		TgianBD DATETIME NOT NULL,
--		TgianKT DATETIME DEFAULT NULL,
--		TienDauCa DECIMAL(20,0) DEFAULT 0,
--		TienMatTaiQuan DECIMAL(20,0) NOT NULL,
--		TienNhoThuHo DECIMAL(20,0) DEFAULT 0,
--		GhiChu NVARCHAR(200)
--	)

--13.Chuc_Vu
CREATE TABLE Chuc_Vu
	(
		IdCV UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
		MaCV VARCHAR(20) UNIQUE NOT NULL,
		TenCV NVARCHAR(100) NOT NULL,
		TrangThai INT DEFAULT 0
	)
--14.Danh mục
CREATE TABLE Danh_Muc
	(
		IdDanhMuc UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
		MaDanhMuc VARCHAR(20) UNIQUE NOT NULL,
		TenDanhMuc NVARCHAR(200) NOT NULL,
		TrangThai INT DEFAULT 0
	)
---Quan hệ giữua các bảng:
-------đệ quy bảng danh mục:
ALTER TABLE Loai ADD CONSTRAINT FK_DanhMuc FOREIGN KEY (IdDanhMuc) REFERENCES Danh_Muc(IdDanhMuc)
--
ALTER TABLE Khuyen_Mai ADD CONSTRAINT FK_NVTaoKM FOREIGN KEY (IdNV) REFERENCES Nhan_Vien(IdNV)

ALTER TABLE Nhan_Vien ADD CONSTRAINT FK_CV FOREIGN KEY (IdCV) REFERENCES Chuc_Vu(IdCV)
ALTER TABLE Hoa_Don_Chi_Tiet ADD CONSTRAINT FK_MonAn_HDCT FOREIGN KEY(IdMonAn) REFERENCES Mon_An(IdMonAn)
ALTER TABLE Hoa_Don_Chi_Tiet ADD CONSTRAINT FK_HD_HDCT FOREIGN KEY(IdHD) REFERENCES Hoa_Don(IdHD)
ALTER TABLE Hoa_Don_Chi_Tiet ADD CONSTRAINT FK_Combo_HDCT FOREIGN KEY(IdCombo) REFERENCES Combo(IdCombo)
ALTER TABLE Chi_Tiet_Combo ADD CONSTRAINT FK_ComboCT FOREIGN KEY (IdCombo) REFERENCES Combo(IdCombo)
ALTER TABLE Chi_Tiet_Combo ADD CONSTRAINT FK_MonAnCombo FOREIGN KEY (IdMonAn) REFERENCES Mon_An(IdMonAn)
ALTER TABLE Hoa_Don ADD CONSTRAINT FK_Ban FOREIGN KEY(IdBan) REFERENCES Ban(IdBan)
ALTER TABLE Hoa_Don ADD CONSTRAINT FK_NV_HD FOREIGN KEY (IdNV) REFERENCES Nhan_Vien(IdNV)
ALTER TABLE Hoa_Don ADD CONSTRAINT FK_KH_HD FOREIGN KEY (IdKH) REFERENCES Khach_Hang(IdKH)
ALTER TABLE Combo ADD CONSTRAINT FK_NV_Combo FOREIGN KEY (IdNV) REFERENCES Nhan_Vien(IdNV)
ALTER TABLE Ban ADD CONSTRAINT FK_KhuVuc FOREIGN KEY(IdKhuVuc) REFERENCES Khu_Vuc(IdKhuVuc)
ALTER TABLE Mon_An ADD CONSTRAINT FK_Loai_monAN FOREIGN KEY(IdLoai) REFERENCES Loai(IdLoai)
ALTER TABLE Mon_An ADD CONSTRAINT FK_KM FOREIGN KEY (IdKM) REFERENCES Khuyen_Mai(IdKM)
ALTER TABLE Giao_Dich ADD CONSTRAINT FK_HD FOREIGN KEY(IdHD) REFERENCES Hoa_Don(IdHD)

--INSERT Dữ Liệu
SELECT * FROM Khu_Vuc
SELECT * FROM Ban
SELECT * FROM Chuc_Vu
SELECT * FROM Nhan_Vien
SELECT * FROM Danh_Muc
SELECT * FROM Loai
SELECT * FROM Mon_An
SELECT * FROM Combo
SELECT * FROM Chi_Tiet_Combo
SELECT * FROM Khach_Hang
SELECT * FROM Khuyen_Mai
--khu vực
INSERT INTO Khu_Vuc(MaKhuVuc, TenKhuVuc, TrangThai) VALUES
	('KV1', N'Tầng 1', 0)
--bàn
INSERT INTO Ban (IdKhuVuc, MaBan, SoLuongChoNgoi,TrangThai) VALUES
	('8E6A751F-5C35-42DB-9707-D389ED2ED338', 1, 10, 0)
INSERT INTO Ban (IdKhuVuc, MaBan, SoLuongChoNgoi,TrangThai) VALUES
	('8E6A751F-5C35-42DB-9707-D389ED2ED338', 2, 5, 0)
--chức vụ
INSERT INTO Chuc_Vu (MaCV,TenCV,  TrangThai) VALUES
	('CV1', N'Nhân Viên', 0)
--nhân viên
INSERT INTO Nhan_Vien(IdCV, MaNV, Ho, TenDem,Ten,GioiTinh, Sdt,Email, NgaySinh, DiaChi, MatKhau, TrangThai) VALUES
	('AE0B9591-B997-4FD5-BE60-BF6BA9391695','NV1', N'Nguyễn', N'Thuỳ', N'Dương', N'Nữ', '0384910040',
	'duongnttph25958@fpt.edu.vn', '2003-11-29', N'Tuyên Quang', '12123', 0)
--danh mục:
INSERT INTO Danh_Muc (MaDanhMuc,TenDanhMuc,TrangThai) VALUES
	('DM1',N'Đồ ăn', 0)
INSERT INTO Danh_Muc (MaDanhMuc,TenDanhMuc,TrangThai) VALUES
	('DM2',N'Đồ uống', 0)
--loại:
INSERT INTO Loai (IdDanhMuc, MaLoai, TenLoai, TrangThai) VALUES
	('BA987442-27D9-4076-B86A-6DD10E377CCE', 'L1', N'Nướng',0)
INSERT INTO Loai (IdDanhMuc, MaLoai, TenLoai, TrangThai) VALUES
	('BA987442-27D9-4076-B86A-6DD10E377CCE', 'L2', N'Hấp',0)
INSERT INTO Loai (IdDanhMuc, MaLoai, TenLoai, TrangThai) VALUES
	('88FDAC67-C940-4C6D-B21B-B24ECAC2E065', 'L3', N'Đồ uống có ga',0)
INSERT INTO Loai (IdDanhMuc, MaLoai, TenLoai, TrangThai) VALUES
	('88FDAC67-C940-4C6D-B21B-B24ECAC2E065', 'L4', N'Đồ uống ko ga',0)
--món ăn
INSERT INTO Mon_An(IdLoai, MaMonAn, TenMonAn, DonGia, DonViTinh, TrangThai) VALUES
	('D67EF4A8-AE09-4054-B9EB-A06F6CA0021C', 'MA1', N'Hàu nướng',200, N'Đĩa', 0)
INSERT INTO Mon_An(IdLoai, MaMonAn, TenMonAn, DonGia, DonViTinh, TrangThai) VALUES
	('B64E93C8-B82F-469C-9DB3-F65218D6C83C', 'MA2', N'Hàu hấp',250, N'Đĩa', 0)
INSERT INTO Mon_An(IdLoai, MaMonAn, TenMonAn, DonGia, DonViTinh, TrangThai) VALUES
	('756B26C7-153F-479B-975A-B1C3336ADEF2', 'MA3', N'pepsi',10, N'chai', 0)
INSERT INTO Mon_An(IdLoai, MaMonAn, TenMonAn, DonGia, DonViTinh, TrangThai) VALUES
	('B64E93C8-B82F-469C-9DB3-F65218D6C83C', 'MA4', N'lavie',5, N'chai', 0)
--combo
INSERT INTO Combo(IdNV, MaCB, TenCB, DonGia, TrangThai) VALUES
	('B37F3855-A833-4DF5-9963-BECA36F261E0','CB1', N'Combo 255k', 255, 0 )
INSERT INTO Combo(IdNV, MaCB, TenCB, DonGia, TrangThai) VALUES
	('B37F3855-A833-4DF5-9963-BECA36F261E0','CB2', N'Combo 215k', 215, 0 )
--chi tiết combo
INSERT INTO Chi_Tiet_Combo (IdCombo,IdMonAn, SoLuongMonAn) VALUES
	('D438E053-B628-4C6D-A35A-7C4F35BD4FC7','DC90E49C-8A95-4C58-BD2A-BE3E40D4FE64', 1)
INSERT INTO Chi_Tiet_Combo (IdCombo,IdMonAn, SoLuongMonAn) VALUES
	('D438E053-B628-4C6D-A35A-7C4F35BD4FC7','F3961D39-76FB-41E7-B3E2-875874D1BC75', 2)
INSERT INTO Chi_Tiet_Combo (IdCombo,IdMonAn, SoLuongMonAn) VALUES
	('9057052C-B134-4B31-AC41-79C7EAFE4389','748AE5E0-D92B-4276-A87E-090FF6F7D741', 1)
INSERT INTO Chi_Tiet_Combo (IdCombo,IdMonAn, SoLuongMonAn) VALUES
	('9057052C-B134-4B31-AC41-79C7EAFE4389','F3961D39-76FB-41E7-B3E2-875874D1BC75', 2)
--Khach hàng
INSERT INTO Khach_Hang (MaKH, Ho, TenDem, Ten, GioiTinh, NgaySinh, Sdt, DiaChi, ThanhPho,QuocGia, TrangThai) VALUES
	('KH1', N'Nguyễn', N'Anh', N'Dũng', N'Nam', '2003-11-29', '0384910040', N'Tuyên Quang', N'Tuyên Quang',
		N'Việt Nam', 0)
--khuyến mãi:
INSERT INTO Khuyen_Mai (IdNV, MaKM, TenKM, ThoiGianBD, ThoiGianKT, LoaiKM, GtriKM, GhiChu, TrangThai) VALUES
	('B37F3855-A833-4DF5-9963-BECA36F261E0', 'KM1', N'Khuyến mãi 1','2022-11-16', '2022-11-18', N'Giá tiền',10, N'Áp dụng với sp có giá >100k', 0)

--select* from Khu_Vuc
--select * from Ban
--select * from Chuc_Vu
--select * from Nhan_Vien
--select * from Danh_Muc
--select * from Combo
--select * from Mon_An
--select * from Chi_Tiet_Combo
--insert Khach_Hang(MaKH) values('KH02')
--insert into Danh_Muc(IdLoai,MaDanhMuc,TenDanhMuc) values('AA5EF128-4354-4917-9C5D-9E19B52F1C3F','DM03',N'Nước uống có ga')
--select * from Hoa_Don
--select * from Khach_Hang

