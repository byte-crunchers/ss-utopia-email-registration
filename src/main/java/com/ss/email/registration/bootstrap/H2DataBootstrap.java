package com.ss.email.registration.bootstrap;


import com.ss.email.registration.entity.Accounts;
import com.ss.email.registration.entity.Loans;
import com.ss.email.registration.entity.Users;
import com.ss.email.registration.repository.AccountRepository;
import com.ss.email.registration.repository.LoanRepository;
import com.ss.email.registration.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDate;


@Component
@RequiredArgsConstructor
public class H2DataBootstrap implements CommandLineRunner {
    private final AccountRepository accountRepository;
    private final LoanRepository loanRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Override
    public void run(String... args) throws Exception {
        if(accountRepository.count()==0&loanRepository.count()==0){
            loadAccount();
            loadLoan();

        }
    }

    private void loadAccount(){

        var User1 = Users.builder()
                .id(1L)
                .username("dan")
                .first_name("Dan")
                .last_name("Wo")

                .email("smoothstack@email.com")
                .city("Miami Beach")
                .state("FL")
                .dob(LocalDate.now())
                .confirmed(false)
                .active(false)
                .approved(false)
                .zip(33139)
                .phone(7778889999L)
                .is_admin(false)
                .password(passwordEncoder.encode("dan123"))

                .build();
        userRepository.save(User1);


        var User2 = Users.builder()
                .id(2L)
                .username("admin")
                .first_name("Tim")
                .last_name("lo")
                .city("Miami Beach")
                .state("FL")
                .dob(LocalDate.now())

                .email("smoothstack@email.com")
                .is_admin(true)
                .confirmed(false)
                .confirmed(false)
                .active(false)
                .approved(false)
                .password(passwordEncoder.encode("admin123"))


                .build();

        userRepository.save(User2);

        var User3 = Users.builder()
                .id(3L)
                .username("manager")
                .first_name("Amy")
                .last_name("To")
                .city("Miami Beach")
                .state("FL")
                .dob(LocalDate.now())
                .email("smoothstack@email.com")
                .is_admin(false)
                .confirmed(false)
                .active(false)
                .approved(false)

                .password(passwordEncoder.encode("manager123"))

                .build();

        userRepository.save(User3);

        var Account1 = Accounts.builder()
                .id(1L)
                .account_type("Credit card")
                .due_date(Timestamp.valueOf("2022-07-12 21:30:00"))
                .balance(9000.21f)
                .confirmed(false)
                .active(false)
                .approved(false)
                .users(User1)
                .payment_due(233f)

                .build();
        accountRepository.save(Account1);

        var Account2 = Accounts.builder()
                .id(2L)
                .account_type("Investing")
                .due_date(Timestamp.valueOf("2022-07-12 21:30:00"))
                .balance(5000.21f)
                .confirmed(false)
                .active(false)
                .approved(false)
                .payment_due(233f)
                .users(User2)

                .build();
        accountRepository.save(Account2);
        var Account3 = Accounts.builder()
                .id(3L)
                .account_type("Saving")
                .due_date(Timestamp.valueOf("2022-07-12 21:30:00"))
                .balance(9000.21f)
                .confirmed(false)
                .active(false)
                .approved(false)
                .users(User1)
                .payment_due(233f)

                .build();
        accountRepository.save(Account3);
        var Account4 = Accounts.builder()
                .id(4L)
                .account_type("Checking")
                .due_date(Timestamp.valueOf("2022-07-12 21:30:00"))
                .balance(5000.21f)
                .confirmed(false)
                .active(false)
                .approved(false)
                .payment_due(233f)
                .users(User3)

                .build();
        accountRepository.save(Account4);
        var Account5 = Accounts.builder()
                .id(5L)
                .account_type("IRA")
                .due_date(Timestamp.valueOf("2022-07-12 21:30:00"))
                .balance(7000.21f)
                .confirmed(false)
                .active(false)
                .approved(false)
                .users(User3)
                .payment_due(233f)

                .build();
        accountRepository.save(Account5);

        var Account27 = Accounts.builder()
                .id(27L)
                .confirmed(false)
                .build();
        accountRepository.save(Account27);

        var Account28 = Accounts.builder()
                .id(28L)
                .confirmed(false)
                .build();
        accountRepository.save(Account28);

        var Account29 = Accounts.builder()
                .id(29L)
                .confirmed(false)
                .build();
        accountRepository.save(Account29);

        var Account30 = Accounts.builder()
                .id(30L)
                .confirmed(false)
                .build();
        accountRepository.save(Account30);

        var Account31 = Accounts.builder()
                .id(31L)
                .confirmed(false)
                .build();
        accountRepository.save(Account31);

        var Account32 = Accounts.builder()
                .id(32L)
                .confirmed(false)
                .build();
        accountRepository.save(Account32);

        var Account33 = Accounts.builder()
                .id(33L)
                .confirmed(false)
                .build();
        accountRepository.save(Account33);

        var Account34 = Accounts.builder()
                .id(34L)
                .confirmed(false)
                .build();
        accountRepository.save(Account34);

    }

    private void loadLoan(){

        var User13 = Users.builder()
                .id(4L)
                .username("GlassDoor")
                .first_name("Summers")
                .last_name("Mitt")
                .city("Miami Beach")
                .state("FL")
                .email("smoothstack@email.com")
                .is_admin(false)
                .confirmed(false)
                .active(false)
                .approved(false)
                .dob(LocalDate.now())

                .password(passwordEncoder.encode("dan123"))

                .build();
        userRepository.save(User13);

        var User14 = Users.builder()
                .id(5L)
                .username("PenWindows")
                .first_name("Ulla")
                .last_name("English")
                .city("Miami Beach")
                .state("FL")

                .email("smoothstack@email.com")
                .is_admin(false)
                .confirmed(false)
                .active(false)
                .approved(false)
                .dob(LocalDate.now())
                .password(passwordEncoder.encode("dan123"))

                .build();

        userRepository.save(User14);


        var User15 = Users.builder()
                .id(6L)
                .username("NoteBook")
                .first_name("Axel")
                .last_name("York")
                .city("Miami Beach")
                .state("FL")

                .email("smoothstack@email.com")
                .is_admin(false)
                .confirmed(false)
                .active(false)
                .approved(false)
                .dob(LocalDate.now())
                .password(passwordEncoder.encode("dan123"))

                .build();

        userRepository.save(User15);



        var Loan1 = Loans.builder()
                .id(1L)
                .loan_type("Personal Loan")
                .monthlyPayment(200f)
                .interestRate(0.04f)
                .balance(80000f)
                .dueDate(LocalDate.now())
                .payment_due(233f)
                .confirmed(false)
                .active(false)
                .approved(false)
                .users(User13)
                .dueDate(LocalDate.now())
                .build();
        loanRepository.save(Loan1);

        var Loan2 = Loans.builder()
                .id(2L)
                .interestRate(0.035f)
                .monthlyPayment(200f)
                .loan_type("Personal Loan")
                .dueDate(LocalDate.now())
                .balance(60000f)
                .payment_due(233f)
                .confirmed(false)
                .active(false)
                .approved(false)
                .users(User14)
                .dueDate(LocalDate.now())
                .build();
        loanRepository.save(Loan2);

        var Loan3 = Loans.builder()
                .id(3L)
                .interestRate(0.03f)
                .monthlyPayment(200f)
                .loan_type("Personal Loan")
                .balance(50000f)
                .dueDate(LocalDate.now())
                .payment_due(233f)
                .confirmed(false)
                .active(false)
                .approved(false)
                .users(User14)
                .dueDate(LocalDate.now())
                .build();
        loanRepository.save(Loan3);

        var Loan4 = Loans.builder()
                .id(4L)
                .interestRate(0.025f)
                .monthlyPayment(200f)
                .loan_type("Personal Loan")
                .balance(40000f)
                .dueDate(LocalDate.now())
                .payment_due(233f)
                .confirmed(false)
                .active(false)
                .approved(false)
                .users(User15)
                .dueDate(LocalDate.now())
                .build();
        loanRepository.save(Loan4);

        var Loan5 = Loans.builder()
                .id(5L)
                .interestRate(0.03f)
                .monthlyPayment(200f)
                .loan_type("Personal Loan")
                .balance(30000f)
                .payment_due(233f)
                .dueDate(LocalDate.now())
                .confirmed(false)
                .active(false)
                .approved(false)
                .users(User15)
                .dueDate(LocalDate.now())
                .build();
        loanRepository.save(Loan5);

        var Loan6 = Loans.builder()
                .id(6L)
                .confirmed(false)
                .build();
        loanRepository.save(Loan6);

        var Loan7 = Loans.builder()
                .id(7L)
                .confirmed(false)
                .build();
        loanRepository.save(Loan7);

        var Loan8 = Loans.builder()
                .id(8L)
                .confirmed(false)
                .build();
        loanRepository.save(Loan8);

        var Loan9 = Loans.builder()
                .id(9L)
                .confirmed(false)
                .build();
        loanRepository.save(Loan9);


        var Loan10 = Loans.builder()
                .id(10L)
                .confirmed(false)
                .build();
        loanRepository.save(Loan10);

        var Loan11 = Loans.builder()
                .id(11L)
                .confirmed(false)
                .build();
        loanRepository.save(Loan11);

        var Loan12 = Loans.builder()
                .id(12L)
                .confirmed(false)
                .build();
        loanRepository.save(Loan12);

        var Loan13 = Loans.builder()
                .id(13L)
                .confirmed(false)
                .build();

        loanRepository.save(Loan13);

    }


}
