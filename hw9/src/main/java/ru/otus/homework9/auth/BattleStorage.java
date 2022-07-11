package ru.otus.homework9.auth;

import org.springframework.stereotype.Component;
import ru.otus.homework9.model.Battle;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class BattleStorage {

    private static final List<Battle> BATTLES = new ArrayList<>();

    public String newBattle(List<String> users) {
        Battle battle = new Battle(users);
        BATTLES.add(battle);
        return battle.getId();
    }

    public Optional<Battle> getBattle(String battleId, String userId) {
        return BATTLES.stream()
                .filter(battle -> battle.getId().equals(battleId)
                        && battle.getUsersList().contains(userId))
                .findFirst();
    }
}
