package gloncak.jozef.hibernate.generated.identifiers.entity;

import javax.persistence.*;

@Entity
@Table(name = "BOOK_WITH_TABLE")
public class BookWithTable {

    @Id
    @GeneratedValue(
            strategy = GenerationType.TABLE
            ,generator = "generator_from_table"
    )
    @TableGenerator(
            name = "generator_from_table"
            ,table = "IDENTIFIER" //name of DB table
            ,pkColumnName = "NAME_OF_TABLE" //name of column of table (IDENTIFIER) which refers to identifier name
            ,valueColumnName = "BOOK_WITH_TABLE_ID" //name of column of table (IDENTIFIER) which refers current value
            ,allocationSize = 1
            // of ID
    )
    private int id;

    @Column(name = "NAME")
    private String name;

    public BookWithTable() {
    }

    public BookWithTable(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Book{" + "id=" + id + ", name='" + name + '\'' + '}';
    }
}
