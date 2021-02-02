package com.mxh.wotvpvpstats.domains;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tb_support_ability_confrontation_detail")
public class SupportAbilityConfrontationDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SAD_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "SAD_COD_ID")
    private ConfrontationDetail confrontationDetail;

    @ManyToOne
    @JoinColumn(name = "SAD_SAP_ID")
    private SupportAbility supportAbility;
}
