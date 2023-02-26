package com.stefanini.dao;

import java.util.Objects;

import javax.persistence.TypedQuery;
import javax.ws.rs.core.Response;

import com.stefanini.dto.JogadorNovoDTO;
import com.stefanini.entity.Jogador;
import com.stefanini.exceptions.RegraDeNegocioException;

public class LoginDAO extends GenericDAO {
	public LoginDAO(){}
	
    public TypedQuery<JogadorNovoDTO> autenticarUsuario(String nickname, String password) {
        return (TypedQuery<JogadorNovoDTO>) createQuery("FROM Jogador WHERE nickname = :nickname AND password = :password")
                .setParameter("nickname", nickname)
                .setParameter("password", password);

    }

}
