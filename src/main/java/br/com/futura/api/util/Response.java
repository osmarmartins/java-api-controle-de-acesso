package br.com.futura.api.util;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Response<T> {
	
	private T data;
	private List<String> errors = new ArrayList<>();

}
