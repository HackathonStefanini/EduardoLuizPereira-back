package com.stefanini.service;

import java.util.List;
import java.util.Objects;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;

import com.stefanini.entity.Jogador;
import com.stefanini.exceptions.RegraDeNegocioException;
import com.stefanini.repository.JogadorRepository;

@ApplicationScoped
public class JogadorService {

	@Inject
    JogadorRepository jogadorRepository;

    public void salvar(Jogador jogador) {
        jogadorRepository.save(jogador);
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
}
