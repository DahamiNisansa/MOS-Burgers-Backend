package edu.icet.pos.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class CustomerDto {

    private Integer id;
    private String name;
    private String email;
    private String address;
    private String phoneNumber;

    private String preferenceNotes;
    private Integer  loyaltyPoints;
    //private String orders;


}
