package com.vilas.hungerHub.repository;

import com.vilas.hungerHub.entity.IdSequence;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IdSequenceRepository extends JpaRepository<IdSequence, String> {

    @Lock(LockModeType.PESSIMISTIC_WRITE) //ensures row-level lock, equivalent to FOR UPDATE.
    @Query("SELECT s FROM IdSequence s WHERE s.seqName = :seqName")
    IdSequence findBySeqNameForUpdate(@Param("seqName") String seqName);

}
