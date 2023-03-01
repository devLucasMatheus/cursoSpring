package com.devLucasMatheus.cursoSpring;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.devLucasMatheus.cursoSpring.domain.Categoria;
import com.devLucasMatheus.cursoSpring.domain.Cidade;
import com.devLucasMatheus.cursoSpring.domain.Cliente;
import com.devLucasMatheus.cursoSpring.domain.Endereco;
import com.devLucasMatheus.cursoSpring.domain.Estado;
import com.devLucasMatheus.cursoSpring.domain.Produto;
import com.devLucasMatheus.cursoSpring.domain.enuns.TipoCliente;
import com.devLucasMatheus.cursoSpring.repositories.CategoriaRepository;
import com.devLucasMatheus.cursoSpring.repositories.CidadeRepository;
import com.devLucasMatheus.cursoSpring.repositories.ClienteRepository;
import com.devLucasMatheus.cursoSpring.repositories.EnderecoRepository;
import com.devLucasMatheus.cursoSpring.repositories.EstadoRepository;
import com.devLucasMatheus.cursoSpring.repositories.ProdutoRepository;

@SpringBootApplication
public class CursoSpringApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursoSpringApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escrtório");
		
		Produto p1 = new Produto(null, "Computador", 2000);
		Produto p2 = new Produto(null, "Impressora", 800);
		Produto p3 = new Produto(null, "Mouse", 80);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		Cliente cli1 = new Cliente(null, "Anna Eliza", "anna@gmail.com",
				"23465478909", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("22345678", "25678776"));
		
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim",
				"09898765", cli1, c1);
		Endereco e2 = new Endereco(null, "Rua Arvores", "4636", "Apto 3625", "Europa",
				"09725345", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
		
	}

}
