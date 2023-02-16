package com.sidneyMUII.propertymanagement.service;

import com.sidneyMUII.propertymanagement.dto.PropertyDTO;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface PropertyService {

    public PropertyDTO saveProperty(PropertyDTO propertyDTO);
    List<PropertyDTO> getAllProperties();

    PropertyDTO updateProperty(PropertyDTO propertyDTO, Long propertyID);
    PropertyDTO updatePropertyDescription(@RequestBody PropertyDTO propertyDTO, Long propertyId);
    void deleteProperty(Long propertyId);
}
