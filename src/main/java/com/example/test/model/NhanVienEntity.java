package com.example.test.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "NhanVien", schema = "dbo", catalog = "FINALASS_FPOLYSHOP_FA22_SOF205__SOF2041")
public class NhanVienEntity {
    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    @Column(name = "Id")
    private UUID id;
    @Basic
    @Column(name = "Ma")
    private String ma;
    @Basic
    @Column(name = "Ten")
    private String ten;
    @Basic
    @Column(name = "TenDem")
    private String tenDem;
    @Basic
    @Column(name = "Ho")
    private String ho;
    @Basic
    @Column(name = "GioiTinh")
    private String gioiTinh;
    @Basic
    @Column(name = "NgaySinh")
    private Date ngaySinh;
    @Basic
    @Column(name = "DiaChi")
    private String diaChi;
    @Basic
    @Column(name = "Sdt")
    private String sdt;
    @Basic
    @Column(name = "MatKhau")
    private String matKhau;

    @Basic
    @Column(name = "TrangThai")
    private Integer trangThai;
    public String getFullname() {
        return this.ho + " " + this.tenDem + " " + this.ten;
    }

}
