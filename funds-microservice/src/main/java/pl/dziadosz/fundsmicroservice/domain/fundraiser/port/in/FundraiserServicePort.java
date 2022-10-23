package pl.dziadosz.fundsmicroservice.domain.fundraiser.port.in;

import java.util.List;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.model.Fundraiser;

public interface FundraiserServicePort {
    Fundraiser save(Fundraiser fundraiser);
    List<Fundraiser> getAll();
}
