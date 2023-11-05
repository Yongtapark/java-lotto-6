package lotto.lotto;

import static lotto.core.enums.WinningChartEnum.FIVE_AND_BONUS_MATCH;
import static lotto.core.enums.WinningChartEnum.FOUR_MATCH;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import lotto.core.lotto.BonusNumber;
import lotto.core.lotto.LottoTicket;
import lotto.core.lotto.ScratchedLottoTicket;
import lotto.core.lotto.ScratchedLottoTicketList;
import lotto.core.lotto.TicketScratcher;
import lotto.core.lotto.WinningNumbers;
import org.junit.jupiter.api.Test;

class ScratchedLottoTicketListTest {
    private static final TicketScratcher TICKET_SCRATCHER = new TicketScratcher();
    private final static List<Integer> LOTTO_NUMBER_FIVE_MATCH = Arrays.asList(1,2,3,4,5,15);
    private final static List<Integer> LOTTO_NUMBER_FOUR_MATCH = Arrays.asList(1,2,3,33,5,15);
    private final static List<Integer> WINNING_NUMBERS = Arrays.asList(1,2,3,32,5,4);
    private final static List<Integer> LOSING_NUMBERS = Arrays.asList(45,44,43,42,41,40);
    private final static LottoTicket lottoTicketWithFiveMatch =new LottoTicket(LOTTO_NUMBER_FIVE_MATCH);
    private final static LottoTicket lottoTicketWithFourMatch =new LottoTicket(LOTTO_NUMBER_FOUR_MATCH);
    private final static WinningNumbers winningNumbers = new WinningNumbers(WINNING_NUMBERS);
    private final static WinningNumbers losingNumbers = new WinningNumbers(LOSING_NUMBERS);
    private final static BonusNumber loseBonusNumber = new BonusNumber(23);
    private final static BonusNumber winBonusNumber = new BonusNumber(15);
    @Test
    void createLottoResult() {
        //if
        List<LottoTicket> lottoTickets = Arrays.asList(lottoTicketWithFiveMatch, lottoTicketWithFourMatch);
        ScratchedLottoTicketList scratchedLottoTicketList = TICKET_SCRATCHER.scratchAllTickets(winningNumbers, winBonusNumber, lottoTickets);

        //when
        List<ScratchedLottoTicket> scratchedLottoTickets = scratchedLottoTicketList.getScratchedLottoTickets();

        List<LottoTicket> findLottoTickets = scratchedLottoTickets.stream().map(ScratchedLottoTicket::getLottoTicket).toList();


        int fiveAndBonusMatchCount = (int)scratchedLottoTickets.stream()
                .filter(ticket -> ticket.getWinningChartEnum().equals(FIVE_AND_BONUS_MATCH)).count();
        int findFourMatchCount = (int)scratchedLottoTickets.stream()
                .filter(ticket -> ticket.getWinningChartEnum().equals(FOUR_MATCH)).count();
        Integer fiveMatchCount = scratchedLottoTicketList.getFiveAndBonusMatchCount();
        Integer fourMatchCount = scratchedLottoTicketList.getFourMatchCount();

        //then
        assertThat(findLottoTickets).contains(lottoTicketWithFourMatch);
        assertThat(findLottoTickets).contains(lottoTicketWithFourMatch);
        assertThat(fiveMatchCount).isEqualTo(fiveAndBonusMatchCount);
        assertThat(fourMatchCount).isEqualTo(findFourMatchCount);
        assertThat(fiveMatchCount).isEqualTo(1);
        assertThat(fourMatchCount).isEqualTo(1);
    }
}