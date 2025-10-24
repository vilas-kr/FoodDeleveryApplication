package com.vilas.hungerHub.serviceInterface;

import org.springframework.transaction.annotation.Transactional;

public interface IdSequenceService {

    @Transactional
    int getSequence(String name);

}
