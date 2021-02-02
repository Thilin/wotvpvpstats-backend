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
@Table(name = "tb_support_ability")
public class SupportAbility {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SAB_ID")
    private Long id;

    @Column(name = "SAB_DESCRIPTION")
    private String description;

    @OneToOne
    @JoinColumn(name = "SAB_JOB_ID", referencedColumnName = "JOB_ID")
    private Job job;
}
