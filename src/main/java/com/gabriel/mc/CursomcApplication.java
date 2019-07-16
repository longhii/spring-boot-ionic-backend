package com.gabriel.mc;

import com.gabriel.mc.domain.*;
import com.gabriel.mc.domain.enums.TipoCliente;
import com.gabriel.mc.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Arrays;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

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
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria c1 = new Categoria(null,"Informática");
		Categoria c2 =  new Categoria(null,"Escritório");

		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 700.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);

		c1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		c1.getProdutos().addAll(Arrays.asList(p2));
		c1.getProdutos().addAll(Arrays.asList(p1,p2,p3));

		p1.getCategorias().addAll(Arrays.asList(c1));
		p2.getCategorias().addAll(Arrays.asList(c1,c2));
		p3.getCategorias().addAll(Arrays.asList(c1));

		categoriaRepository.save(Arrays.asList(c1,c2));
		produtoRepository.save(Arrays.asList(p1,p2,p3));

		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");

		Cidade cid1 = new Cidade(null, "Uberlandia", est1);
		Cidade cid2 = new Cidade(null, "São Paulo", est2);
		Cidade cid3 = new Cidade(null, "Campinas", est2);

		est1.getCidades().addAll(Arrays.asList(cid1));
		est2.getCidades().addAll(Arrays.asList(cid2, cid3));

		estadoRepository.save(Arrays.asList(est1,est2));
		cidadeRepository.save(Arrays.asList(cid1,cid2,cid3));

		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail", "33345454420", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("27363355","988586622"));

		Endereco e1 = new Endereco(null, "Rua Flores","300","Apto 303","Jardim", "38240580", cli1, cid1);
		Endereco e2 = new Endereco(null, "Av Matos","105","Sala 800","Centro", "38240580", cli1, cid2);

		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));

		clienteRepository.save(Arrays.asList(cli1));
		enderecoRepository.save(Arrays.asList(e1,e2));
	}
}
