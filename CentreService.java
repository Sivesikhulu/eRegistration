package com.ECESWA.eRegistration.service;

import com.ECESWA.eRegistration.entity.Centre;
import com.ECESWA.eRegistration.entity.Level;
import com.ECESWA.eRegistration.entity.Student;


import java.util.List;

public interface CentreService {
        List<Centre> getAllCentres();

        Centre getCentreById(Integer centreNo);
        Centre saveCentre(Centre centre);
}
