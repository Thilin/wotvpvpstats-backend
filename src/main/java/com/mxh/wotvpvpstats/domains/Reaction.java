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
@Table(name = "tb_reaction")
public class Reaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REA_ID")
    private Long id;

    @Column(name = "REA_DESCRIPTION")
    private String description;

    @OneToOne
    @JoinColumn(name = "REA_JOB_ID", referencedColumnName = "JOB_ID")
    private Job job;
}
