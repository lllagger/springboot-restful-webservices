package com.javaguide.springboot.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(
        description = "UserDto Model Information"
)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    @Schema(
            description = "Data Base Id"
    )
    private Long id;
    @Schema(
            description = "User First Name"
    )
    @NotEmpty(message = "İsim boş olamaz.")
    private String firstName;
    @Schema(
            description = "User Last Name"
    )
    @NotEmpty(message = "Soy isim boş olamaz.")
    private String lastName;
    @Schema(
            description = "User Email Address"
    )
    @NotEmpty(message = "E-mail boş olamaz.")
    @Email(message = "E-mail geçersiz.")
    private String email;
}
