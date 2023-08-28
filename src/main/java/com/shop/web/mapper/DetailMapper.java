package com.shop.web.mapper;

import com.shop.web.dto.DetailsDTO;
import com.shop.web.models.Details;

public class DetailMapper {
    public static Details maptoDetail(DetailsDTO detailsDTO){
        return Details.builder()
                        .id(detailsDTO.getId())
                        .title(detailsDTO.getTitle())
                        .type(detailsDTO.getType())
                        .description(detailsDTO.getDescription())
                        .createdOn(detailsDTO.getCreatedOn())
                        .updatedOn(detailsDTO.getUpdatedOn())
                        .build();
    }

    public static DetailsDTO maptoDetailDTO(Details details){
        return DetailsDTO.builder()
                        .id(details.getId())
                        .title(details.getTitle())
                        .type(details.getType())
                        .description(details.getDescription())
                        .createdOn(details.getCreatedOn())
                        .updatedOn(details.getUpdatedOn())
                        .build();
    }
}
