package com.devLucasMatheus.cursoSpring;

import java.text.SimpleDateFormat;
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
import com.devLucasMatheus.cursoSpring.domain.Pagamento;
import com.devLucasMatheus.cursoSpring.domain.PagamentoComBoleto;
import com.devLucasMatheus.cursoSpring.domain.PagamentoComCartao;
import com.devLucasMatheus.cursoSpring.domain.Pedido;
import com.devLucasMatheus.cursoSpring.domain.Produto;
import com.devLucasMatheus.cursoSpring.domain.enuns.EstadoPagamento;
import com.devLucasMatheus.cursoSpring.domain.enuns.TipoCliente;
import com.devLucasMatheus.cursoSpring.repositories.CategoriaRepository;
import com.devLucasMatheus.cursoSpring.repositories.CidadeRepository;
import com.devLucasMatheus.cursoSpring.repositories.ClienteRepository;
import com.devLucasMatheus.cursoSpring.repositories.EnderecoRepository;
import com.devLucasMatheus.cursoSpring.repositories.EstadoRepository;
import com.devLucasMatheus.cursoSpring.repositories.PagamentoRepository;
import com.devLucasMatheus.cursoSpring.repositories.PedidoRepository;
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
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursoSpringApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Inform??tica");
		Categoria cat2 = new Categoria(null, "Escrt??rio");
		
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
		Estado est2 = new Estado(null, "S??o Paulo");
		
		Cidade c1 = new Cidade(null, "Uberl??ndia", est1);
		Cidade c2 = new Cidade(null, "S??o Paulo", est2);
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
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, 
				sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
		
	}

}
