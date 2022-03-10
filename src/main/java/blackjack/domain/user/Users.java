package blackjack.domain.user;

import static java.util.stream.Collectors.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import blackjack.domain.card.Deck;

public class Users {

    private final List<Player> players;
    private final Dealer dealer;

    private Users(List<Player> players) {
        this.players = players;
        dealer = new Dealer();
    }

    public static Users from(List<String> inputNames) {
        List<Player> players = inputNames.stream()
            .map(Player::new)
            .collect(toList());
        return new Users(players);
    }

    public void drawInitCards(Deck deck) {
        for (Player player : players) {
            player.drawInitCards(deck);
        }
        dealer.drawInitCards(deck);
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Optional<Player> findPlayersToHit() {
        return players.stream()
            .filter(player -> !player.isBust())
            .findAny();
    }
}
