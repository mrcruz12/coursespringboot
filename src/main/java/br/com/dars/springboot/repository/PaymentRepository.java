package br.com.dars.springboot.repository;

import br.com.dars.springboot.domain.Payment;
import br.com.dars.springboot.domain.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
