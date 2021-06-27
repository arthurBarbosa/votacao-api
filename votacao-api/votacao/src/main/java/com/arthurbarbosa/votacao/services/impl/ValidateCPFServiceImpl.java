package com.arthurbarbosa.votacao.services.impl;

import com.arthurbarbosa.votacao.resources.exceptions.ExceptionEnum;
import com.arthurbarbosa.votacao.services.ValidateCPFService;
import com.arthurbarbosa.votacao.services.exception.ErrorValidateCPFException;
import com.arthurbarbosa.votacao.services.exception.UnableToVoteException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ValidateCPFServiceImpl implements ValidateCPFService {

    @Override
    public void validateCPF(String cpf) {
        RestTemplate restTemplate = new RestTemplate();
        String urlDbcValidateExternalService = "https://user-info.herokuapp.com/users/";
        ResponseEntity<String> responseBody = restTemplate.getForEntity(urlDbcValidateExternalService + cpf, String.class);
        if (!responseBody.getStatusCode().is2xxSuccessful()) {
            throw new ErrorValidateCPFException(ExceptionEnum.SERVICE_UNAVALAIBLE.getDescription());
        }
        if (responseBody.getBody().contains("UNABLE_TO_VOTE")) {
            throw new UnableToVoteException(ExceptionEnum.UNABLE_TO_VOTE.getDescription());
        }

    }
}
