package pl.dziadosz.withdrawalmicroservice;

import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WithdrawalController {

    @GetMapping("/fundraise")
    public FundraiseDto getFundraise() {
        return new FundraiseDto(1L, 2L, BigDecimal.TEN);
    }

    @PostMapping("/fundraise")
    public ResponseEntity<FundraiseDto> getPostFundraise(@RequestBody FundraiseDto fundraiseDto) {
//        return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(new FundraiseDto(1L,2L,BigDecimal.TEN));
    }

    record FundraiseDto(Long fundraiseId, Long accountId, BigDecimal amount) {

    }
}
