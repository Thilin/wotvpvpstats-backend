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
@Table(name = "tb_character_built_job")
public class CharacterBuiltJob {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CBJ_ID")
    private Long id;

    @Column(name = "CBJ_ISMAIN")
    private boolean isMain;

    @ManyToOne
    @JoinColumn(name = "CBJ_CHB_ID")
    private CharacterBuilt characterBuilt;

    @ManyToOne
    @JoinColumn(name = "CBJ_CJO_ID")
    private CharacterJob characterJob;
}
