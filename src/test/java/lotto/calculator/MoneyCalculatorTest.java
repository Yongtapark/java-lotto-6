package lotto.calculator;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import lotto.core.calculator.MoneyCalculator;
import lotto.core.lotto.BonusNumber;
import lotto.core.lotto.LottoTicket;
import lotto.core.lotto.ScratchedLottoTicketList;
import lotto.core.lotto.TicketScratcher;
import lotto.core.lotto.WinningNumbers;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MoneyCalculatorTest {

    private static final MoneyCalculator calculator = new MoneyCalculator();
    private static final TicketScratcher ticketScratcher = new TicketScratcher();
    private final static List<Integer> LOTTO_NUMBER_FIVE_MATCH = Arrays.asList(1, 2, 3, 4, 5, 15);
    private final static List<Integer> LOTTO_NUMBER_FOUR_MATCH = Arrays.asList(1, 2, 3, 33, 5, 15);
    private final static List<Integer> WINNING_NUMBERS = Arrays.asList(1, 2, 3, 32, 5, 4);
    private final static LottoTicket lottoTicketWithFiveMatch = new LottoTicket(LOTTO_NUMBER_FIVE_MATCH);
    private final static LottoTicket lottoTicketWithFourMatch0 = new LottoTicket(LOTTO_NUMBER_FOUR_MATCH);
    private final static LottoTicket lottoTicketWithFourMatch1 = new LottoTicket(LOTTO_NUMBER_FOUR_MATCH);
    private final static LottoTicket lottoTicketWithFourMatch2 = new LottoTicket(LOTTO_NUMBER_FOUR_MATCH);
    private final static LottoTicket lottoTicketWithFourMatch3 = new LottoTicket(LOTTO_NUMBER_FOUR_MATCH);
    private final static WinningNumbers winningNumbers = new WinningNumbers(WINNING_NUMBERS);
    private final static BonusNumber winBonusNumber = new BonusNumber(15);

    @DisplayName("총 수익률을 반환한다.")
    @Test
    void calculateTotalMoneyAndRate() {
        //if
        List<LottoTicket> lottoTickets = Arrays.asList(lottoTicketWithFiveMatch, lottoTicketWithFourMatch0,
                lottoTicketWithFourMatch1, lottoTicketWithFourMatch2, lottoTicketWithFourMatch3);
        ScratchedLottoTicketList scratchedLottoTicketList = ticketScratcher.scratchAllTickets(winningNumbers,
                winBonusNumber, lottoTickets);

        //when
        calculator.calculate(scratchedLottoTicketList,lottoTickets.size());

        //then
        BigDecimal rateOfReturn = calculator.getRateOfReturn();
        Assertions.assertThat(rateOfReturn).isEqualTo(new BigDecimal("604000.0"));
    }
}