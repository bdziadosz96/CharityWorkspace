package pl.dziadosz.fundsmicroservice.infrastructure.persistence.database.fundraiser;


import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.Fundraiser;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.FundraiserEvent;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.FundraiserEventType;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.view.FundraiserModel;
import pl.dziadosz.fundsmicroservice.infrastructure.persistence.database.fundraiser.entity.FundraiserEntity;
import pl.dziadosz.fundsmicroservice.infrastructure.persistence.database.fundraiser.entity.FundraiserEventEntity;
import pl.dziadosz.fundsmicroservice.infrastructure.persistence.database.fundraiser.repository.FundraiserEventRepository;
import pl.dziadosz.fundsmicroservice.infrastructure.persistence.database.fundraiser.repository.FundraiserRepository;

class FundraiserDatabaseAdapterTest extends PersistenceTestBaseClass {

    FundraiserDatabaseAdapter fundraiserDatabaseAdapter;

    @Autowired
    FundraiserRepository fundraiserRepository;

    @Autowired
    FundraiserEventRepository eventRepository;


    @Test
    void shouldSaveFundraiser() {
        fundraiserDatabaseAdapter = new FundraiserDatabaseAdapter(fundraiserRepository, null);
        Fundraiser result = fundraiserDatabaseAdapter.save(fundraiserToSave());

        Assertions.assertFalse(fundraiserRepository.findAll().isEmpty());
        Assertions.assertEquals(1, fundraiserRepository.findAll().size());

        FundraiserEntity actual = fundraiserRepository.findById(result.getId()).get();
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(result.getName(), actual.getName());
        Assertions.assertEquals(result.getId(), result.getId());
        Assertions.assertEquals(result.getAccountId(), actual.getAccountId());
        Assertions.assertEquals(result.getBalance(), actual.getBalance());
    }


    @Test
    void shouldSaveFundraiserEvent() {
        fundraiserDatabaseAdapter = new FundraiserDatabaseAdapter(null, eventRepository);

        FundraiserEvent result = fundraiserDatabaseAdapter.save(fundraiserEventToSave());

        Assertions.assertFalse(eventRepository.findAll().isEmpty());
        Assertions.assertEquals(1, eventRepository.findAll().size());

        FundraiserEventEntity actual = eventRepository.findById(result.getUuid()).get();

        Assertions.assertNotNull(actual);
        Assertions.assertEquals(result.getUuid(), actual.getUuid());
        Assertions.assertEquals(result.getFundraiserId(), actual.getFundraiserId());
        Assertions.assertEquals(result.getAccountId(), actual.getAccountId());
        Assertions.assertEquals(result.getEventType(), actual.getEventType());
    }


    @Test
    void shouldFindFundraiserById() {
        fundraiserRepository.saveAll(fundraiserEntitiesToSave());
        fundraiserDatabaseAdapter = new FundraiserDatabaseAdapter(fundraiserRepository, null);
        FundraiserModel actual = fundraiserDatabaseAdapter.findFundraiserById(1L).get();

        Assertions.assertNotNull(actual);
        Assertions.assertEquals(1L, actual.id());
        Assertions.assertEquals(1L, actual.accountId());
        Assertions.assertEquals(BigDecimal.TEN, actual.balance());
        Assertions.assertEquals("testname1", actual.name());
    }

    @Test
    void shouldFindFundraiserWithProperAccountId() {
        long fakeId = 10L;
        long fakeAccountId = 2L;
        fundraiserDatabaseAdapter = new FundraiserDatabaseAdapter(fundraiserRepository, null);
        Fundraiser savedFundraiser = fundraiserDatabaseAdapter.save(fundraiserToSave());

        Optional<Fundraiser> validResult = fundraiserDatabaseAdapter.findFundraiserWithAccountId(savedFundraiser.getId(), savedFundraiser.getAccountId());
        Optional<Fundraiser> invalidResult = fundraiserDatabaseAdapter.findFundraiserWithAccountId(fakeId, fakeAccountId);

        Assertions.assertFalse(validResult.isEmpty());
        Assertions.assertTrue(invalidResult.isEmpty());

        Fundraiser fundraiser = validResult.get();
        Assertions.assertEquals("testname", fundraiser.getName());
        Assertions.assertEquals(BigDecimal.TEN, fundraiser.getBalance());
    }

    @Test
    void shouldFindDepositSummary() {
        eventRepository.saveAll(fundraiserEventEntitiesToSave());
        fundraiserDatabaseAdapter = new FundraiserDatabaseAdapter(null, eventRepository);

        BigDecimal result = fundraiserDatabaseAdapter.findDepositSummary(1L);

        Assertions.assertEquals(0, BigDecimal.valueOf(11).compareTo(result));
    }

    @Test
    void shouldFindWithdrawalSummary() {
        eventRepository.saveAll(fundraiserEventEntitiesToSave());
        fundraiserDatabaseAdapter = new FundraiserDatabaseAdapter(null, eventRepository);

        BigDecimal result = fundraiserDatabaseAdapter.findWithdrawalSummary(1L);

        Assertions.assertEquals(0, BigDecimal.valueOf(8).compareTo(result));
    }

    @Test
    void shouldReturn0WhenSummaryNotFound() {
        fundraiserDatabaseAdapter = new FundraiserDatabaseAdapter(null, eventRepository);

        BigDecimal result = fundraiserDatabaseAdapter.findWithdrawalSummary(32L);

        Assertions.assertEquals(0, BigDecimal.valueOf(0).compareTo(result));
    }

    private Fundraiser fundraiserToSave() {
        return new Fundraiser(1L, 1L, "testname", BigDecimal.TEN);
    }

    private FundraiserEvent fundraiserEventToSave() {
        return new FundraiserEvent(UUID.randomUUID().toString(), 1L, 1L, BigDecimal.TEN, FundraiserEventType.DEPOSIT);
    }

    private List<FundraiserEntity> fundraiserEntitiesToSave() {
        return List.of(new FundraiserEntity(1L, 1L, "testname1", BigDecimal.TEN),
                new FundraiserEntity(2L, 2L, "testname2", BigDecimal.ONE),
                new FundraiserEntity(3L, 3L, "testname3", BigDecimal.ZERO),
                new FundraiserEntity(4L, 4L, "testname4", BigDecimal.valueOf(5)));
    }

    private List<FundraiserEventEntity> fundraiserEventEntitiesToSave() {
        return List.of(
                new FundraiserEventEntity(UUID.randomUUID().toString(), 1L, 1L, BigDecimal.ONE, FundraiserEventType.DEPOSIT),
                new FundraiserEventEntity(UUID.randomUUID().toString(), 1L, 1L, BigDecimal.TEN, FundraiserEventType.DEPOSIT),
                new FundraiserEventEntity(UUID.randomUUID().toString(), 1L, 1L, BigDecimal.valueOf(3), FundraiserEventType.WITHDRAWAL),
                new FundraiserEventEntity(UUID.randomUUID().toString(), 1L, 1L, BigDecimal.valueOf(5), FundraiserEventType.WITHDRAWAL)
                );
    }
}