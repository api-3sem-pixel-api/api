package fatec.api.pixel.horaextra.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fatec.api.pixel.horaextra.dto.DadosListagemCliente;
import fatec.api.pixel.horaextra.model.Cliente;
import fatec.api.pixel.horaextra.repository.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	ClienteRepository repository;
	
	public List<DadosListagemCliente> listarCliente(){
		List<Cliente> clientes = repository.findAll();
		List<DadosListagemCliente> dados = new ArrayList<DadosListagemCliente>();
		
		for (Cliente cliente : clientes) {
			dados.add(new DadosListagemCliente(cliente.getId(), cliente.getRazaoSocial()));
		}
		return dados;
	}
}