package com.corp.spring.database.repository;

import com.corp.spring.bpp.InjectBean;
import com.corp.spring.database.pool.ConnectionPool;

public class CompanyRepository {

    @InjectBean
    private ConnectionPool connectionPool;
}
