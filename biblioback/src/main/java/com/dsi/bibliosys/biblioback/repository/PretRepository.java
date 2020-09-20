package com.dsi.bibliosys.biblioback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.dsi.bibliosys.biblioback.data.entity.Pret;

@Repository
public interface PretRepository extends JpaRepository<Pret, Integer>, JpaSpecificationExecutor<Pret> {

}
