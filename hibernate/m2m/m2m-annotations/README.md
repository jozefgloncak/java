[&#8593;](../README.md)

# About
Demonstrate usage of annotation _@ManyToMany_. Example is build on model where we have relation M:N between entity 
Employer and Person. It means that employer can have several persons (employees) and vice versa.

Owning site - where isn't annotation attribute _mappedBy_

# SQL
In example there are following couples of DB tables:
- first
  - employeer_unidirect_A
  - person_unidirect_A
- second
  - employer_unidirect_B
  - person_unidirect_B
- third
  - employeer_bidirect
  - person_bidirect
- fourth
  - employer_bidirect
  - person_bidirect
  - employer_person_bidirect

Tables are created in memory (H2 DB). See persistence.xml.

# Source code
Is based on 3 examples in __ManyToManyTest__
- _unidirectManyToManyATest_ - A - uniderectional - owning site (collection of persons) is employer. Deletion is 
  probably possible only on owning site (it is possible to delete employer with all of its persons but not vice versa).
- _unidirectManyToManyBTest_ - B - unidirectional - owning site (collection of employers) is person. Deletion is 
  probably possible only on owning site (it is possible to delete person with all of its employers but not vice versa).
- _bidirectManyToManyTest_ - bidirectional - owning site is employer. Following deletion are possible:
  - delete _employer_ and all its person (because deletion on owning site) - delete from employer_bidirect and from 
    joining table [1]
  - delete _person_ from collection in _employer_ via __removePerson__ method [2]
  - delete _employer_ from collection in _person_ via __removeEmployer__ method [3]
  - delete _person_ after all references are deleted via __removeAllEmployers__ [4]
- _bidirectManyToManyWithJoinTableTest_ - bidirectional with joining table as entity. Joining table contains 
  reference to entities for _person_ and _employer_ where owning site is for both tables in joining entity. 
  Therefore direct deletion with references [1] isn't possible. It is possible to use [2], [3], [4].