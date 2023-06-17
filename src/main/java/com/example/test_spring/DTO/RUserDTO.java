package com.example.test_spring.DTO;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class RUserDTO {
    
    private Integer rUserId;
    private String username;
    private String namaDepan;
    private String namaBelakang;
    private String alamat;
    private Integer departementId;
    private String departementname;
}
