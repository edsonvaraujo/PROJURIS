package com.projuris.serviceordem.clients;

import com.projuris.serviceordem.dtos.ResponsePageDto;
import com.projuris.serviceordem.dtos.UserDto;
import com.projuris.serviceordem.specifications.SpecificationTemplate;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@Log4j2
@RestController
public class UserCourseClient {

    @Autowired
    RestTemplate restTemplate;


    @Value("${projuris.url.authuser}")
    String REQUEST_URL_AUTHUSER;
    public Page<UserDto> getAllUser(SpecificationTemplate.SoSpec spec, Pageable pageable){
        List<UserDto> searchResult= null;
        String url = REQUEST_URL_AUTHUSER + "/users";
        try {
            ParameterizedTypeReference<ResponsePageDto<UserDto>> responseType=new ParameterizedTypeReference<ResponsePageDto<UserDto>>() {};
            ResponseEntity<ResponsePageDto<UserDto>> result = restTemplate.exchange(url, HttpMethod.GET, null,responseType);
            searchResult= result.getBody().getContent();
            log.info("Respose number of Elements {}:", searchResult.size());
        }catch (HttpStatusCodeException e){
            log.error("Erro request URL: {}", e);
        }
        return new PageImpl<>(searchResult);
    }

    public ResponseEntity<UserDto> getOneUserById(UUID userId){
        String url = REQUEST_URL_AUTHUSER + "/users/" + userId;
        return restTemplate.exchange(url, HttpMethod.GET, null, UserDto.class);
    }
}
