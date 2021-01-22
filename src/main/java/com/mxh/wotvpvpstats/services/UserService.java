package com.mxh.wotvpvpstats.services;

import com.mxh.wotvpvpstats.projections.dtos.UserDTO;

public interface UserService {
    UserDTO create(UserDTO dto);
}
