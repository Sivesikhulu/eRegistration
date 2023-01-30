package com.ECESWA.eRegistration.service.Impl;

import com.ECESWA.eRegistration.entity.Centre;
import com.ECESWA.eRegistration.entityPk.CentrePk;
import com.ECESWA.eRegistration.repository.CentreRepository;
import com.ECESWA.eRegistration.service.CentreService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CentreServiceImpl implements CentreService {

    private CentreRepository centreRepository;
    private CentrePk centrePk;

    public CentreServiceImpl(CentreRepository centreRepository) {
        this.centreRepository = centreRepository;
    }

    @Override
    public List<Centre> getAllCentres(){

        return centreRepository.findAll();
    }

    @Override
    public Centre getCentreById(Integer centreNo) {
        return centreRepository.findById(centrePk).get();
    }

    @Override
    public Centre saveCentre(Centre centre) {
        return centreRepository.save(centre);
    }
}
