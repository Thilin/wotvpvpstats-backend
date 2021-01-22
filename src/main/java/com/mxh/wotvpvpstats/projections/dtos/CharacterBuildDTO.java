package com.mxh.wotvpvpstats.projections.dtos;

import lombok.*;

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
}
