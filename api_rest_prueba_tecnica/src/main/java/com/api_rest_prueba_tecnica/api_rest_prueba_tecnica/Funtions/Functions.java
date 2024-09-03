package com.api_rest_prueba_tecnica.api_rest_prueba_tecnica.Funtions;

import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class Functions {

    public Long mathRamdomId() {
        Random random = new Random();
        long min = 1000L;
        long max = 2000L;

        long valorLong = random.nextLong() % (max - min + 1) + min;
        return valorLong;
    }

}
