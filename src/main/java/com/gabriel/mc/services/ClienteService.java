package com.gabriel.mc.services;

import com.gabriel.mc.domain.Cliente;
import com.gabriel.mc.repositories.ClienteRepository;
import com.gabriel.mc.services.exceptions.ObjecNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;
	
	public Cliente buscar(Integer id) {
		Cliente obj = repo.findOne(id);
		if (obj == null) {
			throw new ObjecNotFoundException("Objeto não encontrado! Id: "+ id
					+ ", Tipo: " + Cliente.class.getName());
		}
		return obj;
	}
}
