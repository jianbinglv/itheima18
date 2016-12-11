package com.taotao.sso.service;

import com.taotao.common.util.TaotaoResult;

public interface LogoutService {

	TaotaoResult logout(String token);
}
