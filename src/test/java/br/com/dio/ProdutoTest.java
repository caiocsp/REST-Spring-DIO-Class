package br.com.dio;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;



public class ProdutoTest {
    // /src/test... -> Área definida para classes de testes na arquitetura.
    //Recomendável conhecer sobre o framework JUnit para conhecer algumas funcionalidades
    //Adicionando a notation @Test, da biblioteca Junit (observe o import), a IDE já habilita a opção de execução dos testes.

    @Test
    public void testeFalso() { //Teste de exemplo
        assertTrue(true);
    }
}
