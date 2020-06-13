package com.woniuxy.demo.shiro;

import com.woniuxy.demo.model.Tree;
import com.woniuxy.demo.service.ITreeService;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Shiro的配置类，相当于shiro.ini
 * @author cjw
 * @create 2020/05/23 18:11:31
 */
@Configuration
public class ShiroConfiguration {
    @Resource
    private ITreeService treeServiceImpl;
    //被spring容器所管理
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager){
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        //登录失败跳转的页面
        factoryBean.setLoginUrl("/login.jsp");
        //认证成功跳转的页面
        factoryBean.setSuccessUrl("/admin/index.jsp");
        //认证失败跳转的页面
        factoryBean.setUnauthorizedUrl("/unauthorized.jsp");
        //设置授权管理器
        factoryBean.setSecurityManager(securityManager);

        Map map = new LinkedHashMap<>();
        map.put("/*","anon");
        map.put("/login","anon");
        map.put("/static/**","anon");
        map.put("/login.jsp","anon");
        map.put("/css/**","anon");
        map.put("/js/**","anon");


        //设置访问权限(查询所有的权限)
        List<Tree> trees = treeServiceImpl.findAll();
        for (Tree tree : trees) {
            if (tree.getFile() != null){
                String file = tree.getFile(); //   /admin/role/index.html
                file = file.substring(0,file.lastIndexOf("/")+1);
                map.put(file+"**","perms["+file+":**]");
            }
            
        }
//        map.put("/admin/role/**","perms[/admin/role/:**]");
//        map.put("/admin/userinfo/**","perms[/admin/userinfo/:**]");

        map.put("/admin/**","authc");

        map.put("/**","authc");

        factoryBean.setFilterChainDefinitionMap(map);
        return factoryBean;
    }

    //被spring容器所管理,可以进行自动装配
    @Bean
    public SecurityManager securityManager(MyRealm myRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myRealm);
        return securityManager;
    }

    //拦截spring的所有行为交给shiro操作
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }
}
