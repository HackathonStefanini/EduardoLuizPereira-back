package com.stefanini.service;

import java.util.List;
import java.util.Objects;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.core.Response;

import com.stefanini.dao.LoginDAO;
import com.stefanini.dto.JogadorNovoDTO;
import com.stefanini.entity.Jogador;
import com.stefanini.exceptions.RegraDeNegocioException;
import com.stefanini.repository.JogadorRepository;

import java.util.Base64;

@ApplicationScoped
public class JogadorService {

	@Inject
    JogadorRepository jogadorRepository;

    public void salvar(JogadorNovoDTO jogador) {
        Jogador novoJogador = new Jogador(jogador);
        jogador.setPassword(new String(Base64.getEncoder().encode(jogador.getPassword().getBytes())));
        try {
        jogadorRepository.save(novoJogador);
        }
        catch(Exception ex) {
        	// TODO: Implementar verificacao do erro, mas provavelmente sera por conta de entradas duplicadas
        	// Com mais tempo, seria implementada uma classe específica somente para os casos de erro, verificando
        	// possíveis cenários com o auxílio da tabela
        	throw new RegraDeNegocioException("usuario esta duplicado", Response.Status.CONFLICT);
        }
    }

    public Jogador pegarPorId(Long id) {
        Jogador jogador = jogadorRepository.findById(id);
        if(Objects.isNull(jogador)) {
            throw new RegraDeNegocioException("Ocorreu um erro ao buscar o Jogador de id " + id, Response.Status.NOT_FOUND);
        }
        return jogador;
    }

    public void alterar(Jogador jogador) {
        jogadorRepository.update(jogador);
    }

    public void deletar(Long id) {
        jogadorRepository.delete(id);
    }

    public List<Jogador> listarTodos() {
        return jogadorRepository.listAll();
    }
    
    public void login(@Valid JogadorNovoDTO jogador) {
    	LoginDAO login = new LoginDAO();
        Jogador loginJogador = login.autenticarUsuario(jogador.getNickname(), jogador.getPassword()).getSingleResult();
        if(Objects.isNull(loginJogador)) {
            throw new RegraDeNegocioException("Jogador não encontrado. Por favor, verifique nickname e password.",
                    Response.Status.NOT_FOUND);
        }
    }

	
}
