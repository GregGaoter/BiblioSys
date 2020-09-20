package com.dsi.bibliosys.biblioback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.dsi.bibliosys.biblioback.data.entity.Auteur;

@Repository
public interface AuteurRepository extends JpaRepository<Auteur, Integer>, JpaSpecificationExecutor<Auteur> {

}
