package crypto.middleware.service;



import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import crypto.middleware.helpers.CurrentDateTime;
import crypto.middleware.model.CryptoCurrency;
import crypto.middleware.model.CryptoCurrencyEnum;
import crypto.middleware.model.CryptoCurrencyList;
import crypto.middleware.repositories.CryptoRepository;
import crypto.middleware.service.integration.BinanceProxyService;

@Service
public class CryptoService {
	
    @Autowired
    CryptoRepository cryptoRepository;

	@Autowired
	BinanceProxyService binanceProxyService;

 
	public CryptoCurrencyList getAllCryptoCurrencyPrices() {
		CryptoCurrencyList list = new CryptoCurrencyList();
		for (CryptoCurrencyEnum crypto : CryptoCurrencyEnum.values()) {
			CryptoCurrency entity = binanceProxyService.getCryptoCurrencyValue(crypto.name());
			
			if (entity != null) {
				entity.setLastUpdateDateAndTime(CurrentDateTime.getNewDateString());
			}
			list.addCrypto(entity);

		}
		return list;
	}
    
    
	@Cacheable(value = "cryptoCache", key = "#symbol")
    public CryptoCurrency getCryptoCurrencyValue(
			String symbol) {
		CryptoCurrency entity = binanceProxyService.getCryptoCurrencyValue(symbol);

		SimpleDateFormat formatter = CurrentDateTime.getNewDateFormatter();
		if (entity != null) {
			entity.setLastUpdateDateAndTime(formatter.format(new Date()));
		}
		return entity;
	}
}
