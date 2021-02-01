package com.mxh.wotvpvpstats.projections.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConfrontationResultDTO {

    @JsonFormat(pattern = "MM/dd/yyyy HH:mm")
    private LocalDateTime date;
    private String map;
    private String confrontType;
    private Long kills;
    private Long deaths;
    private List<CharacterBuiltFormationConfrontationDTO> composition;
    private String opponentName;
    private List<OpponentCompositionDTO> opponentComposition;
}
