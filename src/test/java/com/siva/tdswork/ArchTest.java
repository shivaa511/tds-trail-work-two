package com.siva.tdswork;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.siva.tdswork");

        noClasses()
            .that()
            .resideInAnyPackage("com.siva.tdswork.service..")
            .or()
            .resideInAnyPackage("com.siva.tdswork.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.siva.tdswork.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
