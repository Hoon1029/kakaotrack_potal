package ac.kr.jejunu.user;

public class DaoFactory {
    public UserDao getDao(){
        return new JejuUserDao();
    }
}
