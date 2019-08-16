package com.gabriel.mc.services;

import com.gabriel.mc.domain.Cidade;
import com.gabriel.mc.domain.Cliente;
import com.gabriel.mc.domain.Endereco;
import com.gabriel.mc.domain.enums.Perfil;
import com.gabriel.mc.domain.enums.TipoCliente;
import com.gabriel.mc.dto.ClienteDTO;
import com.gabriel.mc.dto.ClienteNewDTO;
import com.gabriel.mc.repositories.ClienteRepository;
import com.gabriel.mc.repositories.EnderecoRepository;
import com.gabriel.mc.security.UserSS;
import com.gabriel.mc.services.exceptions.AuthorizationException;
import com.gabriel.mc.services.exceptions.DataIntegrityException;
import com.gabriel.mc.services.exceptions.ObjecNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private BCryptPasswordEncoder pe;
	
	public Cliente find(Integer id) {

		UserSS user = UserService.authenticate();

		if (user == null || !user.hasHole(Perfil.ADMIN) && !id.equals(user.getId())) {
			throw new AuthorizationException("Acesso negado");
		}

		Cliente obj = repo.findOne(id);
		if (obj == null) {
			throw new ObjecNotFoundException("Objeto não encontrado! Id: "+ id
					+ ", Tipo: " + Cliente.class.getName());
		}
		return obj;
	}

	@Transactional
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj = repo.save(obj);
		enderecoRepository.save(obj.getEnderecos());
		return obj;
	}

	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId());
		updateDate(newObj, obj);
		return repo.save(newObj);
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.delete(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir porque há entidades relacionadas");
		}
	}

	public List<Cliente> findAll() {
		return repo.findAll();
	}

	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	public Cliente fromDTO(ClienteNewDTO objDto) {
		Cliente cli = new Cliente(null, objDto.getNome(), objDto.getEmail(), objDto.getCpfOuCnpj(), TipoCliente.toEnum(objDto.getTipo()), pe.encode(objDto.getSenha()));
		Cidade cid = new Cidade(objDto.getCidadeId(), null, null);
		Endereco end = new Endereco(null,objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(), objDto.getBairro(), objDto.getCep(), cli, cid);
		cli.getEnderecos().add(end);
		cli.getTelefones().add(objDto.getTelefone1());

		if (objDto.getTelefone2() != null)
			cli.getTelefones().add(objDto.getTelefone2());

		if (objDto.getTelefone3() != null)
			cli.getTelefones().add(objDto.getTelefone3());

		return cli;
	}

	public Cliente fromDTO(ClienteDTO objDto) {
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null, null);
	}

	private void updateDate(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}

}
