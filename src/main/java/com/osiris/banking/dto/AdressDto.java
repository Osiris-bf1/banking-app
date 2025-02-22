package com.osiris.banking.dto;

import com.osiris.banking.entity.Account;
import com.osiris.banking.entity.Adress;
import com.osiris.banking.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class AdressDto {

    private Long id;
    private String street;
    private Integer houseNumber;
    private Integer zipCode;
    private String city;
    private String country;
    private Long userId;

    public static AdressDto fromEntity(Adress adress) {
        return AdressDto.builder()
                .id(adress.getId())
                .city(adress.getCity())
                .houseNumber(adress.getHouseNumber())
                .zipCode(adress.getZipCode())
                .street(adress.getStreet())
                .country(adress.getCountry())
                .userId(adress.getUser().getUserId())
                .build();
    }

    public static Adress toEntity(AdressDto adress) {
        return Adress.builder()
                .id(adress.getId())
                .city(adress.getCity())
                .houseNumber(adress.getHouseNumber())
                .zipCode(adress.getZipCode())
                .street(adress.getStreet())
                .country(adress.getCountry())
                .user(
                        User.builder()
                                .id(adress.userId)
                                .build()
                )
                .build();
    }

}
