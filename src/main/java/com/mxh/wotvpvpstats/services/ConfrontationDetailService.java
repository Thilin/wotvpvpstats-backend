package com.mxh.wotvpvpstats.services;

import com.mxh.wotvpvpstats.domains.Confrontation;

public interface ConfrontationDetailService {
    void createDetails(Confrontation confrontation);

    void createOpponentDetails(Confrontation confrontation);
}
