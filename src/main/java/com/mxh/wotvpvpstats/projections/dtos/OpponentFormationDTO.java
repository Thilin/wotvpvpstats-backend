package com.mxh.wotvpvpstats.projections.dtos;

import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OpponentFormationDTO {

    private String opponentName;
    private List<OpponentCharactersPositionDTO> charactersPositions;

}
