package com.optare.technical.test.controllers;


import com.optare.technical.test.dtos.StatusResponse;
import com.optare.technical.test.models.IpAddress;
import com.optare.technical.test.services.IpAddressService;
import jdk.jshell.Snippet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/ip-address")
public class IpAddressController {
    @Autowired
    private IpAddressService ipAddressService;

    @GetMapping("/get")
    public Optional<IpAddress> testApi(@RequestParam(name = "id") Long id){
        return ipAddressService.getIpAddress(id);
    }

    @GetMapping("/get-by-ip")
    public ArrayList<IpAddress> getIpByIp(@RequestParam(name = "ip") String ip){
        return ipAddressService.getIpAddressByIp(ip);
    }

    @GetMapping("/get-all")
    public ArrayList<IpAddress> getAllIpAddresses(){
        return ipAddressService.getAllIpAddresses();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<StatusResponse> deleteIp(@RequestParam(name = "id") Long id){
        ipAddressService.deleteIp(id);
        StatusResponse statusResponse = new StatusResponse();
        statusResponse.markAsSucced("El registro fue eliminado");
        return ResponseEntity.status(HttpStatus.OK).body(statusResponse);
    }


    @GetMapping("/add")
    public ResponseEntity<StatusResponse> addIpAddress(@RequestParam(name = "ip") String ip){
        ArrayList<IpAddress> ipAddress = ipAddressService.getIpAddressByIp(ip);

        StatusResponse statusResponse = new StatusResponse();


        if(!ipAddress.isEmpty()){
            statusResponse.markAsFailed("La IP ya existe en la base de datos");
            return ResponseEntity.status(HttpStatus.OK).body(statusResponse);
        }else{
            IpAddress newIpAddress = ipAddressService.saveIpAddress(ip);

            if(newIpAddress.isSuccess()){
                statusResponse.setIpAddress(newIpAddress);
                statusResponse.markAsSucced("La IP fue guardada correctamente");
                return ResponseEntity.status(HttpStatus.OK).body(statusResponse);
            }else{
                statusResponse.markAsFailed(newIpAddress.getMessage());
                return ResponseEntity.status(HttpStatus.OK).body(statusResponse);
            }
        }
    }
}
