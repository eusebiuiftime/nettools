package dev.eusebiu.nettools.repository;

import dev.eusebiu.nettools.entity.EmailVerificationToken;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmailVerificationTokenRepository extends CrudRepository<EmailVerificationToken, Long> {

    @Query(value = """
            select evt.*
            from email_verification_token evt
            join "user" u on u.id = evt.user_id_fk
            where lower(u.email) = lower(:email)
            order by evt.id desc
            limit 1
            """, nativeQuery = true)
    Optional<EmailVerificationToken> findLatestByUserEmail(
           @Param("email") String email
    );
}
