package pl.dziadosz.fundsmicroservice.infrastructure.application.rest.fundraiser;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.model.Fundraiser;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.model.FundraiserEvent;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.model.FundraiserWithdrawal;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.port.in.FundraiserServicePort;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.port.in.FundraiserWithdrawalPort;

@RestController
@RequestMapping("fundraise")
@RequiredArgsConstructor
public class FundraiserController {
    private final FundraiserServicePort servicePort;
    private final FundraiserWithdrawalPort withdrawalPort;

    @GetMapping()
    List<Fundraiser> getServicePort() {
        return servicePort.getAll();
    }

    @PostMapping
    public FundraiserEvent withdraw(@RequestBody FundraiserWithdrawal fundraiserWithdrawal) {
        return withdrawalPort.withdraw(fundraiserWithdrawal);
    }
}
