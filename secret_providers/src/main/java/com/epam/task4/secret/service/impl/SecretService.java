package com.epam.task4.secret.service.impl;

import com.epam.task4.secret.dto.SecretDto;
import com.epam.task4.secret.entity.Secret;
import com.epam.task4.secret.repository.SecretRepository;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SecretService {

    private SecretRepository secretRepository;


    public String save(SecretDto secretDto) {
        Secret secret = new Secret();
        secret.setText(secretDto.getText());
        secret.setLinkName(createLinkName());

        secretRepository.save(secret);
        return secret.getLinkName();
    }

    public SecretDto findByLinkNameAndDelete(String linkName) {
        Secret secret = secretRepository.findByLinkName(linkName);
        if (secret == null) {
            return null;
        }
        SecretDto secretDto = convertEntityToDto(secret);
        secretRepository.deleteById(secret.getId());
        return secretDto;
    }

    private String createLinkName() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replaceAll("-", "");
    }

    private SecretDto convertEntityToDto(Secret secret){
        SecretDto secretDto = new SecretDto();
        secretDto.setText(secret.getText());
        return secretDto;
    }
}
