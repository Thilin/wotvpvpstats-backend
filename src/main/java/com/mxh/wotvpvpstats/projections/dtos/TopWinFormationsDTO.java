package com.mxh.wotvpvpstats.projections.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TopWinFormationsDTO {

    private Long total;
    private String name1;
    private String name2;
    private String name3;
}
