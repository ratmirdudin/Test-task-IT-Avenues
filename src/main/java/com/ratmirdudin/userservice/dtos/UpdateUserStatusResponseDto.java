package com.ratmirdudin.userservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserStatusResponseDto {

    private Long id;

    private String oldStatus;

    private String newStatus;
}
