package com.mxh.wotvpvpstats.domains;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_oponent_formation")
@Entity
@Builder
public class OpponentFormation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OFO_ID")
    private Long id;

    @Column(name = "OFO_NAME")
    private String name;

    @ManyToOne
    @JoinColumn(name = "OFO_OPO_ID", referencedColumnName = "OPO_ID")
    private Opponent opponent;
}
