package crypto.middleware.arch;


import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;

class DependecyTest {
	
    private JavaClasses baseClasses;
    
    
    @BeforeEach
    public void setup() {
        baseClasses = new ClassFileImporter()
                .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
                .importPackages("crypto.middleware");
    }



   @Test
    void exceptionsClassesShouldEndWithException(){
        classes().that().resideInAPackage("..exceptions..")
                .should().haveSimpleNameEndingWith("Exception").check(baseClasses);
    }

    @Test
    void persistanceClassesShouldEndWithRepo(){
        classes().that().resideInAPackage("..repositories..")
                .should().haveSimpleNameEndingWith("Repository").check(baseClasses);
    }


    @Test
    void webserviceClassesShouldEndWithController(){
        classes().that().resideInAPackage("..webservice..")
                .should().haveSimpleNameEndingWith("Controller").check(baseClasses);
    }

    @Test
    void layeredArchitectureShouldBeRespected(){
        layeredArchitecture()
                .consideringAllDependencies()
                .layer("Controller").definedBy("..webservice..")
                .layer("Service").definedBy("..service..")
                .layer("Persistence").definedBy("..persistence..")

                .whereLayer("Controller").mayNotBeAccessedByAnyLayer()
                .whereLayer("Service").mayOnlyBeAccessedByLayers("Controller")
                .whereLayer("Persistence").mayOnlyBeAccessedByLayers("Service");
    }



    @Test
    void controllerClassesShouldHaveSpringControllerAnnotation() {
        classes().that().resideInAPackage("..webservice..")
                .should().beAnnotatedWith("org.springframework.web.bind.annotation.RestController")
                .check(baseClasses);
    }


}