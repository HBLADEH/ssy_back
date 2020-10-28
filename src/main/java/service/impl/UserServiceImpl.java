package service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import dao.UserDao;
import entity.User;
import org.springframework.stereotype.Service;
import service.UserService;

/**
 * (User)表服务实现类
 *
 * @author makejava
 * @since 2020-10-29 00:35:55
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {

}