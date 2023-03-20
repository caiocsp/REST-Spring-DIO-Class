package br.com.dio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.dio.entity.Produto;
import br.com.dio.exception.ProductPriceException;
import br.com.dio.service.ProdutoService;


@SpringBootTest
public class ProdutoTest {
    // /src/test... -> Área definida para classes de testes na arquitetura.
    //Recomendável conhecer sobre o framework JUnit para conhecer algumas funcionalidades
    //Adicionando a notation @Test, da biblioteca Junit (observe o import), a IDE já habilita a opção de execução dos testes.

    @Autowired
    private ProdutoService produtoService;

    @Test
    public void ProductNegativeValueCheckTest() throws Exception { 
        Produto produto = new Produto();

        produto.setNome("Teste");
        produto.setPreco(-10.5);

        //Aqui, ele vai apontar um erro no teste indicando que irá cair em uma exceção, 
        //e irá parar o teste indicando falha no código:
        //produtoService.save(produto);

        //Já aqui queremos ter a certeza de que ele vai apontar uma exceção, 
        //e então aplicamos que, para passar no teste ele tem que cair em uma exceção
        assertThrows(ProductPriceException.class, () -> produtoService.save(produto)); // "() ->" == ao executar o método
    }
}
