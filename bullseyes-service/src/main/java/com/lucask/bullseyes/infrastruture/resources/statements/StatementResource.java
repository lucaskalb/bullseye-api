package com.lucask.bullseyes.infrastruture.resources.statements;

import static com.lucask.bullseyes.infrastruture.resources.statements.StatementResource.DEFAULT_PATH;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lucask.bullseyes.domain.statements.StatementService;

@RestController
@RequestMapping(DEFAULT_PATH)
public class StatementResource {

	public static final String DEFAULT_PATH = "/statements";

	@Autowired
	private StatementService service;

	@PostMapping
	@ResponseBody
	public StatementResponse create(@RequestBody final StatementCreateData data) {
		return StatementResponse.process( service.create( data.toEntity() ) );
	}
}

