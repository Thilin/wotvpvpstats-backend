package com.mxh.wotvpvpstats.domains;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "tb_equipment_category")
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class EquipmentCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ECA_ID")
    private Long id;

    @Column(name = "ECA_DESCRIPTION")
    private String description;
}
