package ru.otus.homework9.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NewGameResponse {

    @JsonProperty("battleId")
    private String battleId;

    public NewGameResponse(String battleId) {
        this.battleId = battleId;
    }
}
