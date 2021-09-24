package com.ss.email.registration.bootstrap;


import com.ss.email.registration.model.Accounts;
import com.ss.email.registration.model.Loans;
import com.ss.email.registration.repository.AccountRepository;
import com.ss.email.registration.repository.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;


@Component
@RequiredArgsConstructor
public class H2DataBootstrap implements CommandLineRunner {
    private final AccountRepository accountRepository;
    private final LoanRepository loanRepository;

    @Override
    public void run(String... args) throws Exception {
        if(accountRepository.count()==0&loanRepository.count()==0){
            loadAccount();
            loadLoan();

        }
    }

    private void loadAccount(){
        var Account1 = Accounts.builder()
                .id(1L)
                .account_type("Credit card")
                .due_date(Timestamp.valueOf("2022-07-12 21:30:00"))
                .balance(9000.21f)
                .is_active(false)
                .payment_due(Timestamp.valueOf("2022-08-12 21:30:00"))
                .user_id(1)
                .limit(1000000)
                .build();
        accountRepository.save(Account1);

        var Account2 = Accounts.builder()
                .id(2L)
                .account_type("Investing")
                .due_date(Timestamp.valueOf("2022-07-12 21:30:00"))
                .balance(5000.21f)
                .is_active(false)
                .payment_due(Timestamp.valueOf("2022-08-12 21:30:00"))
                .user_id(1)
                .limit(1000000)
                .build();
        accountRepository.save(Account2);
        var Account3 = Accounts.builder()
                .id(3L)
                .account_type("Saving")
                .due_date(Timestamp.valueOf("2022-07-12 21:30:00"))
                .balance(9000.21f)
                .is_active(false)
                .payment_due(Timestamp.valueOf("2022-08-12 21:30:00"))
                .user_id(2)
                .limit(1000000)
                .build();
        accountRepository.save(Account3);
        var Account4 = Accounts.builder()
                .id(4L)
                .account_type("Checking")
                .due_date(Timestamp.valueOf("2022-07-12 21:30:00"))
                .balance(5000.21f)
                .is_active(false)
                .payment_due(Timestamp.valueOf("2022-08-12 21:30:00"))
                .user_id(2)
                .limit(1000000)
                .build();
        accountRepository.save(Account4);
        var Account5 = Accounts.builder()
                .id(5L)
                .account_type("IRA")
                .due_date(Timestamp.valueOf("2022-07-12 21:30:00"))
                .balance(7000.21f)
                .is_active(false)
                .payment_due(Timestamp.valueOf("2022-08-12 21:30:00"))
                .user_id(3)
                .limit(1000000)
                .build();
        accountRepository.save(Account5);

    }

    private void loadLoan(){
        var Loan1 = Loans.builder()
                .id(1L)
                .interest_rate(0.04f)
                .balance(80000f)
                .payment_due(Timestamp.valueOf("2022-08-12 21:30:00"))
                .is_active(false)
                .user_id(1)
                .due_date(Timestamp.valueOf("2022-08-12 21:30:00"))
                .build();
        loanRepository.save(Loan1);

        var Loan2 = Loans.builder()
                .id(2L)
                .interest_rate(0.035f)
                .balance(60000f)
                .payment_due(Timestamp.valueOf("2022-08-12 21:30:00"))
                .is_active(false)
                .user_id(3)
                .due_date(Timestamp.valueOf("2022-08-12 21:30:00"))
                .build();
        loanRepository.save(Loan2);

        var Loan3 = Loans.builder()
                .id(3L)
                .interest_rate(0.03f)
                .balance(50000f)
                .payment_due(Timestamp.valueOf("2022-08-12 21:30:00"))
                .is_active(false)
                .user_id(2)
                .due_date(Timestamp.valueOf("2022-08-12 21:30:00"))
                .build();
        loanRepository.save(Loan3);

        var Loan4 = Loans.builder()
                .id(4L)
                .interest_rate(0.025f)
                .balance(40000f)
                .payment_due(Timestamp.valueOf("2022-08-12 21:30:00"))
                .is_active(false)
                .user_id(4)
                .due_date(Timestamp.valueOf("2022-08-12 21:30:00"))
                .build();
        loanRepository.save(Loan4);

        var Loan5 = Loans.builder()
                .id(5L)
                .interest_rate(0.03f)
                .balance(30000f)
                .payment_due(Timestamp.valueOf("2022-08-12 21:30:00"))
                .is_active(false)
                .user_id(5)
                .due_date(Timestamp.valueOf("2022-08-12 21:30:00"))
                .build();
        loanRepository.save(Loan5);

    }


}
