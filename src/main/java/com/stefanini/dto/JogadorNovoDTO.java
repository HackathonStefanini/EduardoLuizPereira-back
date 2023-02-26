package com.stefanini.dto;

import com.stefanini.dao.GenericDAO;
import com.stefanini.entity.Jogador;

public class JogadorNovoDTO extends Jogador {
    private String nickname;
    private String password;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
