package com.mxh.wotvpvpstats.projections.dtos;

import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CharacterBuildDTO {

    private String name;
    private Long visionCardId;
    private Long characterId;
    private Long userId;
    private Long esperId;
    private Long reactionId;
    private List<CharacterBuiltEquipmentDTO> equipmentsDTO;
    private List<Long> supportAbilitiesId;
}
