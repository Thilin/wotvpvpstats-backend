package com.mxh.wotvpvpstats.projections.dtos;

import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FormationDTO {
    private String name;
    private boolean isAttack;
    private List<CharacterPositionDTO> charactersPositions;
}
