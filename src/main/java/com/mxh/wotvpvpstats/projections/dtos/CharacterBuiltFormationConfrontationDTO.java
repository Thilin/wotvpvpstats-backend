package com.mxh.wotvpvpstats.projections.dtos;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CharacterBuiltFormationConfrontationDTO {

    private String name;
    private String visionCard;
    private String esper;
    private Long position;
}
