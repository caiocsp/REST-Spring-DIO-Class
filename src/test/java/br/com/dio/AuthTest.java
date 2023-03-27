package br.com.dio;

import java.net.URI;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthTest {
    //Utilizando MockMvc para simulação do teste de endpoint

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void validCredentialsTest() throws Exception{
        URI uri = new URI("/auth/signin");

        //CONTEÚDO DO MOCK
        String content = "{ \"username\" : \"caiocsp\" , \"senha\" : \"123456\"}";

        mockMvc.perform(MockMvcRequestBuilders
        .post(uri)
        .content(content)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().is(200));//O que é esperado ao fim do teste
    }

    @Test
    public void invalidCredentialsTest() throws Exception{
        URI uri = new URI("/auth/signin");

        //CONTEÚDO DO MOCK
        String content = "{ \"username\" : \"caiocsp\" , \"senha\" : \"12345789\"}";

        mockMvc.perform(MockMvcRequestBuilders
        .post(uri)
        .content(content)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.content().string("CREDENCIAIS INVALIDAS"));
        //Neste caso, o que é esperado ao fim do teste, é o conteúdo do body
    }
}
