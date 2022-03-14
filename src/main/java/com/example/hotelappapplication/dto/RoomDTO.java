package com.example.hotelappapplication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data

public class RoomDTO {

    private Integer number;
    private Integer floor;
    private Double size;
    private Integer hotelId;

}