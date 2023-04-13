package com.example.test.dao;

import com.example.test.common.JpaUtil;
import com.example.test.model.NhanVienEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.text.Normalizer;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Pattern;

public class NhanvienDAO {
    EntityManager em = JpaUtil.getConnection();

    public List<NhanVienEntity> getAll(){
        String jpql = "select n from NhanVienEntity n";
        TypedQuery<NhanVienEntity> query = em.createQuery(jpql, NhanVienEntity.class);
        List<NhanVienEntity> listNV = query.getResultList();
        return listNV;
    }
    public NhanVienEntity findById(UUID id){
        NhanVienEntity en = em.find(NhanVienEntity.class,id);
        return en;
    }
    public NhanVienEntity add(NhanVienEntity e){
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();

            em.persist(e);

            transaction.commit();

        } catch (Exception ex){

            ex.printStackTrace();

            transaction.rollback();
        }
        return e;
    }
    public Boolean delete(NhanVienEntity entity) {
        EntityTransaction transaction = em.getTransaction();
        Boolean result = false;
        try {
            transaction.begin();

            NhanVienEntity removeEntity = em.find(NhanVienEntity.class, entity.getId());
            em.remove(removeEntity);

            transaction.commit();
            result = true;
        } catch (Exception ex) {

            ex.printStackTrace();

            transaction.rollback();
        }
        return result ;
    }
    public String getMa(String name){
        String namePart[] = name.split("\\s+");
        String firstName =namePart[namePart.length-1];
        String lastName = "";
        for (int i = 0; i < namePart.length - 1; i++){
            lastName += namePart[i].charAt(0);
        }
        String result = firstName + lastName.toUpperCase();
        String nomalized = Normalizer.normalize(result, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCOMBINING_DIACRITICAL_MARKS}+");
        String output = pattern.matcher(nomalized).replaceAll("").toLowerCase();

        Random rd = new Random();
        int ranNum = rd.nextInt(100)+1;

        String ma = output + ranNum;

        return ma;
    }
}
