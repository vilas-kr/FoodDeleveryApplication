package com.vilas.hungerHub.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "id_sequence")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IdSequence {

    @Id
    @Column(name = "seq_name", length = 30)
    private String seqName;

    @Column(name = "next_val")
    private int nextVal;

}
