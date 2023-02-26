package com.stefanini.dao;

import javax.persistence.TypedQuery;

import com.stefanini.dto.JogadorNovoDTO;
import com.stefanini.entity.Jogador;

public class LoginDAO extends GenericDAO {
	public LoginDAO(){}
	
    public TypedQuery<JogadorNovoDTO> autenticarUsuario(String nickname, String password) {
        return (TypedQuery<JogadorNovoDTO>) createQuery("FROM Jogador WHERE nickname = :nickname AND password = :password")
                .setParameter("nickname", nickname)
                .setParameter("password", password);
    }

}
