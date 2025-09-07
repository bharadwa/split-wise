package org.split.splitwise.dtos;

import lombok.Getter;
import lombok.Setter;
import org.split.splitwise.services.dto.Transaction;


import java.util.List;

@Setter
@Getter
public class SettleUpGroupResponseDTO {

    List<Transaction> transactions;

    private ResponseStatus responseStatus;
}
