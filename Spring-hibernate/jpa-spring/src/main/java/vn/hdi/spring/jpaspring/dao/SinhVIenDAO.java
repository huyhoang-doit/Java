package vn.hdi.spring.jpaspring.dao;

import vn.hdi.spring.jpaspring.entity.SinhVien;

import java.util.List;

public interface SinhVIenDAO {
    public void save(SinhVien sinhVien);
    public SinhVien getById(int id);
    public List<SinhVien> getAll();
    public List<SinhVien> getByName(String name);
    public SinhVien update(SinhVien sinhVien);
    public int updateAllName(String name);
    public void delete(long id);
    public void deleteByName(String condition);
}
