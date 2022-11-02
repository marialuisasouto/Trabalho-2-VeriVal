package com.bcopstein.Emprestimos;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import com.google.gson.Gson;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HttpRequestTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void emprestimoJurosSimples() throws Exception {
        EmprestimoDTO expectedResponse = new EmprestimoDTO(true, false, 1000, 0.032, 10, 1480,
                148);

        String response = this.restTemplate.getForObject(
                "http://localhost:" + port + "/emprestimo/jurosSimples?valor=1000&parcelas=10&taxa=0.032",
                String.class);

        Gson g = new Gson();

        EmprestimoDTO emp = g.fromJson(response, EmprestimoDTO.class);

        assertThat(expectedResponse).usingRecursiveComparison().isEqualTo(emp);
    }

    @Test
    public void emprestimoJurosCompostos() throws Exception {
        EmprestimoDTO expectedResponse = new EmprestimoDTO(true, true, 1000, 0.032, 10, 1568.958131151653,
                156.8958131151653);

        String response = this.restTemplate.getForObject(
                "http://localhost:" + port + "/emprestimo/jurosCompostos?valor=1000&parcelas=10&taxa=0.032",
                String.class);

        Gson g = new Gson();

        EmprestimoDTO emp = g.fromJson(response, EmprestimoDTO.class);

        assertThat(expectedResponse).usingRecursiveComparison().isEqualTo(emp);
    }
}
