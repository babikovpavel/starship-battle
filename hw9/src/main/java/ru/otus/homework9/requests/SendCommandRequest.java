package ru.otus.homework9.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.otus.homework9.model.Command;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SendCommandRequest {
    private Long operationId;
    private Object[] args;

    public Command toCommand() {
        return () -> {};
    }
}
