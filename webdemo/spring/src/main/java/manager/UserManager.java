package manager;

import domain.User;

public interface UserManager {
	void save(User user);
	User getUserById(Integer id);
}
