package jgProjectMiniTwitter;

public class UserTotalVisitor implements Visitor {

	@Override
	public int visitUser(User user) {
		// TODO Auto-generated method stub
		int count = 0;

        if (user.getClass() == SingleUser.class) {
            count += visitSingleUser(user);
        } else if (user.getClass() == GroupUser.class) {
            count += visitGroupUser(user);
        }
        return count;
	}

	@Override
	public int visitSingleUser(User user) {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public int visitGroupUser(User user) {
		// TODO Auto-generated method stub
		int count = 0;

        for (User u : ((GroupUser) user).getGroupUsers().values()) {
            count += visitUser(u);
        }

        return count;
    }

	
	

}
