package com.woniuxy.demo.shiro;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.woniuxy.demo.model.Role;
import com.woniuxy.demo.model.Tree;
import com.woniuxy.demo.model.Userinfo;
import com.woniuxy.demo.service.IUserinfoService;
import lombok.SneakyThrows;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.condition.RequestConditionHolder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author cjw
 * @create 2020/05/23 18:08:56
 */
@Component
public class MyRealm extends AuthorizingRealm {
    @Resource
    private IUserinfoService userinfoServiceImpl;
    //授权信息
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = principalCollection.getPrimaryPrincipal().toString();
       // System.out.println("授权过程中的"+username);
        //调用方法查找数据库的数据
        Userinfo userinfo = userinfoServiceImpl.findByUname(username);
        //根据用户名查询数据库得到该用户拥有的权限  并且设置
        List<Role> roles = userinfo.getRoles();


        //设置自己拥有的权限
        List<String> permissions = new ArrayList<String>();
        List<Tree> trees = userinfo.getTrees();
        for (Tree tree : trees) {
            if (tree.getFile() != null){
                String file = tree.getFile(); //   /admin/role/index.html
                file = file.substring(0,file.lastIndexOf("/")+1)+":**";
                permissions.add(file);
            }
        }


        //将权限信息封闭为AuthorizationInfo
        SimpleAuthorizationInfo sai = new SimpleAuthorizationInfo();
        for (Role role : roles) {
            sai.addRole(role.getRolename());
        }
        sai.addStringPermissions(permissions);
        return sai;
    }

    //验证信息
    @SneakyThrows
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获取登录用户名
        String uname = authenticationToken.getPrincipal().toString();

        //调用方法查找数据库的数据
        Userinfo userinfo = userinfoServiceImpl.findByUname(uname);
        if(userinfo==null){//证明该账户不存在
            throw new UnknownAccountException();
        }else{
            //登录成功
            //通过spring容器来获取session
            HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();
            List trees = userinfo.getTrees();
            Tree tree = new Tree();
            tree.setId(10000);
            tree.setName("注销");
            tree.setFile("/admin/loginout");
            trees.add(tree);
            //转换为json数据
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(trees);
            request.getSession().setAttribute("trees",json);
        }

        //如果存在则进行验证
        SimpleAuthenticationInfo sai = new SimpleAuthenticationInfo(userinfo.getUname(),userinfo.getUpass(),getName());
        return sai;
    }

}
