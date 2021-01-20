package com.mxh.wotvpvpstats.domains;

import lombok.*;

import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_element")
public class Element {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ELE_ID")
    private Long id;

    @Column(name = "ELE_DESCRIPTION")
    private String description;

    @Column(name = "ELE_IMAGE")
    private String image;
}
