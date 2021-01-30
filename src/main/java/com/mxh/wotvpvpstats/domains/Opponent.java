package com.mxh.wotvpvpstats.domains;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tb_oponent")
public class Opponent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OPO_ID")
    private Long id;

    @Column(name = "OPO_NAME")
    private String name;
}
