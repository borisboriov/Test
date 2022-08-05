package com.example.TestTask.controllers;

import com.example.TestTask.entities.Socks;
import com.example.TestTask.service.SocksService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/socks")
@RequiredArgsConstructor
public class SocksController {

    private final SocksService socksService;

    @GetMapping
    public List<Socks> getAllWithParams(@RequestParam String color, @RequestParam int operation, @RequestParam int cotton) {
        return socksService.getSocksWithParams(color, operation, cotton);
    }

    @PostMapping("/income")
    public void saveSocks(@RequestBody Socks socks) {
        socksService.saveSocks(socks);
    }

    @DeleteMapping("/outcome")
    public void deleteSocks(@RequestBody Socks socks) {
        socksService.deleteSocks(socks);
    }

    @PostMapping("/change")
    public void change(@RequestBody Socks socks) {
        socksService.changeQuantity(socks);
    }//todo delete


}


