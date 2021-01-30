package com.mxh.wotvpvpstats.domains;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="tb_oponent_formation_character")
@Builder
public class OpponentFormationCharacter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OFC_ID")
    private Long id;

    @Column(name = "OFC_ORDER")
    private Long order;

    @ManyToOne
    @JoinColumn(name = "OFC_OFO_ID")
    private OpponentFormation opponentFormation;

    @ManyToOne
    @JoinColumn(name = "OFC_CTR_ID")
    private Character character;
}
