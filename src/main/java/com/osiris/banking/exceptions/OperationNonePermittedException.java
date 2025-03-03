package com.osiris.banking.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class OperationNonePermittedException extends RuntimeException{

    private final String errorMsg;

    private final String operationId;

    private final String source;

    private final String dependency;

}
