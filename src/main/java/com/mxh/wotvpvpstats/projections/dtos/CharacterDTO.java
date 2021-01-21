package com.mxh.wotvpvpstats.projections.dtos;

import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CharacterDTO {

    private String name;
    private String image;
    private String rarity;
    private String element;
    private List<CharacterJobsDTO> jobs;

}
