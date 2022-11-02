package com.bcopstein.Emprestimos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class EmprestimoTest {
	Emprestimo.Builder empBuilder;
	CalculoDeJuros calcJuros;

	@BeforeEach()
	public void mock() {
		double juros = 1000.0;
		this.calcJuros = Mockito.mock(CalculoDeJuros.class);
		when(this.calcJuros.jurosEmprestimoJurosCompostos(1000.0, 0.035, 5)).thenReturn(juros);
		when(this.calcJuros.jurosEmprestimoJurosSimples(1000.0, 0.035, 5)).thenReturn(juros);
		this.empBuilder = new Emprestimo.Builder(calcJuros);
	}

	@Test()
	public void testCustoTotalJurosCompostos() {
		Emprestimo emprestimo = this.empBuilder.jurosCompostos().build();
		assertEquals(2060, emprestimo.custoTotal());
	}

	@Test()
	public void testCustoTotalEmprestimoJurosSimples() {
		Emprestimo emprestimo = this.empBuilder.jurosSimples().build();
		assertEquals(2060, emprestimo.custoTotal());
	}

}
