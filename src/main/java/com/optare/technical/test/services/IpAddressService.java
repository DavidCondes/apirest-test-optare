package com.optare.technical.test.services;


import com.optare.technical.test.models.IpAddress;
import com.optare.technical.test.repositories.IpAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@Service
public class IpAddressService {
    @Autowired
    private IpAddressRepository ipAddressRepository;

    private final String apiUrl = "https://ip-geolocation-ipwhois-io.p.rapidapi.com/json/";
    private final String apiKey = "bcd247064fmsh044d1c18b47161fp14c304jsn91c6c0693fe9";
    private final String apiHost = "ip-geolocation-ipwhois-io.p.rapidapi.com";

    public IpAddress saveIpAddress(String ip){
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-RapidAPI-Key", this.apiKey);
        headers.set("X-RapidAPI-Host", this.apiHost);
        HttpEntity<String> entity = new HttpEntity<String>(headers);


        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(this.apiUrl)
                .queryParam("ip", ip);

        IpAddress ipAddress = restTemplate.exchange(uriBuilder.toUriString(),
                                            HttpMethod.GET,
                                            entity,
                                            IpAddress.class).getBody();


        if(ipAddress != null && ipAddress.isSuccess()){
            ipAddressRepository.save(ipAddress);
        }

        return ipAddress;
    }

    public void deleteIp(Long id){
        ipAddressRepository.deleteById(id);
    }

    public ArrayList<IpAddress> getIpAddressByIp(String ip){

        return ipAddressRepository.findByIp(ip);
    }

    public ArrayList<IpAddress> getAllIpAddresses(){
        return(ArrayList<IpAddress>) ipAddressRepository.findAll();
    }

    public Optional<IpAddress> getIpAddress(Long id){
        return ipAddressRepository.findById(id);
    }
}
