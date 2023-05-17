# About

Shows various ways how to activate maven profiles.
* presence of file
  * **prod** - causes that **prod** profile is activated
  * **test** - causes that **test** profile is activated
* system property - when set system property **env** to value test then profile **test-by-env-property** is activated
```bash
mvn -Denv=test clean
```

