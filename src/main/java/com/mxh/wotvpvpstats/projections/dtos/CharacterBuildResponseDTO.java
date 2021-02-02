package com.mxh.wotvpvpstats.projections.dtos;

import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CharacterBuildResponseDTO {

    private String name;
    private String character;
    private String visionCard;
    private String esper;
    private String reaction;
    private List<CharacterBuiltEquipmentResponseDTO> equipments;
    private List<String> supportAbilities;

}
