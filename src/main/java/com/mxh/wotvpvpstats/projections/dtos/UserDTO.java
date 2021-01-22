package com.mxh.wotvpvpstats.projections.dtos;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    private String name;
    private String nickName;
    private String email;
    private String password;
    private String passwordConfirmation;
}
