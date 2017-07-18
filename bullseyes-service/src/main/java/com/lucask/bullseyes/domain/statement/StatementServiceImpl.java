package com.lucask.bullseyes.domain.statement;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class StatementServiceImpl implements StatementService {

    @Autowired
    private StatementRepository repository;

    @Override
    public Statement create(Statement statement) {
        return repository.save(statement);
    }

    @Override
    public Statement update(Statement statement) {
        return null;
    }

}
