package vn.hdi.spring.jpaspring.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import vn.hdi.spring.jpaspring.entity.SinhVien;

import java.util.List;

@Repository
@Transactional
public class SinhVienDAOImpl implements SinhVIenDAO{
    private EntityManager entityManager;

    @Autowired
    public SinhVienDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void save(SinhVien sinhVien) {
        this.entityManager.persist(sinhVien);
    }

    @Override
    public SinhVien getById(int id) {
        return this.entityManager.find(SinhVien.class, id);
    }

    @Override
    public List<SinhVien> getAll() {
        String jpql = "SELECT s FROM SinhVien s";
        return this.entityManager.createQuery(jpql,SinhVien.class).getResultList();
    }

    @Override
    public List<SinhVien> getByName(String name) {
        String jpql = "SELECT s FROM SinhVien s WHERE s.ten=:t";
        TypedQuery<SinhVien> query = this.entityManager.createQuery(jpql,SinhVien.class);
        query.setParameter("t", name);
        return query.getResultList();
    }

    @Override
    public SinhVien update(SinhVien sinhVien) {
        return this.entityManager.merge(sinhVien);
    }

    @Override
    public int updateAllName(String name) {
        String jpql = "UPDATE SinhVien s SET s.ten=:t";
        Query query = this.entityManager.createQuery(jpql);
        query.setParameter("t", name);
        return query.executeUpdate();
    }

    @Override
    public void delete(long id) {
        SinhVien sv = this.entityManager.find(SinhVien.class,id);
        this.entityManager.remove(sv);
    }

    @Override
    public void deleteByName(String condition) {
        String jpql = "DELETE FROM SinhVien s WHERE s.ten=:t";
        Query query = this.entityManager.createQuery(jpql);
        query.setParameter("t", condition);
        query.executeUpdate();
    }
}
