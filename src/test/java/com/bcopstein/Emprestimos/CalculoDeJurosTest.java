package com.bcopstein.Emprestimos;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.provider.CsvSource;
public class CalculoDeJurosTest {
    private CalculoDeJuros calcJuros;

    @BeforeEach()
    public void setUpCalculoJuros() {
        this.calcJuros = new CalculoDeJuros();
    }

    @ParameterizedTest()
    @CsvSource({
        "true, 1000, 0.01, 36, 720",
        "false, 1000, 0.01, 36, 360"
    })
    public void testJurosSimples (boolean comSeguro, double valor, double taxa, int nroParcelas, double respEsp) {
        this.calcJuros.setSeguro(comSeguro);
        
        double respObt = this.calcJuros.jurosEmprestimoJurosSimples(valor, taxa, nroParcelas);
        assertEquals(respEsp, respObt);
    }

    @ParameterizedTest()
    @CsvSource({
        "true, 800, 0.04, 36, 3833.4529087774854",
        "false, 800, 0.04, 36, 2483.1460431843307"
    })
    public void testJurosCompostos (boolean comSeguro, double valor, double taxa, int nroParcelas, double respEsp) {
        this.calcJuros.setSeguro(comSeguro);

        double respObt = this.calcJuros.jurosEmprestimoJurosCompostos(valor, taxa, nroParcelas);
        assertEquals(respEsp, respObt);
    }
}
