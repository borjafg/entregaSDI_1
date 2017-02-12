package uo.sdi.business.impl.admin.command;

import uo.sdi.business.exception.BusinessCheck;
import uo.sdi.business.exception.BusinessException;
import uo.sdi.business.impl.command.Command;
import uo.sdi.model.Category;
import uo.sdi.model.Task;
import uo.sdi.model.User;
import uo.sdi.persistence.UserFinder;
import uo.sdi.persistence.util.Jpa;

public class DeepDeleteUserCommand implements Command<Void> {

    private Long userId;

    public DeepDeleteUserCommand(Long id) {
	this.userId = id;
    }

    @Override
    public Void execute() throws BusinessException {
	User user = UserFinder.findById(userId);

	BusinessCheck.isNotNull(user, "El usuario no existe");

	borrarTodasTareas(user);
	borrarTodasCategorias(user);

	Jpa.getManager().remove(user);

	return null;
    }

    private void borrarTodasTareas(User user) throws BusinessException {
	// user.getTasks() devuelve una copia de las
	// categorias del usuario, así que se puede
	// recorrer el Set y eliminarlas a la vez
	//
	for (Task tarea : user.getTasks()) {
	    user.eliminarTarea(tarea);

	    Jpa.getManager().remove(tarea);
	}
    }

    private void borrarTodasCategorias(User user) throws BusinessException {
	for (Category categoria : user.getCategories()) {
	    user.eliminarCategoria(categoria);

	    Jpa.getManager().remove(categoria);
	}
    }

}