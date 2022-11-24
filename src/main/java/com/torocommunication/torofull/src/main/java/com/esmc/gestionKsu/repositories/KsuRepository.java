package com.torocommunication.torofull.src.main.java.com.esmc.gestionKsu.repositories;

import com.esmc.gestionKsu.entities.Ksu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface KsuRepository extends JpaRepository<Ksu,Long> {
    Optional<Ksu> findByCodeKsu(String code);

    //find ksus by pieceidentite
    @Query("select k from Ksu k where k.pieceIdentite.id=?1")
    Page<Ksu> findByPieceIdentite(Long id,Pageable pageable);

    //find ksus by pieceidentite
    @Query("select k from Ksu k where k.abonnement.id=?1")
    Page<Ksu> findByAbonnement(Long id,Pageable pageable);

    //find ksus by pieceidentite
    @Query("select k from Ksu k where k.secteur.id=?1")
    Page<Ksu> findBySecteur(Long id,Pageable pageable);

    //find ksus by pieceidentite
    @Query("select k from Ksu k where k.typePM.id=?1")
    Page<Ksu> findByTypePM(Long id,Pageable pageable);

    //find ksus by pieceidentite
    @Query("select k from Ksu k where k.typeOperateur.id=?1")
    Page<Ksu> findByTypeOperateur(Long id,Pageable pageable);

    //find ksus by pieceidentite
    @Query("select k from Ksu k where k.banque.id=?1")
    Page<Ksu> findByBanque(Long id,Pageable pageable);
/*
    @Query("select k from Ksu k where k.maBanKsu. = :x")
    public Ksu findKsuByMaBanKsu(@Param("x") Long id);*/

    @Query("select max(k) from Ksu k ")
    public Ksu findKsuByIdMax();

    @Query("select count(k) from Ksu k where k.codeKsu like '%P'")
    public Integer codeKsuPP();

    @Query("select count(k) from Ksu k where k.codeKsu like '%M'")
    public Integer codeKsuPM();

    @Query("select k from Ksu k where k.codeKsu = :x")
    public Ksu ksuByCodeKsu(@Param("x") String code);

    @Query("select k from Ksu k where k.IdmaBanKsu = :x")
    public Ksu ksuByMaBanKsu(@Param("x") Long id);

    @Query("select k from Ksu k where k.codeBanKsu = :x")
    public Ksu ksuByCodeBAn(@Param("x") String codeBan);

    @Query("select k from Ksu k where k.EnableConnexionStatus=true and k.codeConnexion= :x")
    public Ksu getKsuByActivationCode(@Param("x") String code);

    @Query("select k from Ksu k where k.codeKsu = ?1")
    Ksu findFirstByCodeKsu(String codeMembre, PageRequest of);

    @Query("select k from Ksu k where k.nom = :x and k.prenom = :y and k.telephone = :z")
    Ksu ksuByInfo(@Param("x") String firstName, @Param("y")String lastName,@Param("z") String numero);

    @Query("select k from Ksu k where k.codeKsuRepresentant = :x")
    Ksu ksuByCodeKsuRepresentant(@Param("x")  String code);
}
