package com.example.hotelappapplication.controller;

import com.example.hotelappapplication.dto.ApiResponse;
import com.example.hotelappapplication.dto.RoomDTO;
import com.example.hotelappapplication.entity.Hotel;
import com.example.hotelappapplication.entity.Room;
import com.example.hotelappapplication.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/room")
public class RoomController {

    @Autowired
    RoomService roomService;

    @GetMapping
    public HttpEntity<?> getAll(){
        List<Room> roomList = roomService.getAll();
        return ResponseEntity.ok().body(roomList);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getOne(@PathVariable Integer id){
        Optional<Room> roomOptional = roomService.getOne(id);
        if (roomOptional.isPresent()){
            return ResponseEntity.status(200).body(roomOptional.get());
        }else {
            return ResponseEntity.status(404).body(new Hotel());
        }
    }

    @PostMapping
    public HttpEntity<?> save(@Valid @RequestBody RoomDTO roomDTO){
        ApiResponse response = roomService.save(roomDTO);
        return ResponseEntity.status(response.isSuccess() ?
                HttpStatus.CREATED : HttpStatus.CONFLICT).body(response);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Integer id, @RequestBody RoomDTO roomDTO){
        ApiResponse response = roomService.edit(id, roomDTO);
        return ResponseEntity.status(response.isSuccess() ?
                HttpStatus.OK : HttpStatus.CONFLICT).body(response);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id){
        ApiResponse response = roomService.delete(id);
        return ResponseEntity.status(response.isSuccess() ?
                HttpStatus.OK : HttpStatus.CONFLICT).body(response);
    }

    @GetMapping("/hotel/{id}")
    public HttpEntity<?> getAllRooms(@RequestParam("page") int page,@PathVariable Integer id) {

        ApiResponse all = roomService.getAllByHotelId(page, id);
        return ResponseEntity.status(HttpStatus.OK).body(all);

    }
}