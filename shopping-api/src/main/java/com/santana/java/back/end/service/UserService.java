package com.santana.java.back.end.service;

import com.santana.java.back.end.dto.ShopDTO;
import com.santana.java.back.end.dto.UserDTO;

import com.santana.java.back.end.model.Shop;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.nio.file.attribute.UserPrincipalNotFoundException;

@Service
public class UserService {
    private String userApiURL = "http://localhost:8080";
    public UserDTO getUserByCpf(String cpf) {
        try {
            WebClient webClient = WebClient
                    .builder()
                    .baseUrl(userApiURL)
                    .build();
            Mono<UserDTO> user = webClient.get()
                    .uri("/user/" + cpf + "/cpf")
                    .retrieve()
                    .bodyToMono(UserDTO.class);

            return user.block();

        } catch (Exception e) { throw new UserNotFoundException(); }
        }

    public ShopDTO save(ShopDTO shopDTO, String key) {
        UserDTO userDTO = userService
                .getUserByCpf(shopDTO.getUserIdentifier(), key);
        validateProducts(shopDTO.getItems());
        shopDTO.setTotal(shopDTO.getItems()
                .stream()
                .map(x -> x.getPrice())
                .reduce((float) 0, Float::sum));
        Shop shop = Shop.convert(shopDTO);
        shop.setDate(LocalDateTime.now());
        shop = shopRepository.save(shop);
        return DTOConverter.convert(shop);
    }

}
