package com.unicus.dtos.responses;

import org.springframework.http.HttpStatus;

public record Response(HttpStatus status, String msg){


}
