package com.best.flyteng.service.impl;

import com.best.flyteng.entity.Personas;
import com.best.flyteng.mapper.PersonasMapper;
import com.best.flyteng.service.IPersonasService;
import com.github.yulichang.base.MPJBaseServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class PersonasServiceImpl  extends MPJBaseServiceImpl<PersonasMapper, Personas> implements IPersonasService {
}
