package com.mxh.wotvpvpstats.domains;

import lombok.*;
import net.bytebuddy.asm.Advice;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tb_equipment_type")
public class EquipmentType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ETY_ID")
    private Long id;

    @Column(name = "ETY_DESCRIPTION")
    private String description;
}
