package uo.sdi.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TCategories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "category")
    private Set<Task> tasks = new HashSet<Task>();

    Category() {

    }

    public Category(String name, User user) {
	this.name = name;

	Association.Organizes.link(user, this);
    }

    // =============================
    // ===== Logica de negocio =====
    // =============================

    public void rename(String name) {
	this.name = name;
    }

    public Category copiar() {
	return new Category(name + " - copy", user);
    }

    // =============================
    // ===== Getters y Setters =====
    // =============================

    public Long getId() {
	return id;
    }

    public String getName() {
	return name;
    }

    public User getUser() {
	return user;
    }

    void _setUser(User user) {
	this.user = user;
    }

    public Set<Task> getTasks() {
	return new HashSet<Task>(tasks);
    }

    Set<Task> _getTasks() {
	return tasks;
    }

    // =========================================
    // ====== hashCode, equals y toString ======
    // =========================================

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;

	result = prime * result + ((name == null) ? 0 : name.hashCode());
	result = prime * result + ((user == null) ? 0 : user.hashCode());

	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;

	if (obj == null)
	    return false;

	if (getClass() != obj.getClass())
	    return false;

	Category other = (Category) obj;

	if (id == null) {
	    if (other.id != null)
		return false;
	} else if (!id.equals(other.id))
	    return false;

	if (name == null) {
	    if (other.name != null)
		return false;
	} else if (!name.equals(other.name))
	    return false;

	if (user == null) {
	    if (other.user != null)
		return false;
	} else if (!user.equals(other.user))
	    return false;

	return true;
    }

    @Override
    public String toString() {
	return "Category [id=" + id + ", name=" + name + ", user=" + user + "]";
    }

}