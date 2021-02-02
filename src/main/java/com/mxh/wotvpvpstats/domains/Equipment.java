package com.mxh.wotvpvpstats.domains;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_equipment")
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EQP_ID")
    private Long id;

    @Column(name = "EQP_NAME")
    private String name;

    @Column(name = "EQP_IMAGE")
    private String image;

    @Column(name = "EQP_RARITY")
    private String rarity;

    @Column(name = "EQP_ISTMR")
    private boolean isTMR;

    @OneToOne
    @JoinColumn(name = "EQP_ETY_ID", referencedColumnName = "ETY_ID")
    private EquipmentType equipmentType;

    @OneToOne
    @JoinColumn(name = "EQP_ECA_ID", referencedColumnName = "ECA_ID")
    private EquipmentCategory equipmentCategory;
}
