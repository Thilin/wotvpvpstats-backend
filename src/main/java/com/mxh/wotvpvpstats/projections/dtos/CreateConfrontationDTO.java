package com.mxh.wotvpvpstats.projections.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateConfrontationDTO {

    private Long opponentFormationId;
    private Long userFormationId;
    private Long confrontationTypeId;
    private Long mapId;
    private Long losses;
    private Long defeats;
    private boolean isWin;
    @JsonFormat(pattern = "MM/dd/yyyy HH:mm")
    private LocalDateTime date;
}
