package crypto.middleware.repositories;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import crypto.middleware.model.CryptoCurrency;

@Configuration
@Repository
public interface CryptoRepository extends CrudRepository<CryptoCurrency,String> { }
