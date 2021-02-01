package com.mxh.wotvpvpstats.projections.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CharacterPositionDTO {

    private Long characterBuiltId;
    private Long position;
}
