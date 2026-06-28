package com.techstore.api.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.util.List;

@Data
public class VendaRequest {

    @NotNull(message = "Cliente é obrigatório")
    private Integer idCliente;

    @NotNull(message = "Funcionário é obrigatório")
    private Integer idFuncionario;

    @NotBlank(message = "Método de pagamento é obrigatório")
    @Size(max = 80)
    private String metodoPagamento;

    @NotEmpty(message = "A venda deve ter ao menos um item")
    private List<ItemVendaRequest> itens;
}
