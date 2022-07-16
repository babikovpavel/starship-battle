package ru.otus.homework9.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.homework9.auth.BattleStorage;
import ru.otus.homework9.model.Battle;
import ru.otus.homework9.requests.NewGameRequest;
import ru.otus.homework9.requests.SendCommandRequest;
import ru.otus.homework9.responses.NewGameResponse;

@RestController
@RequiredArgsConstructor
public class NewGameController {

    private final BattleStorage battleStorage;

    @PostMapping("/battle")
    public ResponseEntity<NewGameResponse> newGame(@RequestBody NewGameRequest newGameRequest) {
        String battleId = battleStorage.newBattle(newGameRequest.getUsers());
        return ResponseEntity.ok(new NewGameResponse(battleId));
    }

    @PostMapping("/battle/{battleId}")
    public ResponseEntity<String> sendCommand(@PathVariable String battleId,
                                      @RequestBody SendCommandRequest sendCommandRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = authentication.getName();
        Battle battle = battleStorage.getBattle(battleId, currentUser)
                .orElseThrow(IllegalArgumentException::new);
        battle.getCommands().add(sendCommandRequest.toCommand());
        return ResponseEntity.ok("Команда принята");
    }
}
