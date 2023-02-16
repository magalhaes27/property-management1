package com.sidneyMUII.propertymanagement.service.impl;

import com.sidneyMUII.propertymanagement.converter.PropertyConverter;
import com.sidneyMUII.propertymanagement.dto.PropertyDTO;
import com.sidneyMUII.propertymanagement.entity.PropertyEntity;
import com.sidneyMUII.propertymanagement.repository.PropertyRepository;
import com.sidneyMUII.propertymanagement.service.PropertyService;
import javafx.beans.property.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class PropertyServiceImpl implements PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private PropertyConverter propertyConverter;

    @Override
    public PropertyDTO saveProperty(PropertyDTO propertyDTO){

        PropertyEntity pe = propertyConverter.convertDTOtoEntity(propertyDTO);
        pe = propertyRepository.save(pe);
        propertyDTO = propertyConverter.convertEntitytoDTO(pe);
        return propertyDTO;
    }

    @Override
    public List<PropertyDTO> getAllProperties() {
        List<PropertyEntity> listproperties = (List<PropertyEntity>) propertyRepository.findAll();
        List<PropertyDTO> propertyDTOList = new ArrayList<>();

        for(PropertyEntity pe:listproperties){
            PropertyDTO dto = propertyConverter.convertEntitytoDTO(pe);
            propertyDTOList.add(dto);
        }
        return propertyDTOList;
    }

    @Override
    public PropertyDTO updateProperty(PropertyDTO propertyDTO, Long propertyID) {
        Optional<PropertyEntity> optionalPropertyEntity = propertyRepository.findById(propertyID);
        PropertyDTO dto = null;

        if (optionalPropertyEntity.isPresent()) {
            PropertyEntity pe = optionalPropertyEntity.get();// datafrom the database
            pe.setTitle(propertyDTO.getTitle());
            pe.setAddress(propertyDTO.getAddress());
            pe.setOwnerEmail(propertyDTO.getOwnerEmail());
            pe.setOwnerName(propertyDTO.getOwnerName());
            pe.setDescription(propertyDTO.getDescription());
            pe.setPrice(propertyDTO.getPrice());

            dto = propertyConverter.convertEntitytoDTO(pe);
            propertyRepository.save(pe);

        }
        return dto;
    }

    @Override
    public PropertyDTO updatePropertyDescription(PropertyDTO propertyDTO, Long propertyId) {
        Optional<PropertyEntity> optionalPropertyEntity = propertyRepository.findById(propertyId);
        PropertyDTO dto = null;

        if (optionalPropertyEntity.isPresent()) {
            PropertyEntity pe = optionalPropertyEntity.get();// data from the database
            pe.setDescription(propertyDTO.getDescription());

            dto = propertyConverter.convertEntitytoDTO(pe);
            propertyRepository.save(pe);

        }
        return dto;
    }

    @Override
    public void deleteProperty(Long propertyId) {
        propertyRepository.deleteById(propertyId);
    }

}
