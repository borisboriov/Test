package com.example.TestTask.service;


import com.example.TestTask.entities.Socks;
import com.example.TestTask.repositories.SocksRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SocksService {

    private final SocksRepository socksRepository;

    public void saveSocks(Socks socks) {
        if (changeSocksQuantityIfExist(socks)) {
            return;
        }
        saveNewSocks(socks);
    }

    public boolean changeSocksQuantityIfExist(Socks socks) {
        if (findSockByColorAndCottonPart(socks).isPresent()) {
            changeQuantity(socks);
            return true;
        }
        return false;
    }

    public Optional<Socks> findSockByColorAndCottonPart(Socks sock) {
        return socksRepository.findSockByColorAndCottonPart(sock.getColor(), sock.getCotton());
    }

    @Transactional
    public void changeQuantity(Socks socks) {
        Optional<Socks> socksFromDB = socksRepository.findSockByColorAndCottonPart(socks.getColor(), socks.getCotton());
        socksRepository.changeQuantity(socksFromDB.get().getId(), socksFromDB.get().getQuantity());
    }

    public void saveNewSocks(Socks socks) {
        socksRepository.save(socks);
    }

    public void deleteSocks(Socks socks) {
        Socks fromDB = findSockByColorAndCottonPart(socks).get();
        socksRepository.deleteById(fromDB.getId());
    }

    public List<Socks> getSocksWithParams(String color, int operation, int cotton) {
        return socksRepository.getSocksWithParams(color, operation, cotton);
    }

}

