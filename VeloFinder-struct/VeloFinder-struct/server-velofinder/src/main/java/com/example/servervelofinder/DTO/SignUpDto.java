package com.example.servervelofinder.DTO;

import com.example.servervelofinder.entities.experience.EXPERIENCE;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class SignUpDto {
    private String firstName;
    private String lastName;
    private String username;
    private String experience;
    private char[] password;
}
