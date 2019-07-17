package com.gabriel.mc.services;

import com.gabriel.mc.domain.Pedido;
import com.gabriel.mc.repositories.PedidoRepository;
import com.gabriel.mc.services.exceptions.ObjecNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repo;
	
	public Pedido buscar(Integer id) {
		Pedido obj = repo.findOne(id);
		if (obj == null) {
			throw new ObjecNotFoundException("Objeto não encontrado! Id: "+ id
					+ ", Tipo: " + Pedido.class.getName());
		}
		return obj;
	}
}
