package com.javaguide.springboot.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;
    @NotEmpty(message = "İsim boş olamaz.")
    private String firstName;
    @NotEmpty(message = "Soy isim boş olamaz.")
    private String lastName;
    @NotEmpty (message = "E-mail boş olamaz.")
    @Email(message = "E-mail geçersiz.")
    private String email;
}
