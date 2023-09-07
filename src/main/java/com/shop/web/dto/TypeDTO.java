package com.shop.web.dto;

import java.util.List;

import com.shop.web.models.Task;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class TypeDTO {
    private Long id;
    private String title;
    private List<Task> tasks;
}
