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
@Table(name = "tb_character_built_support_ability")
public class CharacterBuiltSupportAbility {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CSH_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "CSH_CHB_ID")
    private CharacterBuilt characterBuilt;

    @ManyToOne
    @JoinColumn(name = "CHS_SAB_ID")
    private SupportAbility supportAbility;
}
