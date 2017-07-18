package com.lucask.bullseyes.infrastruture;

import com.lucask.bullseyes.domain.statement.Statement;
import com.lucask.bullseyes.domain.statement.StatementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.lucask.bullseyes.infrastruture.StatementResource.DEFAULT_PATH;

@RestController
@RequestMapping(DEFAULT_PATH)
public class StatementResource {

    public static final String DEFAULT_PATH = "/statement";

    @Autowired
    StatementResponseMapper response;

    @Autowired
    private StatementService service;

    @PostMapping
    @ResponseBody
    public StatementResponse create(@RequestBody Statement statement) {
        return response.map(service.create(statement));
    }
}

