package com.m_myr.nuwm.nuwmschedule.domain;

import com.m_myr.nuwm.nuwmschedule.data.repositories.FastRepository;
import com.m_myr.nuwm.nuwmschedule.network.api.APIMethods;

/* loaded from: classes2.dex */
public class Analitics {
    public static void markPostRead(int uid) {
        FastRepository.call(APIMethods.setPostViewed(uid)).detach().start();
    }
}
