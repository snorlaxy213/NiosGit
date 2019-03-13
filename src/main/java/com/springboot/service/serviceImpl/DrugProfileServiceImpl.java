package com.springboot.service.serviceImpl;

import com.springboot.dto.DrugProfileDto;
import com.springboot.entity.BasicInformation;
import com.springboot.entity.DrugProfile;
import com.springboot.repository.DrugProfileRepository;
import com.springboot.service.DrugProfileService;
import org.apache.log4j.Logger;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DrugProfileServiceImpl implements DrugProfileService {

    private static final Logger LOGGER = Logger.getLogger(DrugProfileServiceImpl.class);

    @Autowired
    @Qualifier("drugProfileRepository")
    DrugProfileRepository drugProfileRepository;

    @Autowired
    @Qualifier("mapper")
    Mapper mapper;

    @Override
    public List<DrugProfileDto> findAll() {
        List<DrugProfile> drugProfiles = drugProfileRepository.findAll();

        List<DrugProfileDto> drugProfileDtos = new ArrayList<>();
        drugProfiles.forEach(drugProfile -> drugProfileDtos.add(mapper.map(drugProfile,DrugProfileDto.class)));
        return drugProfileDtos;
    }

    @Override
    public DrugProfileDto findById(String id) {
        Optional<DrugProfile> drugProfileOptional = drugProfileRepository.findById(id);

        if (drugProfileOptional.isPresent()) {
            return mapper.map(drugProfileOptional.get(),DrugProfileDto.class);
        } else return null;
    }

    @Override
    public void save(DrugProfileDto drugProfileDto) {
        try {
            Long count = drugProfileRepository.countById(drugProfileDto.getId());
            if (count > 0) {
                Optional<DrugProfile> drugProfileOptional = drugProfileRepository.findById(drugProfileDto.getId());
                DrugProfile drugProfile = drugProfileOptional.get();

                drugProfile.setName(drugProfileDto.getName());
                drugProfile.setType(drugProfileDto.getType());
                drugProfile.setDescription(drugProfileDto.getDescription());
                drugProfile.setUnit(drugProfileDto.getUnit());
                drugProfile.setStatus(drugProfileDto.getStatus());
                this.getModifiedInfo(drugProfile.getBasicInformation(), "1", 1);

                drugProfileRepository.save(drugProfile);
            } else {
                DrugProfile drugProfile = mapper.map(drugProfileDto,DrugProfile.class);

                drugProfile.setBasicInformation(new BasicInformation());
                this.getModifiedInfo(drugProfile.getBasicInformation(), "1", 1);

                drugProfileRepository.save(drugProfile);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw e;
        }
    }

    private BasicInformation getModifiedInfo(BasicInformation basicInformation, String userID, Integer ClinicCode) {
        if (basicInformation.getCreateBy() != null) {
            basicInformation.setUpdateBy(userID);
            basicInformation.setUpdateDtm(new Date());
            basicInformation.setUpdateClinic(ClinicCode);
        }else {
            basicInformation.setCreateBy(userID);
            basicInformation.setCreateDtm(new Date());
            basicInformation.setCreateClinic(ClinicCode);
            basicInformation.setUpdateBy(userID);
            basicInformation.setUpdateDtm(new Date());
            basicInformation.setUpdateClinic(ClinicCode);
        }

        return basicInformation;
    }
}
