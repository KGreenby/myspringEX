package com.basic.myspringex.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CustomerReqFormDTO {

    private Long id;

    @NotEmpty(message = "name은 필수 입력 항목입니다.")
    private String name;

    @NotBlank(message = "email은 필수 입력 항목입니다.")
    @Email(message = "Email 형식이 아닙니다.")
    private String email;

    @NotNull(message = "age는 필수 입력 항목입니다.")
    private Long age;
}
