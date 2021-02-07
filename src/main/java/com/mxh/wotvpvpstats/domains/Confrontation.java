package com.mxh.wotvpvpstats.domains;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "tb_confrontation")
public class Confrontation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CON_ID")
    private Long id;

    @Column(name = "CON_DATE")
    private LocalDateTime date;

    @Column(name = "CON_LOSSES")
    private Long losses;

    @Column(name = "CON_DEFEATS")
    private Long defeats;

    @Column(name = "CON_ISWIN")
    private boolean isWin;

    @OneToOne
    @JoinColumn(name = "CON_FMT_ID", referencedColumnName = "FMT_ID")
    private Formation formation;

    @OneToOne
    @JoinColumn(name = "CON_OFO_ID", referencedColumnName = "OFO_ID")
    private OpponentFormation opponentFormation;

    @OneToOne
    @JoinColumn(name = "CON_CTY_ID", referencedColumnName = "CTY_ID")
    private ConfrontationType confrontationType;

    @OneToOne
    @JoinColumn(name = "CON_MAP_ID", referencedColumnName = "MAP_ID")
    private Map map;

    @OneToOne
    @JoinColumn(name = "CON_USR_ID", referencedColumnName = "USR_ID")
    private User user;

    @OneToOne
    @JoinColumn(name = "CON_CCF_ID", referencedColumnName = "CCF_ID")
    private ConfrontationCharacterFormation confrontationCharacterFormation;
}
