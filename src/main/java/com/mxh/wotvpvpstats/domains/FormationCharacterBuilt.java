package com.mxh.wotvpvpstats.domains;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@Entity
@AllArgsConstructor
@Table(name = "tb_formation_character_built")
public class FormationCharacterBuilt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FCB_ID")
    private Long id;

    @Column(name = "FCB_ORDER")
    private Long order;

    @ManyToOne
    @JoinColumn(name = "FCB_FMT_ID")
    private Formation formation;

    @ManyToOne
    @JoinColumn(name = "FCB_CHB_ID")
    private CharacterBuilt characterBuilt;
}
