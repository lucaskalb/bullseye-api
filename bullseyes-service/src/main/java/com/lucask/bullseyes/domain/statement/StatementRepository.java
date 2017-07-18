package com.lucask.bullseyes.domain.statement;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatementRepository extends MongoRepository<Statement, String> {

}
