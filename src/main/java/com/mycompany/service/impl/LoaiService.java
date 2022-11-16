/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.service.impl;

import com.mycompany.domainModel.Loai;
import com.mycompany.repository.impl.LoaiRepository;
import com.mycompany.service.ICommonService;
import com.mycompany.util.ThongBao;
import java.util.List;

/**
 *
 * @author Duongntt
 */
public class LoaiService implements ICommonService<Loai, String> {

    private LoaiRepository loaiRepo = new LoaiRepository();
    private ThongBao thongBao = new ThongBao();

    @Override
    public List<Loai> getAll() {
        return loaiRepo.getAll();
    }

    @Override
    public Loai getOne(String ma) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String add(Loai kh) {
        return thongBao.thongBaoADD(loaiRepo.add(kh));
    }

    @Override
    public String update(Loai kh, String ma) {
        return thongBao.thongBaoUPDATE(loaiRepo.update(kh, ma));
    }

    @Override
    public String remove(String ma) {
        return thongBao.thongBaoDELETE(loaiRepo.remove(ma));
    }

}
