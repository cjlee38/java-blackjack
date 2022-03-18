package blackjack.domain.user;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import blackjack.domain.strategy.hit.DealerHitStrategy;

public class DealerTest {

    @DisplayName("딜러 생성 검증")
    @Test
    public void createDealer() {
        //given & when
        Deck deck = new Deck();
        Dealer dealer = new Dealer(deck);

        //then
        assertThat(dealer).isNotNull();
    }

    @DisplayName("딜러는 카드를 뽑을 수 있다.")
    @Test
    public void testDrawCard() {
        //given
        Deck deck = new Deck();
        Dealer dealer = new Dealer(deck);

        //when
        dealer.hit(new Card(Suit.CLOVER, Denomination.EIGHT));
        List<Card> cards = dealer.getHandCards();

        //then
        assertThat(cards.size()).isEqualTo(1);
    }

    @DisplayName("딜러는 처음에 카드를 한 장만 보여준다.")
    @Test
    public void testShowInitCards() {
        //given
        Deck deck = new Deck();
        Dealer dealer = new Dealer(deck);

        dealer.hit(new Card(Suit.DIAMOND, Denomination.ACE));
        dealer.hit(new Card(Suit.HEART, Denomination.JACK));
        //when
        List<Card> cards = dealer.showInitCards();
        //then
        assertThat(cards.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("딜러는 17점 미만이면 카드를 한장 더 받는다.")
    public void testDealerHitWithScoreLessThan17() {
        // given
        Deck deck = new Deck();
        Dealer dealer = new Dealer(deck);
        DealerHitStrategy strategy = new DealerHitStrategy(dealer.calculateScore());
        // when
        boolean isHit = dealer.hitOrStay(deck, strategy);
        // then
        assertThat(isHit).isTrue();
    }

    @Test
    @DisplayName("딜러는 17점 이상이면 카드를 더 받을 수 없다.")
    public void testDealerStayWithScoreGreaterOrEqualThan17() {
        // given
        Deck deck = new Deck();
        Dealer dealer = new Dealer(deck);

        dealer.hit(new Card(Suit.CLOVER, Denomination.JACK));
        dealer.hit(new Card(Suit.CLOVER, Denomination.KING));
        // when

        boolean isHit = dealer.hitOrStay(deck, new DealerHitStrategy(dealer.calculateScore()));
        // then
        assertThat(isHit).isFalse();
    }
}
