package com.thoughtworks.agenciacompromisso.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "O candidato informado não existe.")
public class CandidateNotFoundException extends RuntimeException {
}
