package com.mxh.wotvpvpstats.domains;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tb_oponent_confrontation_detail")
public class OpponentConfrontationDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OCD_ID")
    private Long id;

    @Column(name = "OCD_NAME")
    private String name;

    @Column(name = "OCD_POSITION")
    private Long position;

    @OneToOne
    @JoinColumn(name = "OCD_ELE_ID", referencedColumnName = "ELE_ID")
    private Element element;

    @ManyToOne
    @JoinColumn(name = "OCD_CON_ID")
    private Confrontation confrontation;
}
