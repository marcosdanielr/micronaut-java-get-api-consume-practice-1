package com.apiconsumeexample.controller;

import com.apiconsumeexample.dto.CepDTO;
import com.apiconsumeexample.model.Cep;
import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.client.HttpClient;

@Controller("/")
public class CepController {
    private final HttpClient httpClient;

    public CepController(@Client("https://viacep.com.br/ws") HttpClient httpClient) {
        this.httpClient = httpClient;
    }
    @Get("/{cep}")
    public String getInformations(@PathVariable int cep) {
        HttpResponse<CepDTO> response = httpClient.toBlocking().exchange(HttpRequest.GET(cep + "/json/"), Argument.of(CepDTO.class));
        CepDTO data = response.body();
        String city = data.getLocalidade();
        String state = data.getUf();

        return  city + ", " + state;
    }
}
