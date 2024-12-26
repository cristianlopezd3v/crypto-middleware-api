package crypto.middleware.service.initializer;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class InitServiceInMemory {

    protected final Log logger = LogFactory.getLog(getClass());

    @Value("${spring.datasource.driverClassName:NONE}")
    private String className;

//    @Autowired
//    private AccountService userService;

    @PostConstruct
    public void initialize()  {
        if (className.equals("org.h2.Driver")) {
            logger.info("Init Data Using H2 DB");
            fireInitialData();
        }
    }

    private void fireInitialData() {
      //  User user = new User("Pepe", "Pepa", "email@gmail.com", "San Martin 185", "unaPassw123??", "1234567891234567891234", "12345678");
       
    
    }
}
