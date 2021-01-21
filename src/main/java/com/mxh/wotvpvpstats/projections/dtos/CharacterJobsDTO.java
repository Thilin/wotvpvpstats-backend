package com.mxh.wotvpvpstats.projections.dtos;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CharacterJobsDTO {

    private String name;
    private boolean isMain;
}
