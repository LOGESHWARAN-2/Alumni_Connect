package com.example.AlumniConnect.controller;

import com.example.AlumniConnect.service.NetworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

// Replaces: Routes/NetworkRoutes.js + Controllers/NetworkController.js
@RestController
@RequestMapping("/api/network")
public class NetworkController {

    @Autowired
    private NetworkService networkService;

    // GET /api/network — getNetworkData
    @GetMapping
    public ResponseEntity<Map<String, Object>> getNetworkData() {
        Map<String, Object> networkData = networkService.getNetworkData();
        return ResponseEntity.ok(networkData);
    }
}