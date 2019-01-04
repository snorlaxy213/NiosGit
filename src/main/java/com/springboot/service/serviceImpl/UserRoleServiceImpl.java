package com.springboot.service.serviceImpl;

import com.springboot.dto.Message;
import com.springboot.dto.UserRoleDto;
import com.springboot.entity.BasicInfomation;
import com.springboot.entity.UserRole;
import com.springboot.repository.UserRoleRepository;
import com.springboot.service.SqeNoService;
import com.springboot.service.UserRoleService;
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

@Service("userRoleServiceImpl")
@Transactional
public class UserRoleServiceImpl implements UserRoleService {

    private static final Logger LOGGER = Logger.getLogger(UserRoleServiceImpl.class);

    @Autowired
    @Qualifier("mapper")
    Mapper mapper;

    @Autowired
    UserRoleRepository userRoleRepository;

    @Autowired
    SqeNoService sqeNoService;

    @Override
    public UserRoleDto findUserRoleById(String id) {
        Optional<UserRole> optional = userRoleRepository.findById(id);

        UserRoleDto userRoleDto = null;
        if (optional.isPresent()) {
            userRoleDto = mapper.map(optional.get(),UserRoleDto.class);
        }
        return userRoleDto;
    }

    @Override
    public List<UserRoleDto> findAll() {
        List<UserRole> userRoles = userRoleRepository.findAll();

        List<UserRoleDto> userRoleDtos = new ArrayList<>();
        userRoles.forEach(userRole -> {
            UserRoleDto userRoleDto = mapper.map(userRole,UserRoleDto.class);
            userRoleDtos.add(userRoleDto);
        });
        return userRoleDtos;
    }

    @Override
    public void save(UserRoleDto userRoleDto) {
        Long count = userRoleRepository.countById(userRoleDto.getId());

        if (count > 0) {
            Optional<UserRole> userRoleOptional = userRoleRepository.findById(userRoleDto.getId());

            userRoleOptional.ifPresent(userRole -> {
                userRole.setId(userRoleDto.getId());
                userRole.setDescription(userRoleDto.getDescription());
                userRole.setName(userRoleDto.getName());
                userRole.setStatus(userRoleDto.getStatus());

                this.getModifiedInfo(userRole.getBasicInfomation(), "1", 1);

                userRoleRepository.save(userRole);
            });
        } else {
            String id = sqeNoService.getSeqNo("USERROLE");

            UserRole userRole = mapper.map(userRoleDto,UserRole.class);
            userRole.setId(id);
            this.getModifiedInfo(userRole.getBasicInfomation(), "1", 1);
            userRoleRepository.save(userRole);
        }
    }

    private BasicInfomation getModifiedInfo(BasicInfomation aBscRwInf, String lUID, Integer lClinicCode) {

        if (aBscRwInf != null) {
            if (aBscRwInf.getCreateBy() == null) {
                aBscRwInf.setCreateBy(lUID);
                aBscRwInf.setCreateDtm(new Date());
                aBscRwInf.setCreateClinic(lClinicCode);
            } else {
                aBscRwInf.setUpdateBy(lUID);
                aBscRwInf.setUpdateDtm(new Date());
                aBscRwInf.setUpdateClinic(lClinicCode);
            }
        }

        return aBscRwInf;
    }
}