package com.store.book;

import com.store.book.model.Book;
import com.store.book.model.User;
import com.store.book.repository.BookRepository;
import com.store.book.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.xml.crypto.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@SpringBootApplication
@ComponentScan(basePackages = {"com.store.book.controllers","com.store.book.service",
        "com.store.book.repository"})
public class BookApplication  extends WebSecurityConfigurerAdapter {

    public static void main(String[] args) {
        SpringApplication.run(BookApplication.class, args);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests().antMatchers("/").permitAll().and()
                .authorizeRequests().antMatchers("/console/**").permitAll();
        httpSecurity.csrf().disable();
        httpSecurity.headers().frameOptions().disable();
    }

    @Bean
    public CommandLineRunner demoData(UserRepository repo, BookRepository bookRepository) {
        return args -> {
            var customer = "Customer";

            User user=User.builder()
                    .userName("Yusuf")
                    .email("bestas@gmail.com")
                    .firstName("yusuf")
                    .lastName("Bestas")
                    .password("1234")
                    .role("Customer")
                    .build();
            repo.save(user);

            Book book= Book.builder()
                    .bookName("Test Book")
                    .author("Test author")
                    .dateCreated(LocalDate.now())
                    .imageURL("Test.img.url")
                    .price(BigDecimal.TEN)
                    .code("SE23AD")
                    .stock(34)
                    .build();
            bookRepository.save(book);
        };
    }
}
