package com.mxh.wotvpvpstats.projections.dtos;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OpponentsCharactersFoundDTO {
    private String name;
    private String rarity;
    private String element;
    private Long totalTimesFound;
}
