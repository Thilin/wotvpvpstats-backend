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
@Table(name = "tb_confrontation_character_formation")
public class ConfrontationCharacterFormation {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "CCF_ID")
    private Long id;

    @Column(name = "CCF_CTR_NAME_1")
    private String name1;

    @Column(name = "CCF_CTR_NAME_2")
    private String name2;

    @Column(name = "CCF_CTR_NAME_3")
    private String name3;
}
