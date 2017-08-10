package ch.trivadis.workshop.entity;

/**
 * Created by amo on 04.04.17.
 */

/**
 * @author Andy Moncsek
 */
public class Person {

  private String id;

  private String firstname;

  private String lastname;

  private String address;


  public Person() {
  }


  public Person(String id, String firstname, String lastname, String address) {
    this.id = id;
    this.firstname = firstname;
    this.lastname = lastname;
    this.address = address;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getFirstname() {
    return firstname;
  }

  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Person person = (Person) o;

    if (id != null ? !id.equals(person.id) : person.id != null) {
      return false;
    }
    if (firstname != null ? !firstname.equals(person.firstname) : person.firstname != null) {
      return false;
    }
    if (lastname != null ? !lastname.equals(person.lastname) : person.lastname != null) {
      return false;
    }
    return address != null ? address.equals(person.address) : person.address == null;
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
    result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
    result = 31 * result + (address != null ? address.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "Person{" +
        "id='" + id + '\'' +
        ", firstname='" + firstname + '\'' +
        ", lastname='" + lastname + '\'' +
        ", address='" + address + '\'' +
        '}';
  }
}