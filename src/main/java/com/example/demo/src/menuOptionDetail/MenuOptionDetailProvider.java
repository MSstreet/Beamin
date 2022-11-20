package com.example.demo.src.menuOptionDetail;

import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuOptionDetailProvider {

    private final MenuOptionDetailDao menuOptionDetailDao;
    private final JwtService jwtService;


    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public MenuOptionDetailProvider(MenuOptionDetailDao menuOptionDetailDao, JwtService jwtService) {
        this.menuOptionDetailDao = menuOptionDetailDao;
        this.jwtService = jwtService;
    }
}
