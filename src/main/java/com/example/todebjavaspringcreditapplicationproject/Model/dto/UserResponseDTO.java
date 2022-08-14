package com.example.todebjavaspringcreditapplicationproject.Model.dto;

import com.example.todebjavaspringcreditapplicationproject.Model.entity.Role;
import lombok.Data;

import java.util.List;

@Data
public class UserResponseDTO {

    private Integer id;
    private String username;
    private String email;
    private List<Role> roles;

}
